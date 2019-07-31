package com.ssm.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

public class ExcelUtils {
	//写
	
	public void test1() throws IOException {
		// 创建工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      // 创建工作表
	      HSSFSheet sheet = workbook.createSheet("sheet1");

	      for (int row = 0; row < 10; row++)
	      {
	         HSSFRow rows = sheet.createRow(row);
	         for (int col = 0; col < 10; col++)
	         {
	            // 向工作表中添加数据
	            
	            if(row==8&&col==8) {
	            	rows.createCell(col).setCellValue("data" + row + col+"${year}");
	            }
	            else
	            {
	            	rows.createCell(col).setCellValue("data" + row + col);
	            }
	         }
	      }

	      File xlsFile = new File("poi.xls");
	      FileOutputStream xlsStream = new FileOutputStream(xlsFile);
	      workbook.write(xlsStream);
	   }
	//读

	public void test2() throws IOException {
		 
		
		  File xlsFile = new File("poi.xls");
	      // 获得工作簿
	      Workbook workbook = WorkbookFactory.create(xlsFile);
	      // 获得工作表个数
	      int sheetCount = workbook.getNumberOfSheets();
	      // 遍历工作表
	      for (int i = 0; i < sheetCount; i++)
	      {
	    
	         Sheet sheet =workbook.getSheetAt(i);
	         // 获得行数
	         int rows = sheet.getLastRowNum() + 1;
	         // 获得列数，先获得一行，在得到改行列数
	         Row tmp = sheet.getRow(0);
	         if (tmp == null)
	         {
	            continue;
	         }
	         int cols = tmp.getPhysicalNumberOfCells();
	         // 读取数据
	         for (int row = 0; row < rows; row++)
	         {
	            Row r = sheet.getRow(row);
	            for (int col = 0; col < cols; col++)
	            {
	               System.out.printf("%10s", r.getCell(col).getStringCellValue());
	            }
	            System.out.println();
	         }
	      }
	   }
	
	  //替换内容
		@Test
		public void test3() throws IOException {
			
			  test1() ;
			  File xlsFile = new File("poi.xls");
		      // 获得工作簿
		      Workbook workbook = WorkbookFactory.create(xlsFile);
		      // 获得工作表个数
		      int sheetCount = workbook.getNumberOfSheets();
		      // 遍历工作表
		      if(sheetCount==0)
		      {
		    	  return ;
		      }
		      
		      Sheet sheet =workbook.getSheetAt(0);
		         // 获得行数
		      int rows = sheet.getLastRowNum() + 1;
		         // 获得列数，先获得一行，在得到改行列数
		      Row tmp = sheet.getRow(0);
		      if (tmp == null)
		       {
		            return;
		       }
		       int cols = tmp.getPhysicalNumberOfCells();
		         // 读取数据
		       for (int row = 0; row < rows; row++)
		        {
		            Row r = sheet.getRow(row);
		            for (int col = 0; col < cols; col++)
		            {
		            	String str=r.getCell(col).getStringCellValue();
		                 System.out.printf("%10s", str);
		                 //如果有包含内容就替换
		                 if(str.contains("${year}")) {
		                	 String regex = "\\$\\{([^\\}]+)\\}"; 
		                	 Pattern p = Pattern.compile(regex); 
		                	 Matcher m = p.matcher(str);   
		            		 String g;
		            		 while (m.find()) { 
		         			    //返回由以前匹配操作所匹配的输入子序列。
		         		        g = m.group(1);
		         		        System.out.println("g=="+g);
		         		       Calendar cal = Calendar.getInstance();
		         		       String replacestr= new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
		         		       System.out.println("replacestr=="+replacestr);
		         		        str = m.replaceFirst(replacestr);     
		         		        m = p.matcher(str);     
		         		    }     
		            		r.getCell(col).setCellValue(str); 
		                 }
		            }
		            System.out.println();
		         }
		       FileOutputStream fileOut = new FileOutputStream("new.xlsx");  
		       
		       workbook.write(fileOut);  
	           fileOut.close();  
	          
		   }

}
