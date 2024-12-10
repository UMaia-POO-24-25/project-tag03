package Snake;

import Entities.Snake;
import Logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class test {

    @Test
    public void testInitialPosition() {
        Snake snake = new Snake(5, 5);
        assertEquals(5, snake.position.getX());
        assertEquals(5, snake.position.getY());
    }

    @Test
    public void testMovement() {
        Snake snake = new Snake(5, 5);
        snake.dir(1, 0); // Move para direita
        snake.update();
        assertEquals(6, snake.position.getX());
        assertEquals(5, snake.position.getY());
    }


    @Test
    public void testDirectionChange() {
        Snake snake = new Snake(5, 5);
        snake.dir(0, -1); // Move para cima
        assertEquals(0, snake.xspeed);
        assertEquals(-1, snake.yspeed);
    }

}
