import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONDataProcessor extends DataProcessor {
    private JSONArray data;

    protected void readData() {
        System.out.println("Reading from data.json...");
        try (InputStream is = new FileInputStream("data.json")) {
            JSONTokener tokener = new JSONTokener(is);
            data = new JSONArray(tokener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void processDataInternally() {
        System.out.println("Processing JSON data...");
        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            System.out.println("Name: " + obj.getString("name") + ", Age: " + obj.getInt("age"));
        }
    }

    protected void writeData() {
        System.out.println("Writing JSON output to console (mock)...");
        System.out.println("Total entries: " + data.length());
    }
}
