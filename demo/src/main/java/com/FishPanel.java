package com;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FishPanel extends JPanel {

    private int x = 100;
    private int y = 100;

    private int dx = 3;
    private int dy = 3;

    private final Image image;

    private final SocketManager socket;

    public FishPanel(SocketManager socket) {

        this.socket = socket;

        image = new ImageIcon(
                getClass().getResource("/fish.gif")
        ).getImage();

        Timer timer = new Timer(16, e -> update());

        timer.start();

    }

    public void receiveFromNetwork() {

        x = 0;
        dx = Math.abs(dx);

    }

    private void update() {

        x += dx;
        y += dy;

        if (y <= 0 || y >= getHeight() - image.getHeight(this)) {
            dy = -dy;
        }

        if (x <= 0) {
            dx = -dx;
        }

        if(x >= getWidth() - image.getWidth(this)) {

            socket.sendTransfer();

            x = -200;

        }

        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(image,x,y,this);

    }

}
