package com.springTestApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springTestApp.vo.Device;

public interface TestService {
    public void insert(Device device);
    public List<Device> list();
    public void delete(String id);
    public void update(Device device);
    public String fetchDataValue(String importIndex, String marketIndex, String timeIndex, String measureIndex);
}
