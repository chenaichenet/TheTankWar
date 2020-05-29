/**
 * FileName: BrekThread
 * Author:   嘉平十七
 * Date:     2020/5/27 9:33
 * Description:
 * History:
 * notes：
 */
package com.main.thread;

import com.main.game.End;
import com.main.game.Game;
import com.minor.things.Bullet;
import com.minor.things.Effect;

public class BreakThread extends Thread{
    Game game;
    Bullet bullet;
    String type;
    int x=0;
    public BreakThread(Game game,String type){
        super();
        this.game=game;
        this.type=type;
    }
    public BreakThread(Game game,Bullet bullet,String type){
        super();
        this.game=game;
        this.bullet=bullet;
        this.type=type;
    }

    @Override
    public void run() {
        switch (type){
            case "爆炸":
                for (int i=0;i<game.booms.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,game.booms[i]);
                    game.effects.add(effect);
                    game.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "死亡":
                for (int i=0;i<game.deads.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,game.deads[i]);
                    game.effects.add(effect);
                    game.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "结束1":
                x=1;
                for (int i=0;i<game.deads.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,game.deads[i]);
                    game.effects.add(effect);
                    game.repaint();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "结束2":
                x=2;
                for (int i=0;i<game.deads.length;i++){
                    Effect effect=new Effect(bullet.x-30,bullet.y-30,game.deads[i]);
                    game.effects.add(effect);
                    game.repaint();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
        game.effects.clear();
        game.repaint();
        if (x!=0){
            End end=new End(x);
            game.dispose();
        }
    }
}