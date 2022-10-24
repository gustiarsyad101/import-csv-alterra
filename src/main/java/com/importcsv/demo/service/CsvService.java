package com.importcsv.demo.service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.importcsv.demo.entity.UploadFile;
import com.importcsv.demo.repo.CsvRepo;
import com.importcsv.demo.utility.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@Service("csvService")
@Transactional
public class CsvService {
    @Autowired(required = false)
    /*@Autowired*/
    private CsvRepo csvRepo;

    public List<UploadFile> save(MultipartFile file){
        try {
            List<UploadFile> uploadFiles = CSVHelper.csvToDatabase(file.getInputStream());
            System.out.println("Tes data>>"+ uploadFiles);
            return csvRepo.saveAll(uploadFiles);
        } catch (Exception err) {
            throw new RuntimeException("Failed to store csv data: "+ err.getMessage());
        }
    }

    public List<UploadFile> findAll(){
        return csvRepo.findAll();
    }

    // function update csv
    public static byte[] updateCSV(String fileToUpdate) throws IOException {
        final byte[] fallback = {};

        // Read existing file
        CSVReader reader = new CSVReader(new StringReader(fileToUpdate));
        List<String[]> csvBody = reader.readAll();

        // get CSV row column  and replace with by using row and column
        for(int i = 1; i < csvBody.size(); i++){
            csvBody.get(i)[1] = "-"+csvBody.get(i)[1];
        }
        reader.close();

        //Write back in csv
        try (StringWriter writer = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(writer)
        ) {
            csvWriter.writeAll(csvBody);
            csvWriter.flush();
            return writer.toString().getBytes();
        } catch (Exception e) {
            //LOGGER.error(LogUtil.systemLoggingContext(), "Cannot modify the current CSV file");
            return fallback;
        }
    }
}
