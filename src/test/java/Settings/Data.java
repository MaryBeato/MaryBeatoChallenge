package Settings;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Data {
    public List<String[]> getData(String file){
        List<String[]> menData = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(file));
            CSVReader csvReader = new CSVReader(reader);

            menData = csvReader.readAll();

        }catch(IOException | CsvException e){
            e.printStackTrace();
        }

        return menData;


    }
}
