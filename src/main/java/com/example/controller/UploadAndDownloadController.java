package com.example.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class UploadAndDownloadController {
    @RequestMapping(value = "/uploadAndDownload")
    public String uploadAndDownload() {
        return "uploadAndDownload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam CommonsMultipartFile file) throws IOException {
        File file0bj = new File("test123.html");
        //这里文件的上传到哪里的路径，这里是相对路径在tomcat的bin目录下,
        // D:\ieDownload\apache-tomcat-8.5.73\bin\test123.html,
        // 也可以改成任意绝对路径

        file.transferTo(file0bj);
        System.out.println("用户上传的文件已保存到: " + file0bj.getAbsolutePath());
        return "文件上传成功!";
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    @ResponseBody
    public void download(HttpServletResponse response) {//下载文件
        response.setContentType("multipart/form-data");
        try (OutputStream stream = response.getOutputStream();
             InputStream inputStream = new FileInputStream("test123.html")) {
            //这是要文件的下载的路径，这里是相对路径在tomcat的bin目录下,
            // D:\ieDownload\apache-tomcat-8.5.73\bin\test123.html,
            // 也可以改成任意绝对路径
            IOUtils.copy(inputStream,stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
