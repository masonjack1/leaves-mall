package ltd.leaves.mall.dao;

import ltd.leaves.mall.entity.LeavesMallOrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LeavesMallOrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(LeavesMallOrderItem record);

    int insertSelective(LeavesMallOrderItem record);

    LeavesMallOrderItem selectByPrimaryKey(Long orderItemId);

    /**
     * Gets a list of order items based on the order ID
     *
     * @param orderId
     * @return
     */
    List<LeavesMallOrderItem> selectByOrderId(Long orderId);

    /**
     * Gets a list of order items according to the order IDS
     *
     * @param orderIds
     * @return
     */
    List<LeavesMallOrderItem> selectByOrderIds(@Param("orderIds") List<Long> orderIds);

    /**
     * Batch INSERT order item data
     *
     * @param orderItems
     * @return
     */
    int insertBatch(@Param("orderItems") List<LeavesMallOrderItem> orderItems);

    int updateByPrimaryKeySelective(LeavesMallOrderItem record);

    int updateByPrimaryKey(LeavesMallOrderItem record);
}