package Entities;

import Logic.Position;
import Logic.Arena;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Food extends Arena {
    private String color;
    public Position position;

    public Food(int x, int y, String color){
        super(x, y);
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }


    public void show(TextGraphics graphics,int width, int height) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(width, height), ' ');
    }

}
