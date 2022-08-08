package com.mydemo.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydemo.demo.Controller.BirthDeathRateController;
import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Service.BirthDeathRateService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;

@RunWith(SpringRunner.class)
@AutoConfigureMybatis
@WebMvcTest(value = BirthDeathRateController.class)
@Import(BirthDeathRateService.class)
public class BirthDeathRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BirthDeathRateService birthDeathRateService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<BirthDeathRate> list=new ArrayList<>();

    @Before
    public void init(){
        BirthDeathRate b1=new BirthDeathRate("2019","death","bay area",200);
        BirthDeathRate b2=new BirthDeathRate("2020","death","china",400);
        BirthDeathRate b3=new BirthDeathRate("2021","birth","japan",600);
        BirthDeathRate b4=new BirthDeathRate("2022","birth","germany",800);
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        for(BirthDeathRate l:list){
            birthDeathRateService.insertData(l);
        }
    }

    @After
    public void clear() throws Exception {
        birthDeathRateService.clearAll();
        list=new ArrayList<>();
    }

    @Test
    public void create() throws Exception{
        BirthDeathRate birthDeathRate=new BirthDeathRate("2019","death","bay area",200);

        // action or behaviour that we are going test
        ResultActions response=mockMvc.perform(post("/api/data")
                .content(objectMapper.writeValueAsString(birthDeathRate))
                .contentType(MediaType.APPLICATION_JSON));

        // verify the result or output using assert statements
        response.andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string("Insertion success!"));

    }

    @Test
    public void getAll() throws Exception{
        ResultActions response=mockMvc.perform(get("/api/data"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(list.size())));
    }

    @Test
    public void findById() throws Exception{
        BirthDeathRate expected=list.get(1);

        ResultActions response=mockMvc.perform(get("/api/data/{id}",2));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.period", is(expected.getPeriod())))
                .andExpect(jsonPath("$.birthOrDeath", is(expected.getBirthOrDeath())))
                .andExpect(jsonPath("$.region", is(expected.getRegion())))
                .andExpect(jsonPath("$.count", is(expected.getCount())));
    }
    // https://www.javaguides.net/2022/03/spring-boot-unit-testing-crud-rest-api-with-junit-and-mockito.html


    //todo:update
    @Test
    public void updateById() throws Exception {
        BirthDeathRate birthDeathRate=new BirthDeathRate(1,"2019","death","bay area",200000);
        ResultActions updateResponse=mockMvc.perform(put("/api/data/{id}",1)
                .content(objectMapper.writeValueAsString(birthDeathRate))
                .contentType(MediaType.APPLICATION_JSON));

        updateResponse.andExpect(status().isOk())
                .andDo(print());

        ResultActions getResponse=mockMvc.perform(get("/api/data/{id}",birthDeathRate.getId()));

        getResponse.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.period",is(birthDeathRate.getPeriod())))
                .andExpect(jsonPath("$.birthOrDeath", is(birthDeathRate.getBirthOrDeath())))
                .andExpect(jsonPath("$.region", is(birthDeathRate.getRegion())))
                .andExpect(jsonPath("$.count",is(birthDeathRate.getCount())));
    }

    @Test
    public void deleteById() throws Exception {
        ResultActions response=mockMvc.perform(delete("/api/data/{id}",1));

        response.andExpect(status().isOk())
                .andDo(print());

        response=mockMvc.perform(get("/api/data"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(list.size()-1)));
    }

    @Test
    public void clearAll() throws Exception {
        ResultActions response=mockMvc.perform(delete("/api/data",1));

        response.andExpect(status().isOk())
                .andDo(print());

        response=mockMvc.perform(get("/api/data"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(0)));
    }

}
