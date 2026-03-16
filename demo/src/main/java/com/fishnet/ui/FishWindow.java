package com.fishnet.ui;

import javax.swing.*;
import java.awt.*;

public class FishWindow extends JFrame {

    private JLabel fishLabel;

    public FishWindow(){

        setTitle("Fish Network");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        ImageIcon icon =
                new ImageIcon(getClass().getResource("/fish.gif"));

        fishLabel = new JLabel(icon);
        fishLabel.setSize(icon.getIconWidth(),icon.getIconHeight());

        add(fishLabel);

    }

    public void updatePosition(int x,int y){
        fishLabel.setLocation(x,y);
    }

    public int getFishWidth(){
        return fishLabel.getWidth();
    }

    public int getFishHeight(){
        return fishLabel.getHeight();
    }

}
