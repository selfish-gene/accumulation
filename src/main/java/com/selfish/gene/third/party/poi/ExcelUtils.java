package com.selfish.gene.third.party.poi;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.apache.poi.ss.formula.eval.NotImplementedFunctionException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Administrator on 2017/6/23.
 */
public class ExcelUtils {

    public static void main(String[] args) {
        Collection<String> supportedFunctionNames = FunctionEval.getSupportedFunctionNames();
        for (String name:supportedFunctionNames) {
//            System.out.println(name);
        }
        Collection<String> supportedFunctionNames1 = WorkbookEvaluator.getSupportedFunctionNames();
        for (String name:supportedFunctionNames1) {
//            System.out.println(name);
        }
        Collection<String> notSupportedFunctionNames = WorkbookEvaluator.getNotSupportedFunctionNames();
        for (String name: notSupportedFunctionNames) {
            System.out.println(name);
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D:\\temp\\test.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Workbook workbook = new HSSFWorkbook(fis);
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        CellReference cellReference = new CellReference("A1");
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());


        if (cell!=null) {
//            switch (evaluator.evaluateInCell(cell).getCellType()) {
            switch (evaluator.evaluate(cell).getCellType()) {
//            switch (evaluator.evaluateFormulaCell(cell)) {
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.println(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    System.out.println(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    System.out.println(cell.getErrorCellValue());
                    break;

                // CELL_TYPE_FORMULA will never occur
                case Cell.CELL_TYPE_FORMULA:
                    CellValue cellValue = evaluator.evaluate(cell);
                    System.out.println(cellValue);
                    break;
            }
        }
    }
}
