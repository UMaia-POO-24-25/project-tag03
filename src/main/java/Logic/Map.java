package Logic;

import Entities.Bomb;
import Entities.Food;
import Entities.Snake;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private static final Random rand = new Random();
    private static final int GRID_SIZE = 1;
    private static final String FOOD_COLOR = "#FF0000";
    private static final String BOMB_COLOR = "#000000";
    private int score = 0;

    private final int height;
    private final int width;
    public Snake s;
    private Food f;
    private List<Bomb> b;
    private boolean gameOver;
    public String message;

    public Map(int width, int height){
        this.height = height;
        this.width = width;
        this.s = new Snake(GRID_SIZE, GRID_SIZE);
        this.f = createFood();
        this.b = new ArrayList<>();
        this.gameOver = false;
    }

    public void show(TextGraphics graphics,  int offsetX, int offsetY){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#828282"));
        graphics.fillRectangle(new TerminalPosition(offsetX, offsetY), new TerminalSize(width, height), ' ');
        f.show(graphics, offsetX, offsetY);
        s.show(graphics, offsetX, offsetY);
        for (Bomb bomb : b) {
            bomb.show(graphics, offsetX, offsetY);
        }
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
    public int getBombCount() {
        return b.size();
    }    
    private boolean checkDeath(){
        int offsetX = 1;
        int offsetY = 1;

        if (s.position.getX()== width + offsetX  || s.position.getX() < offsetX ||
                s.position.getY() == height + offsetY || s.position.getY()  < offsetY) {
            message = "Fora dos limites!";
            return true;
        }

       for (Bomb bomb : b) {
        if (inBomb(bomb.position)) {
            message = "bomb!";
            return true;
        }
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

    public void reset() {
        this.s = new Snake(GRID_SIZE, GRID_SIZE);
        this.f = createFood();
        this.b.clear();
        this.b.add(createBomb());
        this.score = 0;
        this.gameOver = false;
        this.message = null;
    }
    
    public void addBomb() {
        Bomb newBomb;
        boolean positionConflict;
        do {
            newBomb = createBomb();
            Position newPosition = newBomb.getPosition();
            positionConflict = false;
            for (Bomb bomb : b) {
                if (bomb.getPosition().equals(newPosition)) {
                    positionConflict = true;
                    break;
                }
            }
        } while (positionConflict);
        b.add(newBomb);
    }
    
    public boolean eat(Position position) {
        if (position.equals(s.position)) {
            s.setTotal(GRID_SIZE);
            score += 10;
            return true;
        }
        return false;
    }

    public boolean inBomb(Position position) {
        return position.equals(s.position);
    }

    public Food createFood(){
        int fx, fy;
        do {
            fx = 1 + rand.nextInt(width - 2);
            fy = 1 + rand.nextInt(height - 2);
        } while (fx == s.position.getX() && fy == s.position.getY());
        return new Food(fx, fy, FOOD_COLOR);
    }

    public Bomb createBomb() {
        int bx, by;
        do {
            bx = 1 + rand.nextInt(width - 2);
            by = 1 + rand.nextInt(height - 2);
        } while ((bx == f.position.getX() && by == f.position.getY()) ||
                (bx == s.position.getX() && by == s.position.getY()));
            return new Bomb(bx, by, BOMB_COLOR);
    }

    public void updateBomb() {
        for (Bomb bomb : b) {
            int bx, by;
            do {
                bx = 1 + rand.nextInt(width - 2);
                by = 1 + rand.nextInt(height - 2);
            } while ((bx == f.position.getX() && by == f.position.getY()) ||
                    (bx == s.position.getX() && by == s.position.getY()) ||
                    (Math.abs(bx - s.position.getX()) + Math.abs(by - s.position.getY()) < 5));
            bomb.setPosition(new Position(bx, by));
        }
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {

        return score;
    }

    public int getWidth() {
        return width;
    }

    public boolean isFood(int x, int y) {
        return f.getPosition().getX() == x && f.getPosition().getY() == y;
    }

    public boolean isBomb(int x, int y) {
        return b.stream().anyMatch(bomb -> bomb.getPosition().getX() == x && bomb.getPosition().getY() == y);
    }
}

