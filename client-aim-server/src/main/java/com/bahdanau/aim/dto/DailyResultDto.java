package com.bahdanau.aim.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DailyResultDto {
    private MacrosDto macrosDifferenceFromExpected;
    private LocalDate date;
}
