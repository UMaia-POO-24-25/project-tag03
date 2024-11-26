import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Application {

    public Application(Screen screen) {
    }

    public static void main(String[] args) {
        try {
            // Configurando o terminal e a tela
            Screen screen = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(60, 30))
                    .createScreen();
            screen.startScreen();

            // Inicializando o jogo
            Application game = new Application(screen);
            game.start();

        } catch (IOException e) {
            System.err.println("Erro ao iniciar a aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void start() {
        System.out.println("Jogo iniciado!");
    }
}
