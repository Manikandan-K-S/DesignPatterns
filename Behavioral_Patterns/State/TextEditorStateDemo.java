// State interface
interface EditorState {
    void type(TextEditor context, char c);
}

// Insert mode: adds characters
class InsertMode implements EditorState {
    public void type(TextEditor context, char c) {
        context.buffer.insert(context.cursor, c);
        context.cursor++;
        System.out.println("Inserted '" + c + "' → " + context.getText());
    }
}

// Overwrite mode: replaces existing characters
class OverwriteMode implements EditorState {
    public void type(TextEditor context, char c) {
        if (context.cursor < context.buffer.length()) {
            context.buffer.setCharAt(context.cursor, c);
        } else {
            context.buffer.append(c);
        }
        context.cursor++;
        System.out.println("Overwritten with '" + c + "' → " + context.getText());
    }
}

// Context class
class TextEditor {
    StringBuilder buffer = new StringBuilder();
    int cursor = 0;
    private EditorState currentState;

    public TextEditor() {
        currentState = new InsertMode();  // default mode
    }

    public void setState(EditorState state) {
        this.currentState = state;
    }

    public void toggleMode() {
        if (currentState instanceof InsertMode) {
            System.out.println("Switched to Overwrite Mode");
            setState(new OverwriteMode());
        } else {
            System.out.println("Switched to Insert Mode");
            setState(new InsertMode());
        }
    }

    public void type(char c) {
        currentState.type(this, c);
    }

    public String getText() {
        return buffer.toString();
    }
}

// Main class
public class TextEditorStateDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.type('H');
        editor.type('e');
        editor.type('l');
        editor.type('l');
        editor.type('o');

        editor.toggleMode();  // Switch to overwrite
        editor.type('!');
        editor.type(' ');
        editor.type('J');
        editor.type('a');
        editor.type('v');

        editor.toggleMode();  // Switch back to insert
        editor.type('a');
    }
}
