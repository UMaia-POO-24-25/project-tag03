package bomb;
import Entities.Bomb;
import Entities.Snake;
import Logic.Map;
import Logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class test {


    @Test
    void testBombOnMap() {
        Map map = new Map(20, 20);
        Bomb bomb = map.createBomb();
        assertNotNull(bomb, "A bomba deveria estar no mapa.");
        assertTrue(bomb.position.getX() >= 0 && bomb.position.getX() < 20, "A posição X da bomba está fora do limite.");
        assertTrue(bomb.position.getY() >= 0 && bomb.position.getY() < 20, "A posição Y da bomba está fora do limite.");
    }

    @Test
    void testSnakeCollidesWithBomb() {
        Map map = new Map(20, 20);
        Snake snake = new Snake(5, 5);
        Bomb bomb = new Bomb(5, 5, "#000000");

        map.updateSnake();
        map.s = snake;


        assertTrue(map.inBomb(snake.position), "A colisão com a bomba não foi detectada corretamente.");
    }
}
