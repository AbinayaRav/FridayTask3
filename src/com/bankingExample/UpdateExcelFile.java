package com.bankingExample;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class UpdateExcelFile {

    public void writeToExcel(Customer customer, String action) {
        File file = new File("src/SampleData.xlsx");
        try {
            FileInputStream input = new FileInputStream(file);
            Workbook hssf = new XSSFWorkbook(input);
            Sheet sheet = hssf.getSheetAt(0);
            if (action.equalsIgnoreCase("CREATE NEW RECORD")) {
                int lastRow = sheet.getLastRowNum();
                Row row = sheet.createRow(++lastRow);
                row.createCell(0).setCellValue(customer.getAccountNumber());
                row.createCell(1).setCellValue(customer.getCustomerName());
                row.createCell(2).setCellValue(customer.getAge());
                row.createCell(3).setCellValue(customer.getDateOfBirth());
                row.createCell(4).setCellValue(customer.getAddress());
                row.createCell(5).setCellValue(customer.getAccountBalance());
            } else if (action.equalsIgnoreCase("UPDATE AMOUNT BALANCE") || action.equalsIgnoreCase("WITHDRAW AMOUNT")) {
                Iterator<Row> row = sheet.rowIterator();
                while (row.hasNext()) {
                    Row row1 = row.next();
                    Cell cell1 = row1.getCell(0);
                    Cell cell2 = row1.getCell(5);
                    if (cell1.getCellType() == CellType.NUMERIC && cell2.getCellType() == CellType.NUMERIC) {
                        if ((int) (cell1.getNumericCellValue()) == customer.getAccountNumber()) {
                            cell2.setCellValue(customer.getAccountBalance());
                            break;
                        }
                    }
                }
            } else if (action.equalsIgnoreCase("DELETE ACCOUNT")) {
                Iterator<Row> row = sheet.rowIterator();
                while (row.hasNext()) {
                    Row row1 = row.next();
                    Cell cell3 = row1.getCell(0);
                    if (cell3.getCellType() == CellType.NUMERIC) {
                        if ((int) (cell3.getNumericCellValue()) == customer.getAccountNumber()) {
                            sheet.removeRow(row1);
                            break;
                        }
                    }
                }
            }
            input.close();
            FileOutputStream out = new FileOutputStream(file);
            hssf.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}