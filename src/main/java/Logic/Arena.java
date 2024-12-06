package Logic;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Arena {
    protected Position position;

    public Arena(int x, int y) {
        this.position = new Position(x, y);
    }

    public abstract void show(TextGraphics graphics, int width, int height);
}
