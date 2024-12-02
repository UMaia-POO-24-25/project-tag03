import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Food {
    public int x;
    public int y;
    public int grid;

    public Food(){
        Random rand = new Random();
        this.x = rand.nextInt(30);
        this.y = rand.nextInt(30);
    }


    public void show(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.fillRectangle(new TerminalPosition(this.x, this.y), new TerminalSize(1, 1), ' ');
    }

}
