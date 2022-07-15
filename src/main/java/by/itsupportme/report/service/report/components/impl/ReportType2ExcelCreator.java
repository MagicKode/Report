package by.itsupportme.report.service.report.components.impl;

import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.model.dto.TypeReport;
import by.itsupportme.report.model.entity.enums.ReportType2;
import by.itsupportme.report.service.FeignClientService;
import by.itsupportme.report.service.report.components.AbstractExcelCreator;
import by.itsupportme.report.service.report.components.ExcelCreator;
import by.itsupportme.report.service.report.components.FileCreationInDirectory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ReportType2ExcelCreator extends AbstractExcelCreator implements ExcelCreator {
    private final FeignClientService feignClientService;
    private final static String SHEET_NAME = "sheetName";

    public ReportType2ExcelCreator(
            FileCreationInDirectory fileCreationInDirectory,
            FeignClientService feignClientService
    ) {
        super(fileCreationInDirectory);
        this.feignClientService = feignClientService;
    }

    @Override
    public void fillHeader(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet(SHEET_NAME);
        XSSFRow row = sheet.createRow(0);
        Arrays
                .stream(ReportType2.values())
                .forEach(param -> {
                    XSSFCell cell = row.createCell(param.ordinal());
                    cell.setCellValue(param.name());
                    sheet.autoSizeColumn(param.ordinal());
                });
    }

    @Override
    public void fillData(XSSFWorkbook workbook, ReportMessageDto reportMessageDto) {
        AtomicInteger rowNum = new AtomicInteger();
        XSSFSheet sheet = workbook.getSheet(SHEET_NAME);
        Row row = sheet.createRow(rowNum.incrementAndGet());
        String retailersString = reportMessageDto.getRetailer().getName();
        LocalDateTime startDate = reportMessageDto.getStartDate();
        LocalDateTime endDate = reportMessageDto.getEndDate();
        Long productQuantity = getData(reportMessageDto);

        row.createCell(0).setCellValue(retailersString);
        row.createCell(1).setCellValue(startDate);
        row.createCell(2).setCellValue(endDate);
        row.createCell(3).setCellValue(productQuantity);
        Arrays.stream(ReportType2.values()).forEach(param -> sheet.autoSizeColumn(param.ordinal()));
    }

    @Override
    public boolean suitable(TypeReport typeReport) {
        return typeReport == TypeReport.TYPE_2;
    }

    private Long getData(ReportMessageDto reportMessageDto) {
        return feignClientService.getQuantityOfProductByRetailerNameByStartDateByEndDate(
                reportMessageDto.getRetailer().getName(),
                reportMessageDto.getStartDate(),
                reportMessageDto.getEndDate()
        );
    }
}
