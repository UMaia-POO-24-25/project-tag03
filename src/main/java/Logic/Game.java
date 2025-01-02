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
        int height = 20;
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


        TerminalSize screenSize = screen.getTerminalSize();
        int width = screenSize.getColumns();

        String header = "Pontuação: " + map.getScore();
        graphics.putString((width - header.length()) / 2, 0, header);

        map.show(graphics, 1, 1);
        screen.refresh();
    }

    private void gameover() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        TerminalSize screenSize = screen.getTerminalSize();
        int width = screenSize.getColumns();
        int height = screenSize.getRows();

        for (int col = 0; col < width; col++) {
            graphics.putString(col, 0, "-");
            graphics.putString(col, height - 1, "-");
        }
        for (int row = 0; row < height; row++) {
            graphics.putString(0, row, "|");
            graphics.putString(width - 1, row, "|");
        }
        graphics.putString(0, 0, "+");
        graphics.putString(width - 1, 0, "+");
        graphics.putString(0, height - 1, "+");
        graphics.putString(width - 1, height - 1, "+");

        String title = " GAME OVER! ";
        String instruction = " Pressione qualquer tecla para sair... ";
        int titleX = (width - title.length()) / 2;
        int titleY = height / 2 - 1;
        int instructionX = (width - instruction.length()) / 2;
        int instructionY = height / 2 + 1;

        graphics.putString(titleX, titleY, title);
        graphics.putString(instructionX, instructionY, instruction);

        screen.refresh();

        screen.readInput();
        screen.close();
    }

    public void run() throws IOException {
        long lastUpdate = System.currentTimeMillis();
        long lastBombUpdate = System.currentTimeMillis();
        long updateInterval = 100;
        long bombUpdateInterval = 3000;

        while (true) {
            long now = System.currentTimeMillis();
            if (now - lastUpdate >= updateInterval) {
                map.updateSnake();
                show();
                lastUpdate = now;

                if (map.getGameOver()) {
                    gameover();
                    break;
                }
            }

            if (now - lastBombUpdate >= bombUpdateInterval) {
                map.updateBomb();
                show();
                lastBombUpdate = now;
            }

            KeyStroke key = screen.pollInput();
            if (key != null) {
                processKey(key);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.Character) {
            switch (key.getCharacter()) {
                case 'w' -> map.moveSnakeUp();
                case 'd' -> map.moveSnakeRight();
                case 'a' -> map.moveSnakeLeft();
                case 's' -> map.moveSnakeDown();
                case 'q' -> gameover();
            }
        } else if (key.getKeyType() == KeyType.EOF) {
            gameover();
        }
    }
}