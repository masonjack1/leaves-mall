package ltd.leaves.mall.service;

import ltd.leaves.mall.entity.GoodsCategory;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;
import ltd.leaves.mall.controller.vo.LeavesMallIndexCategoryVO;
import ltd.leaves.mall.controller.vo.SearchPageCategoryVO;

import java.util.List;

public interface LeavesMallCategoryService {
    /**
     * The background page
     *
     * @param pageUtil
     * @return
     */
    PageResult getCategorisPage(PageQueryUtil pageUtil);

    String saveCategory(GoodsCategory goodsCategory);

    String updateGoodsCategory(GoodsCategory goodsCategory);

    GoodsCategory getGoodsCategoryById(Long id);

    Boolean deleteBatch(Integer[] ids);

    /**
     * Return classified data (home page call)
     *
     * @return
     */
    List<LeavesMallIndexCategoryVO> getCategoriesForIndex();

    /**
     * Return classified data (search page call)
     *
     * @param categoryId
     * @return
     */
    SearchPageCategoryVO getCategoriesForSearch(Long categoryId);

    /**
     * Get the classification list by parentId and level
     *
     * @param parentIds
     * @param categoryLevel
     * @return
     */
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);
}
