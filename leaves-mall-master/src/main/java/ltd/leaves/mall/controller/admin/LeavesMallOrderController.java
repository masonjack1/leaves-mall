package ltd.leaves.mall.controller.admin;

import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.controller.vo.LeavesMallOrderItemVO;
import ltd.leaves.mall.entity.LeavesMallOrder;
import ltd.leaves.mall.service.LeavesMallOrderService;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.Result;
import ltd.leaves.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class LeavesMallOrderController {

    @Resource
    private LeavesMallOrderService leavesMallOrderService;

    @GetMapping("/orders")
    public String ordersPage(HttpServletRequest request) {
        request.setAttribute("path", "orders");
        return "leaves_mall_order";
    }

    /**
     * list
     */
    @RequestMapping(value = "/orders/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(leavesMallOrderService.getNewBeeMallOrdersPage(pageUtil));
    }

    /**
     * update
     */
    @RequestMapping(value = "/orders/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody LeavesMallOrder leavesMallOrder) {
        if (Objects.isNull(leavesMallOrder.getTotalPrice())
                || Objects.isNull(leavesMallOrder.getOrderId())
                || leavesMallOrder.getOrderId() < 1
                || leavesMallOrder.getTotalPrice() < 1
                || StringUtils.isEmpty(leavesMallOrder.getUserAddress())) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        String result = leavesMallOrderService.updateOrderInfo(leavesMallOrder);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * info
     */
    @GetMapping("/order-items/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id) {
        List<LeavesMallOrderItemVO> orderItems = leavesMallOrderService.getOrderItems(id);
        if (!CollectionUtils.isEmpty(orderItems)) {
            return ResultGenerator.genSuccessResult(orderItems);
        }
        return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
    }

    /**
     * checkDone
     */
    @RequestMapping(value = "/orders/checkDone", method = RequestMethod.POST)
    @ResponseBody
    public Result checkDone(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        String result = leavesMallOrderService.checkDone(ids);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * checkOut
     */
    @RequestMapping(value = "/orders/checkOut", method = RequestMethod.POST)
    @ResponseBody
    public Result checkOut(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        String result = leavesMallOrderService.checkOut(ids);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * closeDone
     */
    @RequestMapping(value = "/orders/close", method = RequestMethod.POST)
    @ResponseBody
    public Result closeOrder(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        String result = leavesMallOrderService.closeOrder(ids);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


}