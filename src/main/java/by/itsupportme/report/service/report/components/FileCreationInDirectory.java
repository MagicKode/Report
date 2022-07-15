package by.itsupportme.report.service.report.components;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public interface FileCreationInDirectory {
    String createFileInDirectory(XSSFWorkbook workbook) throws IOException;
}
