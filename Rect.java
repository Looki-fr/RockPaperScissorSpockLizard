
import javax.swing.*;
import java.awt.*;

public class Rect extends JComponent{
    private int x;
    private int y;
    private int width;
    private int height;
    public Rect(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawRect(x, y, width, height);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public boolean collide(Rect rect){
        if (this.x < rect.getX() + rect.getWidth() &&
                this.x + this.width > rect.getX() &&
                this.y < rect.getY() + rect.getHeight() &&
                this.y + this.height > rect.getY()) {
            return true;
        }
        return false;
    }

}
