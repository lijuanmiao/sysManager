package com.cn.sysManager.toolbox.excel;

import com.cn.sysManager.toolbox.DateUtil;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * excel工具类
 * Created by lijm on 2018-10-17.
 */
public class ExcelUtil {

    private static final String DEFAULT_SHEET_NAME = "sheet";

    public static String[][] readExcel(File file,int ignoreRows)throws Exception{

        int rowSize = 0;
        List<String[]> result = new ArrayList<String[]>();//定义返回的结果集
        InputStream inputStream = new FileInputStream(file);
        String fileName = file.getName();
        Workbook workbook = null;
        try {

            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            }else if(fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    int tempRowSize = row.getLastCellNum();
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }

                    String[] values = new String[rowSize];
                    Arrays.fill(values, "");
                    boolean hasValue = false;
                    for (short columnIndex = 0; columnIndex <= row.getLastCellNum() - 1; columnIndex++) {
                        String value = "";
                        Cell cell = row.getCell(columnIndex);
                        if (cell != null) {

                            switch (cell.getCellType()) {
                                //数值类型
                                case Cell.CELL_TYPE_NUMERIC:
                                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                                        //date类型，获取该cell的date值
                                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        value = format.format(date);
                                    }else{
                                        //纯数字问题解决
                                        BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                                        value = big.toEngineeringString();
                                        //解决1234.0 去掉.0
                                        if(null!=value&&!"".equals(value.trim())){
                                            String[] item = value.split("[.]");
                                            if(1<item.length&&"0".equals(item[1])){
                                                value = item[0];
                                            }
                                        }
                                    }
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue().toString().trim();
                                    break;
                                //公式类型
                                case Cell.CELL_TYPE_FORMULA:
                                    value = String.valueOf(cell.getNumericCellValue());
                                    if(value.equals("NaN")){
                                        //如果获取的数据值为非法值，则转换成字符串
                                        value = cell.getStringCellValue().toString();
                                    }
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    value = " "+cell.getBooleanCellValue();
                                    break;
                                case Cell.CELL_TYPE_BLANK://空值
                                    value = "";
                                    break;
                                case Cell.CELL_TYPE_ERROR://出现故障
                                    value = "";
                                    break;
                                default:
                                    value = cell.getStringCellValue().toString();
                                    break;
                            }
                        }
                        if (columnIndex == 0 && value.trim().equals("")) {
                            break;
                        }
                        values[columnIndex] = rightTrim(value.trim());
                        hasValue = true;
                    }
                    if (hasValue) {
                        result.add(values);
                    }
                }
            }
            inputStream.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (workbook != null) {
                workbook = null;
            }
        }
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;

    }

    /**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

    /**
     * excel 导出
     * @param destOutputStream
     * @param templateInputStream
     * @param data
     * @param dataKey
     * @param maxRowPerSheet
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void generateExcelByTemplate(OutputStream destOutputStream,
                                               InputStream templateInputStream,
                                               List data, String dataKey,
                                               int maxRowPerSheet) throws Exception {
        generateExcelByTemplate(destOutputStream,
                templateInputStream,
                null, null,
                data, dataKey,
                maxRowPerSheet);
    }

    @SuppressWarnings("rawtypes")
    public static void generateExcelByTemplate(OutputStream destOutputStream,
                                               InputStream templateInputStream,
                                               List header, String headerKey,
                                               List data, String dataKey,
                                               int maxRowPerSheet) throws Exception {

        List<List> splitData = null;
        @SuppressWarnings("unchecked")
        Map<String, List> beanMap = new HashMap();
        List<String> sheetNames = new ArrayList<String>();

        if (data!=null&&data.size() > maxRowPerSheet) {
            splitData = splitList(data, maxRowPerSheet);
            sheetNames = new ArrayList<String>(splitData.size());
            for (int i = 0; i < splitData.size(); ++i) {
                sheetNames.add(DEFAULT_SHEET_NAME + i);
            }
        } else {
            splitData = new ArrayList<List>();
            sheetNames.add(DEFAULT_SHEET_NAME + 0);
            splitData.add(data);
        }
        if (null != header) {
            beanMap.put(headerKey, header);
        }

        XLSTransformer transformer = new XLSTransformer();
        Workbook workbook = transformer.transformMultipleSheetsList(
                templateInputStream, splitData, sheetNames, dataKey, beanMap, 0);
        workbook.write(destOutputStream);
    }

    private static List<List> splitList(List data, int maxRowPerSheet) {
        List<List> splitData = new ArrayList<List>();
        List sdata = null;
        for (int i = 0; i < data.size(); ++i) {
            if (0 == i % maxRowPerSheet) {
                if (null != sdata) {
                    splitData.add(sdata);
                }
                sdata = new ArrayList(maxRowPerSheet);
            }
            sdata.add(data.get(i));
        }
        if (0 != maxRowPerSheet % data.size()) {
            splitData.add(sdata);
        }

        return splitData;
    }
    @SuppressWarnings("rawtypes")
    public static void generateExcelByTemplateList(OutputStream destOutputStream,
                                               InputStream templateInputStream,
                                               HttpServletResponse response,
                                               List data, String dataKey,
                                               List data1, String dataKey1,
                                               int maxRowPerSheet) throws Exception {
        generateExcelByTemplate(destOutputStream,
                templateInputStream,response,
                null, null,
                data, dataKey,
                data1, dataKey1,
                maxRowPerSheet);
    }
    @SuppressWarnings("rawtypes")
    public static void generateExcelByTemplate(OutputStream destOutputStream,
                                               InputStream templateInputStream,
                                               HttpServletResponse response,
                                               List header, String headerKey,
                                               List data, String dataKey,
                                               List data1, String dataKey1,
                                               int maxRowPerSheet) throws Exception {
        OutputStream out = null;
        try{
            List<List> splitData = null;
            List<List> splitData1 = null;
            List<List> splitData2 = null;
            @SuppressWarnings("unchecked")
            Map<String, List> beanMap = new HashMap();
            List<String> sheetNames = new ArrayList<String>();
            if (data!=null&&data.size() > maxRowPerSheet
                    || data1!=null&&data1.size() > maxRowPerSheet) {
                splitData = splitList(data, maxRowPerSheet);
                splitData1 = splitList(data1,maxRowPerSheet);
                sheetNames = new ArrayList<String>(splitData.size());
                for (int i = 0; i < splitData.size(); ++i) {
                    sheetNames.add(DateUtil.formatTime(DateUtil.getTime(), "yyyyMMdd") + "_用户信息");
                    sheetNames.add(DateUtil.formatTime(DateUtil.getTime(), "yyyyMMdd") + "_城市编码");
                }
                beanMap.put("cityList", splitData1);
            } else {

                splitData = new ArrayList<List>();
                splitData.add(data);
                splitData.add(data1);
                sheetNames.add(DateUtil.formatTime(DateUtil.getTime(), "yyyyMMdd") + "_用户信息");
                sheetNames.add(DateUtil.formatTime(DateUtil.getTime(), "yyyyMMdd") + "_城市编码");
                beanMap.put("cityList", data1);
            }
            XLSTransformer transformer = new XLSTransformer();
            Workbook workbook = transformer.transformMultipleSheetsList(
                    templateInputStream, splitData, sheetNames, dataKey, beanMap, 0);
           // workbook.removeSheetAt(workbook.getSheetIndex(DateUtil.formatTime(DateUtil.getTime(), "yyyyMMdd") + "_SY8管理费账户"));
            workbook.write(destOutputStream);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            try {
                if (out != null)
                    out.close();
                out = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*public static  void  main(String[] args){
        try {

            File file = new File("F:/user.xlsx");
            String[][] result = readExcel(file, 1);

            int rowLength = result.length;
            for(int i=0;i<rowLength;i++) {
                for(int j=0;j<result[i].length;j++) {
                    System.out.print(result[i][j]+"\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
