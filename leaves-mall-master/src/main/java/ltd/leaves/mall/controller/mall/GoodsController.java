package ltd.leaves.mall.controller.mall;

import ltd.leaves.mall.common.Constants;
import ltd.leaves.mall.common.LeavesMallException;
import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.controller.vo.LeavesMallGoodsDetailVO;
import ltd.leaves.mall.controller.vo.SearchPageCategoryVO;
import ltd.leaves.mall.entity.LeavesMallGoods;
import ltd.leaves.mall.service.LeavesMallCategoryService;
import ltd.leaves.mall.service.LeavesMallGoodsService;
import ltd.leaves.mall.util.BeanUtil;
import ltd.leaves.mall.util.PageQueryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class GoodsController {

    @Resource
    private LeavesMallGoodsService leavesMallGoodsService;
    @Resource
    private LeavesMallCategoryService leavesMallCategoryService;

    @GetMapping({"/search", "/search.html"})
    public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //Encapsulated classified data
        if (params.containsKey("goodsCategoryId") && !StringUtils.isEmpty(params.get("goodsCategoryId") + "")) {
            Long categoryId = Long.valueOf(params.get("goodsCategoryId") + "");
            SearchPageCategoryVO searchPageCategoryVO = leavesMallCategoryService.getCategoriesForSearch(categoryId);
            if (searchPageCategoryVO != null) {
                request.setAttribute("goodsCategoryId", categoryId);
                request.setAttribute("searchPageCategoryVO", searchPageCategoryVO);
            }
        }
        //Encapsulate parameters for front-end echo
        if (params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy") + "")) {
            request.setAttribute("orderBy", params.get("orderBy") + "");
        }
        String keyword = "";
        //Filter the keyword to remove Spaces
        if (params.containsKey("keyword") && !StringUtils.isEmpty((params.get("keyword") + "").trim())) {
            keyword = params.get("keyword") + "";
        }
        request.setAttribute("keyword", keyword);
        params.put("keyword", keyword);
        //Search for items that are on the shelves
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        //Encapsulated commodity data
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("pageResult", leavesMallGoodsService.searchLeavesMallGoods(pageUtil));
        return "mall/search";
    }

    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId < 1) {
            return "error/error_5xx";
        }
        LeavesMallGoods goods = leavesMallGoodsService.getLeavesMallGoodsById(goodsId);
        if (goods == null) {
            LeavesMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        if (Constants.SELL_STATUS_UP != goods.getGoodsSellStatus()) {
            LeavesMallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }
        LeavesMallGoodsDetailVO goodsDetailVO = new LeavesMallGoodsDetailVO();
        BeanUtil.copyProperties(goods, goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        request.setAttribute("goodsDetail", goodsDetailVO);
        return "mall/detail";
    }

}
