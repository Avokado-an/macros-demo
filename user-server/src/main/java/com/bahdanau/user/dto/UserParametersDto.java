package com.bahdanau.user.dto;

import com.bahdanau.user.entity.ActivityTypeFactor;
import com.bahdanau.user.entity.AimTypeFactor;
import com.bahdanau.user.entity.Gender;
import lombok.Data;

@Data
public class UserParametersDto {
    private Gender gender;
    private Double weight;
    private Double height;
    private Integer age;
    private AimTypeFactor aimType;
    private ActivityTypeFactor activityType;
}
