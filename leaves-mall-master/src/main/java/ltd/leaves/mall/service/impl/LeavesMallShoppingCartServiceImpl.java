package ltd.leaves.mall.service.impl;

import ltd.leaves.mall.common.Constants;
import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.dao.LeavesMallGoodsMapper;
import ltd.leaves.mall.dao.LeavesMallShoppingCartItemMapper;
import ltd.leaves.mall.entity.LeavesMallGoods;
import ltd.leaves.mall.entity.LeavesMallShoppingCartItem;
import ltd.leaves.mall.service.LeavesMallShoppingCartService;
import ltd.leaves.mall.util.BeanUtil;
import ltd.leaves.mall.controller.vo.LeavesMallShoppingCartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LeavesMallShoppingCartServiceImpl implements LeavesMallShoppingCartService {

    @Autowired
    private LeavesMallShoppingCartItemMapper leavesMallShoppingCartItemMapper;

    @Autowired
    private LeavesMallGoodsMapper leavesMallGoodsMapper;

    //todo Modify the number of items purchased in the session

    @Override
    public String saveLeavesMallCartItem(LeavesMallShoppingCartItem leavesMallShoppingCartItem) {
        LeavesMallShoppingCartItem temp = leavesMallShoppingCartItemMapper.selectByUserIdAndGoodsId(leavesMallShoppingCartItem.getUserId(), leavesMallShoppingCartItem.getGoodsId());
        if (temp != null) {
            //If it already exists, modify the record
            //todo count = tempCount + 1
            temp.setGoodsCount(leavesMallShoppingCartItem.getGoodsCount());
            return updateLeavesMallCartItem(temp);
        }
        LeavesMallGoods leavesMallGoods = leavesMallGoodsMapper.selectByPrimaryKey(leavesMallShoppingCartItem.getGoodsId());
        //Goods is empty
        if (leavesMallGoods == null) {
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }
        int totalItem = leavesMallShoppingCartItemMapper.selectCountByUserId(leavesMallShoppingCartItem.getUserId()) + 1;
        //Exceeds the maximum quantity of a single item
        if (leavesMallShoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //Exceeding the maximum quantity
        if (totalItem > Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR.getResult();
        }
        //Save the record
        if (leavesMallShoppingCartItemMapper.insertSelective(leavesMallShoppingCartItem) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateLeavesMallCartItem(LeavesMallShoppingCartItem leavesMallShoppingCartItem) {
        LeavesMallShoppingCartItem leavesMallShoppingCartItemUpdate = leavesMallShoppingCartItemMapper.selectByPrimaryKey(leavesMallShoppingCartItem.getCartItemId());
        if (leavesMallShoppingCartItemUpdate == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        //Exceeds the maximum quantity of a single item
        if (leavesMallShoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //todo The same quantity will not be modified
        //todo The userId is different and cannot be changed
        leavesMallShoppingCartItemUpdate.setGoodsCount(leavesMallShoppingCartItem.getGoodsCount());
        leavesMallShoppingCartItemUpdate.setUpdateTime(new Date());
        //Modify the record
        if (leavesMallShoppingCartItemMapper.updateByPrimaryKeySelective(leavesMallShoppingCartItemUpdate) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }



    @Override
    public LeavesMallShoppingCartItem getLeavesMallCartItemById(Long leavesMallShoppingCartItemId) {
        return leavesMallShoppingCartItemMapper.selectByPrimaryKey(leavesMallShoppingCartItemId);
    }

    @Override
    public Boolean deleteById(Long leavesMallShoppingCartItemId) {
        //todo Can not delete different userId
        return leavesMallShoppingCartItemMapper.deleteByPrimaryKey(leavesMallShoppingCartItemId) > 0;
    }

    @Override
    public List<LeavesMallShoppingCartItemVO> getMyShoppingCartItems(Long leavesMallUserId) {
        List<LeavesMallShoppingCartItemVO> leavesMallShoppingCartItemVOS = new ArrayList<>();
        List<LeavesMallShoppingCartItem> leavesMallShoppingCartItems = leavesMallShoppingCartItemMapper.selectByUserId(leavesMallUserId, Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);
        if (!CollectionUtils.isEmpty(leavesMallShoppingCartItems)) {
            //Inquire commodity information and do data conversion
            List<Long> leavesMallGoodsIds = leavesMallShoppingCartItems.stream().map(LeavesMallShoppingCartItem::getGoodsId).collect(Collectors.toList());
            List<LeavesMallGoods> leavesMallGoods = leavesMallGoodsMapper.selectByPrimaryKeys(leavesMallGoodsIds);
            Map<Long, LeavesMallGoods> leavesMallGoodsMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(leavesMallGoods)) {
                leavesMallGoodsMap = leavesMallGoods.stream().collect(Collectors.toMap(LeavesMallGoods::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
            }
            for (LeavesMallShoppingCartItem leavesMallShoppingCartItem : leavesMallShoppingCartItems) {
                LeavesMallShoppingCartItemVO leavesMallShoppingCartItemVO = new LeavesMallShoppingCartItemVO();
                BeanUtil.copyProperties(leavesMallShoppingCartItem, leavesMallShoppingCartItemVO);
                if (leavesMallGoodsMap.containsKey(leavesMallShoppingCartItem.getGoodsId())) {
                    LeavesMallGoods leavesMallGoodsTemp = leavesMallGoodsMap.get(leavesMallShoppingCartItem.getGoodsId());
                    leavesMallShoppingCartItemVO.setGoodsCoverImg(leavesMallGoodsTemp.getGoodsCoverImg());
                    String goodsName = leavesMallGoodsTemp.getGoodsName();
                    // An issue with a string that is too long resulting in text overrun
                    if (goodsName.length() > 28) {
                        goodsName = goodsName.substring(0, 28) + "...";
                    }
                    leavesMallShoppingCartItemVO.setGoodsName(goodsName);
                    leavesMallShoppingCartItemVO.setSellingPrice(leavesMallGoodsTemp.getSellingPrice());
                    leavesMallShoppingCartItemVOS.add(leavesMallShoppingCartItemVO);
                }
            }
        }
        return leavesMallShoppingCartItemVOS;
    }
}
