package business_actions;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sourceforge.htmlunit.corejs.javascript.GeneratedClassLoader;
import utility.Constant_Class;

public class ExcelAction {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	public static String crtnls[];

	public static void setExcelfile() throws Exception {
		try {

			FileInputStream Excelfile = new FileInputStream(Constant_Class.Path_TestData);
			ExcelWBook = new XSSFWorkbook(Excelfile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");
			System.out.println("Excel file is opened");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("Excel file is not opened");
		}
	}

	public static String getCellData(int Row, int column) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(Row).getCell(column);
			String CellData = Cell.getStringCellValue();
			return CellData;

		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}

	}

	public static String[] getCredentials() {

		crtnls = new String[100];
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		int val1 = stElements.length;
		int rowcount = ExcelWSheet.getLastRowNum();
		for (int j = 0; j < val1; j++) {
			StackTraceElement main1 = stElements[j];
			String mainClass1 = main1.getClassName();
			String simpleClassName1 = mainClass1.substring(mainClass1.lastIndexOf('.') + 1);

			for (int i = 0; i <= rowcount; i++) {

				Row row = ExcelWSheet.getRow(i);

				for (int m = 0; m < 1; m++) {
					String cell_val = row.getCell(m).getStringCellValue();
					if (cell_val.equalsIgnoreCase(simpleClassName1)) {

						for (int k = 0; k <= row.getLastCellNum() - 1; k++) {
							String val = row.getCell(k).getStringCellValue().toString();
							crtnls[k] = val;

						}
						break;
					}

				}

			}

		}

		/*
		 * StackTraceElement main1 = stElements[stElements.length - 1]; // it
		 * will wrk when run the script individually(manual) StackTraceElement
		 * main2 = stElements[3]; // it will work when run the script by
		 * batchrunner
		 * 
		 * String mainClass1 = main1.getClassName(); String mainClass2 =
		 * main2.getClassName();
		 * 
		 * String simpleClassName1 =
		 * mainClass1.substring(mainClass1.lastIndexOf('.') + 1); String
		 * simpleClassName2 = mainClass2.substring(mainClass2.lastIndexOf('.') +
		 * 1); System.out.println(simpleClassName1);
		 */

		return crtnls;
	}

}
