public class AdapterPatternDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new BankPaymentAdapter(new BankPaymentAPI());
        EcommerceClient client = new EcommerceClient();
        client.checkout(processor);
    }
}

// Target Interface
interface PaymentProcessor {
    void processPayment(double amount, String currency);
}

// Adaptee - Third-party API
class BankPaymentAPI {
    public void makeTransaction(long amountInPaise, String currencyCode) {
        System.out.printf("Processing payment of %d paise in %s via Bank API%n", amountInPaise, currencyCode);
    }
}

// Adapter
class BankPaymentAdapter implements PaymentProcessor {
    private final BankPaymentAPI bankAPI;

    public BankPaymentAdapter(BankPaymentAPI bankAPI) {
        this.bankAPI = bankAPI;
    }

    @Override
    public void processPayment(double amount, String currency) {
        long amountInPaise = Math.round(amount * 100); // convert to paise
        bankAPI.makeTransaction(amountInPaise, currency);
    }
}

// Client
class EcommerceClient {
    public void checkout(PaymentProcessor processor) {
        System.out.println("Initiating checkout...");
        processor.processPayment(2499.75, "INR");
        System.out.println("Checkout complete.");
    }
}
