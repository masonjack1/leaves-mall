package ltd.leaves.mall.service;

import ltd.leaves.mall.entity.AdminUser;

public interface AdminUserService {

    AdminUser login(String userName, String password);

    /**
     * Obtaining user information
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * Change the password of the current logged-in user
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * Modify the name information of the current logged-in user
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);

}
