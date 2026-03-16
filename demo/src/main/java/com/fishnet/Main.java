package com.fishnet;

import javax.swing.SwingUtilities;

import com.fishnet.animation.FishAnimator;
import com.fishnet.network.SocketClient;
import com.fishnet.network.SocketServer;
import com.fishnet.ui.FishWindow;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            FishWindow window = new FishWindow();

            SocketClient client = new SocketClient("192.168.1.10");

            FishAnimator animator = new FishAnimator(window, client);

            SocketServer server = new SocketServer(state -> {

                FishWindow newWindow = new FishWindow();

                FishAnimator newAnimator =
                        new FishAnimator(newWindow, client);

                newWindow.setVisible(true);

                newAnimator.start();

            });

            server.start();

            window.setVisible(true);

            animator.start();

        });

    }

}