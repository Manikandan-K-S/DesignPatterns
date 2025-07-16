class Singleton {
    // private static instance of the class
    private static Singleton instance;

    // Static varible to count the no of instance created totally
    private static int instanceCount = 0;

    // To store count of instances
    public final int count;

    // Making constructor as private so it cannot be instantiated outside
    private Singleton() {
        this.count = instanceCount++;
        System.out.println("Singleton Instance Created");
    }

    // Provides a public static method to get the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

public class SingletonExample {
    public static void main(String[] args) {

        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        obj1.showMessage();

        // Verify both references point to the same object
        System.out.println("Are both objects same? " + (obj1 == obj2));
    }
}