package ltd.leaves.mall.controller.mall;

import ltd.leaves.mall.common.Constants;
import ltd.leaves.mall.common.IndexConfigTypeEnum;
import ltd.leaves.mall.controller.vo.LeavesMallIndexCarouselVO;
import ltd.leaves.mall.controller.vo.LeavesMallIndexCategoryVO;
import ltd.leaves.mall.controller.vo.LeavesMallIndexConfigGoodsVO;
import ltd.leaves.mall.service.LeavesMallCarouselService;
import ltd.leaves.mall.service.LeavesMallCategoryService;
import ltd.leaves.mall.service.LeavesMallIndexConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private LeavesMallCarouselService leavesMallCarouselService;

    @Resource
    private LeavesMallIndexConfigService leavesMallIndexConfigService;

    @Resource
    private LeavesMallCategoryService leavesMallCategoryService;

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(HttpServletRequest request) {
        List<LeavesMallIndexCategoryVO> categories = leavesMallCategoryService.getCategoriesForIndex();
        if (CollectionUtils.isEmpty(categories)) {
            return "error/error_5xx";
        }
        List<LeavesMallIndexCarouselVO> carousels = leavesMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        List<LeavesMallIndexConfigGoodsVO> hotGoodses = leavesMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
        List<LeavesMallIndexConfigGoodsVO> newGoodses = leavesMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
        List<LeavesMallIndexConfigGoodsVO> recommendGoodses = leavesMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
        request.setAttribute("categories", categories);//Categorical data
        request.setAttribute("carousels", carousels);//Shuffling figure
        request.setAttribute("hotGoodses", hotGoodses);//hot goods
        request.setAttribute("newGoodses", newGoodses);//new goods
        request.setAttribute("recommendGoodses", recommendGoodses);//recommend goods
        return "mall/index";
    }
}
