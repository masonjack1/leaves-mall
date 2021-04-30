package ltd.leaves.mall.dao;

import ltd.leaves.mall.entity.LeavesMallGoods;
import ltd.leaves.mall.entity.StockNumDTO;
import ltd.leaves.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LeavesMallGoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(LeavesMallGoods record);

    int insertSelective(LeavesMallGoods record);

    LeavesMallGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(LeavesMallGoods record);

    int updateByPrimaryKeyWithBLOBs(LeavesMallGoods record);

    int updateByPrimaryKey(LeavesMallGoods record);

    List<LeavesMallGoods> findLeavesMallGoodsList(PageQueryUtil pageUtil);

    int getTotalLeavesMallGoods(PageQueryUtil pageUtil);

    List<LeavesMallGoods> selectByPrimaryKeys(List<Long> goodsIds);

    List<LeavesMallGoods> findLeavesMallGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalLeavesMallGoodsBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("leavesMallGoodsList") List<LeavesMallGoods> leavesMallGoodsList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds,@Param("sellStatus") int sellStatus);

}