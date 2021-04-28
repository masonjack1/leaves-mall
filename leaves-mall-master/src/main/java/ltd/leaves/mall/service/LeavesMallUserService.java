package ltd.leaves.mall.service;

import ltd.leaves.mall.entity.MallUser;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;
import ltd.leaves.mall.controller.vo.LeavesMallUserVO;

import javax.servlet.http.HttpSession;

public interface LeavesMallUserService {
    /**
     * The background page
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil);

    /**
     * User registration
     *
     * @param loginName
     * @param password
     * @return
     */
    String register(String loginName, String password);

    /**
     * The login
     *
     * @param loginName
     * @param passwordMD5
     * @param httpSession
     * @return
     */
    String login(String loginName, String passwordMD5, HttpSession httpSession);

    /**
     * User information is modified and the latest user information is returned
     *
     * @param mallUser
     * @return
     */
    LeavesMallUserVO updateUserInfo(MallUser mallUser, HttpSession httpSession);

    /**
     * User disables and undisables (0- Unlocked 1- Locked)
     *
     * @param ids
     * @param lockStatus
     * @return
     */
    Boolean lockUsers(Integer[] ids, int lockStatus);
}
