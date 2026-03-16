package com.fishnet.animation;

import com.fishnet.model.FishState;
import com.fishnet.network.SocketClient;
import com.fishnet.ui.FishWindow;

import javax.swing.*;

public class FishAnimator {

    private FishWindow window;

    private int x = 100;
    private int y = 100;

    private int dx = 4;
    private int dy = 3;

    private Timer timer;

    private SocketClient client;

    public FishAnimator(FishWindow window, SocketClient client) {

        this.window = window;
        this.client = client;

        timer = new Timer(16, e -> update());
    }

    public void start() {
        timer.start();
    }

    private void update() {

        x += dx;
        y += dy;

        int w = window.getWidth();
        int h = window.getHeight();

        int fw = window.getFishWidth();
        int fh = window.getFishHeight();

        if (y <= 0 || y + fh >= h) {
            dy = -dy;
        }

        if (x <= 0) {
            dx = -dx;
        }

        if (x + fw >= w) {

            FishState state = new FishState(0, y, dx, dy);

            client.sendFish(state);

            timer.stop();

            window.dispose();

            return;
        }

        window.updatePosition(x, y);
    }

}