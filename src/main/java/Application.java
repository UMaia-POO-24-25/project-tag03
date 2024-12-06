import Logic.Game;

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
