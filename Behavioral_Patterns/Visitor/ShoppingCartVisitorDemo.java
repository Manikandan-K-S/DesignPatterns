// Visitor Interface
interface ShoppingCartVisitor {
    int visit(Book book);

    int visit(Fruit fruit);
}

// Concrete Visitor
class PriceCalculator implements ShoppingCartVisitor {
    public int visit(Book book) {
        int cost = book.getPrice();
        System.out.println("Book: " + book.getName() + " = Rs." + cost);
        return cost;
    }

    public int visit(Fruit fruit) {
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println("Fruit: " + fruit.getName() + " = Rs." + cost);
        return cost;
    }
}

// Element Interface
interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}

// Concrete Element: Book
class Book implements ItemElement {
    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

// Concrete Element: Fruit
class Fruit implements ItemElement {
    private String name;
    private int pricePerKg;
    private int weight;

    public Fruit(String name, int pricePerKg, int weight) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

// Main class
public class ShoppingCartVisitorDemo {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[] {
                new Book("Design Patterns", 500),
                new Fruit("Apple", 100, 2),
                new Fruit("Banana", 40, 5)
        };

        ShoppingCartVisitor visitor = new PriceCalculator();
        int total = 0;
        for (ItemElement item : items) {
            total += item.accept(visitor);
        }

        System.out.println("Total Bill: Rs." + total);
    }
}
