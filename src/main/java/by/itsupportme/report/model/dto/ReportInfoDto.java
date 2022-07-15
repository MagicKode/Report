package by.itsupportme.report.model.dto;

import by.itsupportme.report.model.entity.enums.StatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReportInfoDto {
    private Long id;
    private StatusType statusType;
    private String title;
    private String path;
}
