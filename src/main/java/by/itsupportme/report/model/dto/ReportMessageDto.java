package by.itsupportme.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportMessageDto {
    private Date startDate;
    private Date endDate;
    private Long minStockLevel;
    private Set<RetailerDto> retailers = new HashSet<>();
}
