package jayslabs.test.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataUtil {

	
	public static void main(String[] args) throws FileNotFoundException {
		Properties prop = new Properties();

		String fstr = System.getProperty("user.dir") + "\\src\\main\\resources\\seleniumTestData.xlsx";
		FileInputStream fis = new FileInputStream(fstr);
		
		try {
			XSSFWorkbook wrkbk = new XSSFWorkbook(fis);
			XSSFSheet sheet = wrkbk.getSheet("2023");

			//scan entire 1st row and identify to retrieve testcases column
			Iterator<Row> rows = sheet.iterator();
			Row row1 = rows.next();
			int colindex =0;
			Iterator<Cell> celliter = row1.cellIterator();
			Cell cell;
			while (celliter.hasNext()) {
				cell = celliter.next();
				if (cell.getStringCellValue().equalsIgnoreCase("testcases")) {
					colindex = cell.getColumnIndex();
					break;
				}
				colindex++;
			}
			
			//get testcases column and scan for specific tc. e.g purchase
			Row row;
			while (rows.hasNext()) {
				row = rows.next();
				cell = row.getCell(colindex);
				if (cell.getStringCellValue().equalsIgnoreCase("purchase")) {
					Iterator<Cell> cells = row.cellIterator();
					cells.next();
					while (cells.hasNext()) {
						System.out.println(cells.next().getStringCellValue());
					}
				}
			}
			
			//get entire row associated with purchase
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
