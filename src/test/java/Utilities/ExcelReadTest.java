package Utilities;

import java.io.IOException;

public class ExcelReadTest {

    public static void main(String[] args) throws IOException {

        String data = ExcelUtility.getCellData("LoginData", 1, 1);

        System.out.println("Email from Excel: " + data);
    }
}