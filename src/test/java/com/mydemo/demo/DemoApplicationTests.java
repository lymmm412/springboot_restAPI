package com.mydemo.demo;

import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Mapper.BirthDeathRateMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private BirthDeathRateMapper birthDeathRateMapper;

    @Test
    public void create(){
        BirthDeathRate birthDeathRate=new BirthDeathRate("2022","death","bay area",1000);
        birthDeathRateMapper.insert(birthDeathRate);
        BirthDeathRate res=birthDeathRateMapper.findById(birthDeathRate.getId());
        System.out.println(res.toString());
        assertEquals("2022", res.getPeriod());
        assertEquals("death", res.getBirthOrDeath());
        assertEquals("bay area", res.getRegion());
        assertEquals(1000, res.getCount());
    }

    @Test
    public void findById(){
        BirthDeathRate birthDeathRate=birthDeathRateMapper.findById(1);
        assertNotNull(birthDeathRate);
        System.out.println(birthDeathRate.toString());
    }
}
