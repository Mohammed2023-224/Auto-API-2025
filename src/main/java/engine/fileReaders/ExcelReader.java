package engine.fileReaders;

import engine.reporter.CustomLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ExcelReader {
    String path = "src/test/resources/data.xlsx";
    FileInputStream fr;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;
    XSSFWorkbook workbook;

    public ExcelReader() {
    }

    public ExcelReader(String path, String sheetName) {
        readFile(path);
        this.sheet = changeSheet(sheetName);
    }

    public Object[][] readValuesHashMap(String path, String sheetName, String columnName, String condition) {
        readFile(path);
        sheet = changeSheet(sheetName);
        if (sheet == null) {
            return null;
        }
        int testCases = 0;
        int tabIndex = 0;
        row = sheet.getRow(0);
        // find number of rows with correct condition
        for (int i = 0; i < getNumberOfRows(); i++) {
            if (getCellValue(getColumnNumFromColumnName(columnName), i).equalsIgnoreCase(condition)) {
                testCases++;
            }
        }
        if (testCases == 0) {
            CustomLogger.logger.info("No test cases found");
            return null;
        }
        CustomLogger.logger.info("Number of test cases: {}", testCases);
        Object[][] values = new Object[testCases][1];
        for (int i = 0; i < getNumberOfRows(); i++) {
            HashMap<String, String> map = new HashMap<>();
            if (getCellValue(getColumnNumFromColumnName(columnName), i).equalsIgnoreCase(condition)) {
                for (int j = 0; j < getNumberOfColumns(); j++) {
                    CustomLogger.logger.info("read key --> value : {}-->{}", getCellValue(j, 0), getCellValue(j, i));
                    map.put(getCellValue(j, 0), getCellValue(j, i));
                }
                values[tabIndex][0] = map;
                tabIndex++;
            }
        }
        return values;
    }

    public String readExcelValue(String path, String sheetName, String colName, int rowNum) {
        workbook = readFile(path);
        String vv = "";
        sheet = changeSheet(sheetName);
        return getCellValue(colName, rowNum);
    }

    private XSSFSheet changeSheet(String sheetName) {
        CustomLogger.logger.info("Change to sheet: {}", sheetName);
        return workbook.getSheet(sheetName);
    }

    private XSSFWorkbook readFile(String path) {
        try {
            fr = new FileInputStream(path);
            workbook = new XSSFWorkbook(fr);
            CustomLogger.logger.info("Read file: {}", path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return workbook;
    }

    private String getCellValue(XSSFCell cel) {
        String cellValue = "";
        if (cel == null) {
            return "";
        }
        switch (cel.getCellType()) {
            case STRING:
                cellValue = cel.getStringCellValue();
                break;
            case NUMERIC:
            case FORMULA:
                cellValue = String.valueOf(cel.getNumericCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cel.getBooleanCellValue());
                break;
            case BLANK:
            case _NONE:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    public String getCellValue(int colNum, int rowNum) {
        row = sheet.getRow(rowNum);
        return getCellValue(row.getCell(colNum));
    }

    public String getCellValue(String colName, int rowNum) {
        row = sheet.getRow(rowNum);
        return getCellValue(row.getCell(getColumnNumFromColumnName(colName)));
    }

    private int getColumnNumFromColumnName(String columnName) {
        // iterate over all cells in first row(headers) to find the correct header
        row = sheet.getRow(0);
        int columnNum = 0;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (getCellValue(row.getCell(i)).equalsIgnoreCase(columnName)) {
                columnNum = i;
            }
        }
        return columnNum;
    }

    private int getNumberOfColumns() {
        return row.getLastCellNum();
    }

    private int getNumberOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }
}