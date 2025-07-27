public class TemplateMethodDemo {
    public static void main(String[] args) {
        System.out.println("=== CSV Processing ===");
        DataProcessor csv = new CSVDataProcessor();
        csv.processData();

        System.out.println("\n=== JSON Processing ===");
        DataProcessor json = new JSONDataProcessor();
        json.processData();
    }
}
