package com.leoman.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangbin on 2015/8/4.
 */
public  class HttpUtil {

    public static String getFullUrl(HttpServletRequest httpRequest,String host){
        String fullUrl = null;
        String uri = httpRequest.getRequestURI();
        String queryStr = httpRequest.getQueryString();
        if(queryStr!=null){
            fullUrl =  host+uri+"?"+queryStr;
        }else{
            fullUrl= host+uri;
        }

        return fullUrl;
    }

    /**
     * 获取请求地址
     * @param request
     * @return
     */
    public static String getRemotePath(HttpServletRequest request) {
        String path = request.getContextPath();
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    }

    public static String getFullUrl(HttpServletRequest httpRequest){
        String fullUrl = null;
        String url = httpRequest.getRequestURL().toString();
        String queryStr = httpRequest.getQueryString();
        if(queryStr!=null){
            fullUrl =  url+"?"+queryStr;
        }else{
            fullUrl= url;
        }
        return fullUrl;
    }
}
