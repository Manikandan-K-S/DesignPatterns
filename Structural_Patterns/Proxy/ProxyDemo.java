// Document interface
interface Document {
    void display();
}

// Real Document (heavy object)
class RealDocument implements Document {
    private final String filename;

    public RealDocument(String filename) {
        this.filename = filename;
        loadFromDisk(); // Expensive operation
    }

    private void loadFromDisk() {
        System.out.println("Loading document: " + filename);
        try {
            Thread.sleep(2000); // simulate heavy load
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying: " + filename);
    }
}

// Proxy for Document
class DocumentProxy implements Document {
    private final String filename;
    private RealDocument realDocument;

    public DocumentProxy(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realDocument == null) {
            realDocument = new RealDocument(filename); // load only when needed
        }
        realDocument.display();
    }
}

// Client
public class ProxyDemo {
    public static void main(String[] args) {
        Document doc1 = new DocumentProxy("report1.pdf");
        Document doc2 = new DocumentProxy("report2.pdf");

        // At this point, nothing heavy loaded yet
        System.out.println("Documents created, but not loaded yet.\n");

        // Now load and display
        doc1.display(); // loads + displays
        System.out.println();

        doc1.display(); // already loaded, no delay
        System.out.println();

        doc2.display(); // loads + displays
    }
}
