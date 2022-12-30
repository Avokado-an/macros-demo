package com.bahdanau.calculator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MacrosDto {
    private Integer calories;
    private Integer fats;
    private Integer carbs;
    private Integer proteins;
}
