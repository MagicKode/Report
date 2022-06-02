package by.itsupportme.report.service;

import by.itsupportme.report.model.dto.ReportMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsConsumer {
//    private final FeignClientService feignClientService;

    @JmsListener(destination = "${activemq.topic}")
    public void onMessage(ReportMessageDto reportMessageDto) {
        System.out.println("receive this message from activemq: " +  reportMessageDto);
       /* ProductDto productDto = feignClientService.findById(123L);
        System.out.println(productDto);*/
    }
}
