package com.mydemo.demo.Utils;

import com.mydemo.demo.Entity.BirthDeathRate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static String TYPE = "text/csv";
    static String[] HEADERS = { "Period", "Birth_Death", "Region", "Count" };
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

//    create BufferedReader from InputStream
//    create CSVParser from the BufferedReader and CSV format
//    iterate over CSVRecords by Iterator with CsvParser.getRecords()
//    from each CSVRecord, use CSVRecord.get() to read and parse fields
    public static List<BirthDeathRate> csvToBirthDeathRate(InputStream is) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        List<BirthDeathRate> birthDeathRates = new ArrayList<BirthDeathRate>();
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            BirthDeathRate birthDeathRate = new BirthDeathRate(
                    csvRecord.get("Period"),
                    csvRecord.get("Birth_Death"),
                    csvRecord.get("Region"),
                    Integer.parseInt(csvRecord.get("Count"))
            );
            birthDeathRates.add(birthDeathRate);
        }
            return birthDeathRates;
    }
}
