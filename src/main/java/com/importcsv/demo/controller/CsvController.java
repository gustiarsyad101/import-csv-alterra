package com.importcsv.demo.controller;

import com.importcsv.demo.dto.ResponseData;
import com.importcsv.demo.entity.UploadFile;
import com.importcsv.demo.service.CsvService;
import com.importcsv.demo.utility.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @GetMapping
    public ResponseEntity<?> findAllCsv(){
        ResponseData responseData = new ResponseData();
        try {
            List<UploadFile> uploadCsv = csvService.findAll();
            responseData.setStatus(true);
            responseData.getMessages().add("Get all csv files");
            responseData.setPayload(uploadCsv);
            return ResponseEntity.ok(responseData);
        } catch (Exception err) {
            responseData.setStatus(false);
            responseData.getMessages().add("Could not get csv files: "+ err.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        ResponseData responseData = new ResponseData();
        System.out.println("Check Error 1 >>" + responseData);
        if(!CSVHelper.hasCSVFormat(file)){
            responseData.setStatus(false);
            responseData.getMessages().add("Please upload a csv file");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try{
            List<UploadFile> uploadFiles = csvService.save(file);
            responseData.setStatus(true);
            responseData.getMessages().add("Uploaded a file csv successfully: "+ file.getOriginalFilename());
            responseData.setPayload(uploadFiles);
//            return ResponseEntity.status(HttpStatus.OK).body(responseData);
            //System.out.println("Tess>>> Upload").(responseData);
            return ResponseEntity.ok(responseData);
        } catch (Exception err) {
            responseData.setStatus(false);
            System.out.println("Check Error 2 >>" + err.getMessage());
            responseData.getMessages().add("Could not upload the file: "+ file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseData);
        }
    }
}
