package Logic;

import Entities.Bomb;
import Entities.Food;
import Entities.Snake;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;

public class Map {
    private static final Random rand = new Random();
    private static final int GRID_SIZE = 1;
    private static final String FOOD_COLOR = "#FF0000";
    private static final String BOMB_COLOR = "#000000";

    private final int height;
    private final int width;
    public Snake s;
    private Food f;
    public Bomb b;
    private boolean gameOver;
    public String message;

    public Map(int width, int height){
        this.height = height;
        this.width = width;
        this.s = new Snake(GRID_SIZE, GRID_SIZE);
        this.f = createFood();
        this.b = createBomb();
        this.gameOver = false;
    }

    public void show(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#828282"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        f.show(graphics, GRID_SIZE, GRID_SIZE);
        s.show(graphics, GRID_SIZE, GRID_SIZE);
        b.show(graphics, GRID_SIZE, GRID_SIZE);
    }

    public void moveSnakeUp(){
        s.dir(0, -GRID_SIZE);
    }
    public void moveSnakeDown(){
        s.dir(0, GRID_SIZE);
    }
    public void moveSnakeLeft(){
        s.dir(-GRID_SIZE, 0);
    }
    public void moveSnakeRight(){
        s.dir(GRID_SIZE, 0);
    }

    private boolean checkDeath(){
        if (s.position.getX()== width || s.position.getX() == -GRID_SIZE ||
                s.position.getY() == height || s.position.getY() == -GRID_SIZE) {
            return true;
        }
       if (inBomb(b.position)){
           message = "bomb!";
            return true;
        }
       return s.death();
    }

    public void updateSnake(){
        s.update();
        if (eat(f.position)){
            f = createFood();
        }
        if (checkDeath()){
            gameOver = true;
        }
    }

    public boolean getGameOver(){
        return gameOver;
    }

    public boolean eat(Position position) {
        if (position.equals(s.position)) {
            s.setTotal(GRID_SIZE);
            return true;
        }
        return false;
    }

    public boolean inBomb(Position position) {
        return position.equals(s.position);
    }

    public Food createFood(){
        int fx = rand.nextInt(width);
        int fy = rand.nextInt(height);
        return new Food(fx, fy, FOOD_COLOR);
    }

    public Bomb createBomb() {
        int bx, by;
        do {
            bx = rand.nextInt(width);
            by = rand.nextInt(height);
        } while (bx == f.position.getX() && by == f.position.getY());
            return new Bomb(bx, by, BOMB_COLOR);
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isFood(int x, int y) {
        return f.getPosition().getX() == x && f.getPosition().getY() == y;
    }

    public boolean isBomb(int x, int y) {
        return b.getPosition().getX() == x && b.getPosition().getY() == y;
    }
}

