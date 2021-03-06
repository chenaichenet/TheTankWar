/**
 * FileName: Game
 * Author:   嘉平十七
 * Date:     2020/5/26 16:28
 * Description:
 * History:
 * notes：
 */
package com.main.game;

import com.main.thread.BulletThread;
import com.minor.things.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Game extends Frame {
    //爆炸图片集合
    public static Image[] booms=new Image[8];
    //死亡图片集合
    public static Image[] deads=new Image[4];
    //玩家坦克四种图片集合
    static Image[] tank1=new Image[4];
    static Image[] tank2=new Image[4];
    //墙的图片集合
    public static List<Wall> walls=new ArrayList<Wall>();
    //铁墙的图片集合
    public static List<IronWall> ironwalls=new ArrayList<IronWall>();
    //水的图片集合
    public static List<Water> waters=new ArrayList<Water>();
    //子弹效果集合
    public static List<Effect>effects=new ArrayList<Effect>();
    //双方子弹集合
    public static List<Bullet>bullets1=new ArrayList<Bullet>();
    public static List<Bullet>bullets2=new ArrayList<Bullet>();
    //定义双方的家
    public Home up,down;
    //定义things图片
    Image image_bullet,image_wall,image_ironwall,image_water,image_home;
    //定义双方坦克图片
    Image tank1_up,tank1_down,tank1_left,tank1_right,tank2_up,tank2_down,tank2_left,tank2_right;
    //控制坦克放向
    public static int index1=1;
    public static int index2=0;
    //初始化玩家位置根放向
    public Tank p1=new Tank(300,30,"下");
    public Tank p2=new Tank(540,740,"上");
    //解决同时监听多按键
    private boolean oneup,onedown,oneleft,oneright,oneattack,twoup,twodown,twoleft,tworight,twoattack;
    Image buffer;
    Graphics gBuffer;

    public Game(){
        setImage();
        putImage();
        this.setTitle("坦克大战");
        this.setSize(900,800);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener((new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }));
        image_wall=this.getToolkit().getImage("res/images/wall.jpg");
        image_ironwall=this.getToolkit().getImage("res/images/ironwall.jpg");
        image_water=this.getToolkit().getImage("res/images/water.jpg");
        image_home=this.getToolkit().getImage("res/images/home.jpg");
        image_bullet=this.getToolkit().getImage("res/images/zd.jpg");

        new BulletThread(this).start();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int code=keyEvent.getKeyCode();
                switch (code){
                    case 87:
                        oneup=true;
                        break;
                    case 65:
                        oneleft=true;
                        break;
                    case 83:
                        onedown=true;
                        break;
                    case 68:
                        oneright=true;
                        break;
                    case 32:
                        oneattack=true;
                        break;
                    case 38:
                        twoup=true;
                        break;
                    case 40:
                        twodown=true;
                        break;
                    case 37:
                        twoleft=true;
                        break;
                    case 39:
                        tworight=true;
                        break;
                    case 10:
                        twoattack=true;
                        break;
                    default:
                        break;
                }
                if (oneup){
                    index1=0;
                    p1.direction="上";
                    if (check(p1,"y",-10)){
                        p1.y-=10;
                    }
                }
                if (onedown){
                    index1=1;
                    p1.direction="下";
                    if (check(p1,"y",10)){
                        p1.y+=10;
                    }
                }
                if (oneleft){
                    index1=2;
                    p1.direction="左";
                    if (check(p1,"x",-10)){
                        p1.x-=10;
                    }
                }
                if (oneright){
                    index1=3;
                    p1.direction="右";
                    if (check(p1,"x",10)){
                        p1.x+=10;
                    }
                }
                if (oneattack){
                    Bullet bullet=new Bullet(p1.x+20,p1.y+20,p1.direction);
                    bullets1.add(bullet);
                }
                if (twoup){
                    index2=0;
                    p2.direction="上";
                    if (check(p2,"y",-10)){
                        p2.y-=10;
                    }
                }
                if (twodown){
                    index2=1;
                    p2.direction="下";
                    if (check(p2,"y",10)){
                        p2.y+=10;
                    }
                }
                if (twoleft){
                    index2=2;
                    p2.direction="左";
                    if (check(p2,"x",-10)){
                        p2.x-=10;
                    }
                }
                if (tworight){
                    index2=3;
                    p2.direction="右";
                    if (check(p2,"x",10)){
                        p2.x+=10;
                    }
                }
                if (twoattack){
                    Bullet bullet=new Bullet(p2.x+20,p2.y+20,p2.direction);
                    bullets2.add(bullet);
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                int code= keyEvent.getKeyCode();
                switch (code){
                    case 87:
                        oneup=false;
                        break;
                    case 65:
                        oneleft=false;
                        break;
                    case 83:
                        onedown=false;
                        break;
                    case 68:
                        oneright=false;
                        break;
                    case 32:
                        oneattack=false;
                        break;
                    case 38:
                        twoup=false;
                        break;
                    case 40:
                        twodown=false;
                        break;
                    case 37:
                        twoleft=false;
                        break;
                    case 39:
                        tworight=false;
                        break;
                    case 10:
                        twoattack=false;
                        break;
                    default:
                        break;
                }
            }
        });

    }
    public void paint(Graphics g){
        super.paint(g);
        if (buffer==null){
            buffer=this.createImage(900,800);
            gBuffer=buffer.getGraphics();
        }
        gBuffer.clearRect(0,0,900,800);
        if(bullets1!=null) {
            for(int i=0;i<bullets1.size();i++) {
                gBuffer.drawImage(image_bullet, bullets1.get(i).x, bullets1.get(i).y, this);
            }
        }
        if(bullets2!=null) {
            for(int i=0;i<bullets2.size();i++) {
                gBuffer.drawImage(image_bullet, bullets2.get(i).x, bullets2.get(i).y, this);
            }
        }
        if(walls!=null) {
            for(int i=0;i<walls.size();i++) {
                gBuffer.drawImage(image_wall, walls.get(i).x, walls.get(i).y, this);
            }
        }
        if(ironwalls!=null) {
            for(int i=0;i<ironwalls.size();i++) {
                gBuffer.drawImage(image_ironwall, ironwalls.get(i).x, ironwalls.get(i).y, this);
            }
        }
        if(waters!=null) {
            for(int i=0;i<waters.size();i++) {
                gBuffer.drawImage(image_water, waters.get(i).x, waters.get(i).y, this);
            }
        }
        if(effects!=null) {
            for(int i=0;i<effects.size();i++) {
                gBuffer.drawImage(effects.get(i).image, effects.get(i).x, effects.get(i).y, this);
            }
        }
        gBuffer.drawImage(image_home,up.x , up.y, this);
        gBuffer.drawImage(image_home,down.x , down.y, this);
        //玩家1
        gBuffer.drawImage(tank1[index1], p1.x, p1.y, this);

        //玩家2
        gBuffer.drawImage(tank2[index2], p2.x, p2.y, this);

        g.drawImage(buffer, 0, 0, this);
    }
    /*载入图片*/
    public void setImage(){
        booms[0] = this.getToolkit().getImage("res/images/boom1.jpg");
        booms[1] = this.getToolkit().getImage("res/images/boom2.jpg");
        booms[2] = this.getToolkit().getImage("res/images/boom3.jpg");
        booms[3] = this.getToolkit().getImage("res/images/boom4.jpg");
        booms[4] = this.getToolkit().getImage("res/images/boom5.jpg");
        booms[5] = this.getToolkit().getImage("res/images/boom6.jpg");
        booms[6] = this.getToolkit().getImage("res/images/boom7.jpg");
        booms[7] = this.getToolkit().getImage("res/images/boom8.jpg");

        deads[0] = this.getToolkit().getImage("res/images/dead1.jpg");
        deads[1] = this.getToolkit().getImage("res/images/dead2.jpg");
        deads[2] = this.getToolkit().getImage("res/images/dead3.jpg");
        deads[3] = this.getToolkit().getImage("res/images/dead4.jpg");

        tank1[0] = this.getToolkit().getImage("res/images/enemy1.jpg");
        tank1[1] = this.getToolkit().getImage("res/images/enemy2.jpg");
        tank1[2] = this.getToolkit().getImage("res/images/enemy3.jpg");
        tank1[3] = this.getToolkit().getImage("res/images/enemy4.jpg");

        tank2[0] = this.getToolkit().getImage("res/images/enemy5.jpg");
        tank2[1] = this.getToolkit().getImage("res/images/enemy6.jpg");
        tank2[2] = this.getToolkit().getImage("res/images/enemy7.jpg");
        tank2[3] = this.getToolkit().getImage("res/images/enemy8.jpg");
    }
    /*确定图片位置并加入集合*/
    public void putImage(){
        //中间左边墙
        for (int i=0;i<4;i++){
            Wall w=new Wall(i*60,400);
            walls.add(w);
        }
        //中间右边墙
        for (int i=1;i<5;i++){
            Wall w=new Wall(900-(i*60),400);
            walls.add(w);
        }
        //中间铁墙
        for (int i=0;i<3;i++){
            IronWall iw=new IronWall(360+(i*60),400);
            ironwalls.add(iw);
        }
        //中间左边水
        for (int i=0;i<2;i++){
            Water wa=new Water(240+(i*60),400);
            waters.add(wa);
        }
        //中间右边水
        for (int i=0;i<2;i++){
            Water wa=new Water(600-(i*60),400);
            waters.add(wa);
        }
        //下方基地

        Wall w0=new Wall(360,740);
        Wall w1=new Wall(360,680);
        Wall w2=new Wall(420,680);
        Wall w3=new Wall(480,680);
        Wall w4=new Wall(480,740);
        down=new Home(430,750);
        Wall w5=new Wall(360,30);
        Wall w6=new Wall(360,90);
        Wall w7=new Wall(420,90);
        Wall w8=new Wall(480,90);
        Wall w9=new Wall(480,30);
        Wall[] ws={w0,w1,w2,w3,w4,w5,w6,w7,w8,w9};
        up=new Home(430,40);
        for (int x=0;x<10;x++){
            walls.add(ws[x]);
        }
    }
    /*检测坦克是否能正常移动不碰撞*/
    public boolean check(Tank tk,String s,int d){
        Tank test=new Tank(tk.x,tk.y,tk.direction);
        switch (s){
            case "x":
                test.x+=d;
                break;
            case "y":
                test.y+=d;
                break;
            default:
                break;
        }
        /*Rectangle rec=new Rectangle(test.x,test.y,60,60);
        List<?>[] str=new List[3];
        str[0]= walls;
        str[1]=waters;
        str[2]=ironwalls;
        for (int x=0;x<3;x++){
            for (int i=0;i<str[x].size();i++){
                Rectangle rec1=new Rectangle(walls.get(i).x,str[x].get(i).y,60,60);
                if (rec1.intersects(rec))
                    return false;
            }
        }*/
        Rectangle rec = new Rectangle(test.x,test.y, 60, 60);
        for(int i=0;i<walls.size();i++) {
            Rectangle rec1 = new Rectangle(walls.get(i).x,walls.get(i).y, 60, 60);
            if(rec1.intersects(rec)) {
                return false;
            }
        }
        for(int i=0;i<waters.size();i++) {
            Rectangle rec1 = new Rectangle(waters.get(i).x,waters.get(i).y, 60, 60);
            if(rec1.intersects(rec)) {
                return false;
            }
        }
        for(int i=0;i<ironwalls.size();i++) {
            Rectangle rec1 = new Rectangle(ironwalls.get(i).x,ironwalls.get(i).y, 60, 60);
            if(rec1.intersects(rec)) {
                return false;
            }
        }
        if (test.x<0||test.x>840||test.y<30||test.y>740)
            return false;
        return true;
    }
}