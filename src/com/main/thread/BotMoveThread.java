/**
 * FileName: BotMoveThread
 * Author:   嘉平十七
 * Date:     2020/5/27 14:39
 * Description:
 * History:
 * notes：
 */
package com.main.thread;

import com.main.game.GameBot;
import com.minor.things.Bullet;

public class BotMoveThread extends Thread{
    GameBot gameBot;
    public BotMoveThread(GameBot gameBot){
        super();
        this.gameBot=gameBot;
    }

    @Override
    public void run() {
        for (int i=0;i<200;i++){
            move(i);

        }
    }

    private void move(int i) {
        if (i<12){
            gameBot.boti1=2;
            gameBot.e1.direction="左";
            gameBot.e1.x-=10;
            gameBot.boti2=3;
            gameBot.e2.direction="右";
            gameBot.e2.x+=10;
        }
        if (i>=12&&i<60){
            if (i==14&&i==20){
                Bullet bullet1=new Bullet(gameBot.e1.x+20,gameBot.e1.y+20,gameBot.e1.direction);
                gameBot.bullets1.add(bullet1);
                Bullet bullet2=new Bullet(gameBot.e2.x+20,gameBot.e2.y+20,gameBot.e2.direction);
                gameBot.bullets1.add(bullet2);
            }
            gameBot.boti1=1;
            gameBot.e1.direction="下";
            gameBot.e1.y+=10;
            gameBot.boti2=1;
            gameBot.e2.direction="下";
            gameBot.e2.y+=10;
        }
        if (i>=60&&i<78){
            gameBot.boti1=3;
            gameBot.e1.direction="右";
            gameBot.e1.x+=10;
            gameBot.boti2=1;
            gameBot.e2.direction="下";
            gameBot.e2.y+=10;
        }
        if(i>=78&&i<83) {
            if(i==80) {
                Bullet bb=new Bullet(gameBot.e1.x+20, gameBot.e1.y+20, gameBot.e1.direction);
                gameBot.bullets1.add(bb);
                Bullet bb2=new Bullet(gameBot.e2.x+20, gameBot.e2.y+20, gameBot.e2.direction);
                gameBot.bullets1.add(bb2);
            }
            gameBot.boti1=1;
            gameBot.e1.direction="下";
            gameBot.e1.y+=10;
            gameBot.boti2=1;
            gameBot.e2.direction="下";
            gameBot.e2.y+=10;
        }
        if(i>=83&&i<90) {
            if(i==88) {
                Bullet bb=new Bullet(gameBot.e1.x+20, gameBot.e1.y+20, gameBot.e1.direction);
                gameBot.bullets1.add(bb);
                Bullet bb2=new Bullet(gameBot.e2.x+20, gameBot.e2.y+20, gameBot.e2.direction);
                gameBot.bullets1.add(bb2);
            }
            gameBot.boti1=1;
            gameBot.e1.direction="下";
            gameBot.e1.y+=10;
            gameBot.boti2=2;
            gameBot.e2.direction="左";
            gameBot.e2.x-=10;
        }
        if(i>=90&&i<100) {
            if(i==98) {
                Bullet bb=new Bullet(gameBot.e1.x+20, gameBot.e1.y+20, gameBot.e1.direction);
                gameBot.bullets1.add(bb);
                Bullet bb2=new Bullet(gameBot.e2.x+20, gameBot.e2.y+20, gameBot.e2.direction);
                gameBot.bullets1.add(bb2);
            }
            gameBot.boti1=1;
            gameBot.e1.direction="下";
            gameBot.e1.y+=10;
        }
        if(i>=100&&i<104) {
            if(i==102) {
                Bullet bb=new Bullet(gameBot.e1.x+20, gameBot.e1.y+20, gameBot.e1.direction);
                gameBot.bullets1.add(bb);
                Bullet bb2=new Bullet(gameBot.e2.x+20, gameBot.e2.y+20, gameBot.e2.direction);
                gameBot.bullets1.add(bb2);
            }
            gameBot.boti1=3;
            gameBot.e1.direction="右";
            gameBot.e1.x+=10;
        }
    }
}