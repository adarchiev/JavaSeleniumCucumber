package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonMethods {
	private static final String pathDir = new java.io.File("").getAbsolutePath();
	
	public static void writeToFile(String username, String first_name, String last_name)
			throws EncryptedDocumentException, IOException {

		FileInputStream excelFile = new FileInputStream(new File(pathDir));
		Workbook wb = WorkbookFactory.create(excelFile);
		Sheet sheet = wb.getSheetAt(0);
		int rows = sheet.getLastRowNum();

		Row row = sheet.createRow(rows+1);
		Cell cell = row.createCell(0);
		cell.setCellValue(username);
		cell = row.createCell(1);
		cell.setCellValue(first_name);
		cell = row.createCell(2);
		cell.setCellValue(last_name);

		FileOutputStream fileOut = new FileOutputStream(new File(pathDir));
		wb.write(fileOut);
		fileOut.close();
	}
	
	public static Map<String, String> readFromExcel(String property, String Sheet)
			throws FileNotFoundException, IOException {
		Map<String, String> map = new HashedMap<>();
		DataFormatter dataFormatter = new DataFormatter();
		String file = pathDir;

		FileInputStream excelFile = new FileInputStream(new File(file));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet datatypeSheet = workbook.getSheet(Sheet);
		workbook.close();
		for (Row row : datatypeSheet) {
			int cells = row.getLastCellNum();

			Cell cell = row.getCell(0);
			String cellValue = dataFormatter.formatCellValue(cell);
			if (cellValue.trim().equalsIgnoreCase(property.trim())) {
				for (int i = 0; i < cells; i++) {
					cell = row.getCell(i);
					String rowValue = dataFormatter.formatCellValue(datatypeSheet.getRow(0).getCell(i));
					cellValue = dataFormatter.formatCellValue(cell);
					map.put(rowValue.trim(), cellValue.trim());
				}
				return map;
			}
		}

		return map;
	}
}
