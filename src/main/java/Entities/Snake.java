package Entities;

import Logic.Position;
import Logic.Arena;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Snake extends Arena {
    public int xspeed;
    public int yspeed;
    private int total;
    public ArrayList<Position> tail;

    public Snake(int x, int y){
        super(x, y);
        this.xspeed = 1; // Movimento inicial para a direita
        this.yspeed = 0;
        this.total = 0;
        this.tail = new ArrayList<>();
    }

    public void update(){
        position.setX(position.getX() + xspeed);
        position.setY(position.getY() + yspeed);

        this.tail.add(0, new Position(this.position.getX(), this.position.getY()));
        if (this.tail.size() > this.total + 1) {
            this.tail.remove(this.tail.size() - 1);
        }

    }
    public Position getPosition() {
        return this.position;
    }

    public void show(TextGraphics graphics, int width, int height){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        for (Position pos : this.tail) {
            graphics.fillRectangle(
                    new TerminalPosition(pos.getX(), pos.getY()),
                    new TerminalSize(width, height), ' ');
        }
    }



    public boolean death() {
        for (int i = 1; i < this.tail.size(); i++) {
            if (this.position.equals(this.tail.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void dir(int x, int y){

        if (this.total > 0) {
            if ((x != 0 && x == -xspeed) || (y != 0 && y == -yspeed)) {
                return;
            }
        }
        this.xspeed = x;
        this.yspeed= y;
    }

    public  void setTotal(int x){
        this.total += x;
    }


}