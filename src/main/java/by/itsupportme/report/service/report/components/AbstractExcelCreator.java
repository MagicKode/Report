package by.itsupportme.report.service.report.components;

import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.model.dto.TypeReport;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

@RequiredArgsConstructor
public abstract class AbstractExcelCreator implements ExcelCreator {
    private final FileCreationInDirectory fileCreationInDirectory;

    @Override
    public String reportCreation(ReportMessageDto reportMessageDto) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        fillHeader(workbook);
        fillData(workbook, reportMessageDto);
        return fileCreationInDirectory.createFileInDirectory(workbook);
    }

    public abstract void fillHeader(XSSFWorkbook workbook);
    public abstract void fillData(XSSFWorkbook workbook, ReportMessageDto reportMessageDto);
    public abstract boolean suitable(TypeReport typeReport);
}
