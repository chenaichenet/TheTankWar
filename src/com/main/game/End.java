/**
 * FileName: End
 * Author:   嘉平十七
 * Date:     2020/5/27 9:42
 * Description:
 * History:
 * notes：
 */
package com.main.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class End extends Frame {
    Image end;
    public End(int i){
        end=this.getToolkit().getImage("res/images/end.jpg");
        this.setTitle("坦克大战");
        this.setSize(900,800);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose();
            }
        });
        switch (i){
            case 1:
                JOptionPane.showMessageDialog(null,"玩家一获胜");
                break;
            case 2:
                JOptionPane.showMessageDialog(null,"玩家二获胜");
                break;
            case 3:
                JOptionPane.showMessageDialog(null,"玩家获胜");
                break;
            case 4:
                JOptionPane.showMessageDialog(null,"电脑获胜");
                break;
            default:
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(end,250,200,this);
    }
}