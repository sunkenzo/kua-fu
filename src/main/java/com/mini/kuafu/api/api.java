package com.mini.kuafu.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kenzo
 * @create 2021-02-04 17:48
 */
@RestController
@RequestMapping("auth")
public class api {

    @PostMapping("/login")
    public String process() {
        return "sucess";
    }
}
