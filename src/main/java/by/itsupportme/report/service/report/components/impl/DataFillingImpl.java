package by.itsupportme.report.service.report.components.impl;

import by.itsupportme.report.model.dto.ProductDto;
import by.itsupportme.report.model.dto.RetailerDto;
import by.itsupportme.report.model.entity.enums.ReportParams;
import by.itsupportme.report.service.report.components.DataFilling;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DataFillingImpl implements DataFilling {
    @Value("${sheet.name}")
    private String sheetName;

    @Override
    public void fillDataTable(XSSFWorkbook workbook, List<ProductDto> productDtos) {
//        var lambdaContext = new Object() {
//            int rowNum = 0;
//        };
        AtomicInteger rowNum = new AtomicInteger();
        XSSFSheet sheet = workbook.getSheet(sheetName);
        productDtos.forEach(productDto -> {
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
        });
        Arrays.stream(ReportParams.values()).forEach(param -> sheet.autoSizeColumn(param.ordinal()));
    }
}
