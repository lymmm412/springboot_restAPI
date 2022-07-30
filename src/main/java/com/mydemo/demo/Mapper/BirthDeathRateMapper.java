package com.mydemo.demo.Mapper;

import com.mydemo.demo.Entity.BirthDeathRate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BirthDeathRateMapper {

    @Insert("INSERT INTO birth_death_rate (period, birth_or_death, region, count) VALUE(#{period}, #{birth_or_death}, #{region}, #{count})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(BirthDeathRate birthDeathRate);

    @Select("SELECT * FROM birth_death_rate WHERE id = #{id}")
    BirthDeathRate findById(int id);
}
