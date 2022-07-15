package by.itsupportme.report.service.report.components;

import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.model.dto.TypeReport;

import java.io.IOException;

public interface ExcelCreator {
    String reportCreation(ReportMessageDto reportMessageDto) throws IOException; //String for example
    boolean suitable(TypeReport typeReport);
}
