import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Logger {
    private static Logger instance;
    private PrintWriter writer;

    // Private constructor to prevent external instantiation
    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter("app.log", true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Public method to provide a global point of access
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Logging method
    public void log(String message) {
        writer.println(message);
    }
}

public class LoggerApp {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Application started.");
        logger2.log("User logged in.");

        System.out.println("Are both logger instances same? " + (logger1 == logger2));
    }

}
