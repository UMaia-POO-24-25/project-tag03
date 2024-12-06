import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.text.Position;


public class Food extends Arena{
    public Food(int x, int y){
        super(x, y);
    }


    public void show(TextGraphics graphics,int width, int height) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
    }

}
