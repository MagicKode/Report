package by.itsupportme.report.service.report;

import by.itsupportme.report.model.dto.ReportMessageDto;

public interface Reporter {
    void reportCreation(ReportMessageDto reportMessageDto);
}
