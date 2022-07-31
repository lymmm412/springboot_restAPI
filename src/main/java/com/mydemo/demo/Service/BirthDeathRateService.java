package com.mydemo.demo.Service;

import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Mapper.BirthDeathRateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BirthDeathRateService {
    @Autowired
    BirthDeathRateMapper birthDeathRateMapper;

    public boolean insertData(BirthDeathRate birthDeathRate){
        return birthDeathRateMapper.insert(birthDeathRate);
    }

    public BirthDeathRate findDataById(int id){
        return birthDeathRateMapper.findById(id);
    }

    public List<BirthDeathRate> findAllData(){
        return birthDeathRateMapper.findAll();
    }

    public List<BirthDeathRate> filterByType(String birthOrDeath){
        return birthDeathRateMapper.filterBirth(birthOrDeath);
    }

    public boolean deleteData(int id){
        return birthDeathRateMapper.deleteById(id);
    }

    public boolean updateData(BirthDeathRate birthDeathRate){
        return birthDeathRateMapper.updateById(birthDeathRate);
    }

    public void clearAll(){
        birthDeathRateMapper.clearAll();
    }
}
