import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {   
    static ArrayList<Object> all;
    static JFrame frame;
    static boolean play=true;
    static JLabel label1;
    static JLabel label2;
    static JLabel label3;
    static JLabel label4;
    static JLabel label5;
    static JLabel label6;
    static ArrayList<String> images;
    static int kills;
    public static void main(String[] args) {
        Random random = new Random();
        frame = new JFrame("Rock Paper Scissors Spock Lizard Battle Royale");

        images=new ArrayList<String>();

        images.add("rock");
        images.add("paper");
        images.add("scissors");
        images.add("spock");
        images.add("lizard");

        double ran=random.nextDouble();
        
        if (ran>0.2){
            shiftArray(images);
        }
        if (ran>0.4){
            shiftArray(images);
        }
        if (ran>0.6){
            shiftArray(images);
        }
        if (ran>0.8){
            shiftArray(images);
        }

        JPanel textPanel = new JPanel();
        int verticalGap = 10;
        textPanel.setLayout(new GridLayout(6, 1, 0, verticalGap));

        int nbr=140;

        label1 = new JLabel(images.get(0) + " : " + nbr/5);
        label2 = new JLabel(images.get(1) + " : " + nbr/5);
        label3 = new JLabel(images.get(2) + " : " + nbr/5);
        label4 = new JLabel(images.get(3) + " : " + nbr/5);
        label5 = new JLabel(images.get(4) + " : " + nbr/5);
        label6 = new JLabel("Kills : " + kills);

        Font customFont = new Font("Arial", Font.BOLD, 16);
        label1.setFont(customFont);
        label2.setFont(customFont);
        label3.setFont(customFont);
        label4.setFont(customFont);
        label5.setFont(customFont);
        label6.setFont(customFont);

        textPanel.add(label1);
        textPanel.add(label2);
        textPanel.add(label3);
        textPanel.add(label4);
        textPanel.add(label5);
        textPanel.add(label6);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(textPanel, BorderLayout.NORTH);

        ArrayList<Rect> rects=new ArrayList<Rect>();

        int screenHeight=1000;
        int screenWidth=1980;

        rects.add(new Rect(screenWidth/6, screenHeight/4, screenWidth/5, 10));
        rects.add(new Rect(screenWidth/6, screenHeight/4, 10, screenHeight/5));

        rects.add(new Rect(screenWidth-screenWidth/6-screenWidth/5 , screenHeight-screenHeight/4-10, screenWidth/5, 10));
        rects.add(new Rect(screenWidth-screenWidth/6-10, screenHeight-screenHeight/4-screenHeight/5, 10, screenHeight/5));

        rects.add(new Rect(screenWidth/2-5, screenHeight/2-screenHeight/8, 10, screenHeight/4));

        rects.add(new Rect(screenWidth/2-screenWidth/8, screenHeight/2-5, screenWidth/4, 10));

        rects.add(new Rect(screenWidth/6-screenWidth/20, screenHeight-screenHeight/4, screenWidth/10+10, 10)); 
        rects.add(new Rect(screenWidth/6, screenHeight-screenHeight/4-screenWidth/20, 10, screenWidth/10+10)); 

        rects.add(new Rect(screenWidth-screenWidth/6-screenWidth/20-10+5, screenHeight/4, screenWidth/10+10, 10));
        rects.add(new Rect(screenWidth-screenWidth/6, screenHeight/4-screenWidth/20, 10, screenWidth/10+10));

        all = new ArrayList<Object>();
        int width=30;
        
        for (int i = 0; i < nbr; i++) {
            Object obj=null;
            boolean b=true;
            while (b){
                obj=new Object(width, width, i%5, random, screenWidth, screenHeight, images, rects);
                b=false;
                for (int y=0; y<rects.size(); y++){
                    if (rects.get(y).collide(new Rect(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight()))){
                        b=true;
                    }
                }
                for (int y=0; y<all.size(); y++){
                    if (i%5 != y%5 && all.get(y).collide(all.get(y))){
                        b=true;
                    }
                }
                
            }   
            all.add(obj);
            frame.add(all.get(i).label);
        }
        
        for (int i=0; i<rects.size(); i++){
            frame.add(rects.get(i));
        }

        frame.add(containerPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(screenWidth, screenHeight+width);

        frame.setVisible(true);

        while(play){
            update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void shiftArray(ArrayList<String> images){
        String temp=images.get(4);
        for(int i=4;i>0;i--)
        {
            images.set(i, images.get(i-1));
        }
        images.set(0, temp);
    }

    public static void update(){
        ArrayList<Object> rocks=new ArrayList<Object>();
        ArrayList<Object> scissors=new ArrayList<Object>();
        ArrayList<Object> paper=new ArrayList<Object>();
        ArrayList<Object> spocks=new ArrayList<Object>();
        ArrayList<Object> lizards=new ArrayList<Object>();
        for (int i=0; i<all.size(); i++){
            for (int j=i; j<all.size(); j++){
                if (all.get(i).collide(all.get(j))){
                    // 0 rock
                    // 1 paper 
                    // 2 scissors
                    // 3 spock
                    // 4 lizard

                    if (all.get(i).getImage()!= all.get(j).getImage()){
                        kills+=1;
                    }

                    if (all.get(i).getImage()==0 && all.get(j).getImage()==1){
                        all.get(i).setImage(1);
                    }
                    else if (all.get(i).getImage()==0 && all.get(j).getImage()==2){
                        all.get(j).setImage(0);
                    }
                    else if (all.get(i).getImage()==0 && all.get(j).getImage()==3){
                        all.get(i).setImage(3);
                    }
                    else if (all.get(i).getImage()==0 && all.get(j).getImage()==4){
                        all.get(j).setImage(0);
                    }
                    else if (all.get(i).getImage()==1 && all.get(j).getImage()==0){
                        all.get(j).setImage(1);
                    }
                    else if (all.get(i).getImage()==1 && all.get(j).getImage()==2){
                        all.get(i).setImage(2);
                    }
                    else if (all.get(i).getImage()==1 && all.get(j).getImage()==3){
                        all.get(j).setImage(1);
                    }
                    else if (all.get(i).getImage()==1 && all.get(j).getImage()==4){
                        all.get(i).setImage(4);
                    }
                    else if (all.get(i).getImage()==2 && all.get(j).getImage()==0){
                        all.get(i).setImage(0);
                    }
                    else if (all.get(i).getImage()==2 && all.get(j).getImage()==1){
                        all.get(j).setImage(2);
                    }
                    else if (all.get(i).getImage()==2 && all.get(j).getImage()==3){
                        all.get(j).setImage(2);
                    }
                    else if (all.get(i).getImage()==2 && all.get(j).getImage()==4){
                        all.get(i).setImage(4);
                    }
                    else if (all.get(i).getImage()==3 && all.get(j).getImage()==0){
                        all.get(i).setImage(0);
                    }
                    else if (all.get(i).getImage()==3 && all.get(j).getImage()==1){
                        all.get(i).setImage(1);
                    }
                    else if (all.get(i).getImage()==3 && all.get(j).getImage()==2){
                        all.get(j).setImage(3);
                    }
                    else if (all.get(i).getImage()==3 && all.get(j).getImage()==4){
                        all.get(j).setImage(3);
                    }
                    else if (all.get(i).getImage()==4 && all.get(j).getImage()==0){
                        all.get(j).setImage(4);
                    }
                    else if (all.get(i).getImage()==4 && all.get(j).getImage()==1){
                        all.get(i).setImage(1);
                    }
                    else if (all.get(i).getImage()==4 && all.get(j).getImage()==2){
                        all.get(j).setImage(4);
                    }
                    else if (all.get(i).getImage()==4 && all.get(j).getImage()==3){
                        all.get(i).setImage(3);
                    }


                }
            }
            if(all.get(i).getImage()==0){
                rocks.add(all.get(i));
            }
            else if(all.get(i).getImage()==1){
                scissors.add(all.get(i));
            }
            else if(all.get(i).getImage()==2){
                paper.add(all.get(i));
            }
            else if(all.get(i).getImage()==3){
                spocks.add(all.get(i));
            }
            else if(all.get(i).getImage()==4){
                lizards.add(all.get(i));
            }
        }
        
        moveToward(rocks, scissors, lizards);
        moveToward(scissors, paper, lizards);
        moveToward(paper, rocks, spocks);
        moveToward(spocks, scissors, rocks);
        moveToward(lizards, paper, spocks);

        label1.setText(images.get(0) + " : " + rocks.size());
        label2.setText(images.get(1) + " : " + scissors.size());
        label3.setText(images.get(2) + " : " + paper.size());
        label4.setText(images.get(3) + " : " + spocks.size());
        label5.setText(images.get(4) + " : " + lizards.size());
        label6.setText("Kills : " + kills);

        if (rocks.size()==all.size() || scissors.size()==all.size() || paper.size()==all.size() || spocks.size()==all.size() || lizards.size()==all.size()){
            play=false;
        }

    }

    public static void moveToward(ArrayList<Object> rocks, ArrayList<Object> scissors, ArrayList<Object> lizards){
        for (int i=0; i<rocks.size(); i++){
            Object closer=null;
            for(int y=0; y<scissors.size(); y++){
                double distance=Math.sqrt(Math.pow(rocks.get(i).getX()-scissors.get(y).getX(),2)+Math.pow(rocks.get(i).getY()-scissors.get(y).getY(),2));
                if (distance<150){
                    if (closer==null){
                        closer=scissors.get(y);
                    }
                    else if (distance<Math.sqrt(Math.pow(rocks.get(i).getX()-closer.getX(),2)+Math.pow(rocks.get(i).getY()-closer.getY(),2))){
                        closer=scissors.get(y);
                    }
                }
            }
            for(int y=0; y<lizards.size(); y++){
                double distance=Math.sqrt(Math.pow(rocks.get(i).getX()-lizards.get(y).getX(),2)+Math.pow(rocks.get(i).getY()-lizards.get(y).getY(),2));
                if (distance<150){
                    if (closer==null){
                        closer=lizards.get(y);
                    }
                    else if (distance<Math.sqrt(Math.pow(rocks.get(i).getX()-closer.getX(),2)+Math.pow(rocks.get(i).getY()-closer.getY(),2))){
                        closer=lizards.get(y);
                    }
                }
            }
            if (closer!=null){
                rocks.get(i).moveToward(closer);
            }
            else {
                rocks.get(i).move(false);
            }
        }
    }

}

