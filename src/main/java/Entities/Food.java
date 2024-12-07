package Entities;

import Logic.Position;
import Logic.Arena;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Food extends Arena {


    public Food(int x, int y){
        super(x, y);
    }


    public void show(TextGraphics graphics,int width, int height) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(width, height), ' ');
    }

}
