package com.mydemo.demo;

import com.mydemo.demo.Controller.BirthDeathRateController;
import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Mapper.BirthDeathRateMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private BirthDeathRateMapper birthDeathRateMapper;

    private List<BirthDeathRate> list=new ArrayList<>();
    @Test
    public void create(){
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
            birthDeathRateMapper.insert(l);
            BirthDeathRate res=birthDeathRateMapper.findById(l.getId());
            System.out.println(res.toString());
            assertEquals(l.getPeriod(), res.getPeriod());
            assertEquals(l.getBirthOrDeath(), res.getBirthOrDeath());
            assertEquals(l.getRegion(), res.getRegion());
            assertEquals(l.getCount(), res.getCount());
        }


    }

    @Test
    public void findById(){
        create();
        BirthDeathRate res=birthDeathRateMapper.findById(1);
        BirthDeathRate expected=list.get(0);
        assertEquals(expected.getCount(),res.getCount());
        assertEquals(expected.getPeriod(),res.getPeriod());
        assertEquals(expected.getBirthOrDeath(),res.getBirthOrDeath());
        assertEquals(expected.getRegion(),res.getRegion());

    }

    @Test
    public void findAll(){
        create();

        List<BirthDeathRate> res=birthDeathRateMapper.findAll();
        assertEquals(list.size(),res.size());
        for(int i=0; i<res.size();i++){
            assertEquals(list.get(i).getCount(), res.get(i).getCount());
            assertEquals(list.get(i).getPeriod(), res.get(i).getPeriod());
            assertEquals(list.get(i).getBirthOrDeath(),res.get(i).getBirthOrDeath());
            assertEquals(list.get(i).getRegion(), res.get(i).getRegion());
        }
    }

    @Test
    public void update(){
        create();

        BirthDeathRate update=list.get(2);
        update.setCount(0);
        birthDeathRateMapper.updateById(update);
        BirthDeathRate res=birthDeathRateMapper.findById(update.getId());

        assertEquals(update.getCount(),res.getCount());
        assertEquals(update.getPeriod(),res.getPeriod());
        assertEquals(update.getBirthOrDeath(),res.getBirthOrDeath());
        assertEquals(update.getRegion(),res.getRegion());

    }

    @Test
    public void filter(){
        create();
        List<BirthDeathRate> birthList=birthDeathRateMapper.filterBirth("birth");
        List<BirthDeathRate> deathList=birthDeathRateMapper.filterBirth("death");
        assertEquals(birthList.size(), list.size()/2);
        assertEquals(deathList.size(), list.size()/2);
    }
    @Test
    public void deleteById(){
        create();

        BirthDeathRate toBeDelete=list.get(1);
        birthDeathRateMapper.deleteById(toBeDelete.getId());

        List<BirthDeathRate> res=birthDeathRateMapper.findAll();
        assertEquals(res.size(), list.size()-1);

        for(BirthDeathRate r:res){
            assertNotEquals(toBeDelete.getId(),r.getId());
        }

        clear();
    }
    @Test
    public void clear(){
        birthDeathRateMapper.clearAll();
        assertEquals(0,birthDeathRateMapper.findAll().size());
        list=new ArrayList<>();
    }


    @RunWith(SpringRunner.class)
    @SpringBootTest
    public static class DemoApplicationIntegrationTests {
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
}
