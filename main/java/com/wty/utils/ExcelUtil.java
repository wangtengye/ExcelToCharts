package com.wty.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 2017/8/4.
 */
public class ExcelUtil {
    static MultipartFile file;
    static String type;
    static String id;

    public static void storeFile(MultipartFile file, String type, String id) {
        ExcelUtil.file = file;
        ExcelUtil.type = type;
        ExcelUtil.id = id;
    }

    public static Workbook getWorkbook() {
        try {
            if (file != null)
                return WorkbookFactory.create(file.getInputStream());
            return WorkbookFactory.create(ExcelUtil.getInputStream(type + id + ".xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Workbook getWorkbook(String fileName) {
        try {
            return WorkbookFactory.create(ExcelUtil.getInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getInputStream(String fileName) {

        try {
            return FileUtils.openInputStream(new File(ExcelUtil.class.getClassLoader().getResource("excel/").getPath() + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
