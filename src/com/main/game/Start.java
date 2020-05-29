/**
 * FileName: Start
 * Author:   嘉平十七
 * Date:     2020/5/26 16:49
 * Description:
 * History:
 * notes：
 */
package com.main.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Start extends Frame{
        Image start;
        public Start(){
            start=this.getToolkit().getImage("res/images/start.jpg");
            this.setTitle("坦克大战");
            this.setSize(900, 800);
            this.setBackground(Color.BLACK);
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getY()>380&&e.getY()<400){
                        GameBot gameBot=new GameBot();
                        dispose();
                    }
                    if (e.getY()>410&&e.getY()<425){
                        Game game=new Game();
                        dispose();
                    }
                }
            });
        }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(start,250,200,this);
    }

    public static void main(String[] args) {
        Start s=new Start();
    }
}