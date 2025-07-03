import java.util.List;

public class ETLRunner {

    public static void main(String[] args) throws Exception {

        CSVReader reader = new CSVReader();
        DataTransformer transformer = new DataTransformer();
        DataBaseWriter writer = new DataBaseWriter();

        List<DataRecord> records = reader.readCSV("src/input/data.csv");
        List<DataRecord> cleanRecords = transformer.cleanData(records);
        writer.writeToDatabase(cleanRecords);

        System.out.println("ETL Pipeline Completed.");
    }
}
