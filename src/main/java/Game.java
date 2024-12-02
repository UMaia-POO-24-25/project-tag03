import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TerminalPosition;


import java.io.IOException;

public class Game {
    private Screen screen;
    private int width;
    private int height;
    private Snake s;
    private int grid;

    public Game() throws IOException {

        s = new Snake(grid, grid);

        this.grid = 1;
        this.width = 60;
        this.height = 30;

        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize)
                .setForceAWTOverSwing(true);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }
    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#828282"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        s.show(graphics);
        s.update();
        screen.refresh();
    }
    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.pollInput();


            if (key != null) {
                processKey(key);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') {
            s.dir(0, -1);
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') {
            s.dir(1, 0);
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') {
            s.dir(-1, 0);
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') {
            s.dir(0, 1);
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        } else if (key.getKeyType() == KeyType.EOF) {
            System.exit(0);
        }
    }
}