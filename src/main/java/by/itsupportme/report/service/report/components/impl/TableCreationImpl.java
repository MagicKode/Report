package by.itsupportme.report.service.report.components.impl;

import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.model.entity.enums.ReportParams;
import by.itsupportme.report.service.report.components.TableCreation;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class TableCreationImpl implements TableCreation {
    @Value("${sheet.name}")
    private String sheetName;

    @Override
    public XSSFWorkbook createTable(ReportMessageDto reportMessageDto) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFRow row = sheet.createRow(0);
        Arrays
                .stream(ReportParams.values())
                .forEach(param -> {
                    XSSFCell cell = row.createCell(param.ordinal());
                    cell.setCellValue(param.name());
                    sheet.autoSizeColumn(param.ordinal());
                });
        return workbook;
    }
}
