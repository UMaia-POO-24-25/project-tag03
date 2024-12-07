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
    private final int height;
    private final int width;
    private final int grid;
    private Snake s;
    private Food f;
    private Bomb b;
    private boolean gameOver;
    private String foodColor;
    private String bombColor;


    public Map(int width, int height){
        this.height = height;
        this.width = width;
        this.grid = 1;
        this.s = new Snake(grid, grid);
        this.foodColor = "#FF0000";
        this.bombColor = "#000000";
        this.f = createFood();
        this.b = createBomb();
        this.gameOver = false;
    }

    public void show(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#828282"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        f.show(graphics, grid, grid);
        s.show(graphics, grid, grid);
        b.show(graphics, grid, grid);
    }

    public void moveSnakeUp(){
        s.dir(0, -grid);
    }
    public void moveSnakeDown(){
        s.dir(0, grid);
    }
    public void moveSnakeLeft(){
        s.dir(-grid, 0);
    }
    public void moveSnakeRight(){
        s.dir(grid, 0);
    }

    private boolean checkDeath(){
        if (s.position.getX()== width) {
            return true;
        } else if (s.position.getX() == -grid) {
            return true;
        } else if (s.position.getY() == height) {
            return true;
        } else if (s.position.getY() == -grid) {
            return true;
        } else if (inBomb(b.position)){
            return true;
        } else return s.death();
    }

    public void updateSnake(){
        s.update();
        if (eat(f.position)){
            f = createFood();
        }
        if (checkDeath()){
            this.gameOver = true;
        }
    }

    public boolean getGameOver(){
        return gameOver;
    }

    public boolean eat(Position position) {
        if (position.equals(s.position)) {
            s.setTotal(grid);
            return true;
        }
        return false;
    }

    public boolean inBomb(Position position) {
        return position.equals(s.position);
    }

    public Food createFood(){
        Random rand = new Random();
        int fx = rand.nextInt(width);
        int fy = rand.nextInt(height);
        return new Food(fx, fy, foodColor);
    }

    private Bomb createBomb() {
        Random rand = new Random();
        int bx = rand.nextInt(width);
        int by = rand.nextInt(height);
        if (bx == f.position.getX() && by == f.position.getY()) {
            createBomb();
        }
        return new Bomb(bx, by, bombColor);
    }
}