package com.springTestApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springTestApp.service.TestService;

@RestController
@Scope("request")
@RequestMapping("/nielsen")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/ndx", method = RequestMethod.GET)
    public String get(@RequestParam("importIndex") String importIndex, @RequestParam("marketIndex") String marketIndex,
                    @RequestParam("timeIndex") String timeIndex, @RequestParam("measureIndex") String measureIndex) {
        return testService.fetchDataValue(importIndex, marketIndex, timeIndex, measureIndex);
    }
}