import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Map {
    private final int height;
    private final int width;
    private final int grid;
    private Snake s;
    private boolean gameOver;


    public Map(int width, int height){
        this.height = height;
        this.width = width;
        this.grid = 1;
        this.s = new Snake(grid, grid);
        this.gameOver = false;
    }

    public void show(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#828282"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        s.show(graphics);

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

    private boolean checkMapBorders(){
        if (s.position.getX()== width+grid) {
            return true;
        } else if (s.position.getX() == 0-grid) {
            return true;
        } else if (s.position.getY() == height+grid) {
            return true;
        } else if (s.position.getY() == 0-grid) {
            return true;
        }
        return false;
    }

    public void updateSnake(){
        s.update();
        if (checkMapBorders()){
            this.gameOver = true;
        }
    }

    public boolean getGameOver(){
        return gameOver;
    }



}