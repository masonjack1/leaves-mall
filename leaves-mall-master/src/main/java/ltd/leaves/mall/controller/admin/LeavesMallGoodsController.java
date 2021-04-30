package ltd.leaves.mall.controller.admin;

import ltd.leaves.mall.common.Constants;
import ltd.leaves.mall.common.LeavesMallCategoryLevelEnum;
import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.entity.GoodsCategory;
import ltd.leaves.mall.entity.LeavesMallGoods;
import ltd.leaves.mall.service.LeavesMallCategoryService;
import ltd.leaves.mall.service.LeavesMallGoodsService;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.Result;
import ltd.leaves.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class LeavesMallGoodsController {

    @Resource
    private LeavesMallGoodsService leavesMallGoodsService;
    @Resource
    private LeavesMallCategoryService leavesMallCategoryService;

    @GetMapping("/goods")
    public String goodsPage(HttpServletRequest request) {
        request.setAttribute("path", "leaves_mall_goods");
        return "admin/leaves_mall_goods";
    }

    @GetMapping("/goods/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        //Query all first-level classifications
        List<GoodsCategory> firstLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), LeavesMallCategoryLevelEnum.LEVEL_ONE.getLevel());
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            //Query all secondary categories for the first entity in the list of primary categories
            List<GoodsCategory> secondLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), LeavesMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //Query all tertiary categories for the first entity in the secondary category list
                List<GoodsCategory> thirdLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), LeavesMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                request.setAttribute("firstLevelCategories", firstLevelCategories);
                request.setAttribute("secondLevelCategories", secondLevelCategories);
                request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                request.setAttribute("path", "goods-edit");
                return "admin/leaves_mall_goods_edit";
            }
        }
        return "error/error_5xx";
    }

    @GetMapping("/goods/edit/{goodsId}")
    public String edit(HttpServletRequest request, @PathVariable("goodsId") Long goodsId) {
        request.setAttribute("path", "edit");
        LeavesMallGoods leavesMallGoods = leavesMallGoodsService.getLeavesMallGoodsById(goodsId);
        if (leavesMallGoods == null) {
            return "error/error_400";
        }
        if (leavesMallGoods.getGoodsCategoryId() > 0) {
            if (leavesMallGoods.getGoodsCategoryId() != null || leavesMallGoods.getGoodsCategoryId() > 0) {
                //If there is a classification field, the relevant classification data will be returned to the front end for the three-level linkage display of the classification
                GoodsCategory currentGoodsCategory = leavesMallCategoryService.getGoodsCategoryById(leavesMallGoods.getGoodsCategoryId());
                //The category ID field stored in the item table is the ID of the tertiary category. Failure to be a tertiary category is an error
                if (currentGoodsCategory != null && currentGoodsCategory.getCategoryLevel() == LeavesMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
                    //查询所有的一级分类
                    List<GoodsCategory> firstLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), LeavesMallCategoryLevelEnum.LEVEL_ONE.getLevel());
                    //根据parentId查询当前parentId下所有的三级分类
                    List<GoodsCategory> thirdLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(currentGoodsCategory.getParentId()), LeavesMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                    //Query the parent secondary category of the current tertiary category
                    GoodsCategory secondCategory = leavesMallCategoryService.getGoodsCategoryById(currentGoodsCategory.getParentId());
                    if (secondCategory != null) {
                        //Select parentId from parentId and parentId from parentId
                        List<GoodsCategory> secondLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondCategory.getParentId()), LeavesMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                        //Query the parent level 1 category of the current level 2 category
                        GoodsCategory firestCategory = leavesMallCategoryService.getGoodsCategoryById(secondCategory.getParentId());
                        if (firestCategory != null) {
                            //After all the classified data are obtained, they are put into the Request object for the front end to read
                            request.setAttribute("firstLevelCategories", firstLevelCategories);
                            request.setAttribute("secondLevelCategories", secondLevelCategories);
                            request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                            request.setAttribute("firstLevelCategoryId", firestCategory.getCategoryId());
                            request.setAttribute("secondLevelCategoryId", secondCategory.getCategoryId());
                            request.setAttribute("thirdLevelCategoryId", currentGoodsCategory.getCategoryId());
                        }
                    }
                }
            }
        }
        if (leavesMallGoods.getGoodsCategoryId() == 0) {
            //Query all first-level classifications
            List<GoodsCategory> firstLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), LeavesMallCategoryLevelEnum.LEVEL_ONE.getLevel());
            if (!CollectionUtils.isEmpty(firstLevelCategories)) {
                //Query all secondary categories for the first entity in the list of primary categories
                List<GoodsCategory> secondLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), LeavesMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                    //Query all tertiary categories for the first entity in the secondary category list
                    List<GoodsCategory> thirdLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), LeavesMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                    request.setAttribute("firstLevelCategories", firstLevelCategories);
                    request.setAttribute("secondLevelCategories", secondLevelCategories);
                    request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                }
            }
        }
        request.setAttribute("goods", leavesMallGoods);
        request.setAttribute("path", "goods-edit");
        return "admin/leaves_mall_goods_edit";
    }

    /**
     * list
     */
    @RequestMapping(value = "/goods/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(leavesMallGoodsService.getLeavesMallGoodsPage(pageUtil));
    }

    /**
     * save
     */
    @RequestMapping(value = "/goods/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody LeavesMallGoods leavesMallGoods) {
        if (StringUtils.isEmpty(leavesMallGoods.getGoodsName())
                || StringUtils.isEmpty(leavesMallGoods.getGoodsIntro())
                || StringUtils.isEmpty(leavesMallGoods.getTag())
                || Objects.isNull(leavesMallGoods.getOriginalPrice())
                || Objects.isNull(leavesMallGoods.getGoodsCategoryId())
                || Objects.isNull(leavesMallGoods.getSellingPrice())
                || Objects.isNull(leavesMallGoods.getStockNum())
                || Objects.isNull(leavesMallGoods.getGoodsSellStatus())
                || StringUtils.isEmpty(leavesMallGoods.getGoodsCoverImg())
                || StringUtils.isEmpty(leavesMallGoods.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        String result = leavesMallGoodsService.saveLeavesMallGoods(leavesMallGoods);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


    /**
     * save
     */
    @RequestMapping(value = "/goods/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody LeavesMallGoods leavesMallGoods) {
        if (Objects.isNull(leavesMallGoods.getGoodsId())
                || StringUtils.isEmpty(leavesMallGoods.getGoodsName())
                || StringUtils.isEmpty(leavesMallGoods.getGoodsIntro())
                || StringUtils.isEmpty(leavesMallGoods.getTag())
                || Objects.isNull(leavesMallGoods.getOriginalPrice())
                || Objects.isNull(leavesMallGoods.getSellingPrice())
                || Objects.isNull(leavesMallGoods.getGoodsCategoryId())
                || Objects.isNull(leavesMallGoods.getStockNum())
                || Objects.isNull(leavesMallGoods.getGoodsSellStatus())
                || StringUtils.isEmpty(leavesMallGoods.getGoodsCoverImg())
                || StringUtils.isEmpty(leavesMallGoods.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        String result = leavesMallGoodsService.updateLeavesMallGoods(leavesMallGoods);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * info
     */
    @GetMapping("/goods/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id) {
        LeavesMallGoods goods = leavesMallGoodsService.getLeavesMallGoodsById(id);
        if (goods == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(goods);
    }

    /**
     * Modify sales status in bulk
     */
    @RequestMapping(value = "/goods/status/{sellStatus}", method = RequestMethod.PUT)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids, @PathVariable("sellStatus") int sellStatus) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        if (sellStatus != Constants.SELL_STATUS_UP && sellStatus != Constants.SELL_STATUS_DOWN) {
            return ResultGenerator.genFailResult("Abnormal state！");
        }
        if (leavesMallGoodsService.batchUpdateSellStatus(ids, sellStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("Modify the failure");
        }
    }

}