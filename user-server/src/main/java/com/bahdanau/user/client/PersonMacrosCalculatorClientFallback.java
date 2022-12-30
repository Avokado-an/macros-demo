package com.bahdanau.user.client;

import com.bahdanau.user.dto.UserParametersDto;
import com.bahdanau.user.entity.Macros;
import org.springframework.stereotype.Component;

@Component
public class PersonMacrosCalculatorClientFallback implements PersonMacrosCalculatorClient {
    @Override
    public Macros getMacrosForPerson(UserParametersDto userParametersDto) {
        return new Macros(180, 10, 10, 10);
    }
}
