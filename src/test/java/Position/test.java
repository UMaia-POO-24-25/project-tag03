package Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import Logic.Position;

public class test {

    @Test
    public void testPositionInitialization() {

        Position position = new Position(5, 10);

        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    public void testPositionEquality() {
        Position position1 = new Position(5, 10);
        Position position2 = new Position(5, 10);

        assertTrue(position1.equals(position2));

        Position position3 = new Position(10, 15);

        assertFalse(position1.equals(position3));
    }
}
