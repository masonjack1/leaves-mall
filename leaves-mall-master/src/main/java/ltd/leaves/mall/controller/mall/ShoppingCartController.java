package ltd.leaves.mall.controller.mall;

import ltd.leaves.mall.common.Constants;
import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.controller.vo.LeavesMallShoppingCartItemVO;
import ltd.leaves.mall.controller.vo.LeavesMallUserVO;
import ltd.leaves.mall.entity.LeavesMallShoppingCartItem;
import ltd.leaves.mall.service.LeavesMallShoppingCartService;
import ltd.leaves.mall.util.Result;
import ltd.leaves.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Resource
    private LeavesMallShoppingCartService leavesMallShoppingCartService;

    @GetMapping("/shop-cart")
    public String cartListPage(HttpServletRequest request,
                               HttpSession httpSession) {
        LeavesMallUserVO user = (LeavesMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        int itemsTotal = 0;
        int priceTotal = 0;
        List<LeavesMallShoppingCartItemVO> myShoppingCartItems = leavesMallShoppingCartService.getMyShoppingCartItems(user.getUserId());
        if (!CollectionUtils.isEmpty(myShoppingCartItems)) {
            //Total number of shopping items
            itemsTotal = myShoppingCartItems.stream().mapToInt(LeavesMallShoppingCartItemVO::getGoodsCount).sum();
            if (itemsTotal < 1) {
                return "error/error_5xx";
            }
            //The total price
            for (LeavesMallShoppingCartItemVO leavesMallShoppingCartItemVO : myShoppingCartItems) {
                priceTotal += leavesMallShoppingCartItemVO.getGoodsCount() * leavesMallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                return "error/error_5xx";
            }
        }
        request.setAttribute("itemsTotal", itemsTotal);
        request.setAttribute("priceTotal", priceTotal);
        request.setAttribute("myShoppingCartItems", myShoppingCartItems);
        return "mall/cart";
    }

    @PostMapping("/shop-cart")
    @ResponseBody
    public Result saveNewBeeMallShoppingCartItem(@RequestBody LeavesMallShoppingCartItem leavesMallShoppingCartItem,
                                                 HttpSession httpSession) {
        LeavesMallUserVO user = (LeavesMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        leavesMallShoppingCartItem.setUserId(user.getUserId());
        //todo determine the number
        String saveResult = leavesMallShoppingCartService.saveLeavesMallCartItem(leavesMallShoppingCartItem);
        //Add a success
        if (ServiceResultEnum.SUCCESS.getResult().equals(saveResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //Add failure
        return ResultGenerator.genFailResult(saveResult);
    }

    @PutMapping("/shop-cart")
    @ResponseBody
    public Result updateNewBeeMallShoppingCartItem(@RequestBody LeavesMallShoppingCartItem leavesMallShoppingCartItem,
                                                   HttpSession httpSession) {
        LeavesMallUserVO user = (LeavesMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        leavesMallShoppingCartItem.setUserId(user.getUserId());
        //todo determine the number
        String updateResult = leavesMallShoppingCartService.updateLeavesMallCartItem(leavesMallShoppingCartItem);
        //Modify the success
        if (ServiceResultEnum.SUCCESS.getResult().equals(updateResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //Modify the failure
        return ResultGenerator.genFailResult(updateResult);
    }

    @DeleteMapping("/shop-cart/{newBeeMallShoppingCartItemId}")
    @ResponseBody
    public Result updateNewBeeMallShoppingCartItem(@PathVariable("newBeeMallShoppingCartItemId") Long newBeeMallShoppingCartItemId,
                                                   HttpSession httpSession) {
        LeavesMallUserVO user = (LeavesMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        Boolean deleteResult = leavesMallShoppingCartService.deleteById(newBeeMallShoppingCartItemId);
        //delete successful
        if (deleteResult) {
            return ResultGenerator.genSuccessResult();
        }
        //delete fail
        return ResultGenerator.genFailResult(ServiceResultEnum.OPERATE_ERROR.getResult());
    }

    @GetMapping("/shop-cart/settle")
    public String settlePage(HttpServletRequest request,
                             HttpSession httpSession) {
        int priceTotal = 0;
        LeavesMallUserVO user = (LeavesMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        List<LeavesMallShoppingCartItemVO> myShoppingCartItems = leavesMallShoppingCartService.getMyShoppingCartItems(user.getUserId());
        if (CollectionUtils.isEmpty(myShoppingCartItems)) {
            //No data does not jump to the account page
            return "/shop-cart";
        } else {
            //The total price
            for (LeavesMallShoppingCartItemVO leavesMallShoppingCartItemVO : myShoppingCartItems) {
                priceTotal += leavesMallShoppingCartItemVO.getGoodsCount() * leavesMallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                return "error/error_5xx";
            }
        }
        request.setAttribute("priceTotal", priceTotal);
        request.setAttribute("myShoppingCartItems", myShoppingCartItems);
        return "mall/order-settle";
    }
}
