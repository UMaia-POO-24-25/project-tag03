
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import javax.swing.text.Position;
import java.util.ArrayList;

public class Snake extends Arena{
    public int xspeed;
    public int yspeed;
    public int total;
    public ArrayList<Position> tail;

    public Snake(int x, int y){
        super(x, y);
        this.xspeed = 1; // Movimento inicial para a direita
        this.yspeed = 0;
        this.total = 0;
        this.tail = new ArrayList<>();
    }

    public void update(){
        this.tail.add(0, new Position(this.position.getX(), this.position.getY()));
        if (this.tail.size() > this.total + 1) {
            this.tail.remove(this.tail.size() - 1);
        }
        position.setX(position.getX() + xspeed);
        position.setY(position.getY() + yspeed);

        if (position.getX() > 60-1){
            position.setX(60-1);
        } else if (position.getX() < 0){
            position.setX(0);
        } else if (position.getY() > 30-1){
            position.setY(30-1);
        } else if (position.getY() < 0){
            position.setY(0);
        }
    }

    public void show(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        for (Position pos : this.tail) {
            graphics.fillRectangle(
                    new TerminalPosition(pos.getX(), pos.getY()),
                    new TerminalSize(1, 1), ' ');
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
        this.xspeed = x*1;
        this.yspeed= y*1;
    }

    public Position getPosition() {
        return position;
    }

    public boolean eat(Position position) {
        if (this.position.equals(position)) {
            this.total += 1;
            return true;
        }
        return false;
    }

}