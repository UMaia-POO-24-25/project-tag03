package Entities;

import javax.swing.text.Position;

public class Bomb extends Food{
    public Bomb(int x, int y, String color){
        super(x, y, color);
    }
    public void setPosition(Logic.Position newPosition) {
        this.position = (Logic.Position) newPosition;
    }
}
