package com.importcsv.demo.utility;

import com.importcsv.demo.entity.UploadFile;
//import au.com.bytecode.opencsv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    private static final String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<UploadFile> csvToDatabase(InputStream is){
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

            List<UploadFile> uploadFileJobs = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for(CSVRecord csvRecord: csvRecords){
                UploadFile uploadFile = new UploadFile();
                uploadFile.setProductName(csvRecord.get("Product_Name"));
                uploadFile.setSku(csvRecord.get("Sku"));
                uploadFile.setPrice(Double.parseDouble(csvRecord.get("Price")));
                uploadFile.setImages(csvRecord.get("Images"));
                uploadFileJobs.add(uploadFile);
            }
            csvParser.close();
            return uploadFileJobs;
        } catch (IOException err) {
            throw new RuntimeException("Failed to convert to csv format"+ err.getMessage());
        }
    }
}
