package com.bahdanau.calculator.controller;

import com.bahdanau.calculator.dto.MacrosDto;
import com.bahdanau.calculator.util.DataProvisionUtil;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WireMockTest(httpPort = 8095)
public class DishMacrosCalculationControllerTest {
    @Test
    public void calculateMacrosForDishValidTest() throws IOException {
        MacrosDto macros = DataProvisionUtil.provideMacros(100, 10, 10, 10);
        stubFor(post("/calculate-dish")
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/json")
                        .withBody(macros.toString()))
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8095/calculate-dish");
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(macros.toString(), convertResponseToString(httpResponse));
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, StandardCharsets.UTF_8);
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }
}
