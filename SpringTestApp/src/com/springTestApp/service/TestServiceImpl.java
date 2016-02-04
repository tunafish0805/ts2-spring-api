package com.springTestApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springTestApp.dao.TestDAO;
import com.springTestApp.vo.Device;

@Service
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService{
    
    @Autowired
    private TestDAO testDAO;
    
    @Override
    public void insert(Device device) {
       testDAO.insert(device);
    }

    @Override
    public List<Device> list() {
        return testDAO.list();
    }

    @Override
    public void delete(String id) {
        testDAO.delete(id);
    }

    @Override
    public void update(Device device) {
        testDAO.delete(device.getId());
        testDAO.insert(device);
    }

}
