package ltd.leaves.mall.dao;

import ltd.leaves.mall.entity.LeavesMallOrder;
import ltd.leaves.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LeavesMallOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(LeavesMallOrder record);

    int insertSelective(LeavesMallOrder record);

    LeavesMallOrder selectByPrimaryKey(Long orderId);

    LeavesMallOrder selectByOrderNo(String orderNo);

    int updateByPrimaryKeySelective(LeavesMallOrder record);

    int updateByPrimaryKey(LeavesMallOrder record);

    List<LeavesMallOrder> findNewBeeMallOrderList(PageQueryUtil pageUtil);

    int getTotalNewBeeMallOrders(PageQueryUtil pageUtil);

    List<LeavesMallOrder> selectByPrimaryKeys(@Param("orderIds") List<Long> orderIds);

    int checkOut(@Param("orderIds") List<Long> orderIds);

    int closeOrder(@Param("orderIds") List<Long> orderIds, @Param("orderStatus") int orderStatus);

    int checkDone(@Param("orderIds") List<Long> asList);
}