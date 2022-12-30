package com.bahdanau.user.dto;

import com.bahdanau.user.entity.ActivityTypeFactor;
import com.bahdanau.user.entity.AimTypeFactor;
import com.bahdanau.user.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CreateUserDto {
    @NotBlank(message = "username can not be blank")
    private String username;
    @NotBlank(message = "email can not be blank")
    @Email
    private String email;
    @NotNull(message = "gender can not be blank")
    private Gender gender;
    @NotNull(message = "weight can not be blank")
    private Double weight;
    @NotNull(message = "height can not be blank")
    private Double height;
    @NotNull(message = "age can not be blank")
    private Integer age;
    @NotNull(message = "aim can not be blank")
    private AimTypeFactor aimType;
    @NotNull(message = "activity can not be blank")
    private ActivityTypeFactor activityType;
}
