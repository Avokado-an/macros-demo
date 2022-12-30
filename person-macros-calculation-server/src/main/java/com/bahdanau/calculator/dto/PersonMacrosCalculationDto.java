package com.bahdanau.calculator.dto;

import com.bahdanau.calculator.util.ActivityTypeFactor;
import com.bahdanau.calculator.util.AimTypeFactor;
import com.bahdanau.calculator.util.Gender;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class PersonMacrosCalculationDto {
    private Gender gender;
    private Double weight;
    private Double height;
    private Integer age;
    private AimTypeFactor aimType;
    private ActivityTypeFactor activityType;
}
