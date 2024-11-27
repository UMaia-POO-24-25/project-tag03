import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        try {
            Game game = new Game(60, 30);

            // Iniciando o jogo
            game.start();

        } catch (IOException e) {
            System.err.println("Erro ao iniciar o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
