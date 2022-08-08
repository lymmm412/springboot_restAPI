package com.mydemo.demo.Mapper;

import com.mydemo.demo.Entity.BirthDeathRate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BirthDeathRateMapper {

    boolean insert(BirthDeathRate birthDeathRate);

    BirthDeathRate findById(int id);

    List<BirthDeathRate> findAll();

    List<BirthDeathRate> filterBirth(String birthOrDeath);

    boolean deleteById(int id);

    boolean updateById(BirthDeathRate birthDeathRate);

    void clearAll();

    void saveAll(List<BirthDeathRate> birthDeathRates);
}
