package com.bahdanau.aim.feign;

import com.bahdanau.aim.dto.MacrosDto;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public MacrosDto getUserAimMacros() {
        return new MacrosDto();
    }
}
