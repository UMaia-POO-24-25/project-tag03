package Logic;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;

public class Game {
    private final Screen screen;
    private final Map map;

    public Game() throws IOException {

        int width = 30;
        int height = 30;
        this.map = new Map(width, height);

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


    private void show() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        map.show(graphics);
        screen.refresh();
    }
    private void gameover() throws IOException {
        screen.close();
        System.out.print("Logic.Game Over!");
    }

    public void run() throws IOException {
        while (true) {
            map.updateSnake();
            show();
            KeyStroke key = screen.pollInput();


            if (key != null) {
                processKey(key);
            }

            if (map.getGameOver()){
                gameover();
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
            map.moveSnakeUp();
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') {
            map.moveSnakeRight();
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') {
            map.moveSnakeLeft();
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') {
            map.moveSnakeDown();
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        } else if (key.getKeyType() == KeyType.EOF) {
            System.exit(0);
        }
    }
}