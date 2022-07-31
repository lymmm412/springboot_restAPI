package com.mydemo.demo.Controller;

import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Service.BirthDeathRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BirthDeathRateController {
    @Autowired
    private BirthDeathRateService birthDeathRateService;

    @PostMapping("/data")
    public ResponseEntity<String> getData(@RequestBody BirthDeathRate birthDeathRate){
        if(birthDeathRateService.insertData(birthDeathRate)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<BirthDeathRate> getOneRow(@PathVariable int id){
        BirthDeathRate res=birthDeathRateService.findData(id);
        if(res!=null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
