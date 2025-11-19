// Composite Pattern Example - Company Organization
import java.util.*;

public class CompositePatternDemo {

    // Component
    interface Employee {
        void showDetails();
    }

    // Leaf
    static class Developer implements Employee {
        private String name;
        public Developer(String name) {
            this.name = name;
        }
        public void showDetails() {
            System.out.println("Developer: " + name);
        }
    }

    // Leaf
    static class Designer implements Employee {
        private String name;
        public Designer(String name) {
            this.name = name;
        }
        public void showDetails() {
            System.out.println("Designer: " + name);
        }
    }

    // Composite
    static class Manager implements Employee {
        private String name;
        private List<Employee> subordinates = new ArrayList<>();

        public Manager(String name) {
            this.name = name;
        }

        public void add(Employee e) {
            subordinates.add(e);
        }

        public void remove(Employee e) {
            subordinates.remove(e);
        }

        public void showDetails() {
            System.out.println("Manager: " + name);
            for (Employee e : subordinates) {
                e.showDetails();
            }
        }
    }

    // Test
    public static void main(String[] args) {
        Employee dev1 = new Developer("Alice");
        Employee dev2 = new Developer("Bob");
        Employee des1 = new Designer("Charlie");

        Manager manager = new Manager("Diana");
        manager.add(dev1);
        manager.add(dev2);
        manager.add(des1);

        manager.showDetails();
    }
}
