/**
 * FileName: BotBreakThread
 * Author:   嘉平十七
 * Date:     2020/5/27 14:36
 * Description:
 * History:
 * notes：
 */
package com.main.thread;

import com.main.game.End;
import com.main.game.GameBot;
import com.minor.things.Bullet;
import com.minor.things.Effect;

public class BotBreakThread extends Thread {
    GameBot gameBot;
    Bullet bullet;
    String type;
    int x=0;
    public BotBreakThread(GameBot gameBot, Bullet bullet, String type){
        super();
        this.gameBot=gameBot;
        this.bullet=bullet;
        this.type=type;
    }

    @Override
    public void run() {
        switch (type){
            case "爆炸":
                for (int i=0;i<gameBot.booms.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,gameBot.booms[i]);
                    gameBot.effects.add(effect);
                    gameBot.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "死亡":
                for (int i=0;i<gameBot.deads.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,gameBot.deads[i]);
                    gameBot.effects.add(effect);
                    gameBot.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "结束1":
                x=1;
                for (int i=0;i<gameBot.deads.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,gameBot.deads[i]);
                    gameBot.effects.add(effect);
                    gameBot.repaint();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "结束2":
                x=2;
                for (int i=0;i<gameBot.deads.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,gameBot.deads[i]);
                    gameBot.effects.add(effect);
                    gameBot.repaint();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "结束3":
                x=3;
                for (int i=0;i<gameBot.deads.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,gameBot.deads[i]);
                    gameBot.effects.add(effect);
                    gameBot.repaint();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "结束4":
                x=4;
                for(int i=0;i<gameBot.deads.length;i++) {
                    Effect effect=new Effect(bullet.x-30, bullet.y-30, gameBot.deads[i]);
                    gameBot.effects.add(effect);
                    gameBot.repaint();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
        gameBot.effects.clear();
        gameBot.repaint();
        if (x!=0){
            End end=new End(x);
            gameBot.dispose();
        }
    }
}