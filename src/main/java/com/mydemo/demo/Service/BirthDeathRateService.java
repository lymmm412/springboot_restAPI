package com.mydemo.demo.Service;

import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Mapper.BirthDeathRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirthDeathRateService {
    @Autowired
    BirthDeathRateMapper birthDeathRateMapper;

    public boolean insertData(BirthDeathRate birthDeathRate){
        return birthDeathRateMapper.insert(birthDeathRate);
    }

    public BirthDeathRate findData(int id){
        return birthDeathRateMapper.findById(id);
    }
}
