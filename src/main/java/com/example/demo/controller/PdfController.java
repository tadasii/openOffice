package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
public class PdfController {
    @Autowired
    private DocumentConverter converter;
    @Autowired
    HttpServletResponse response;


    @Value("${word.dir}")
    private  String wordDir; //文档路径

    /**
     * 生成pdf 文件放在D:/test/ 生成的文件放在 D:/test/pdf
     * @param fileName 文件名
     * @param suffix 文件后缀
     */
    @GetMapping("/genPdf/{fileName}/{suffix}")
    public void genPdf(@PathVariable String fileName,@PathVariable String suffix) {

        try {
            converter.convert(new File(wordDir+fileName+"."+suffix)).
                    to(new File(wordDir+"pdf"+ fileName +".pdf")).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("docx");
    }

    /**
     * 预览pdf 文件放在D:/test/
     * @param fileName 文件名
     * @param suffix 文件后缀
     * @return
     */
    @GetMapping("/show/{fileName}/{suffix}")
    public String toPdfFile(@PathVariable String fileName,@PathVariable String suffix) {
        StopWatch stopWatch = new StopWatch("转换pdf");
        log.info("开始转换pdf");
        stopWatch.start();
        File file = new File(wordDir+fileName+"."+suffix);//需要转换的文件
        String outFilePath = wordDir +"\\pdf\\"+ fileName +".pdf";
        File outFile = new File(outFilePath);
        ServletOutputStream outputStream = null;
        InputStream in = null;
        try {
            //文件转化
            converter.convert(file).to(outFile).execute();
            //使用response,将pdf文件以流的方式发送的前端
            outputStream = response.getOutputStream();
             in = new FileInputStream(outFile);// 读取文件
            // copy文件
            int i = IOUtils.copy(in, outputStream);
            System.out.println(i);
            in.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        stopWatch.stop();
        log.info("结束转换pdf");
        log.info("耗时：{}毫秒",stopWatch.getTotalTimeMillis());
        return "ok";
    }


}
