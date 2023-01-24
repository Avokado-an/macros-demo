package com.bahdanau.aim.feign;

import com.bahdanau.aim.dto.DailyMacrosDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DailyDietFeignClientFallback implements DailyDietFeignClient {
    @Override
    public List<DailyMacrosDto> getDishesMacrosForPeriod(String userId, LocalDate startDate, LocalDate endDate) {
        return new ArrayList<>();
    }
}
