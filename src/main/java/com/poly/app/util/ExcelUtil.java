package com.poly.app.util;

import com.poly.app.enity.Users;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;


public class ExcelUtil{
	
    public static void writeExcelUser(String filePath, List<Users> usersList,String[] hearder)  {

    		try {
    			  XSSFWorkbook workbook = new XSSFWorkbook();
    		        XSSFSheet sheet = workbook.createSheet("Danh Sách Tài Khoản");

    		        Row headerRow = sheet.createRow(0);
    		        CellStyle headerStyle = workbook.createCellStyle();
    		        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    		        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    		        for(int i =0;i<hearder.length;i++) {
    		        	Cell cell = headerRow.createCell(i);
    		            cell.setCellValue(hearder[i]);
    		            cell.setCellStyle(headerStyle);
    		        }
    		        int rowNum = 1;
    		        for (Users user : usersList) {
    		            Row row =sheet.createRow(rowNum++);
    		            row.createCell(0).setCellValue(rowNum);
    		            row.createCell(1).setCellValue(user.getId());
    		            row.createCell(2).setCellValue(user.getFullname());
    		            row.createCell(3).setCellValue(AES.decrypt(user.getPassword(), Keyword.keyCode));
    		            row.createCell(4).setCellValue(user.getEmail());
    		            row.createCell(5).setCellValue(user.getGender()?"Nam":"Nữ");
    		            row.createCell(6).setCellValue(user.getBirthday().toString());
    		            row.createCell(7).setCellValue(user.getActive()?"Hoạt Động":"Ngưng Hoạt Động");
    		            row.createCell(8).setCellValue(user.getCreateday().toString());
    		            row.createCell(9).setCellValue(user.getUpdateday().toString());
    		            row.createCell(10).setCellValue(user.getRole()?"Quản Lý":"Người Dùng");
    		           
    		        }
    		        for (int i = 0; i < 11; i++) {
    		        	sheet.autoSizeColumn(i);
    		        }
    		        FileOutputStream outputStream = new FileOutputStream(filePath);
    		        workbook.write(outputStream);
    		        workbook.close();
    		        outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
	
}
