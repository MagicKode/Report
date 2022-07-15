package by.itsupportme.report.repository;

import by.itsupportme.report.model.entity.ReportInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportInfoRepository extends JpaRepository<ReportInfo, Long> {

}
