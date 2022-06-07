package by.itsupportme.report.service;

import by.itsupportme.report.model.dto.ProductDto;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(value = "product-instance-client", url = "${product-api.url}")
public interface FeignClientService {
    @RequestLine("GET /products/")
    List<ProductDto> findAll();
    @RequestLine("GET /products/{id}")
    ProductDto findById(@Param Long id);
    @RequestLine("GET /products/byParams/?retailerName={retailerName}&stockLevel={stockLevel}&startDate={startDate}&endDate={endDate}")
    List<ProductDto> findAllRetailerNameByStockLevelByStartDateByEndDate(
            @Param String retailerName,
            @Param Long stockLevel,
            @Param LocalDateTime startDate,
            @Param LocalDateTime endDate
    );
}