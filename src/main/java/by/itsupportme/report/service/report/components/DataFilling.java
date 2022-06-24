package by.itsupportme.report.service.report.components;

import by.itsupportme.report.model.dto.ProductDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface DataFilling {
    void fillDataTable(XSSFWorkbook workbook, List<ProductDto> productDtos);
}
