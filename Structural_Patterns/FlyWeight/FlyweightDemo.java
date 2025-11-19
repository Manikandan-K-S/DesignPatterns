import java.util.*;

// Flyweight interface
interface Flyweight {
    void display(int position, int fontSize);
}

// Concrete Flyweight
class CharacterFlyweight implements Flyweight {
    private final char symbol; // intrinsic state

    public CharacterFlyweight(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void display(int position, int fontSize) {
        System.out.println("Character: " + symbol +
                " at position " + position +
                " with font size " + fontSize);
    }
}

// Flyweight Factory
class CharacterFactory {
    private final Map<java.lang.Character, Flyweight> characters = new HashMap<>();

    public Flyweight getCharacter(char symbol) {
        java.lang.Character key = java.lang.Character.valueOf(symbol); // ✅ safe
        characters.putIfAbsent(key, new CharacterFlyweight(symbol));
        return characters.get(key);
    }
}

// Client
public class FlyweightDemo {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        String document = "aabaac";
        int position = 1;

        for (char c : document.toCharArray()) {
            Flyweight character = factory.getCharacter(c);
            character.display(position++, 12);
        }
    }
}
