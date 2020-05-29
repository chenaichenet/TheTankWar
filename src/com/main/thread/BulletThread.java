/**
 * FileName: BulletThread
 * Author:   嘉平十七
 * Date:     2020/5/27 8:53
 * Description: 子弹线程
 * History:
 * notes：
 */
package com.main.thread;

import com.main.game.Game;

import java.awt.*;

public class BulletThread extends Thread{
    Game game;
    public BulletThread(Game g){
        this.game=g;
    }

    @Override
    public void run() {
        while (true){
            //检测是否存在子弹，并进行移动
            if (game.bullets1!=null){
                for (int i=0;i<game.bullets1.size();i++){
                    String dir=game.bullets1.get(i).direction;
                    switch (dir){
                        case "上":
                            game.bullets1.get(i).y-=4;
                            break;
                        case "下":
                            game.bullets1.get(i).y+=4;
                            break;
                        case "左":
                            game.bullets1.get(i).x-=4;
                            break;
                        case "右":
                            game.bullets1.get(i).x+=4;
                        default:
                            break;
                    }
                }
                for (int x=0;x<game.bullets1.size();x++){
                    Rectangle rec1=new Rectangle(game.bullets1.get(x).x,game.bullets1.get(x).y,15,15);

                    //如果子弹与墙碰撞则子弹与墙都消失，留下爆炸效果
                    if (game.walls!=null){
                        for (int j=0;j<game.walls.size();j++){
                            Rectangle rec2=new Rectangle(game.walls.get(j).x,game.walls.get(j).y,60,60);
                            if (rec1.intersects(rec2)){
                                new BreakThread(game,game.bullets1.get(x),"爆炸").start();
                                game.bullets1.remove(x);
                                game.walls.remove(j);
                            }
                        }
                    }
                    //如果子弹与铁墙碰撞则子弹消失,留下爆炸效果
                    if (game.ironwalls!=null){
                        for (int j=0;j<game.ironwalls.size();j++){
                            Rectangle rec2=new Rectangle(game.ironwalls.get(j).x,game.ironwalls.get(j).y,60,60);
                            if (rec1.intersects(rec2)){
                                new BreakThread(game,game.bullets1.get(j),"爆炸").start();
                                game.bullets1.remove(j);
                            }
                        }
                    }
                    //建立玩家2坦克矩形，如果击中重生点重生，留下死亡效果
                    Rectangle rec3=new Rectangle(game.p2.x,game.p2.y,60,60);
                    if (rec1.intersects(rec3)){
                        new BreakThread(game,game.bullets1.get(x),"死亡").start();
                        game.bullets1.remove(x);
                        game.index2=0;
                        game.p2.x=540;
                        game.p2.y=740;
                        game.p2.direction="上";
                    }
                    //建立玩家1基地，如果击中留下死亡效果，游戏结束
                    Rectangle rec4=new Rectangle(game.up.x,game.up.y,60,60);
                    if (rec1.intersects(rec4)){
                        new BreakThread(game,game.bullets1.get(x),"结束2").start();
                        game.bullets1.remove(x);
                    }
                    //建立玩家2基地，如果击中留下死亡效果，游戏结束
                    Rectangle rec5=new Rectangle(game.up.x,game.up.y,60,60);
                    if (rec1.intersects(rec5)){
                        new BreakThread(game,game.bullets1.get(x),"结束1").start();
                        game.bullets1.remove(x);
                    }
                }
            }
            //检测是否存在，并进行移动
            if (game.bullets2!=null){
                for (int i=0;i<game.bullets2.size();i++){
                    String dir=game.bullets2.get(i).direction;
                    switch (dir){
                        case "上":
                            game.bullets2.get(i).y-=4;
                            break;
                        case "下":
                            game.bullets2.get(i).y+=4;
                            break;
                        case "左":
                            game.bullets2.get(i).x-=4;
                            break;
                        case "右":
                            game.bullets2.get(i).x+=4;
                        default:
                            break;
                    }
                }
                for (int x=0;x<game.bullets2.size();x++){
                    Rectangle rec1=new Rectangle(game.bullets2.get(x).x,game.bullets2.get(x).y,15,15);

                    //如果子弹与墙碰撞则子弹与墙都消失，留下爆炸效果
                    if (game.walls!=null){
                        for (int j=0;j<game.walls.size();j++){
                            Rectangle rec2=new Rectangle(game.walls.get(j).x,game.walls.get(j).y,60,60);
                            if (rec1.intersects(rec2)){
                                new BreakThread(game,game.bullets2.get(x),"爆炸").start();
                                game.bullets2.remove(x);
                                game.walls.remove(j);
                            }
                        }
                    }
                    //如果子弹与铁墙碰撞则子弹消失,留下爆炸效果
                    if (game.ironwalls!=null){
                        for (int j=0;j<game.ironwalls.size();j++){
                            Rectangle rec2=new Rectangle(game.ironwalls.get(j).x,game.ironwalls.get(j).y,60,60);
                            if (rec1.intersects(rec2)){
                                new BreakThread(game,game.bullets2.get(j),"爆炸").start();
                                game.bullets2.remove(j);
                            }
                        }
                    }
                    //建立玩家1坦克矩形，如果击中重生点重生，留下死亡效果
                    Rectangle rec3=new Rectangle(game.p1.x,game.p1.y,60,60);
                    if (rec1.intersects(rec3)){
                        new BreakThread(game,game.bullets2.get(x),"死亡").start();
                        game.bullets2.remove(x);
                        game.index2=0;
                        game.p1.x=300;
                        game.p1.y=30;
                        game.p1.direction="下";
                    }
                    //建立玩家1基地，如果击中留下死亡效果，游戏结束
                    Rectangle rec4=new Rectangle(game.up.x,game.up.y,60,60);
                    if (rec1.intersects(rec4)){
                        new BreakThread(game,game.bullets2.get(x),"结束2").start();
                        game.bullets2.remove(x);
                    }
                    //建立玩家2基地，如果击中留下死亡效果，游戏结束
                    Rectangle rec5=new Rectangle(game.up.x,game.up.y,60,60);
                    if (rec1.intersects(rec5)){
                        new BreakThread(game,game.bullets2.get(x),"结束1").start();
                        game.bullets2.remove(x);
                    }
                }
            }
            Rectangle rec1=new Rectangle(game.p1.x,game.p1.y,60,60);
            Rectangle rec2=new Rectangle(game.p2.x,game.p2.y,60,60);
            if (rec1.intersects(rec2)){
                new BreakThread(game,"爆炸");
                game.index1=1;
                game.p1.x=300;
                game.p1.y=30;
                game.p1.direction="下";

                game.index2=0;
                game.p2.x=540;
                game.p2.y=740;
                game.p2.direction="上";
            }
            game.repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}