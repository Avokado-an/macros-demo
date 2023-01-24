package com.bahdanau.diet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailyMacrosDto {
    private LocalDate date;
    private MacrosDto macrosDto;
}
