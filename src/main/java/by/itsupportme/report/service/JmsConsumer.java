package by.itsupportme.report.service;

import by.itsupportme.report.model.dto.ProductDto;
import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.model.dto.RetailerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JmsConsumer {
    private final FeignClientService feignClientService;

    @JmsListener(destination = "${activemq.topic}")
    public void onMessage(ReportMessageDto reportMessageDto) {
        System.out.println("receive this message from activemq: " + reportMessageDto);
        System.out.println(ReportMessageDto.class.getSimpleName());
        List<ProductDto> products = feignClientService
                .findAllByRetailerNameAndStockLevel(
                        reportMessageDto.getRetailer().getName(),
                        reportMessageDto.getMinStockLevel(),
                        reportMessageDto.getStartDate(),
                        reportMessageDto.getEndDate()
                );
        System.out.println(products);
    }
}
