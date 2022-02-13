package com.example.houseutils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 헬스 체크 컨트롤러
 */
@RestController
public class HealthCheckController {

    @GetMapping("/api/ping")
    public String ping(){
        return "OK";
    }

}
