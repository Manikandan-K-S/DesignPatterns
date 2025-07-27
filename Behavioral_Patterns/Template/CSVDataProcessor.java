import java.io.*;
import java.util.*;

public class CSVDataProcessor extends DataProcessor {
    private List<String[]> rows = new ArrayList<>();

    protected void readData() {
        System.out.println("Reading from data.csv...");
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void processDataInternally() {
        System.out.println("Processing CSV data...");
        for (String[] row : rows) {
            System.out.println("Row: " + Arrays.toString(row));
        }
    }

    protected void writeData() {
        System.out.println("Writing CSV output to console (mock)...");
        System.out.println("Total rows: " + rows.size());
    }
}
