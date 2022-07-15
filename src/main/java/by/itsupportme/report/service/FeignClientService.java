package by.itsupportme.report.service;

import by.itsupportme.report.model.dto.ProductDto;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(value = "product-instance-client", url = "${product-api.url}")
public interface FeignClientService {
    @RequestLine("GET " +
            "/products" +
            "/findByStockLevelGreaterThanEqualAndRetNameAndDateBetweenCreatedAtDates" +
            "/?retailerName={retailerName}&minStockLevel={minStockLevel}&startDate={startDate}&endDate={endDate}")
    List<ProductDto> findAllByRetailerNameByStockLevelByStartDateByEndDate(
            @Param String retailerName,
            @Param Long minStockLevel,
            @Param LocalDateTime startDate,
            @Param LocalDateTime endDate
    );

    @RequestLine("GET " +
            "/products" +
            "/getQuantityOfProductByRetNameAndDateBetweenCreatedAtDates" +
            "/?retailerName={retailerName}&startDate={startDate}&endDate={endDate}")
    Long getQuantityOfProductByRetailerNameByStartDateByEndDate(
            @Param String retailerName,
            @Param LocalDateTime startDate,
            @Param LocalDateTime endDate
    );
}
