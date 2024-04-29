import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// Flyweight class representing character properties (font, color, size)
public class TextEditor {
    private final String font;
    private final String color;
    private final int size;

    public TextEditor(String font, String color, int size) {
        this.font = font;
        this.color = color;
        this.size = size;
    }

    public String getFont() {
        return font;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextEditor)) return false;
        TextEditor that = (TextEditor) o;
        return size == that.size &&
                font.equals(that.font) &&
                color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(font, color, size);
    }
}

// Factory class managing flyweight objects
class CharacterFactory {
    private final Map<TextEditor, TextEditor> propertiesMap = new HashMap<>();

    public TextEditor getCharacterProperties(String font, String color, int size) {
        TextEditor properties = new TextEditor(font, color, size);
        if (!propertiesMap.containsKey(properties)) {
            propertiesMap.put(properties, properties);
        }
        return propertiesMap.get(properties);
    }
}

class Character {
    private final char character;
    private final TextEditor properties;

    public Character(char character, TextEditor properties) {
        this.character = character;
        this.properties = properties;
    }

    public char getCharacter() {
        return character;
    }

    public TextEditor getProperties() {
        return properties;
    }
}

class Document {
    private final List<Character> characters = new ArrayList<>();

    public void addCharacter(char character, TextEditor properties) {
        this.characters.add(new Character(character, properties));
    }

    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Character character : characters) {
                writer.write(character.getCharacter() + " ");
                TextEditor properties = character.getProperties();
                writer.write("Font: " + properties.getFont() + ", Color: " + properties.getColor() + ", Size: " + properties.getSize() + "\n");
            }
        }
    }

    public void loadFromFile(String filePath) throws IOException {
        characters.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int c;
            while ((c = reader.read()) != -1) {
                characters.add(new Character((char) c, null));
            }
        }
    }

    public void display() {
        for (Character character : characters) {
            System.out.print(character.getCharacter());
        }
        System.out.println();
    }
}