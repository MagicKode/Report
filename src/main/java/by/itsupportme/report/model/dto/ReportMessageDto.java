package by.itsupportme.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportMessageDto {
    private /*LocalDate*/ Date startDate;
    private /*LocalDate*/ Date endDate;
    private Long minStockLevel;
    private RetailerDto retailer;
}
