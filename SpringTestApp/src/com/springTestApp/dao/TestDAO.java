package com.springTestApp.dao;

import java.util.List;

import com.springTestApp.vo.Device;

public interface TestDAO {
    public void insert(Device device);
    public List<Device> list();
    public void delete(String id);
}
