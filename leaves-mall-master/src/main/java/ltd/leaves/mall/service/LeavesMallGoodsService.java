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
    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * Add the goods
     *
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(LeavesMallGoods goods);

    /**
     * Add commodity data in batch
     *
     * @param leavesMallGoodsList
     * @return
     */
    void batchSaveNewBeeMallGoods(List<LeavesMallGoods> leavesMallGoodsList);

    /**
     * Modify product information
     *
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(LeavesMallGoods goods);

    /**
     * Get product details
     *
     * @param id
     * @return
     */
    LeavesMallGoods getNewBeeMallGoodsById(Long id);

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
    PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil);
}
