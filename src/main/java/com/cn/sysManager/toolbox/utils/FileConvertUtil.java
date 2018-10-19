package com.cn.sysManager.toolbox.utils;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * Created by lijm on 2018-03-02.
 */
public class FileConvertUtil {

    final static Logger logger = LoggerFactory.getLogger(FileConvertUtil.class);


    /**
     * 根据PDF模板生成PDF文件
     * @param sourceFile
     * @param fileName
     * @param targetFilePath
     * @param fieldMap
     * @throws Exception
     */
    public static void toPDFWriteValue(String sourceFile,String fileName,String targetFilePath,Map<String,Object> fieldMap){
        try {
            PdfReader reader = new PdfReader(sourceFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper ps = new PdfStamper(reader, bos);
            AcroFields s = ps.getAcroFields();
            //解决中文

            //BaseFont bfChinese = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            com.itextpdf.text.pdf.BaseFont bfChinese = com.itextpdf.text.pdf.BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            s.addSubstitutionFont(bfChinese);
            //判断写入文件是否存在，不存在创建
            File file = new File(targetFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String fileSavePath = targetFilePath + fileName;
            File file1 = new File(fileSavePath);
            if (!file1.exists()) {
                file1.createNewFile();
            }

            //将值封装至map中
            for(String key:fieldMap.keySet()){
                //遍历赋值
                s.setField(key,fieldMap.get(key).toString());
            }
            ps.setFormFlattening(true);
            ps.close();
            FileOutputStream fos = new FileOutputStream(fileSavePath);
            fos.write(bos.toByteArray());
        } catch (FileNotFoundException e) {

            logger.error("生成PDF文件异常", e);
        } catch (Exception e) {
            logger.error("生成PDF文件异常", e);
        }
    }
}
