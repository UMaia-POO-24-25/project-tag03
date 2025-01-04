package Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import Logic.Map;
public class test {

    @Test
    void testMapInitialization() {
        Map map = new Map(10, 10);
        assertEquals(10, map.getWidth(), "A largura do mapa está incorreta.");
        assertEquals(10, map.getHeight(), "A altura do mapa está incorreta.");
    }
}
