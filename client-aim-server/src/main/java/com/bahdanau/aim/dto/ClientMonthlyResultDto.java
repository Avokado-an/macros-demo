package com.bahdanau.aim.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class ClientMonthlyResultDto {
    private LocalDate startDate;
    private List<DailyResultDto> dailyResults;
    private MacrosDto totalMonthlyDifference;
    private float weightTotalDifference;
    private MacrosDto clientMacrosForTheDay;
    private String userId;
}
