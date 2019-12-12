package com.sds.alioolio.authtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 2019-06-03 Developer: dongha.shin Description :
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
