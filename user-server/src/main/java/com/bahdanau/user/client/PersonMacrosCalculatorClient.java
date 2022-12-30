package com.bahdanau.user.client;

import com.bahdanau.user.dto.UserParametersDto;
import com.bahdanau.user.entity.Macros;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "person-macros-calculation-server",
        fallback = PersonMacrosCalculatorClientFallback.class)
public interface PersonMacrosCalculatorClient {
    @RequestMapping(method = RequestMethod.POST, value = "/calculate")
    Macros getMacrosForPerson(@RequestBody UserParametersDto userParametersDto);
}
