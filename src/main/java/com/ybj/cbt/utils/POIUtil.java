package com.ybj.cbt.utils;//package com.atoz.cbtsys.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class POIUtil {

    private static Workbook getWorkbook(FileInputStream inputStream, String excelFilePath)
            throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        workbook.close();
        return workbook;
    }

    private static Object getCellValue(Cell cell) {
        if(cell.getCellType()==STRING){

            return cell.getStringCellValue() ;
        }
        else if (HSSFDateUtil.isCellDateFormatted(cell)) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(cell.getDateCellValue());
            return strDate ;
        }
        else {
            return cell.getBooleanCellValue();
        }
    }

    private static List<String[]> readExcel(Workbook workbook) throws IOException {
        List<String[]> excleContent=new ArrayList<>();
        Sheet firstSheet = workbook.getSheetAt(0);
        //获得列数
        int numberOfCells=firstSheet.getRow(0).getPhysicalNumberOfCells();
        String[] excelLineContent=new String[numberOfCells];
        Iterator<Row> iterator = firstSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            int i=0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cell.getCellType()==STRING){
                    excelLineContent[i]= (String) getCellValue(cell);
                }
                i++;
            }
            excleContent.add(excelLineContent);
        }
        return excleContent;

    }

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(new File("C:\\Users\\baojian.yuan\\Desktop\\教学内容批量导入模板.xlsx"));
        List<String[]> xlsx = readExcelByFileInputStream(fileInputStream, "xlsx");
        System.out.println("xlsx = " + xlsx);
    }

    public static List<String[]> readExcelByFileInputStream(FileInputStream fileInputStream, String postFix) throws IOException {
        Workbook workbook = getWorkbook(fileInputStream, postFix);
        List<String[]> excelContent = readExcel(workbook);
        return excelContent;
    }


}
