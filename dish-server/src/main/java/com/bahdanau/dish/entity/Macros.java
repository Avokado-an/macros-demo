package com.bahdanau.dish.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Macros {
    private Integer calories;
    private Integer fats;
    private Integer carbs;
    private Integer proteins;
}
