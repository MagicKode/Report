package by.itsupportme.report.mapper;

import by.itsupportme.report.model.dto.ReportInfoDto;
import by.itsupportme.report.model.entity.ReportInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = ReportInfoMapper.class)
public interface ReportInfoMapper {
    ReportInfoDto toReportInfoDto (ReportInfo reportInfo);
    List<ReportInfoDto> toListReportInfoDto(List<ReportInfo> products);
}
