package by.itsupportme.report.service.report.components.impl;

import by.itsupportme.report.model.dto.ProductDto;
import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.model.dto.RetailerDto;
import by.itsupportme.report.model.dto.TypeReport;
import by.itsupportme.report.model.entity.enums.ReportType1;
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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class ReportType1ExcelCreator extends AbstractExcelCreator implements ExcelCreator {

    private final FeignClientService feignClientService;
    private final static String SHEET_NAME = "sheetName";

    public ReportType1ExcelCreator(
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
        Arrays.stream(ReportType1.values())
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
        getData(reportMessageDto).forEach(productDto -> {
            Row row = sheet.createRow(rowNum.incrementAndGet());
            row.createCell(0).setCellValue(productDto.getTitle());
            row.createCell(1).setCellValue(productDto.getDescription());
            String retailersString = productDto.getRetailers()
                    .stream()
                    .map(RetailerDto::getName)
                    .collect(Collectors.joining(", ")
                    );
            row.createCell(2).setCellValue(retailersString);
            row.createCell(3).setCellValue(productDto.getStockLevel());
            Arrays.stream(ReportType1.values()).forEach(param -> sheet.autoSizeColumn(param.ordinal()));
        });
    }

    @Override
    public boolean suitable(TypeReport typeReport) {
        return typeReport == TypeReport.TYPE_1;
    }

    private List<ProductDto> getData(ReportMessageDto reportMessageDto) {
        return feignClientService.findAllByRetailerNameByStockLevelByStartDateByEndDate(
                reportMessageDto.getRetailer().getName(),
                reportMessageDto.getMinStockLevel(),
                reportMessageDto.getStartDate(),
                reportMessageDto.getEndDate()
        );
    }


}
