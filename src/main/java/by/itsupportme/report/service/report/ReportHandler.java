package by.itsupportme.report.service.report;

import by.itsupportme.report.model.dto.ReportMessageDto;

public interface ReportHandler {
    void handle(ReportMessageDto reportMessageDto);
}
