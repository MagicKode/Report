package by.itsupportme.report.service.report.components;

import by.itsupportme.report.model.dto.ReportMessageDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface TableCreation {
    XSSFWorkbook createTable(ReportMessageDto reportMessageDto);
}
