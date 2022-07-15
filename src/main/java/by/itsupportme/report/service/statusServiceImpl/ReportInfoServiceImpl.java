package by.itsupportme.report.service.statusServiceImpl;

import by.itsupportme.report.exceptionHandler.exception.NotFoundException;
import by.itsupportme.report.mapper.ReportInfoMapper;
import by.itsupportme.report.model.dto.ReportInfoDto;
import by.itsupportme.report.model.entity.ReportInfo;
import by.itsupportme.report.repository.ReportInfoRepository;
import by.itsupportme.report.service.ReportInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReportInfoServiceImpl implements ReportInfoService {
    private static final Logger log = LoggerFactory.getLogger(ReportInfoServiceImpl.class);
    private final ReportInfoRepository reportInfoRepository;
    private final ReportInfoMapper reportInfoMapper;


    @Transactional
    @Override
    public ReportInfoDto create(ReportInfo reportInfo) {
        return reportInfoMapper.toReportInfoDto(reportInfoRepository.save(reportInfo));
    }

    @Transactional
    @Override
    public ReportInfoDto update(ReportInfo reportInfo) {
        ReportInfo reportInfoFromDb = reportInfoRepository.findById(
                reportInfo.getId()).orElseThrow(
                () -> new NotFoundException("No reportInfo updated with such id = " + reportInfo.getId())
        );
        reportInfoFromDb.setTypeReport(reportInfo.getTypeReport());
        reportInfoFromDb.setStatusType(reportInfo.getStatusType());
        reportInfoFromDb.setFilePath(reportInfo.getFilePath());
        return reportInfoMapper.toReportInfoDto(reportInfoRepository.save(reportInfoFromDb));
    }

    @Override
    public ReportInfoDto findById(Long id) {
        ReportInfo reportInfo = reportInfoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No reportInfo found with such id = " + id)
        );
        log.info("Found product with id = {}", id);
        return reportInfoMapper.toReportInfoDto(reportInfo);
    }
}
