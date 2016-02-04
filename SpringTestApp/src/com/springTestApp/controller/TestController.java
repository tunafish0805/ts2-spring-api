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
import com.springTestApp.vo.Device;

@RestController
@Scope("request")
@RequestMapping("/inventory")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    public Device create(@RequestBody Device device) {
        testService.insert(device);
        return device;
    }

    @RequestMapping(value = "/device", method = RequestMethod.PUT)
    public Device update(@RequestBody Device device) {
        testService.update(device);
        return device;
    }

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public List<Device> request() {
        return testService.list();
    }

    @RequestMapping(value = "/device", method = RequestMethod.DELETE)
    public void delete(@RequestBody Device device) {
        testService.delete(device.getId());
    }
}