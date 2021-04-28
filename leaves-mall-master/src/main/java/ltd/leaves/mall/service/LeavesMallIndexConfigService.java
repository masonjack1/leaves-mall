package ltd.leaves.mall.service;

import ltd.leaves.mall.entity.IndexConfig;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;
import ltd.leaves.mall.controller.vo.LeavesMallIndexConfigGoodsVO;

import java.util.List;

public interface LeavesMallIndexConfigService {
    /**
     * The background page
     *
     * @param pageUtil
     * @return
     */
    PageResult getConfigsPage(PageQueryUtil pageUtil);

    String saveIndexConfig(IndexConfig indexConfig);

    String updateIndexConfig(IndexConfig indexConfig);

    IndexConfig getIndexConfigById(Long id);

    /**
     * Returns a fixed number of home page configuration goods objects (home page calls)
     *
     * @param number
     * @return
     */
    List<LeavesMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);

    Boolean deleteBatch(Long[] ids);
}
