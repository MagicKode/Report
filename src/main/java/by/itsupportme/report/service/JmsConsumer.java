package by.itsupportme.report.service;

import by.itsupportme.report.model.dto.ReportMessageDto;
import by.itsupportme.report.service.report.ReportHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsConsumer {
    private final ReportHandler reportHandler;

    @JmsListener(destination = "${activemq.topic}")
    public void onMessage(ReportMessageDto reportMessageDto) {
        reportHandler.handle(reportMessageDto);
    }
}
