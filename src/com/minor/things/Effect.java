/**
 * FileName: Effect
 * Author:   嘉平十七
 * Date:     2020/5/26 16:21
 * Description: 子弹效果
 * History:
 * notes：
 */
package com.minor.things;

import java.awt.*;

public class Effect {
    public int x,y;
    public Image image;

    public Effect(int x, int y, Image image) {
        super();
        this.x = x;
        this.y = y;
        this.image = image;
    }
}