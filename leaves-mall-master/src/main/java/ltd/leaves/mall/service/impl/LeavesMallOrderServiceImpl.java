package ltd.leaves.mall.service.impl;

import ltd.leaves.mall.common.*;
import ltd.leaves.mall.controller.vo.*;
import ltd.leaves.mall.dao.LeavesMallGoodsMapper;
import ltd.leaves.mall.dao.LeavesMallOrderItemMapper;
import ltd.leaves.mall.dao.LeavesMallOrderMapper;
import ltd.leaves.mall.dao.LeavesMallShoppingCartItemMapper;
import ltd.leaves.mall.entity.LeavesMallGoods;
import ltd.leaves.mall.entity.LeavesMallOrder;
import ltd.leaves.mall.entity.LeavesMallOrderItem;
import ltd.leaves.mall.entity.StockNumDTO;
import ltd.leaves.mall.service.LeavesMallOrderService;
import ltd.leaves.mall.util.BeanUtil;
import ltd.leaves.mall.util.NumberUtil;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class LeavesMallOrderServiceImpl implements LeavesMallOrderService {

    @Autowired
    private LeavesMallOrderMapper leavesMallOrderMapper;
    @Autowired
    private LeavesMallOrderItemMapper leavesMallOrderItemMapper;
    @Autowired
    private LeavesMallShoppingCartItemMapper leavesMallShoppingCartItemMapper;
    @Autowired
    private LeavesMallGoodsMapper newBeeMallGoodsMapper;

    @Override
    public PageResult getNewLeavesOrdersPage(PageQueryUtil pageUtil) {
        List<LeavesMallOrder> leavesMallOrders = leavesMallOrderMapper.findNewBeeMallOrderList(pageUtil);
        int total = leavesMallOrderMapper.getTotalNewBeeMallOrders(pageUtil);
        PageResult pageResult = new PageResult(leavesMallOrders, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    @Transactional
    public String updateOrderInfo(LeavesMallOrder leavesMallOrder) {
        LeavesMallOrder temp = leavesMallOrderMapper.selectByPrimaryKey(leavesMallOrder.getOrderId());
        //Not empty and OrderStatus >=0 and the status is out of the library before you can modify some information
        if (temp != null && temp.getOrderStatus() >= 0 && temp.getOrderStatus() < 3) {
            temp.setTotalPrice(leavesMallOrder.getTotalPrice());
            temp.setUserAddress(leavesMallOrder.getUserAddress());
            temp.setUpdateTime(new Date());
            if (leavesMallOrderMapper.updateByPrimaryKeySelective(temp) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            }
            return ServiceResultEnum.DB_ERROR.getResult();
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String checkDone(Long[] ids) {
        //Query all orders to determine status change status and update time
        List<LeavesMallOrder> orders = leavesMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (LeavesMallOrder leavesMallOrder : orders) {
                if (leavesMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += leavesMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (leavesMallOrder.getOrderStatus() != 1) {
                    errorOrderNos += leavesMallOrder.getOrderNo() + " ";
                }
            }
            if (StringUtils.isEmpty(errorOrderNos)) {
                //The order status is normal, and the order status and update time can be modified by performing the distribution completion operation
                if (leavesMallOrderMapper.checkDone(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                //The order cannot be outbound at this time
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "The status of the order is not successfully paid and the outbound operation cannot be performed";
                } else {
                    return "You selected too many orders with status that were not paid successfully to perform the allocation completion operation";
                }
            }
        }
        //Error message returned when data was not queried
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String checkOut(Long[] ids) {
        //Query all orders to determine status change status and update time
        List<LeavesMallOrder> orders = leavesMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (LeavesMallOrder leavesMallOrder : orders) {
                if (leavesMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += leavesMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (leavesMallOrder.getOrderStatus() != 1 && leavesMallOrder.getOrderStatus() != 2) {
                    errorOrderNos += leavesMallOrder.getOrderNo() + " ";
                }
            }
            if (StringUtils.isEmpty(errorOrderNos)) {
                //The order status is normal and can be carried out warehouse operation to modify the order status and update time
                if (leavesMallOrderMapper.checkOut(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                //The order cannot be outbound at this time
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "The status of the order is not that the payment is successful or the distribution is completed and the outbound operation cannot be performed";
                } else {
                    return "You selected too many orders with status other than payment success or distribution completion. Outbound operations cannot be performed";
                }
            }
        }
        //Error message returned when data was not queried
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String closeOrder(Long[] ids) {
        //Query all orders to determine status change status and update time
        List<LeavesMallOrder> orders = leavesMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (LeavesMallOrder leavesMallOrder : orders) {
                // IsDeleted =1 must be for closed orders
                if (leavesMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += leavesMallOrder.getOrderNo() + " ";
                    continue;
                }
                //The order was closed or completed and could not be closed
                if (leavesMallOrder.getOrderStatus() == 4 || leavesMallOrder.getOrderStatus() < 0) {
                    errorOrderNos += leavesMallOrder.getOrderNo() + " ";
                }
            }
            if (StringUtils.isEmpty(errorOrderNos)) {
                //The order status is normal and a close operation can be performed to modify the order status and update time
                if (leavesMallOrderMapper.closeOrder(Arrays.asList(ids), LeavesMallOrderStatusEnum.ORDER_CLOSED_BY_JUDGE.getOrderStatus()) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                //The order cannot be closed at this time
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "The order cannot perform a close operation";
                } else {
                    return "The order you selected cannot perform the close operation";
                }
            }
        }
        //Error message returned when data was not queried
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String saveOrder(LeavesMallUserVO user, List<LeavesMallShoppingCartItemVO> myShoppingCartItems) {
        List<Long> itemIdList = myShoppingCartItems.stream().map(LeavesMallShoppingCartItemVO::getCartItemId).collect(Collectors.toList());
        List<Long> goodsIds = myShoppingCartItems.stream().map(LeavesMallShoppingCartItemVO::getGoodsId).collect(Collectors.toList());
        List<LeavesMallGoods> leavesMallGoods = newBeeMallGoodsMapper.selectByPrimaryKeys(goodsIds);
        //Check for items that have been removed from the shelves
        List<LeavesMallGoods> goodsListNotSelling = leavesMallGoods.stream()
                .filter(newBeeMallGoodsTemp -> newBeeMallGoodsTemp.getGoodsSellStatus() != Constants.SELL_STATUS_UP)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(goodsListNotSelling)) {
            //GoodsListnotselling to people who are not empty means there are items being pulled
            LeavesMallException.fail(goodsListNotSelling.get(0).getGoodsName() + "No longer available. Order cannot be generated");
        }
        Map<Long, LeavesMallGoods> newBeeMallGoodsMap = leavesMallGoods.stream().collect(Collectors.toMap(LeavesMallGoods::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
        //Determine commodity inventory
        for (LeavesMallShoppingCartItemVO shoppingCartItemVO : myShoppingCartItems) {
            //The associated item data in the shopping cart is not present in the found item, and the error reminder is returned directly
            if (!newBeeMallGoodsMap.containsKey(shoppingCartItemVO.getGoodsId())) {
                LeavesMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
            }
            //If the quantity is larger than the inventory, the error reminder will be returned directly
            if (shoppingCartItemVO.getGoodsCount() > newBeeMallGoodsMap.get(shoppingCartItemVO.getGoodsId()).getStockNum()) {
                LeavesMallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
            }
        }
        //Delete shopping items
        if (!CollectionUtils.isEmpty(itemIdList) && !CollectionUtils.isEmpty(goodsIds) && !CollectionUtils.isEmpty(leavesMallGoods)) {
            if (leavesMallShoppingCartItemMapper.deleteBatch(itemIdList) > 0) {
                List<StockNumDTO> stockNumDTOS = BeanUtil.copyList(myShoppingCartItems, StockNumDTO.class);
                int updateStockNumResult = newBeeMallGoodsMapper.updateStockNum(stockNumDTOS);
                if (updateStockNumResult < 1) {
                    LeavesMallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
                }
                //Generate order number
                String orderNo = NumberUtil.genOrderNo();
                int priceTotal = 0;
                //Save the order
                LeavesMallOrder leavesMallOrder = new LeavesMallOrder();
                leavesMallOrder.setOrderNo(orderNo);
                leavesMallOrder.setUserId(user.getUserId());
                leavesMallOrder.setUserAddress(user.getAddress());
                //The total price
                for (LeavesMallShoppingCartItemVO leavesMallShoppingCartItemVO : myShoppingCartItems) {
                    priceTotal += leavesMallShoppingCartItemVO.getGoodsCount() * leavesMallShoppingCartItemVO.getSellingPrice();
                }
                if (priceTotal < 1) {
                    LeavesMallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getResult());
                }
                leavesMallOrder.setTotalPrice(priceTotal);
                //todo The body field of the order, which is used to generate the description information of the payment order, is not connected to the third-party payment interface, so this field is temporarily set to an empty string
                String extraInfo = "";
                leavesMallOrder.setExtraInfo(extraInfo);
                //Create order items and save the order item record
                if (leavesMallOrderMapper.insertSelective(leavesMallOrder) > 0) {
                    //Take snapshots of all order items and save them to the database
                    List<LeavesMallOrderItem> leavesMallOrderItems = new ArrayList<>();
                    for (LeavesMallShoppingCartItemVO leavesMallShoppingCartItemVO : myShoppingCartItems) {
                        LeavesMallOrderItem leavesMallOrderItem = new LeavesMallOrderItem();
                        //Using BeanUtil tools copies of newBeeMallShoppingCartItemVO attribute to newBeeMallOrderItem object
                        BeanUtil.copyProperties(leavesMallShoppingCartItemVO, leavesMallOrderItem);
                        //The newBeeMallOrderMapper file uses UseGeneratedKeys in the insert() method so the orderID can be retrieved
                        leavesMallOrderItem.setOrderId(leavesMallOrder.getOrderId());
                        leavesMallOrderItems.add(leavesMallOrderItem);
                    }
                    //Save to a database
                    if (leavesMallOrderItemMapper.insertBatch(leavesMallOrderItems) > 0) {
                        //When all operations are successful, the order number is returned so that the Controller method can jump to the order details
                        return orderNo;
                    }
                    LeavesMallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getResult());
                }
                LeavesMallException.fail(ServiceResultEnum.DB_ERROR.getResult());
            }
            LeavesMallException.fail(ServiceResultEnum.DB_ERROR.getResult());
        }
        LeavesMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
        return ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult();
    }

    @Override
    public LeavesMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId) {
        LeavesMallOrder leavesMallOrder = leavesMallOrderMapper.selectByOrderNo(orderNo);
        if (leavesMallOrder != null) {
            //todo Verify that the order was placed by the current userId, otherwise an error will be reported
            List<LeavesMallOrderItem> orderItems = leavesMallOrderItemMapper.selectByOrderId(leavesMallOrder.getOrderId());
            //Gets the order item data
            if (!CollectionUtils.isEmpty(orderItems)) {
                List<LeavesMallOrderItemVO> leavesMallOrderItemVOS = BeanUtil.copyList(orderItems, LeavesMallOrderItemVO.class);
                LeavesMallOrderDetailVO leavesMallOrderDetailVO = new LeavesMallOrderDetailVO();
                BeanUtil.copyProperties(leavesMallOrder, leavesMallOrderDetailVO);
                leavesMallOrderDetailVO.setOrderStatusString(LeavesMallOrderStatusEnum.getNewBeeMallOrderStatusEnumByStatus(leavesMallOrderDetailVO.getOrderStatus()).getName());
                leavesMallOrderDetailVO.setPayTypeString(PayTypeEnum.getPayTypeEnumByType(leavesMallOrderDetailVO.getPayType()).getName());
                leavesMallOrderDetailVO.setNewBeeMallOrderItemVOS(leavesMallOrderItemVOS);
                return leavesMallOrderDetailVO;
            }
        }
        return null;
    }

    @Override
    public LeavesMallOrder getNewBeeMallOrderByOrderNo(String orderNo) {
        return leavesMallOrderMapper.selectByOrderNo(orderNo);
    }

    @Override
    public PageResult getMyOrders(PageQueryUtil pageUtil) {
        int total = leavesMallOrderMapper.getTotalNewBeeMallOrders(pageUtil);
        List<LeavesMallOrder> leavesMallOrders = leavesMallOrderMapper.findNewBeeMallOrderList(pageUtil);
        List<LeavesMallOrderListVO> orderListVOS = new ArrayList<>();
        if (total > 0) {
            //Data transformation converts entity classes to VO
            orderListVOS = BeanUtil.copyList(leavesMallOrders, LeavesMallOrderListVO.class);
            //Set the order status display value in Chinese
            for (LeavesMallOrderListVO leavesMallOrderListVO : orderListVOS) {
                leavesMallOrderListVO.setOrderStatusString(LeavesMallOrderStatusEnum.getNewBeeMallOrderStatusEnumByStatus(leavesMallOrderListVO.getOrderStatus()).getName());
            }
            List<Long> orderIds = leavesMallOrders.stream().map(LeavesMallOrder::getOrderId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(orderIds)) {
                List<LeavesMallOrderItem> orderItems = leavesMallOrderItemMapper.selectByOrderIds(orderIds);
                Map<Long, List<LeavesMallOrderItem>> itemByOrderIdMap = orderItems.stream().collect(groupingBy(LeavesMallOrderItem::getOrderId));
                for (LeavesMallOrderListVO leavesMallOrderListVO : orderListVOS) {
                    //Encapsulates order item data for each order list object
                    if (itemByOrderIdMap.containsKey(leavesMallOrderListVO.getOrderId())) {
                        List<LeavesMallOrderItem> orderItemListTemp = itemByOrderIdMap.get(leavesMallOrderListVO.getOrderId());
                        //Converts the list of NewBeeAllOrderItem objects to the list of NewBeeAllOrderItemVo objects
                        List<LeavesMallOrderItemVO> leavesMallOrderItemVOS = BeanUtil.copyList(orderItemListTemp, LeavesMallOrderItemVO.class);
                        leavesMallOrderListVO.setNewBeeMallOrderItemVOS(leavesMallOrderItemVOS);
                    }
                }
            }
        }
        PageResult pageResult = new PageResult(orderListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String cancelOrder(String orderNo, Long userId) {
        LeavesMallOrder leavesMallOrder = leavesMallOrderMapper.selectByOrderNo(orderNo);
        if (leavesMallOrder != null) {
            //todo Verify that the order was placed by the current userId, otherwise an error will be reported
            //todo Order status judgment
            if (leavesMallOrderMapper.closeOrder(Collections.singletonList(leavesMallOrder.getOrderId()), LeavesMallOrderStatusEnum.ORDER_CLOSED_BY_MALLUSER.getOrderStatus()) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            } else {
                return ServiceResultEnum.DB_ERROR.getResult();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getResult();
    }

    @Override
    public String finishOrder(String orderNo, Long userId) {
        LeavesMallOrder leavesMallOrder = leavesMallOrderMapper.selectByOrderNo(orderNo);
        if (leavesMallOrder != null) {
            //todo Verify that the order was placed by the current userId, otherwise an error will be reported
            //todo Order status judgment
            leavesMallOrder.setOrderStatus((byte) LeavesMallOrderStatusEnum.ORDER_SUCCESS.getOrderStatus());
            leavesMallOrder.setUpdateTime(new Date());
            if (leavesMallOrderMapper.updateByPrimaryKeySelective(leavesMallOrder) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            } else {
                return ServiceResultEnum.DB_ERROR.getResult();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getResult();
    }

    @Override
    public String paySuccess(String orderNo, int payType) {
        LeavesMallOrder leavesMallOrder = leavesMallOrderMapper.selectByOrderNo(orderNo);
        if (leavesMallOrder != null) {
            //todo Order status is determined not to be paid in the state of no modification operation
            leavesMallOrder.setOrderStatus((byte) LeavesMallOrderStatusEnum.OREDER_PAID.getOrderStatus());
            leavesMallOrder.setPayType((byte) payType);
            leavesMallOrder.setPayStatus((byte) PayStatusEnum.PAY_SUCCESS.getPayStatus());
            leavesMallOrder.setPayTime(new Date());
            leavesMallOrder.setUpdateTime(new Date());
            if (leavesMallOrderMapper.updateByPrimaryKeySelective(leavesMallOrder) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            } else {
                return ServiceResultEnum.DB_ERROR.getResult();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getResult();
    }

    @Override
    public List<LeavesMallOrderItemVO> getOrderItems(Long id) {
        LeavesMallOrder leavesMallOrder = leavesMallOrderMapper.selectByPrimaryKey(id);
        if (leavesMallOrder != null) {
            List<LeavesMallOrderItem> orderItems = leavesMallOrderItemMapper.selectByOrderId(leavesMallOrder.getOrderId());
            //Gets the order item data
            if (!CollectionUtils.isEmpty(orderItems)) {
                List<LeavesMallOrderItemVO> leavesMallOrderItemVOS = BeanUtil.copyList(orderItems, LeavesMallOrderItemVO.class);
                return leavesMallOrderItemVOS;
            }
        }
        return null;
    }
}