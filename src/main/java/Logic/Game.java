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
    private long updateInterval;
    private int lastScoreForLevelUp;
    private int level;
    private int foodEatenSinceLastBomb; 

    public Game() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setForceAWTOverSwing(true);

        Terminal terminal = terminalFactory.createTerminal();
        TerminalSize terminalSize = terminal.getTerminalSize();

        int width = terminalSize.getColumns() - 2;
        int height = terminalSize.getRows() - 3;

        this.map = new Map(width, height);

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        this.updateInterval = 100; 
        this.map.addBomb(); 
        this.lastScoreForLevelUp = 0;
        this.level = 1;
        this.foodEatenSinceLastBomb = 0;

    }

    private void show() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        TerminalSize screenSize = screen.getTerminalSize();
        int width = screenSize.getColumns();
        int height = screenSize.getRows();

        for (int col = 0; col < width; col++) {
            graphics.putString(col, 0, "-");
            graphics.putString(col, height - 1, "-");
        }
        for (int row = 1; row < height - 1; row++) {
            graphics.putString(0, row, "|");
            graphics.putString(width - 1, row, "|");
        }
        graphics.putString(0, 0, "+");
        graphics.putString(width - 1, 0, "+");
        graphics.putString(0, height - 1, "+");
        graphics.putString(width - 1, height - 1, "+");

        String header = "Pontuação: " + map.getScore() + "  Nível: " + level;
        graphics.putString((width - header.length()) / 2, 0, header);

        map.show(graphics, 1, 1);
        screen.refresh();
    }


    private void increaseLevel() {
        if (map.getScore() > lastScoreForLevelUp) {
            lastScoreForLevelUp = map.getScore();
            foodEatenSinceLastBomb++;
            if (foodEatenSinceLastBomb == 5) {
                if (map.getBombCount() < 15) {
                    map.addBomb(); 
                }
                foodEatenSinceLastBomb = 0; 
            }
            if (map.getScore() % 5 == 0) {
                level++;
                updateInterval = Math.max(50, updateInterval - 10);
            }
        }
    }
    
    
    

    private void startScreen() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        TerminalSize screenSize = screen.getTerminalSize();
        int width = screenSize.getColumns();
        int height = screenSize.getRows();

        for (int col = 0; col < width; col++) {
            graphics.putString(col, 0, "-");
            graphics.putString(col, height - 1, "-");
        }
        for (int row = 1; row < height - 1; row++) {
            graphics.putString(0, row, "|");
            graphics.putString(width - 1, row, "|");
        }
        graphics.putString(0, 0, "+");
        graphics.putString(width - 1, 0, "+");
        graphics.putString(0, height - 1, "+");
        graphics.putString(width - 1, height - 1, "+");

        String title = " Bem-vindo ao Jogo da Cobra ";
        String optionStart = " Pressione 'S' para iniciar o jogo ";
        String optionExit = " Pressione 'Q' para sair ";

        graphics.putString((width - title.length()) / 2, height / 2 - 2, title);
        graphics.putString((width - optionStart.length()) / 2, height / 2, optionStart);
        graphics.putString((width - optionExit.length()) / 2, height / 2 + 2, optionExit);

        screen.refresh();

        while (true) {
            KeyStroke key = screen.readInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.Character) {
                    char c = key.getCharacter();
                    if (c == 's' || c == 'S') {
                        return;
                    } else if (c == 'q' || c == 'Q') {
                        screen.close();
                        System.exit(0);
                    }
                }
            }
        }
    }

    public void run() throws IOException {
        startScreen();

        long lastUpdate = System.currentTimeMillis();
        long lastBombUpdate = System.currentTimeMillis();
        long bombUpdateInterval = 3000;

        while (true) {
            long now = System.currentTimeMillis();
            if (now - lastUpdate >= updateInterval) {
                map.updateSnake();
                show();
                increaseLevel();
                lastUpdate = now;

                if (map.getGameOver()) {
                    if (gameover()) {
                        map.reset();
                        level = 1;
                        updateInterval = 150;
                        continue;
                    } else {
                        break;
                    }
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

    private boolean gameover() throws IOException {
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
        String scoreMessage = " Sua pontuação: " + map.getScore() + "  Nível: " + level;
        String restartOption = " Pressione 'R' para reiniciar ou 'Q' para sair ";

        int titleX = (width - title.length()) / 2;
        int scoreX = (width - scoreMessage.length()) / 2;
        int restartX = (width - restartOption.length()) / 2;

        int titleY = height / 2 - 2;
        int scoreY = height / 2;
        int restartY = height / 2 + 2;

        graphics.putString(titleX, titleY, title);
        graphics.putString(scoreX, scoreY, scoreMessage);
        graphics.putString(restartX, restartY, restartOption);

        screen.refresh();

        while (true) {
            KeyStroke key = screen.readInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.Character) {
                    char c = key.getCharacter();
                    if (c == 'r' || c == 'R') {
                        return true;
                    } else if (c == 'q' || c == 'Q') {
                        screen.close();
                        System.exit(0);
                    }
                }
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
