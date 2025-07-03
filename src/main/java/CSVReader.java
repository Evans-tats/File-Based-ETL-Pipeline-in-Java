import java.io.*;
import java.util.*;

public class CSVReader {

    public List<DataRecord> readCSV(String filepath) throws IOException {
        List<DataRecord> records = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length < 4 ) continue;
                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];
                    int age = Integer.parseInt(parts[3]);

                    records.add(new DataRecord(id,name,email,age));
                } catch (NumberFormatException e) {
                    System.out.println("skipping invalid row: " + line);
                }
            }
        }
        return records;
    }
}