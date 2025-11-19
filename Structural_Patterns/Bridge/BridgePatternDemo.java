// Bridge Pattern Example - Notification System
public class BridgePatternDemo {

    // Implementor
    interface MessageSender {
        void sendMessage(String message);
    }

    // Concrete Implementors
    static class EmailSender implements MessageSender {
        public void sendMessage(String message) {
            System.out.println("Email sent: " + message);
        }
    }

    static class SmsSender implements MessageSender {
        public void sendMessage(String message) {
            System.out.println("SMS sent: " + message);
        }
    }

    // Abstraction
    static abstract class Message {
        protected MessageSender sender;
        public Message(MessageSender sender) {
            this.sender = sender;
        }
        abstract void send(String msg);
    }

    // Refined Abstractions
    static class AlertMessage extends Message {
        public AlertMessage(MessageSender sender) {
            super(sender);
        }
        public void send(String msg) {
            sender.sendMessage("[ALERT] " + msg);
        }
    }

    static class ReminderMessage extends Message {
        public ReminderMessage(MessageSender sender) {
            super(sender);
        }
        public void send(String msg) {
            sender.sendMessage("[REMINDER] " + msg);
        }
    }

    // Test
    public static void main(String[] args) {
        Message alert = new AlertMessage(new EmailSender());
        alert.send("Server is down!");

        Message reminder = new ReminderMessage(new SmsSender());
        reminder.send("Meeting at 10 AM");
    }
}
