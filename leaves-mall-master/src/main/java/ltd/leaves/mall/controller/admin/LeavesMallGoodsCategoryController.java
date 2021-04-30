package ltd.leaves.mall.controller.admin;

import ltd.leaves.mall.common.LeavesMallCategoryLevelEnum;
import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.entity.GoodsCategory;
import ltd.leaves.mall.service.LeavesMallCategoryService;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.Result;
import ltd.leaves.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class LeavesMallGoodsCategoryController {

    @Resource
    private LeavesMallCategoryService leavesMallCategoryService;

    @GetMapping("/categories")
    public String categoriesPage(HttpServletRequest request, @RequestParam("categoryLevel") Byte categoryLevel, @RequestParam("parentId") Long parentId, @RequestParam("backParentId") Long backParentId) {
        if (categoryLevel == null || categoryLevel < 1 || categoryLevel > 3) {
            return "error/error_5xx";
        }
        request.setAttribute("path", "leaves_mall_category");
        request.setAttribute("parentId", parentId);
        request.setAttribute("backParentId", backParentId);
        request.setAttribute("categoryLevel", categoryLevel);
        return "leaves_mall_category";
    }

    /**
     * list
     */
    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit")) || StringUtils.isEmpty(params.get("categoryLevel")) || StringUtils.isEmpty(params.get("parentId"))) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(leavesMallCategoryService.getCategorisPage(pageUtil));
    }

    /**
     * list
     */
    @RequestMapping(value = "/categories/listForSelect", method = RequestMethod.GET)
    @ResponseBody
    public Result listForSelect(@RequestParam("categoryId") Long categoryId) {
        if (categoryId == null || categoryId < 1) {
            return ResultGenerator.genFailResult("Lack of parameter！");
        }
        GoodsCategory category = leavesMallCategoryService.getGoodsCategoryById(categoryId);
        //No data is returned if neither the first level nor the second level
        if (category == null || category.getCategoryLevel() == LeavesMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        Map categoryResult = new HashMap(2);
        if (category.getCategoryLevel() == LeavesMallCategoryLevelEnum.LEVEL_ONE.getLevel()) {
            //If it is a first-level category, all the second-level categories under the current first-level category and all the third-level categories under the first data in the second-level category list are returned
            //Query all secondary categories for the first entity in the list of primary categories
            List<GoodsCategory> secondLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), LeavesMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //Query all tertiary categories for the first entity in the secondary category list
                List<GoodsCategory> thirdLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), LeavesMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                categoryResult.put("secondLevelCategories", secondLevelCategories);
                categoryResult.put("thirdLevelCategories", thirdLevelCategories);
            }
        }
        if (category.getCategoryLevel() == LeavesMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
            //If it is a secondary category, a list of all tertiary categories under the current category is returned
            List<GoodsCategory> thirdLevelCategories = leavesMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), LeavesMallCategoryLevelEnum.LEVEL_THREE.getLevel());
            categoryResult.put("thirdLevelCategories", thirdLevelCategories);
        }
        return ResultGenerator.genSuccessResult(categoryResult);
    }

    /**
     * save
     */
    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody GoodsCategory goodsCategory) {
        if (Objects.isNull(goodsCategory.getCategoryLevel())
                || StringUtils.isEmpty(goodsCategory.getCategoryName())
                || Objects.isNull(goodsCategory.getParentId())
                || Objects.isNull(goodsCategory.getCategoryRank())) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        String result = leavesMallCategoryService.saveCategory(goodsCategory);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


    /**
     * update
     */
    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody GoodsCategory goodsCategory) {
        if (Objects.isNull(goodsCategory.getCategoryId())
                || Objects.isNull(goodsCategory.getCategoryLevel())
                || StringUtils.isEmpty(goodsCategory.getCategoryName())
                || Objects.isNull(goodsCategory.getParentId())
                || Objects.isNull(goodsCategory.getCategoryRank())) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        String result = leavesMallCategoryService.updateGoodsCategory(goodsCategory);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * info
     */
    @GetMapping("/categories/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id) {
        GoodsCategory goodsCategory = leavesMallCategoryService.getGoodsCategoryById(id);
        if (goodsCategory == null) {
            return ResultGenerator.genFailResult("No data was queried");
        }
        return ResultGenerator.genSuccessResult(goodsCategory);
    }

    /**
     * delete
     */
    @RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        if (leavesMallCategoryService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("Delete failed");
        }
    }


}