package com.mydemo.demo.Service;

import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Utils.CSVReader;
import com.mydemo.demo.Mapper.BirthDeathRateMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class BirthDeathRateService {
    protected final Logger LOG=LoggerFactory.getLogger(this.getClass());
    @Autowired
    BirthDeathRateMapper birthDeathRateMapper;

    public boolean insertData(BirthDeathRate birthDeathRate){
        return birthDeathRateMapper.insert(birthDeathRate);
    }

    public BirthDeathRate findDataById(int id) throws Exception{
        BirthDeathRate birthDeathRate=birthDeathRateMapper.findById(id);
        System.out.println(birthDeathRate.toString());
        return birthDeathRate;
    }



    public List<BirthDeathRate> filterByType(String birthOrDeath) throws Exception{
        return birthDeathRateMapper.filterBirth(birthOrDeath);
    }

    public boolean deleteData(int id) throws Exception{
        return birthDeathRateMapper.deleteById(id);
    }

    public boolean updateData(BirthDeathRate birthDeathRate) throws Exception{
        return birthDeathRateMapper.updateById(birthDeathRate);
    }

    public void clearAll() throws Exception{
        birthDeathRateMapper.clearAll();
    }

    public void saveFile(MultipartFile file) throws IOException {
        List<BirthDeathRate> birthDeathRates= CSVReader.csvToBirthDeathRate(file.getInputStream());
        birthDeathRateMapper.saveAll(birthDeathRates);
    }

    public List<BirthDeathRate> findAllData() throws Exception{
        return birthDeathRateMapper.findAll();
    }
}
