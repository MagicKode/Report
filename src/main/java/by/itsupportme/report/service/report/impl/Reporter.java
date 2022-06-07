package by.itsupportme.report.service.report.impl;

import by.itsupportme.report.model.dto.ProductDto;
import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.service.FeignClientService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Reporter {

    private static final String filePath = "C:\\DZ\\IT\\Projects\\ProjectManager";
    private final FeignClientService feignClientService;

    public void creatReport(ReportMessageDto reportMessageDto){
        // TODO change file name
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Report");
        String[] columnHeaders = {"Title", "Description", "RetailerName", "Stock_Level"};
        Row header = sheet.createRow(0);
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

//        List<ProductDto> createData = fillData(reportMessageDto, sheet);
        List<ProductDto> productDtos = feignClientService.findAllRetailerNameByStockLevelByStartDateByEndDate(
                reportMessageDto.getRetailer().getName(),
                reportMessageDto.getMinStockLevel(),
                reportMessageDto.getStartDate(),
                reportMessageDto.getEndDate()
        );
        int rowNum = 1;
        for (ProductDto productDto : productDtos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(productDto.getTitle());
            row.createCell(1).setCellValue(productDto.getDescription());
            row.createCell(2).setCellValue(String.valueOf(productDto.getRetailers()));
            row.createCell(3).setCellValue(productDto.getStockLevel());
        }
        //autosize column
        for (int i = 0; i < columnHeaders.length; i++) {
            sheet.autoSizeColumn(i);
        }
//        File currDir = new File(filePath);
//        String path = currDir.getAbsolutePath();
        String fileLocation = filePath.substring(0, filePath.length() - 1) + "report-" + System.currentTimeMillis() + ".xlsx";
        try {
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*private static void createSheetHeader(XSSFSheet sheet, int rowNum, ProductDto productDto) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(productDto.getTitle());
        row.createCell(1).setCellValue(productDto.getDescription());
        row.createCell(2).setCellValue(productDto.getStockLevel());
        row.createCell(3).setCellValue(String.valueOf(productDto.getRetailers()));
    }*/

    private void fillData(ReportMessageDto reportMessageDto, XSSFSheet sheet) {
        List<ProductDto> productDtos = feignClientService.findAllRetailerNameByStockLevelByStartDateByEndDate(
                reportMessageDto.getRetailer().getName(),
                reportMessageDto.getMinStockLevel(),
                reportMessageDto.getStartDate(),
                reportMessageDto.getEndDate()
        );
    }
}
