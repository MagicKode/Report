package by.itsupportme.report.service.report.impl;

import by.itsupportme.report.model.dto.ProductDto;
import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.service.FeignClientService;
import by.itsupportme.report.service.report.Reporter;
import by.itsupportme.report.service.report.components.DataFilling;
import by.itsupportme.report.service.report.components.FileCreationInDirectory;
import by.itsupportme.report.service.report.components.TableCreation;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReporterImpl implements Reporter {
    private final TableCreation tableCreation;
    private final FeignClientService feignClientService;
    private final DataFilling dataFilling;
    private final FileCreationInDirectory fileCreationInDirectory;

    @Override
    public void reportCreation(ReportMessageDto reportMessageDto) {
        XSSFWorkbook workbook = tableCreation.createTable(reportMessageDto);
        List<ProductDto> productDtos = feignClientService.findAllByRetailerNameByStockLevelByStartDateByEndDate(
                reportMessageDto.getRetailer().getName(),
                reportMessageDto.getMinStockLevel(),
                reportMessageDto.getStartDate(),
                reportMessageDto.getEndDate()
        );
        dataFilling.fillDataTable(workbook, productDtos);
        fileCreationInDirectory.createFileInDirectory(workbook);
    }
}
