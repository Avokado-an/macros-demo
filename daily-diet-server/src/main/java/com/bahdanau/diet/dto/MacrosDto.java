package com.bahdanau.diet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MacrosDto {
    private int calories;
    private int fats;
    private int carbs;
    private int proteins;
}
