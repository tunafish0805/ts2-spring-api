package com.springTestApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springTestApp.service.TestService;

@RestController
@Scope("request")
@RequestMapping("/inventory")
public class TestController {

    @Autowired
    private TestService testService;
    
    // comment 
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String get(@RequestParam("importIndex") String importIndex, @RequestParam("marketIndex") String marketIndex,
                    @RequestParam("timeIndex") String timeIndex, @RequestParam("measureIndex") String measureIndex) {
        return testService.fetchDataValue(importIndex, marketIndex, timeIndex, measureIndex);
    }
}