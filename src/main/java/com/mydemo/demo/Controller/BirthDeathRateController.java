package com.mydemo.demo.Controller;

import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Service.BirthDeathRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BirthDeathRateController {
    @Autowired
    private BirthDeathRateService birthDeathRateService;

    @PostMapping("/data")
    public ResponseEntity<String> add(@RequestBody BirthDeathRate birthDeathRate){
        if(!birthDeathRateService.insertData(birthDeathRate)){
            return new ResponseEntity<>("Insertion failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Insertion success!" ,HttpStatus.OK);

    }

    @GetMapping("/data/{id}")
    public ResponseEntity<BirthDeathRate> getDataById(@PathVariable int id){
        BirthDeathRate birthDeathRate=birthDeathRateService.findDataById(id);
        if(birthDeathRate==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(birthDeathRate, HttpStatus.OK);
    }

    @GetMapping("/data")
    public ResponseEntity<List<BirthDeathRate>> getAll(){
        List<BirthDeathRate> list=birthDeathRateService.findAllData();
        if(list==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/data/{type}")
    public ResponseEntity<List<BirthDeathRate>> filterByType(@PathVariable String birthOrDeath){
        List<BirthDeathRate> list=birthDeathRateService.filterByType(birthOrDeath);
        if(list==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/data/{id}")
    public ResponseEntity<String> update(@PathVariable BirthDeathRate birthDeathRate){
        //TODO: other cases of errors
        if(!birthDeathRateService.updateData(birthDeathRate)){
            return new ResponseEntity<>("Update success!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Update failed.", HttpStatus.OK);
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        if(!birthDeathRateService.deleteData(id)){
            return new ResponseEntity<>("Deletion failed.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deletion success!", HttpStatus.OK);
    }

    @DeleteMapping("/data")
    public ResponseEntity<String> clear(){
        birthDeathRateService.clearAll();
        return new ResponseEntity<>("Clear Success!", HttpStatus.OK);
    }

}
