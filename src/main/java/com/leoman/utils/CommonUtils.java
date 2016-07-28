package com.leoman.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.leoman.image.entity.Attach;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonUtils {

    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: getWebPath
     * @Description: 获取项目路径 例:http://localhost:8080/yqss
     */
    public static String getRemotePath() {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        String absPath = null;

        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        String context = request.getContextPath();
        absPath = scheme + "://" + host + (port != 80 ? ":" + port : "") + context;
        return absPath;
    }

    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: getRemotePathWithoutContext
     * @Description: 获取项目路径 例:http://localhost:8080/
     */
    public static String getRemotePathWithoutContext() {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        String absPath = null;

        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        absPath = scheme + "://" + host + (port != 80 ? ":" + port : "");
        return absPath;
    }

    /**
     * 上传附件
     *
     * @param file
     * @param attachPath
     * @return
     */
    public static Attach uploadAttach(MultipartFile file, String webRoot, String attachPath, String userId) {

        if (file != null && !file.isEmpty() && file.getSize() > 0) {
            webRoot = webRoot.substring(0, webRoot.lastIndexOf(File.separator)) + File.separator + attachPath + File.separator;
            File f = new File(webRoot);
            if (!f.exists()) {
                f.mkdirs();
            }

            FileOutputStream fos = null;
            BufferedInputStream bis = null;
            String oldFileName = file.getOriginalFilename();
            //文件扩展名
            String kzm = oldFileName.substring(oldFileName.lastIndexOf("."));
            //服务器存储文件名
            String newFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".")) + "_" + getDateOf16() + kzm;
            String newFilePath = getRemotePathWithoutContext() + attachPath + "/" + newFileName;

            Attach attach = null;
            try {

                fos = new FileOutputStream(webRoot + newFileName);
                bis = new BufferedInputStream(file.getInputStream());
                int c;
                byte buffer[] = new byte[1024];
                while ((c = bis.read(buffer)) != -1) {
                    for (int i = 0; i < c; i++)
                        fos.write(buffer[i]);
                }

//		        AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
                attach = new Attach();
                attach.setAttachName(newFileName);
                attach.setAttachTruename(oldFileName);
                attach.setAttachPath(attachPath);
                attach.setAttachSize(file.getSize() / 1024.00);
                attach.setAttachUser("");
                attach.setBak1(newFilePath);
//				newattach = attachMgr.save(attach);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return attach;
        }
        return null;
    }

    /**
     * 获取16为日期(yyyyMMddHHmmssSS)
     */
    public static String getDateOf16() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String id = sdf.format(new Date());
        return id;
    }

    /**
     * 生成验证码
     *
     * @param length
     * @return
     */
    public static String getCode(int length) {
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
