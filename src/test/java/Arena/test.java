package Arena;

import Logic.Arena;
import Entities.Food;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class test {

    @Test
    void testArenaInitialization() {
        Arena arena = new Food(5, 5, "#FF0000");
        assertNotNull(arena);
        assertEquals(5, arena.position.getX());
        assertEquals(5, arena.position.getY());
    }
}
