package com.bahdanau.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String username;
    private Integer weight;
    private Integer height;
    private Integer age;
    private Gender gender;
    private AimTypeFactor aimType;
    private ActivityTypeFactor activityType;
    private Macros macros;
}
