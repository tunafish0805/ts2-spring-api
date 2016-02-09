package com.springTestApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springTestApp.dao.TestDAO;

@Service
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService{
    
    @Autowired
    private TestDAO testDAO;

    @Override
    public String fetchDataValue(String importIndex, String marketIndex, String timeIndex, String measureIndex) {
        return testDAO.fetchDataValue(importIndex, marketIndex, timeIndex, measureIndex);
    }

}
