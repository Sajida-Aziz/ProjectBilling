package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;
public class ExcelRead {
	

	 XSSFSheet sh;
	public static int row;
	 public static int column;
	 public ExcelRead(String sheet) throws IOException
	 {
		 String excelfilePath="C:\\Users\\Sajida\\eclipse-workspace\\ProjectBilling\\src\\main\\resources\\loginData.xlsx";
			
			FileInputStream inputstream=new FileInputStream(excelfilePath);
			
			
		    XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
		    sh=workbook.getSheet(sheet);
		    row=sh.getLastRowNum();
		    column=sh.getRow(1).getLastCellNum();
		}
	 public String ReadData(int row,int column)
	 {
		 XSSFRow r= sh.getRow(row);
		
		 XSSFCell cell=r.getCell(column); 
		 int cellType=cell.getCellType();
		 
		 switch(cellType)
         {
         case 1:
         {
         	return cell.getStringCellValue();
         }	
		case 0:
		{
         int val=(int) cell.getNumericCellValue();
         return String.valueOf(val);
		}	
         	
         default:
             return " ";
         }
		}
	 
	 

}
