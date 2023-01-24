package com.bahdanau.aim.feign;

import com.bahdanau.aim.dto.DailyMacrosDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "daily-diet-server", fallback = DailyDietFeignClientFallback.class)
public interface DailyDietFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/daily-diet/{userId}/{startDate}/{endDate}/macros")
    List<DailyMacrosDto> getDishesMacrosForPeriod(@PathVariable("userId") String userId,
                                                  @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                  @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);
}
