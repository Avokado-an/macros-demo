package com.bahdanau.aim.feign;

import com.bahdanau.aim.config.BearerAuthFeignConfig;
import com.bahdanau.aim.dto.MacrosDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-server", fallback = UserFeignClientFallback.class, configuration = BearerAuthFeignConfig.class)
public interface UserFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/macros")
    MacrosDto getUserAimMacros();
}
