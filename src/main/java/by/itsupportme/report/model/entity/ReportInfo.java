package by.itsupportme.report.model.entity;

import by.itsupportme.report.model.dto.TypeReport;
import by.itsupportme.report.model.entity.enums.StatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
@NoArgsConstructor
@Getter
@Setter
public class ReportInfo extends DateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportInfo_id")
    private Long id;
    @Column(name = "status_type")
    @Enumerated(EnumType.ORDINAL)
    private StatusType statusType;
    @Column(name = "report_type")
    @Enumerated(EnumType.ORDINAL)
    private TypeReport typeReport;
    @Column(name = "file_path")
    private String filePath;

//    @OneToMany(mappedBy = "status")
//    private s;



}
