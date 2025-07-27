import java.util.*;

// Observer Interface
interface Observer {
    void update(float temperature, float humidity);
}

// Subject Interface
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;

    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity);
        }
    }
}

// Concrete Observer 1
class WeatherDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("WeatherDisplay → Temperature: " + temperature + "°C, Humidity: " + humidity + "%");
    }
}

// Concrete Observer 2
class StatisticsDisplay implements Observer {
    private List<Float> temps = new ArrayList<>();

    @Override
    public void update(float temperature, float humidity) {
        temps.add(temperature);
        float avg = (float) temps.stream().mapToDouble(Float::doubleValue).average().orElse(0);
        System.out.println("StatisticsDisplay → Avg Temperature: " + String.format("%.2f", avg) + "°C");
    }
}

// Concrete Observer 3
class LoggerDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("LoggerDisplay → Logged data: { temp: " + temperature + ", humidity: " + humidity + " }");
    }
}

// Main Demo
public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        // Create and register observers
        Observer display = new WeatherDisplay();
        Observer stats = new StatisticsDisplay();
        Observer logger = new LoggerDisplay();

        station.registerObserver(display);
        station.registerObserver(stats);
        station.registerObserver(logger);

        // Simulate new data
        System.out.println("=== First Update ===");
        station.setMeasurements(28.5f, 65.0f);

        System.out.println("\n=== Second Update ===");
        station.setMeasurements(30.2f, 70.0f);

        // Unregister one observer
        station.removeObserver(logger);

        System.out.println("\n=== Third Update (logger removed) ===");
        station.setMeasurements(27.0f, 60.0f);
    }
}
