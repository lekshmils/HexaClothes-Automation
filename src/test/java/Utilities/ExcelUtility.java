package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    // =========================================================
    // GET CELL DATA
    // =========================================================

    public static String getCellData(
            String filePath,
            String sheetName,
            int rowNum,
            int colNum) throws IOException {

        FileInputStream file = new FileInputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        String data = "";

        if (sheet != null) {

            XSSFRow row = sheet.getRow(rowNum);

            if (row != null) {

                XSSFCell cell = row.getCell(colNum);

                if (cell != null) {
                    data = cell.toString();
                }
            }
        }

        workbook.close();
        file.close();

        return data;
    }


    // =========================================================
    // GET ROW COUNT
    // =========================================================

    public static int getRowCount(
            String filePath,
            String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = 0;

        if (sheet != null) {
            rowCount = sheet.getLastRowNum();
        }

        workbook.close();
        file.close();

        return rowCount;
    }


    // =========================================================
    // OLD LOGIN METHODS
    // =========================================================
    // These methods keep your existing Login code working.
    // =========================================================

    public static String getCellData(
            String sheetName,
            int rowNum,
            int colNum) throws IOException {

        return getCellData(
                "src/test/resources/LoginTestData.xlsx",
                sheetName,
                rowNum,
                colNum);
    }


    public static int getRowCount(
            String sheetName) throws IOException {

        return getRowCount(
                "src/test/resources/LoginTestData.xlsx",
                sheetName);
    }
}