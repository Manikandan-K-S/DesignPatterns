// FactoryMethodDemo.java

// 1. Product Interface
interface Notification {
    void notifyUser();
}

// 2. Concrete Products
class SMSNotification implements Notification {
    public void notifyUser() {
        System.out.println("Sending SMS Notification");
    }
}

class EmailNotification implements Notification {
    public void notifyUser() {
        System.out.println("Sending Email Notification");
    }
}

// 3. Creator (Abstract Factory)
abstract class NotificationFactory {
    public abstract Notification createNotification();
}

// 4. Concrete Factories
class SMSFactory extends NotificationFactory {
    public Notification createNotification() {
        return new SMSNotification();
    }
}

class EmailFactory extends NotificationFactory {
    public Notification createNotification() {
        return new EmailNotification();
    }
}

// 5. Client Code
public class FactoryMethodDemo {
    public static void main(String[] args) {
        NotificationFactory factory;

        // Using SMS Factory
        factory = new SMSFactory();
        Notification sms = factory.createNotification();
        sms.notifyUser(); // Output: Sending SMS Notification

        // Using Email Factory
        factory = new EmailFactory();
        Notification email = factory.createNotification();
        email.notifyUser(); // Output: Sending Email Notification
    }
}
