package com.ajin.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ajin
 */
@Slf4j
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {

        log.info("test");

        return "test";
    }

}
