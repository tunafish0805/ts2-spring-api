package com.springTestApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

public interface TestService {
    public String fetchDataValue(String importIndex, String marketIndex, String timeIndex, String measureIndex);
}
