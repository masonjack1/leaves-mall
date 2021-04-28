package ltd.leaves.mall.service;

import ltd.leaves.mall.entity.Carousel;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;
import ltd.leaves.mall.controller.vo.LeavesMallIndexCarouselVO;

import java.util.List;

public interface LeavesMallCarouselService {
    /**
     * The background page
     *
     * @param pageUtil
     * @return
     */
    PageResult getCarouselPage(PageQueryUtil pageUtil);

    String saveCarousel(Carousel carousel);

    String updateCarousel(Carousel carousel);

    Carousel getCarouselById(Integer id);

    Boolean deleteBatch(Integer[] ids);

    /**
     * Returns a fixed number of rotated graph objects (home page call)
     *
     * @param number
     * @return
     */
    List<LeavesMallIndexCarouselVO> getCarouselsForIndex(int number);
}
