import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
            try {
                Game game = new Game();
                game.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
