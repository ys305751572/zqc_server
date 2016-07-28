package com.leoman.common.interceptor;

import com.leoman.admin.entity.Admin;
import com.leoman.common.core.Constant;
import com.leoman.common.log.entity.Log;
import com.leoman.common.log.entity.LogEntity;
import com.leoman.common.log.service.LogService;
import com.leoman.common.log.service.impl.LogServiceImpl;
import com.leoman.common.logger.Logger;
import com.leoman.utils.AesUtils;
import com.leoman.utils.BeanUtil;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.encryption.BackAES;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/22.
 */
public class LogInterceptor extends HandlerInterceptorAdapter{

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        try {
            if(! (handler instanceof HandlerMethod)) {
                return;
            }
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Log log = method.getAnnotation(Log.class);
            if(log != null) {
                Admin admin = (Admin) request.getSession().getAttribute(Constant.SESSION_MEMBER_GLOBLE);
                if(admin != null) {
                    String message = log.message();
                    MessageFormat mf = new MessageFormat(message);
                    String result = mf.format(new Object[]{admin.getUsername()});
                    LogServiceImpl logService = (LogServiceImpl) BeanUtil.getBean("logService");
                    LogEntity logEntity = new LogEntity();
                    logEntity.setMessage(result);
                    logEntity.setUserId(admin.getId());
                    logEntity.setLogType(LogEntity.LOG_TYPE_INFO);
                    logEntity.setUrl("");
                    logEntity.setParams("");
                    logEntity.setUserType(LogEntity.USER_TYPE_ADMIN);
                    logService.save(logEntity);
                }
            }

            super.postHandle(request, response, handler, modelAndView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
