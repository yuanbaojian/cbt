package com.ybj.cbt.controller;

import org.apache.http.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author ResponseController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Controller
@RequestMapping("/response")
public class ResponseController {

    @RequestMapping(value = "/httpRequest", method = RequestMethod.POST)
    public void test(HttpServletRequest request , HttpServletResponse response) throws IOException {
        System.out.println("request = " + request);
        request.getSession().setAttribute("param1", "firstParameter");

        String filePath="C:\\Users\\yuanbaojian\\Desktop\\desc\\budejie\\hot\\1.jpg";
        File file=new File(filePath);
        InputStream in=new FileInputStream(file);
//        BufferedInputStream in = new BufferedInputStream(inputStream);
        response.reset();
        response.setContentType("image/jpg; charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=\"" + URLEncoder.encode("test", "UTF-8").replaceAll("\\+", "%20") + "\"");
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();

    }

}
