import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

class Logger {
    private static Logger instance;
    private static final String LOG_FILE = "app_log.txt";
    private PrintWriter writer;

    // Private constructor
    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter(LOG_FILE, true)); // Append mode
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Public method to get the singleton instance (synchronized for thread safety)
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Logging method
    public void log(String message) {
        String logEntry = "[" + LocalDateTime.now() + "] " + message;
        writer.println(logEntry);
        writer.flush(); // Ensure it's written immediately
        System.out.println(logEntry); // Optional: also print to console
    }
}

public class LoggerApp {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started");

        Logger logger2 = Logger.getInstance();
        logger2.log("Performing task A...");

        Logger logger3 = Logger.getInstance();
        logger3.log("Application shutting down...");

        // Demonstrate Singleton behavior
        System.out.println("Are all logger instances same? " + (logger1 == logger2));
    }
}
