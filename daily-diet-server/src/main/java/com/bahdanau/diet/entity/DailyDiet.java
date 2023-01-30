package com.bahdanau.diet.entity;


import com.bahdanau.diet.dto.DishDto;
import com.bahdanau.diet.dto.MacrosDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Document
public class DailyDiet {
    @Id
    private String id;
    private LocalDate documentedDate;
    private String userEmail;
    private List<DishDto> dishesForDay;
    private MacrosDto macrosDtoForFullDay;
}
