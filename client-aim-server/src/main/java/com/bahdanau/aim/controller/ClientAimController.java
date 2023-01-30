package com.bahdanau.aim.controller;

import com.bahdanau.aim.dto.ClientMonthlyResultDto;
import com.bahdanau.aim.service.ClientAimService;
import com.bahdanau.aim.config.BearerAuthFeignConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monthly-aim")
public class ClientAimController {
    private final ClientAimService clientAimService;

    @GetMapping("/{date}")
    public ClientMonthlyResultDto getClientResultsForMonth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                           JwtAuthenticationToken auth) {
        return clientAimService.getClientResultsForMonth((String) auth.getToken().getClaims().get("email"), date);
    }
}
