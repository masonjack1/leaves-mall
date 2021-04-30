package ltd.leaves.mall.service.impl;

import ltd.leaves.mall.common.Constants;
import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.dao.MallUserMapper;
import ltd.leaves.mall.entity.MallUser;
import ltd.leaves.mall.service.LeavesMallUserService;
import ltd.leaves.mall.util.*;
import ltd.leaves.mall.controller.vo.LeavesMallUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class LeavesMallUserServiceImpl implements LeavesMallUserService {

    @Autowired
    private MallUserMapper mallUserMapper;

    @Override
    public PageResult getleavesMallUsersPage(PageQueryUtil pageUtil) {
        List<MallUser> mallUsers = mallUserMapper.findMallUserList(pageUtil);
        int total = mallUserMapper.getTotalMallUsers(pageUtil);
        PageResult pageResult = new PageResult(mallUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String register(String loginName, String password) {
        if (mallUserMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        MallUser registerUser = new MallUser();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (mallUserMapper.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        MallUser user = mallUserMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            //Nicknames that are too long affect page presentation
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }
            LeavesMallUserVO leavesMallUserVO = new LeavesMallUserVO();
            BeanUtil.copyProperties(user, leavesMallUserVO);
            //Sets the quantity in the shopping cart
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, leavesMallUserVO);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public LeavesMallUserVO updateUserInfo(MallUser mallUser, HttpSession httpSession) {
        LeavesMallUserVO userTemp = (LeavesMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        MallUser userFromDB = mallUserMapper.selectByPrimaryKey(userTemp.getUserId());
        if (userFromDB != null) {
            if (!StringUtils.isEmpty(mallUser.getNickName())) {
                userFromDB.setNickName(NewBeeMallUtils.cleanString(mallUser.getNickName()));
            }
            if (!StringUtils.isEmpty(mallUser.getAddress())) {
                userFromDB.setAddress(NewBeeMallUtils.cleanString(mallUser.getAddress()));
            }
            if (!StringUtils.isEmpty(mallUser.getIntroduceSign())) {
                userFromDB.setIntroduceSign(NewBeeMallUtils.cleanString(mallUser.getIntroduceSign()));
            }
            if (mallUserMapper.updateByPrimaryKeySelective(userFromDB) > 0) {
                LeavesMallUserVO leavesMallUserVO = new LeavesMallUserVO();
                userFromDB = mallUserMapper.selectByPrimaryKey(mallUser.getUserId());
                BeanUtil.copyProperties(userFromDB, leavesMallUserVO);
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, leavesMallUserVO);
                return leavesMallUserVO;
            }
        }
        return null;
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return mallUserMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}
