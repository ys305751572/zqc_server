package com.leoman.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description webservice请求帮助类
 * @date 2014-09-25 10:52:51
 * @author 龙哲
 */
public class WebserviceUtil {

    public static void main(String[] adsf) {
        WebserviceUtil.post("http://www.baidu.com/s?wd=abc");
    }

    /**
     * @Description 发送post请求
     *            String urlStr 请求url地址;
     * @return String 返回值
     * @date 2014-7-30 下午03:08:52
     * @author 龙哲
     */
    public static String post(String urlStr) {
        // url地址
        URL url = null;
        // http连接
        HttpURLConnection httpConn = null;
        // 输入流
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            // 提交模式
            httpConn.setRequestMethod("POST");
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {
            }
        }
        String result = sb.toString();
        return result;
    }

    /**
     * @Description 发送post请求
     *            String urlStr 请求url地址; String paramStr 参数列表 paramData=123
     * 
     * @return String 返回值
     * @date 2014-7-30 下午03:08:52
     * @author 龙哲
     */
    public static String post(String urlStr, String paramStr) {
        // url地址
        URL url = null;
        // http连接
        HttpURLConnection httpConn = null;
        // 输入流
        BufferedReader in = null;
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            // 提交模式
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
//            httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            out = new PrintWriter(httpConn.getOutputStream());
            out.print(paramStr);
            out.flush();
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {
            }
        }
        String result = sb.toString();
        return result;
    }
}
