import java.io.*;

public class TextDriver {

	public static void main(String[] args) {
		Document document = new Document();
        CharacterFactory characterFactory = new CharacterFactory();

        // Define different properties
        TextEditor properties1 = characterFactory.getCharacterProperties("Arial", "Red", 12);
        TextEditor properties2 = characterFactory.getCharacterProperties("Calibri", "Blue", 14);
        TextEditor properties3 = characterFactory.getCharacterProperties("Verdana", "Black", 16);
        TextEditor properties4 = characterFactory.getCharacterProperties("Helvetica", "Green", 18);

        // Add characters to the document
        document.addCharacter('H', properties1);
        document.addCharacter('e', properties1);
        document.addCharacter('l', properties1);
        document.addCharacter('l', properties1);
        document.addCharacter('o', properties2);
        document.addCharacter('W', properties2);
        document.addCharacter('o', properties2);
        document.addCharacter('r', properties2);
        document.addCharacter('l', properties3);
        document.addCharacter('d', properties3);
        document.addCharacter('C', properties3);
        document.addCharacter('S', properties3);
        document.addCharacter('5', properties4);
        document.addCharacter('8', properties4);
        document.addCharacter('0', properties4);
        document.addCharacter('0', properties4);

        try {
            document.saveToFile("document.txt");
            System.out.println("Document saved to file: document.txt");
        } catch (IOException e) {
            System.err.println("Error saving document: " + e.getMessage());
        }

        document.display();

	}

}
