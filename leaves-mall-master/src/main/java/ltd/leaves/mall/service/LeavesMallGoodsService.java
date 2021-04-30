package ltd.leaves.mall.service;

import ltd.leaves.mall.entity.LeavesMallGoods;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;

import java.util.List;

public interface LeavesMallGoodsService {
    /**
     * The background page
     *
     * @param pageUtil
     * @return
     */
    PageResult getLeavesMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * Add the goods
     *
     * @param goods
     * @return
     */
    String saveLeavesMallGoods(LeavesMallGoods goods);

    /**
     * Add commodity data in batch
     *
     * @param leavesMallGoodsList
     * @return
     */
    void batchSaveLeavesMallGoods(List<LeavesMallGoods> leavesMallGoodsList);

    /**
     * Modify product information
     *
     * @param goods
     * @return
     */
    String updateLeavesMallGoods(LeavesMallGoods goods);

    /**
     * Get product details
     *
     * @param id
     * @return
     */
    LeavesMallGoods getLeavesMallGoodsById(Long id);

    /**
     * Batch modification of sales status (on and off shelves)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    /**
     * Commodity search
     *
     * @param pageUtil
     * @return
     */
    PageResult searchLeavesMallGoods(PageQueryUtil pageUtil);
}
