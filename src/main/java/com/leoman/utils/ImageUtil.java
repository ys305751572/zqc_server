package com.leoman.utils;

import com.leoman.common.core.Configue;
import com.leoman.image.entity.Image;
import net.coobird.thumbnailator.Thumbnails;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangbin on 2014/12/30.
 */
public class ImageUtil {


    public static void compactImage(File srcFile, String destDir, String destImage) throws IOException {
        Thumbnails.of(srcFile).scale(1f).outputQuality(0.5).toFile(new File(destDir, destImage));
    }

    public static void thumbImage(File srcFile, String destDir, String destImage, int width, int height) throws IOException {
        BufferedImage srcImage = ImageIO.read(srcFile);
        Thumbnails.of(srcImage)
                .size(width, height)
                .outputQuality(1)
                .keepAspectRatio(false)
                .toFile(new File(destDir, destImage));
    }


    public static void changeImgPath(Image image) {
        if (image == null) {
            return;
        }
        String path = image.getPath();
        if (path.contains(Configue.getUploadUrl())) {
            return;
        }
        image.setPath(Configue.getUploadUrl() + image.getPath());
    }

    public static Map<String, Long> getImgInfo(String imgPath) {
        File file = new File(imgPath);
        return getImgInfo(file);
    }


    public static Map<String, Long> getImgInfo(File imgFile) {
        Map<String, Long> map = new HashMap<String, Long>(3);
        try {
            FileInputStream fis = new FileInputStream(imgFile);
            BufferedImage buff = ImageIO.read(imgFile);
            map.put("width", buff.getWidth() * 1L);
            map.put("height", buff.getHeight() * 1L);
            map.put("size", imgFile.length());
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("所给的图片文件" + imgFile.getPath() + "不存在！计算图片尺寸大小信息失败！");
            map = null;
        } catch (IOException e) {
            System.err.println("计算图片" + imgFile.getPath() + "尺寸大小信息失败！");
            map = null;
        }
        return map;
    }

    /**
     * @param fileIn     要被copy的文件
     * @param fileOutPut 将文件copy到那个目录下面
     * @throws Exception
     */
    public static void copyFile(File fileIn, File fileOutPut) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(fileIn);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOutPut);
        byte[] by = new byte[1024];
        int len;
        while ((len = fileInputStream.read(by)) != -1) {
            fileOutputStream.write(by, 0, len);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static File fileIsExist(String proId,String salemanId) {
        String path = ConfigUtil.getString("upload.path") + KdxgUtils.getFileName(proId, salemanId);
        File file = new File(path);
        if(file.exists()) {
            return file;
        }
        return null;
    }
}
