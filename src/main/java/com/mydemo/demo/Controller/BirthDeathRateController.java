package com.mydemo.demo.Controller;

import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Utils.CSVReader;
import com.mydemo.demo.Service.BirthDeathRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BirthDeathRateController {
    @Autowired
    private BirthDeathRateService birthDeathRateService;

    Logger LOG= LoggerFactory.getLogger(LoggerFactory.class);
    @PostMapping("/data")
    public ResponseEntity<String> add(@RequestBody BirthDeathRate birthDeathRate){
        if(!birthDeathRateService.insertData(birthDeathRate)){
            return new ResponseEntity<>("Insertion failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Insertion success!" ,HttpStatus.CREATED);

    }

    @GetMapping("/data/{id}")
    @ResponseBody
    public ResponseEntity<BirthDeathRate> getDataById(@PathVariable(name = "id") int id) throws Exception {
        BirthDeathRate birthDeathRate=birthDeathRateService.findDataById(id);
        if(birthDeathRate==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(birthDeathRate, HttpStatus.OK);
    }

    @GetMapping("/data")
    public ResponseEntity<List<BirthDeathRate>> getAll() throws Exception {
        List<BirthDeathRate> list=birthDeathRateService.findAllData();
        if(list==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/data/type/{type}")
    public ResponseEntity<List<BirthDeathRate>> filterByType(@PathVariable(name = "type") String type) throws Exception {
        List<BirthDeathRate> list=birthDeathRateService.filterByType(type);

        if(list==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/data/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody BirthDeathRate birthDeathRate) throws Exception {
        //TODO: other cases of errors
        birthDeathRate.setId(id);
        if(!birthDeathRateService.updateData(birthDeathRate)){
            return new ResponseEntity<>("Update failed", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Update success!", HttpStatus.OK);
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id) throws Exception {
        if(!birthDeathRateService.deleteData(id)){
            return new ResponseEntity<>("Deletion failed.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deletion success!", HttpStatus.OK);
    }

    @DeleteMapping("/data")
    public ResponseEntity<String> clear() throws Exception {
        birthDeathRateService.clearAll();
        return new ResponseEntity<>("Clear Success!", HttpStatus.OK);
    }

    @PostMapping("/csv/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {
        if(CSVReader.hasCSVFormat(file)){
            LOG.info("start to save file");
            birthDeathRateService.saveFile(file);
            LOG.info("saved");
            return new ResponseEntity<>("Uploaded the file successfully: "+file.getOriginalFilename(), HttpStatus.CREATED);
        }
        LOG.info("invalid file");
        return new ResponseEntity<>("Please upload a csv file!", HttpStatus.BAD_REQUEST);
    }



}
