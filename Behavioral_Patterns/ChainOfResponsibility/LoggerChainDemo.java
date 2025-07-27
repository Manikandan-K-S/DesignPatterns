// Abstract Handler
abstract class Logger {
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int ERROR = 3;

    protected int level;
    protected Logger nextLogger;

    public void setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract void write(String message);
}

// Concrete Handler: Debug Logger
class DebugLogger extends Logger {
    public DebugLogger() {
        this.level = DEBUG;
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}

// Concrete Handler: Info Logger
class InfoLogger extends Logger {
    public InfoLogger() {
        this.level = INFO;
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}

// Concrete Handler: Error Logger
class ErrorLogger extends Logger {
    public ErrorLogger() {
        this.level = ERROR;
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}

// Client
public class LoggerChainDemo {
    public static void main(String[] args) {
        // Build the chain: Debug → Info → Error
        Logger debug = new DebugLogger();
        Logger info = new InfoLogger();
        Logger error = new ErrorLogger();

        debug.setNext(info);
        info.setNext(error);

        Logger loggerChain = debug;

        // Test messages
        loggerChain.logMessage(Logger.DEBUG, "This is a debug message.");
        loggerChain.logMessage(Logger.INFO, "This is an info message.");
        loggerChain.logMessage(Logger.ERROR, "This is an error message.");
    }
}
