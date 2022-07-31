package com.mydemo.demo.Mapper;

import com.mydemo.demo.Entity.BirthDeathRate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BirthDeathRateMapper {

    boolean insert(BirthDeathRate birthDeathRate);

    BirthDeathRate findById(int id);
}
