package com.example.demo;

import org.jodconverter.DocumentConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DocumentConverter converter;


    @Test
    public void testPdf() {
        try {
            converter.convert(new File("d:\\test\\docx.docx")).
                    to(new File("d:\\test\\pdf\\test.pdf")).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
