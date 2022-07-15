package by.itsupportme.report.service.report.impl;

import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.model.entity.ReportInfo;
import by.itsupportme.report.model.entity.enums.StatusType;
import by.itsupportme.report.service.ReportInfoService;
import by.itsupportme.report.service.report.ReportHandler;
import by.itsupportme.report.service.report.components.ExcelCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportHandlerImpl implements ReportHandler {
    private final List<ExcelCreator> excelCreatorList;
    private final ReportInfoService reportInfoService;

    @Override
    public void handle(ReportMessageDto reportMessageDto) {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setStatusType(StatusType.IN_PROGRESS);
        reportInfo.setTypeReport(reportMessageDto.getType());
        reportInfoService.create(reportInfo);
        try {
            ExcelCreator creator = excelCreatorList.stream()
                    .filter(excelCreator -> excelCreator.suitable(reportMessageDto.getType()))
                    .findFirst()
                    .orElseThrow();
            String fileCreation = creator.reportCreation(reportMessageDto);
            reportInfo.setFilePath(fileCreation);
            reportInfo.setStatusType(StatusType.DONE);
        } catch (Exception e) {
            reportInfo.setStatusType(StatusType.ERROR);
        } finally {
            reportInfoService.update(reportInfo);
        }
    }
}
