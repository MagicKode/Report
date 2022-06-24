package by.itsupportme.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportMessageDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long minStockLevel;
    private RetailerDto retailer;
    private TypeReport type;
}
