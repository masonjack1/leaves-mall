package ltd.leaves.mall.controller.admin;

import ltd.leaves.mall.service.LeavesMallUserService;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.Result;
import ltd.leaves.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LeavesMallUserController {

    @Resource
    private LeavesMallUserService leavesMallUserService;

    @GetMapping("/users")
    public String usersPage(HttpServletRequest request) {
        request.setAttribute("path", "users");
        return "leaves_mall_user";
    }

    /**
     * list
     */
    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(leavesMallUserService.getleavesMallUsersPage(pageUtil));
    }

    /**
     * User disables and undisables (0- Unlocked 1- Locked)
     */
    @RequestMapping(value = "/users/lock/{lockStatus}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids, @PathVariable int lockStatus) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Parameters of the abnormal！");
        }
        if (lockStatus != 0 && lockStatus != 1) {
            return ResultGenerator.genFailResult("Illegal operation！");
        }
        if (leavesMallUserService.lockUsers(ids, lockStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("Disable the failure");
        }
    }
}