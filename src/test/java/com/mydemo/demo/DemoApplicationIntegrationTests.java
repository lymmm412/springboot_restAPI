package com.mydemo.demo;

import com.mydemo.demo.Controller.BirthDeathRateController;
import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Service.BirthDeathRateService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationIntegrationTests {
    @Autowired
    private BirthDeathRateController birthDeathRateController;
    private List<BirthDeathRate> list=new ArrayList<>();

    @Test
    public void create() throws Exception {
        clear();
        BirthDeathRate b1=new BirthDeathRate("2019","death","bay area",200);
        BirthDeathRate b2=new BirthDeathRate("2020","death","china",400);
        BirthDeathRate b3=new BirthDeathRate("2021","birth","japan",600);
        BirthDeathRate b4=new BirthDeathRate("2022","birth","germany",800);
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        for(BirthDeathRate l:list){
            birthDeathRateController.add(l);
            ResponseEntity<BirthDeathRate> res=birthDeathRateController.getDataById(l.getId());
            System.out.println(res.getBody().toString());
            assertEquals(l.getPeriod(), res.getBody().getPeriod());
            assertEquals(l.getBirthOrDeath(), res.getBody().getBirthOrDeath());
            assertEquals(l.getRegion(), res.getBody().getRegion());
            assertEquals(l.getCount(), res.getBody().getCount());
        }

    }
    @Test
    public void clear() throws Exception {
        birthDeathRateController.clear();
        assertEquals(0,birthDeathRateController.getAll().getBody().size());
        list=new ArrayList<>();
    }
}
