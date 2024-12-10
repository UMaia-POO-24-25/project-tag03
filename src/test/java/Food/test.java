package Food;

import Entities.Food;
import Logic.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

public class test {

    @Test
    void testFoodInitialization() {

        int x = 5;
        int y = 10;
        String color = "#FF0000";
        Food food = new Food(x, y, color);


        Position position = food.position;


        assertEquals(x, position.getX(), "A posição X da comida está incorreta.");
        assertEquals(y, position.getY(), "A posição Y da comida está incorreta.");
    }

    @Test
    void testFoodShow() {

        int x = 5;
        int y = 10;
        String color = "#FF0000";
        Food food = new Food(x, y, color);
        TextGraphics graphicsMock = mock(TextGraphics.class);


        food.show(graphicsMock, 1, 1);


        verify(graphicsMock).setBackgroundColor(TextColor.Factory.fromString(color));
        verify(graphicsMock).fillRectangle(
                new com.googlecode.lanterna.TerminalPosition(x, y),
                new com.googlecode.lanterna.TerminalSize(1, 1),
                ' '
        );
    }
}
