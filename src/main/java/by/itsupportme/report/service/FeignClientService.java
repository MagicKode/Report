package by.itsupportme.report.service;

import by.itsupportme.report.model.dto.ProductDto;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@FeignClient(value = "product-instance-client", url = "${product-api.url}")
public interface FeignClientService {
    @RequestLine("GET /products/")
    List<ProductDto> findAll();
    @RequestLine("GET /products/{id}")
    ProductDto findById(@Param Long id);
    @RequestLine("GET /products/feign/?retailerName={retailerName}&stockLevel={stockLevel}&startDate={startDate}&endDate={endDate}")
    List<ProductDto> findAllByRetailerNameAndStockLevel(@Param String retailerName, @Param Long stockLevel, @Param LocalDate startDate, @Param LocalDate endDate);
}