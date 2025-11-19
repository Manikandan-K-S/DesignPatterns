// Decorator Pattern Example - Coffee Shop
public class DecoratorPatternDemo {

    // Component
    interface Coffee {
        String getDescription();
        double getCost();
    }

    // Concrete Component
    static class SimpleCoffee implements Coffee {
        public String getDescription() {
            return "Simple Coffee";
        }
        public double getCost() {
            return 5.0;
        }
    }

    // Decorator
    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee decoratedCoffee;

        public CoffeeDecorator(Coffee coffee) {
            this.decoratedCoffee = coffee;
        }

        public String getDescription() {
            return decoratedCoffee.getDescription();
        }

        public double getCost() {
            return decoratedCoffee.getCost();
        }
    }

    // Concrete Decorators
    static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }
        public String getDescription() {
            return super.getDescription() + ", Milk";
        }
        public double getCost() {
            return super.getCost() + 1.5;
        }
    }

    static class SugarDecorator extends CoffeeDecorator {
        public SugarDecorator(Coffee coffee) {
            super(coffee);
        }
        public String getDescription() {
            return super.getDescription() + ", Sugar";
        }
        public double getCost() {
            return super.getCost() + 0.5;
        }
    }

    static class ChocolateDecorator extends CoffeeDecorator {
        public ChocolateDecorator(Coffee coffee) {
            super(coffee);
        }
        public String getDescription() {
            return super.getDescription() + ", Chocolate";
        }
        public double getCost() {
            return super.getCost() + 2.0;
        }
    }

    // Test
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new ChocolateDecorator(coffee);

        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());
    }
}
