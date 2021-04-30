package ltd.leaves.mall.service;

import ltd.leaves.mall.controller.vo.LeavesMallOrderItemVO;
import ltd.leaves.mall.entity.LeavesMallOrder;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;
import ltd.leaves.mall.controller.vo.LeavesMallOrderDetailVO;
import ltd.leaves.mall.controller.vo.LeavesMallShoppingCartItemVO;
import ltd.leaves.mall.controller.vo.LeavesMallUserVO;

import java.util.List;

public interface LeavesMallOrderService {
    /**
     * The background page
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewLeavesOrdersPage(PageQueryUtil pageUtil);

    /**
     * Modification of order information
     *
     * @param leavesMallOrder
     * @return
     */
    String updateOrderInfo(LeavesMallOrder leavesMallOrder);

    /**
     * checkDone
     *
     * @param ids
     * @return
     */
    String checkDone(Long[] ids);

    /**
     * checkOut
     *
     * @param ids
     * @return
     */
    String checkOut(Long[] ids);

    /**
     * close order
     *
     * @param ids
     * @return
     */
    String closeOrder(Long[] ids);

    /**
     * save order
     *
     * @param user
     * @param myShoppingCartItems
     * @return
     */
    String saveOrder(LeavesMallUserVO user, List<LeavesMallShoppingCartItemVO> myShoppingCartItems);

    /**
     * Get order details
     *
     * @param orderNo
     * @param userId
     * @return
     */
    LeavesMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId);

    /**
     * Get order details
     *
     * @param orderNo
     * @return
     */
    LeavesMallOrder getNewBeeMallOrderByOrderNo(String orderNo);

    /**
     * My order list
     *
     * @param pageUtil
     * @return
     */
    PageResult getMyOrders(PageQueryUtil pageUtil);

    /**
     * Manual cancellation of order
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String cancelOrder(String orderNo, Long userId);

    /**
     * Confirm the goods
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String finishOrder(String orderNo, Long userId);

    String paySuccess(String orderNo, int payType);

    List<LeavesMallOrderItemVO> getOrderItems(Long id);
}
