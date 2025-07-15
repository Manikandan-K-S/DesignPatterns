import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
        Properties env = new Properties();
        try (FileInputStream fis = new FileInputStream(".env")) {
            env.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load .env");
            e.printStackTrace();
            return;
        }

        final String from = env.getProperty("EMAIL_USER");
        final String password = env.getProperty("EMAIL_PASS");
        final String to = env.getProperty("EMAIL_TO");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Factory Method: Email Notification");
            message.setText("This is a real email sent using the Factory Method pattern!");

            Transport.send(message);
            System.out.println("Email Notification Sent Successfully!");

        } catch (MessagingException e) {
            System.err.println("Failed to send email");
            e.printStackTrace();
        }
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
        email.notifyUser(); // Sends real email!
    }
}
