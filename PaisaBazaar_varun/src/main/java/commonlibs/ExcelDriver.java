
package commonlibs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	private InputStream oFileReader;
	private OutputStream oFileWriter;
	private Workbook oExcelWorkbook;
	private String sExcelFileName;

	public ExcelDriver() {
		this.setNull();
	}

	public void createNewExcelWorkbook(String sFileName) {
		try {

			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if ((new File(sFileName)).exists()) {
				throw new Exception("File already Exists");
			}

			if (sFileName.toLowerCase().endsWith(".xlsx")) {
				oExcelWorkbook = new XSSFWorkbook();

			} else if (sFileName.toLowerCase().endsWith(".xls")) {
				oExcelWorkbook = new HSSFWorkbook();

			} else {
				throw new Exception("Invalid File Extension...");
			}

			oFileWriter = new FileOutputStream(sFileName);
			oExcelWorkbook.write(oFileWriter);
			oFileWriter.close();
//			 oExcelWorkbook.close();
			((FileOutputStream) oExcelWorkbook).close();
			this.setNull();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setNull() {
		oFileReader = null;
		oFileWriter = null;
		oExcelWorkbook = null;
		sExcelFileName = "";
	}

	// --------------------------------------------------------------------------------------

	public void openExcelWorkbook(String sFileName) {
		try {

			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if (!(new File(sFileName)).exists()) {
				throw new Exception("File doesn't Exist");
			}

			oFileReader = new FileInputStream(sFileName);
			sExcelFileName = sFileName;
			oExcelWorkbook = WorkbookFactory.create(oFileReader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------------

	public void save() {
		try {
			oFileWriter = new FileOutputStream(sExcelFileName);
			oExcelWorkbook.write(oFileWriter);
			oFileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------------

	public void saveAs(String sFileName) {
		try {
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if ((new File(sFileName)).exists()) {
				throw new Exception("File already Exists");
			}

			oFileWriter = new FileOutputStream(sFileName);
			oExcelWorkbook.write(oFileWriter);
			oFileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------

	public void close(String sFileName) {
		try {
//			 oExcelWorkbook.close();
//			((FileOutputStream) oExcelWorkbook).close();
			oFileWriter = new FileOutputStream(sFileName);
			oExcelWorkbook.write(oFileWriter);
			oFileWriter.close();
			oFileReader.close();
			setNull();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// --------------------------------------------------------------------

	public void createSheet(String sSheetName) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}

			Sheet oSheet;

			oSheet = oExcelWorkbook.getSheet(sSheetName);
			if (oSheet != null) {
				throw new Exception("Sheet already Exist");
			}
			oExcelWorkbook.createSheet(sSheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// --------------------------------------------------------------------

	public int getRowCountOfSheet(String sSheetName) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}

			Sheet oSheet;

			oSheet = oExcelWorkbook.getSheet(sSheetName);
			if (oSheet == null) {
				throw new Exception("Sheet doesnot Exist");
			}

			return oSheet.getLastRowNum() + 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	// ------------------------------------------------------------------------

	
	public int getCellCount(String sSheetName, int iRow, String sFileName) {
		try {
			oFileReader = new FileInputStream(sFileName);
			sExcelFileName = sFileName;
			oExcelWorkbook = WorkbookFactory.create(oFileReader);
//			FileInputStream oFileReader = new FileInputStream(sFileName);
//			oExcelWorkbook = new HSSFWorkbook(oFileReader);
			Sheet oSheet;
			oSheet = oExcelWorkbook.getSheet(sSheetName);
//          Sheet sheet = oExcelWorkbook.getSheet(sSheetName);
//			FileInputStream InputStream = new FileInputStream(sFileName);
//			oExcelWorkbook.openExcelWork
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");
			}

//			Sheet oSheet;
//			oSheet = oExcelWorkbook.getSheet(sSheetName);
			if (oSheet == null) {
				throw new Exception("Sheet doesnot Exist");
			}

			if (iRow < 1) {
				throw new Exception("ROw Index start from 1");
			}

			Row oRow;

			oRow = oSheet.getRow(iRow - 1);
			if (oRow == null) {
				return 0;
			} else {
				return oRow.getLastCellNum();	
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}	
	}

	
	// ---------------------------------------------------------------------

	public String getCellCData(String sSheetName, int iRow, int iCell) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}

			Sheet oSheet;

			oSheet = oExcelWorkbook.getSheet(sSheetName);
			if (oSheet == null) {
				throw new Exception("Sheet doesnot Exist");
			}

			if (iRow < 1 || iCell < 1) {
				throw new Exception("Row & Cell Index start from 1");
			}

			Row oRow;

			oRow = oSheet.getRow(iRow - 1);

			if (oRow == null) {
				return "";
			}

			Cell oCell;
			oCell = oRow.getCell(iCell - 1);
			if (oCell == null) {
				return "";
			} else {

				if (oCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					oCell.setCellType(Cell.CELL_TYPE_STRING);
					return oCell.getStringCellValue();
					// return String.valueOf((int)oCell.getNumericCellValue());
				} else {

					return oCell.getStringCellValue();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	// ---------------------------------------------------------------------------------------------

	public int RowCount(String sFileName, String sSheetName) {
		try {
			Sheet oSheet;
			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if (!(new File(sFileName)).exists()) {
				throw new Exception("File doesnot Exists");
			}

			oFileReader = new FileInputStream(sFileName);
			sExcelFileName = sFileName;
			oExcelWorkbook = WorkbookFactory.create(oFileReader);
			oSheet = oExcelWorkbook.getSheet(sSheetName);
			return oSheet.getLastRowNum();

		} catch (Exception e) {

			e.printStackTrace();
			return -1;
		}
	}

	// ------------------------------------------------------------------------------------------------------

	public void setCellCData(String sSheetName, int iRow, int iCell, String sValue, String sFileName) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}

			Sheet oSheet;

			oSheet = oExcelWorkbook.getSheet(sSheetName);
			try{
//				if(oSheet instanceof Sheet){
//				}
			if (oSheet == null) {
				throw new Exception("Sheet doesnot Exist");
				
			}
			}
			catch(Exception e){
				e.getMessage();
			}
			if (iRow < 1 || iCell < 1) {
				throw new Exception("Row & Cell Index start from 1");
			}

			Row oRow;

			oRow = oSheet.getRow(iRow - 1);

			if (oRow == null) {
				oSheet.createRow(iRow - 1);
				oRow = oSheet.getRow(iRow - 1);
			}

			Cell oCell;
			oCell = oRow.getCell(iCell - 1);
			if (oCell == null) {
				oRow.createCell(iCell - 1);
				oCell = oRow.getCell(iCell - 1);
			}

			oCell.setCellValue(sValue);
			FileOutputStream outputStream = new FileOutputStream(sFileName);
			oExcelWorkbook.write(outputStream);
		} catch (Exception e) {
			//e.printStackTrace();
			e.getMessage();

		}

	}

	// -----------------------------------------------------------------------------------------------------------------------------------

	public HashMap<String, HashMap<String, String>> ReadTestCaseData(
			String sFileName, String sSheetName) {
		HashMap<String, HashMap<String, String>> myArray = new HashMap<String, HashMap<String, String>>();
		try {
			Sheet oSheet;
			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if (!(new File(sFileName)).exists()) {
				throw new Exception("File doesnot Exists");
			}

			oFileReader = new FileInputStream(sFileName);
			sExcelFileName = sFileName;
			oExcelWorkbook = WorkbookFactory.create(oFileReader);

			// System.out.println("Start Reading");

			oSheet = oExcelWorkbook.getSheet(sSheetName);

			int colCount = oSheet.getRow(0).getLastCellNum();
			int rowCount = oSheet.getLastRowNum() + 1;

			String tsd = this.getCellCData(sSheetName, 1, 1).trim();
			String tsdName = "TestCaseID";
			if (!tsdName.equals(tsd)) {
				throw new Exception("Test Case Id Column Not Found."
						+ this.getCellCData(sSheetName, 1, 1));
			}

			// First row - column heading
			String[] columnNames = new String[colCount];
			columnNames[0] = "TestCaseID";
			for (int k = 2; k <= colCount; k++) {
				String headerValue = this.getCellCData(sSheetName, 1, k).trim();
				columnNames[k - 1] = headerValue;
			}

			// match reqColumnNames in columnNames if all columns or present
			// then proceed further otherwise through error
			// that required column are missing.

			for (int j = 2; j <= rowCount; j++) {
				String testCaseId = this.getCellCData(sSheetName, j, 1).trim();// column[TestCaseColumnName]
				if (!myArray.containsKey(testCaseId)) {
					myArray.put(testCaseId, new HashMap<String, String>());
				}
				for (int i = 2; i <= colCount; i++) {
					String cellValue = this.getCellCData(sSheetName, j, i)
							.trim();
					myArray.get(testCaseId).put(columnNames[i - 1], cellValue);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return myArray;
	}

	//----------------------------------------------------------------------------------------------------
	
	public HashMap<String, HashMap<String, String>> SetTestResult(
			String sFileName, String sSheetName) {
		HashMap<String, HashMap<String, String>> myArray = new HashMap<String, HashMap<String, String>>();
		try {
			Sheet oSheet;
			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if (!(new File(sFileName)).exists()) {
				throw new Exception("File doesnot Exists");
			}

			oFileReader = new FileInputStream(sFileName);
			sExcelFileName = sFileName;
			oExcelWorkbook = WorkbookFactory.create(oFileReader);

			// System.out.println("Start Reading");

			oSheet = oExcelWorkbook.getSheet(sSheetName);

			int colCount = oSheet.getRow(0).getLastCellNum();
			int rowCount = oSheet.getLastRowNum() + 1;

			String tsd = this.getCellCData(sSheetName, 1, 1).trim();
			String tsdName = "TestCaseID";
			if (!tsdName.equals(tsd)) {
				throw new Exception("Test Case Id Column Not Found."
						+ this.getCellCData(sSheetName, 1, 1));
			}

			// First row - column heading
			String[] columnNames = new String[colCount];
			columnNames[0] = "TestCaseID";
			for (int k = 2; k <= colCount; k++) {
				String headerValue = this.getCellCData(sSheetName, 1, k).trim();
				columnNames[k - 1] = headerValue;
			}

			// match reqColumnNames in columnNames if all columns or present
			// then proceed further otherwise through error
			// that required column are missing.

			for (int j = 2; j <= rowCount; j++) {
				String testCaseId = this.getCellCData(sSheetName, j, 1).trim();// column[TestCaseColumnName]
				if (!myArray.containsKey(testCaseId)) {
					myArray.put(testCaseId, new HashMap<String, String>());
				}
				for (int i = 2; i <= colCount; i++) {
					String cellValue = this.getCellCData(sSheetName, j, i)
							.trim();
					myArray.get(testCaseId).put(columnNames[i - 1], cellValue);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return myArray;
	}
	
	//-----------------------------------------------------------------------------------------------------
	
	public HashMap<String, HashMap<String, String>> SetTestCaseData(
			String sFileName, String sSheetName) {
		HashMap<String, HashMap<String, String>> myArray = new HashMap<String, HashMap<String, String>>();
		try {
			Sheet oSheet;
			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if (!(new File(sFileName)).exists()) {
				throw new Exception("File doesnot Exists");
			}

			oFileReader = new FileInputStream(sFileName);
			sExcelFileName = sFileName;
			oExcelWorkbook = WorkbookFactory.create(oFileReader);

			// System.out.println("Start Reading");

			oSheet = oExcelWorkbook.getSheet(sSheetName);

			int colCount = oSheet.getRow(0).getLastCellNum();
			int rowCount = oSheet.getLastRowNum() + 1;

			String tsd = this.getCellCData(sSheetName, 1, 1).trim();
			String tsdName = "TestCaseID";
			if (!tsdName.equals(tsd)) {
				throw new Exception("Test Case Id Column Not Found."
						+ this.getCellCData(sSheetName, 1, 1));
			}

			
			// First row - column heading
			String[] columnNames = new String[colCount];
			columnNames[0] = "TestCaseID";
			for (int k = 2; k <= colCount; k++) {
				this.setCellCData("sSheetName", 1, k, "chintu", sFileName);
//				columnNames[k - 1] = headerValue;             
			}

			// match reqColumnNames in columnNames if all columns or present
			// then proceed further otherwise through error
			// that required column are missing.

			for (int j = 2; j <= rowCount; j++) {
				String testCaseId = this.getCellCData(sSheetName, j, 1).trim();// column[TestCaseColumnName]
				if (!myArray.containsKey(testCaseId)) {
					myArray.put(testCaseId, new HashMap<String, String>());
				}
				for (int i = 2; i <= colCount; i++) {
					String cellValue = this.getCellCData(sSheetName, j, i)
							.trim();
					myArray.get(testCaseId).put(columnNames[i - 1], cellValue);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return myArray;
	}
	
	// ----------------------------------------------------------------------------------------------------

	public HashMap<String, HashMap<String, String>> ReadLoginCredentials(
			String sFileName, String sSheetName) {
		HashMap<String, HashMap<String, String>> myArray = new HashMap<String, HashMap<String, String>>();
		try {

			Sheet oSheet;
			sSheetName = sSheetName.trim();
			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if (!(new File(sFileName)).exists()) {
				throw new Exception("File doesnot Exists");
			}
			

			oFileReader = new FileInputStream(sFileName);
			sExcelFileName = sFileName;
			oExcelWorkbook = WorkbookFactory.create(oFileReader);

			// System.out.println("Start Reading");

			oSheet = oExcelWorkbook.getSheet(sSheetName);

			int colCount = oSheet.getRow(0).getLastCellNum();
			int rowCount = oSheet.getLastRowNum() + 1;

			String tsd = this.getCellCData(sSheetName, 1, 1).trim();
			String tsdName = "Id";
			if (!tsd.equals(tsdName)) {
				throw new Exception("Id Column Not Found."
						+ this.getCellCData(sSheetName, 1, 1));
			}

			// String [] columnNames = new String[]{"browser", "url",
			// "username", "password"};
			// First row - column heading
			String[] columnNames = new String[colCount];
			columnNames[0] = "Id";
			for (int k = 2; k <= colCount; k++) {
				String headerValue = this.getCellCData(sSheetName, 1, k).trim();
				columnNames[k - 1] = headerValue;
			}

			for (int j = 2; j <= rowCount; j++) {
				String testCaseId = this.getCellCData(sSheetName, j, 1).trim();
				// check testcaseid is present in array
				// myArray.containsKey(testCaseId) will return true if
				// testcaseid found otherwise false.
				if (!myArray.containsKey(testCaseId)) {
					myArray.put(testCaseId, new HashMap<String, String>());
				}
				for (int i = 2; i <= colCount; i++) {
					String cellValue = this.getCellCData(sSheetName, j, i)
							.trim();
					myArray.get(testCaseId).put(columnNames[i - 1], cellValue);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return myArray;
	}

}
