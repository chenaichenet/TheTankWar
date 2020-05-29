/**
 * FileName: BotBulletThread
 * Author:   嘉平十七
 * Date:     2020/5/28 8:27
 * Description:
 * History:
 * notes：
 */
package com.main.thread;

import com.main.game.End;
import com.main.game.GameBot;

import java.awt.*;

public class BotBulletThread extends Thread{
    GameBot gameBot;
    public BotBulletThread(GameBot g) {
        // TODO Auto-generated constructor stub
        this.gameBot =g;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            //检测是否存在子弹 并进行移动
            if(gameBot.bullets1!=null) {
                for(int i = 0; i< gameBot.bullets1.size(); i++) {
                    String dir= gameBot.bullets1.get(i).direction;
                    switch (dir) {
                        case "上":
                            gameBot.bullets1.get(i).y-=4;
                            break;
                        case "下":
                            gameBot.bullets1.get(i).y+=4;
                            break;
                        case "左":
                            gameBot.bullets1.get(i).x-=4;
                            break;
                        case "右":
                            gameBot.bullets1.get(i).x+=4;
                        default:
                            break;
                    }
                }
            }
            //检测是否存在子弹 并进行移动
            if(gameBot.bullets2!=null) {
                for(int i = 0; i< gameBot.bullets2.size(); i++) {
                    String dir= gameBot.bullets2.get(i).direction;
                    switch (dir) {
                        case "上":
                            gameBot.bullets2.get(i).y-=4;
                            break;
                        case "下":
                            gameBot.bullets2.get(i).y+=4;
                            break;
                        case "左":
                            gameBot.bullets2.get(i).x-=4;
                            break;
                        case "右":
                            gameBot.bullets2.get(i).x+=4;
                        default:
                            break;
                    }
                }
            }
            if(gameBot.bullets1!=null) {
                for(int i = 0; i< gameBot.bullets1.size(); i++) {
                    Rectangle rec1 = new Rectangle(gameBot.bullets1.get(i).x, gameBot.bullets1.get(i).y, 15, 15);

                    //如果与木墙碰撞则子弹与墙都消失，并留下爆炸效果
                    if(gameBot.walls!=null) {
                        for(int j = 0; j< gameBot.walls.size(); j++) {
                            Rectangle rec2 = new Rectangle(gameBot.walls.get(j).x, gameBot.walls.get(j).y, 60, 60);
                            if(rec1.intersects(rec2)) {
                                new BotBreakThread(gameBot, gameBot.bullets1.get(i),"爆炸").start();
                                gameBot.bullets1.remove(i);
                                gameBot.walls.remove(j);
                            }
                        }
                    }

                    //如果与铁墙碰撞则子弹消失，并留下爆炸效果
                    if(gameBot.ironwalls!=null) {
                        for(int j = 0; j< gameBot.ironwalls.size(); j++) {
                            Rectangle rec2 = new Rectangle(gameBot.ironwalls.get(j).x, gameBot.ironwalls.get(j).y, 60, 60);
                            if(rec1.intersects(rec2)) {
                                new BotBreakThread(gameBot, gameBot.bullets1.get(i),"爆炸").start();
                                gameBot.bullets1.remove(i);
                            }
                        }
                    }
                    //建立玩家2坦克的矩形，如果击中则将其送回起点并留下死亡效果
                    Rectangle rec3 = new Rectangle(gameBot.p2.x, gameBot.p2.y, 60, 60);
                    if(rec1.intersects(rec3)) {
                        new BotBreakThread(gameBot, gameBot.bullets1.get(i),"死亡").start();
                        gameBot.bullets1.remove(i);
                        //540, 740, "上"
                        gameBot.index2=0;
                        gameBot.p2.x=540;
                        gameBot.p2.y=740;
                        gameBot.p2.direction="上";
                    }

                    //建立玩家1基地，如果击中则留下死亡效果游戏结束
                    Rectangle rec4 = new Rectangle(gameBot.up.x, gameBot.up.y, 60, 60);
                    if(rec1.intersects(rec4)) {
                        new BotBreakThread(gameBot, gameBot.bullets1.get(i),"结束3").start();
                        gameBot.bullets1.remove(i);
                    }

                    //建立玩家2基地，如果击中则留下死亡效果游戏结束
                    Rectangle rec5 = new Rectangle(gameBot.down.x, gameBot.down.y, 60, 60);
                    if(rec1.intersects(rec5)) {
                        new BotBreakThread(gameBot, gameBot.bullets1.get(i),"结束4").start();
                        gameBot.bullets1.remove(i);
                    }
                }
            }

            if(gameBot.bullets2!=null) {
                for(int i = 0; i< gameBot.bullets2.size(); i++) {
                    Rectangle rec1 = new Rectangle(gameBot.bullets2.get(i).x, gameBot.bullets2.get(i).y, 15, 15);

                    //如果与木墙碰撞则子弹与墙都消失，并留下爆炸效果
                    if(gameBot.walls!=null) {
                        for(int j = 0; j< gameBot.walls.size(); j++) {
                            Rectangle rec2 = new Rectangle(gameBot.walls.get(j).x, gameBot.walls.get(j).y, 60, 60);
                            if(rec1.intersects(rec2)) {
                                new BotBreakThread(gameBot, gameBot.bullets2.get(i),"爆炸").start();;
                                gameBot.bullets2.remove(i);
                                gameBot.walls.remove(j);

                            }
                        }
                    }

                    //如果与铁墙碰撞则子弹消失，并留下爆炸效果
                    if(gameBot.ironwalls!=null) {
                        for(int j = 0; j< gameBot.ironwalls.size(); j++) {
                            Rectangle rec2 = new Rectangle(gameBot.ironwalls.get(j).x, gameBot.ironwalls.get(j).y, 60, 60);
                            if(rec1.intersects(rec2)) {
                                new BotBreakThread(gameBot, gameBot.bullets2.get(i),"爆炸").start();;
                                gameBot.bullets2.remove(i);

                            }
                        }
                    }

                    //建立玩家1坦克的矩形，如果击中则将其送回起点并留下死亡效果
                    Rectangle rec3 = new Rectangle(gameBot.e1.x, gameBot.e1.y, 60, 60);
                    if(rec1.intersects(rec3)) {
                        new BotBreakThread(gameBot, gameBot.bullets2.get(i),"爆炸").start();
                        gameBot.bullets2.remove(i);
                        //300, 30, "下"
                        gameBot.index2=0;
                        gameBot.e1.x=1000;
                        gameBot.e1.y=1000;
                        gameBot.p2.direction="下";
                    }
                    Rectangle rec6 = new Rectangle(gameBot.e2.x, gameBot.e2.y, 60, 60);
                    if(rec1.intersects(rec6)) {
                        new BotBreakThread(gameBot, gameBot.bullets2.get(i),"死亡").start();
                        gameBot.bullets2.remove(i);
                        //300, 30, "下"
                        gameBot.boti2=1;
                        gameBot.e2.x=1000;
                        gameBot.e2.y=1000;
                        gameBot.p2.direction="下";
                    }

                    //建立玩家1基地，如果击中则留下死亡效果游戏结束
                    Rectangle rec4 = new Rectangle(gameBot.up.x, gameBot.up.y, 60, 60);
                    if(rec1.intersects(rec4)) {
                        new BotBreakThread(gameBot, gameBot.bullets2.get(i),"结束3").start();
                        gameBot.bullets2.remove(i);
                    }

                    //建立玩家2基地，如果击中则留下死亡效果游戏结束
                    Rectangle rec5 = new Rectangle(gameBot.down.x, gameBot.down.y, 60, 60);
                    if(rec1.intersects(rec5)) {
                        new BotBreakThread(gameBot, gameBot.bullets2.get(i),"结束4").start();
                        gameBot.bullets2.remove(i);
                    }
                }
            }
            Rectangle rec3 = new Rectangle(gameBot.e1.x, gameBot.e1.y, 60, 60);
            Rectangle rec6 = new Rectangle(gameBot.e2.x, gameBot.e2.y, 60, 60);
            Rectangle rec = new Rectangle(gameBot.p2.x, gameBot.p2.y, 60, 60);
            if(rec3.intersects(rec)||rec6.intersects(rec)) {
                gameBot.p2.x=1000;
                End e=new End(4);
                gameBot.dispose();
            }
            gameBot.repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}