package com.leoman.index.service.impl;

import com.leoman.admin.entity.Admin;
import com.leoman.admin.service.AdminService;
import com.leoman.common.core.Constant;
import com.leoman.index.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangbin on 2015/3/3.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminService adminService;

    @Override
    public Admin getMember(HttpServletRequest request, String type) {
//        Member member = null;
//        if (Constant.MEMBER_TYPE_GLOBLE.equals(type)) {
//            member = (Member) request.getSession().getAttribute(Constant.SESSION_MEMBER_GLOBLE);
//        } else if (Constant.MEMBER_TYPE_BUSINESS.equals(type)) {
//            member = (Member) request.getSession().getAttribute(Constant.SESSION_MEMBER_BUSINESS);
//        }
//        return member;
        return null;
    }

    // 总后台登陆
    @Override
    public Boolean login(HttpServletRequest request, String username, String password, String remark) {
//        Member member = memberService.findByUsernameAndPassword(username,password);
//        if(member!=null){
//            request.getSession().setAttribute(Constant.SESSION_MEMBER_GLOBLE, member);
//            if(StringUtils.isNotBlank(remark)){
//                request.getSession().setMaxInactiveInterval(60*60*24*7);
//            }
//            return true;
//        }else {
//            return false;
//        }
        return false;
    }

    @Override
    public Boolean login(HttpServletRequest request, Admin member, String remark, String type) {
//        if (member != null) {
//            if (Constant.MEMBER_TYPE_GLOBLE.equals(type)) {
//                request.getSession().setAttribute(Constant.SESSION_MEMBER_GLOBLE, member);
//            } else if (Constant.MEMBER_TYPE_BUSINESS.equals(type)) {
//                request.getSession().setAttribute(Constant.SESSION_MEMBER_BUSINESS, member);
//            }
//            if (StringUtils.isNotBlank(remark)) {
//                request.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
//            }
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }

    @Override
    public Boolean login(HttpServletRequest request, String username, String password, String type, String remark) {
        Admin admin = null;
        try {
            admin = adminService.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (admin != null && password.equals(admin.getPassword())) {
            request.getSession().setAttribute(Constant.SESSION_MEMBER_GLOBLE, admin);
            admin.setLastLoginDate(System.currentTimeMillis());

            adminService.save(admin);
            if (StringUtils.isNotBlank(remark)) {
                request.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logOut(HttpServletRequest request, String type) {
        HttpSession session = request.getSession(false);
        session.removeAttribute(Constant.SESSION_MEMBER_GLOBLE);
    }

    @Override
    public Boolean loginWeixin(HttpServletRequest request, HttpServletResponse response, String username, String password) {

        return false;
    }
}
