package com.leoman.utils;

import com.leoman.common.core.Configue;
import com.leoman.image.entity.FileBo;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by wangbin on 2014/12/6.
 */
public class FileUtil {

    public static String getFileExt(String fileName){

        int index=fileName.lastIndexOf('.');
        if(index==-1){
            return "";
        }
        String ext=fileName.substring(index);
        return ext;
    }


    public static boolean isImage(String fileName){
        return fileName.matches("(?i).+?\\.(jpg|gif|bmp|jpeg|png)");
    }




    public static String  readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));

            StringBuffer sb = new StringBuffer();

            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r'&&((char) tempchar) != '\n') {
                    sb.append(String.valueOf((char) tempchar));
                }
            }
            reader.close();
            return sb.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }

    public static FileBo  save(MultipartFile file, String fileName) throws IOException{
        FileBo fileBo = new FileBo();
        InputStream inputStream = file.getInputStream();
        String origFileName = file.getOriginalFilename(); //原始名称,如aa.jpg
        String ext =  FileUtil.getFileExt(origFileName); //后缀，如jpg
        String uploadPath = UploadUtil.getImagesUpladPath(); //生成日期目录 image/2014/7/21/
        String foreName = uploadPath+fileName;   //文件名称 image/2014/7/21/221144144554
        String desFilePathName = uploadPath+fileName+ext;//完整文件名称 image/2014/7/21/221144144554.jpg
        File theFile = new File(Configue.getUploadPath(),desFilePathName); //生成的文件对象
        fileBo.setName(fileName);
        fileBo.setForeName(foreName);
        fileBo.setExt(ext);
        fileBo.setPath(desFilePathName);
        fileBo.setFile(theFile);
        FileUtils.copyInputStreamToFile(inputStream,theFile);
        return fileBo;
    }



    public static FileBo save(MultipartFile file) throws IOException{
        return save(file,String.valueOf(System.currentTimeMillis()));
    }

    /**
     * 文件压缩
     */
    public static void zip(String srcPath,String finalPath) {
        File file = new File(finalPath);

        File srcFile = new File(srcPath);
        if(!srcFile.exists()) {
            throw new RuntimeException(srcPath + "不存在");
        }

        Project project = new Project();
        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(file);

        FileSet fileSet = new FileSet();
        fileSet.setProject(project);
        fileSet.setDir(srcFile);

        zip.addFileset(fileSet);
        zip.execute();
    }

    public static void main(String[] args) {
        FileUtil.zip("E:\\project\\leoman\\middleware\\velocityweb\\velocityweb\\src\\main\\java\\com\\leoman\\utils\\gson","E:\\gson.zip");
    }
}
