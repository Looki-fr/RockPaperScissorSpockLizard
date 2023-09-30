import java.util.Random;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

public class Object {
    private double x;
    private double y;
    private int width;
    private int height;
    private int image;
    private int screen_width;
    private int screen_height;
    public JLabel label;
    private Random random;
    private double angle;
    private double speed;
    private ArrayList<String> images;
    private ArrayList<Rect> rects;
    public Object(int width, int height, int image, Random random, int screen_width, int screen_height, ArrayList<String> images, ArrayList<Rect> rects) {
        this.random=random;
        this.x = (screen_width-width) * random.nextDouble();
        this.y = (screen_height-height) * random.nextDouble();
        this.width = width;
        this.height = height;
        this.image = image;
        this.speed=3;
        this.images=images;
        this.rects=rects;

        this.angle = random.nextDouble()*2*Math.PI;
        this.screen_width = screen_width;
        this.screen_height = screen_height;
        label = new JLabel(new ImageIcon("rock.png"));
        updateImageLabel();

    }
    private void updateImageLabel(){
        ImageIcon originalIcon = new ImageIcon("assets//"+images.get(image)+".png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        this.label.setIcon(resizedIcon);

    }

    public boolean collide(Object obj){

        if (((this.x >obj.x && this.x < obj.x+obj.width) || (this.x+width >obj.x && this.x+width < obj.x+obj.width)) && ((this.y > obj.y && this.y < obj.y+obj.height) || (this.y+this.height > obj.y && this.y+height < obj.y+obj.height))){
            return true;
        }
        return false;
    }

    public void moveToward(Object obj){
        double rotationRate=0.01;
        double newAngle = Math.atan2(obj.y-this.y, obj.x-this.x);
        if (angle<newAngle){
            angle+=rotationRate;
        }
        else if (angle>newAngle){
            angle-=rotationRate;
        }
        move(false);
    }

    public void move(boolean slow){
        double addSpeed=1;
        if (slow){
            addSpeed=0.5;
        }
        boolean changeAngle=false;
        this.x=this.x+Math.cos(angle)*speed*addSpeed;
        for (int i=0; i<rects.size(); i++){
            if(rects.get(i).collide(new Rect((int) Math.round(x),(int) Math.round(y), width, height))){
                changeAngle=true;
                break;
            }
        }
        if (changeAngle){
            angle+=Math.PI;
        }
        if (x < 0 || x > screen_width-width){
            angle+=Math.PI;
        }

        this.y=this.y+Math.sin(angle)*speed*addSpeed;
        if (y < 0 || y > screen_height-1.3*height){
            angle=-angle;
        }
        label.setBounds((int) Math.round(x),(int) Math.round(y), width, height);
    }
    public int getImage(){
        return this.image;
    }
    public void setImage(int image){
        this.image = image;
        updateImageLabel();
    }
    public int getX(){
        return (int)this.x;
    }
    public int getY(){
        return (int)this.y;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
}