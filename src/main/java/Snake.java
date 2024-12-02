
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Snake {
    public int x, y; // Coordenadas da cobra
    public int xspeed, yspeed;
    public int grid;

    public Snake(int x, int y){
        this.x = x;
        this.y = y;
        this.xspeed = 1; // Movimento inicial para a direita
        this.yspeed = 0;
        this.grid = 1;
    }

    public void update(){
        this.x += xspeed; // Atualiza a posição baseada na velocidade
        this.y += yspeed;


        if (this.x > 29){
            this.x = 29;
        } else if (this.x < 0){
            this.x = 0;
        } else if (this.y > 29){
            this.y = 29;
        } else if (this.y < 0){
            this.y = 0;
        }
    }

    public void show(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(this.x, this.y), new TerminalSize(1, 1), ' '); // Desenha a cobra
    }
    public void dir(int x, int y){
        this.xspeed = x*grid;
        this.yspeed= y*grid;
    }
}