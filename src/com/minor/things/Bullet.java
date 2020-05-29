/**
 * FileName: Bullet
 * Author:   嘉平十七
 * Date:     2020/5/26 16:14
 * Description: 定义子弹
 * History:
 * notes：
 */
package com.minor.things;

public class Bullet {
    public int x,y;
    public String direction;
    public Bullet(int x,int y,String direction){
        super();
        this.x=x;
        this.y=y;
        this.direction=direction;
    }
}