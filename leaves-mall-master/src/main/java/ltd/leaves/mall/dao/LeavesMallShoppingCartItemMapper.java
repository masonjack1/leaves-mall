package ltd.leaves.mall.dao;

import ltd.leaves.mall.entity.LeavesMallShoppingCartItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public interface LeavesMallShoppingCartItemMapper {
    int deleteByPrimaryKey(Long cartItemId);

    int insert(LeavesMallShoppingCartItem record);

    int insertSelective(LeavesMallShoppingCartItem record);

    LeavesMallShoppingCartItem selectByPrimaryKey(Long cartItemId);

    LeavesMallShoppingCartItem selectByUserIdAndGoodsId(@Param("leavesMallUserId") Long newBeeMallUserId, @Param("goodsId") Long goodsId);

    List<LeavesMallShoppingCartItem> selectByUserId(@Param("leavesMallUserId") Long newBeeMallUserId, @Param("number") int number);

    int selectCountByUserId(Long newBeeMallUserId);

    int updateByPrimaryKeySelective(LeavesMallShoppingCartItem record);

    int updateByPrimaryKey(LeavesMallShoppingCartItem record);

    int deleteBatch(List<Long> ids);
}