package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtility {

    // =========================================================
    // LOGIN DATA PROVIDER
    // =========================================================

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {

        int rowCount =
                ExcelUtility.getRowCount("LoginData");

        Object[][] data =
                new Object[rowCount][4];

        for (int i = 0; i < rowCount; i++) {

            int excelRow = i + 1;

            data[i][0] =
                    ExcelUtility.getCellData(
                            "LoginData",
                            excelRow,
                            0);

            data[i][1] =
                    ExcelUtility.getCellData(
                            "LoginData",
                            excelRow,
                            1);

            data[i][2] =
                    ExcelUtility.getCellData(
                            "LoginData",
                            excelRow,
                            2);

            data[i][3] =
                    ExcelUtility.getCellData(
                            "LoginData",
                            excelRow,
                            3);
        }

        return data;
    }


    // =========================================================
    // CHECKOUT VALIDATION DATA PROVIDER
    // =========================================================

    @DataProvider(name = "checkoutValidationData")
    public Object[][] getCheckoutValidationData()
            throws IOException {

        int rowCount =
                ExcelUtility.getRowCount(
                        "src/test/resources/CheckoutValidationData.xlsx",
                        "CheckoutValidation");

        Object[][] data =
                new Object[rowCount][13];

        for (int i = 0; i < rowCount; i++) {

            int excelRow = i + 1;

            // TC ID
            data[i][0] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            0);

            // First Name
            data[i][1] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            1);

            // Last Name
            data[i][2] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            2);

            // Email
            data[i][3] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            3);

            // Address
            data[i][4] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            4);

            // City
            data[i][5] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            5);

            // Postal Code
            data[i][6] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            6);

            // Country
            data[i][7] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            7);

            // Card Number
            data[i][8] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            8);

            // Card Holder Name
            data[i][9] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            9);

            // Date
            data[i][10] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            10);

            // CVC
            data[i][11] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            11);

            // Expected Result
            data[i][12] =
                    ExcelUtility.getCellData(
                            "src/test/resources/CheckoutValidationData.xlsx",
                            "CheckoutValidation",
                            excelRow,
                            12);
        }

        return data;
    }
}