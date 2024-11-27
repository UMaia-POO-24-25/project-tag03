import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {

    private final TerminalScreen screen;
    private final Snake snake;

    public Game(int width, int height) throws IOException {
        // Configura o terminal e a tela
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height))
                .createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        // Inicializa a cobra
        snake = new Snake();
    }

    public void start() {
        try {
            TextGraphics graphics = screen.newTextGraphics(); // Objeto para desenhar

            while (true) {
                snake.update();
                screen.clear();
                snake.show(graphics);
                screen.refresh();

                Thread.sleep(200);

                if (snake.x < 0 || snake.y < 0 ||
                        snake.x >= screen.getTerminalSize().getColumns() ||
                        snake.y >= screen.getTerminalSize().getRows()) {
                    System.out.println("Game Over!");
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Erro no jogo: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                screen.stopScreen();
            } catch (IOException e) {
                System.err.println("Erro ao fechar o terminal: " + e.getMessage());
            }
        }
    }
}
