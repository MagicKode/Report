package by.itsupportme.report.service;


import by.itsupportme.report.model.dto.ReportInfoDto;
import by.itsupportme.report.model.dto.TypeReport;
import by.itsupportme.report.model.entity.ReportInfo;
import by.itsupportme.report.model.entity.enums.StatusType;

public interface ReportInfoService {
    ReportInfoDto create(ReportInfo reportInfo);
    ReportInfoDto update(ReportInfo reportInfo);
    ReportInfoDto findById(Long id);
}
