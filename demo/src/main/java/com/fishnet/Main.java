package com.fishnet;

import com.fishnet.animation.FishAnimator;
import com.fishnet.model.FishState;
import com.fishnet.network.DiscoveryService;
import com.fishnet.network.SocketClient;
import com.fishnet.network.SocketServer;
import com.fishnet.ui.FishWindow;

import javax.swing.*;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            DiscoveryService discovery = new DiscoveryService();
            discovery.start();

            SocketServer server = new SocketServer(state -> {

                FishWindow newWindow = new FishWindow();

                FishAnimator animator = new FishAnimator(newWindow, exitState -> {

                    Set<String> peers = discovery.getPeers();

                    if(peers.isEmpty()) return;

                    String peer = peers.iterator().next();

                    SocketClient client = new SocketClient(peer);
                    client.sendFish(exitState);

                });

                newWindow.setVisible(true);
                animator.start();

            });

            server.start();

            FishWindow window = new FishWindow();

            FishAnimator animator = new FishAnimator(window, state -> {

                Set<String> peers = discovery.getPeers();

                if(peers.isEmpty()) return;

                String peer = peers.iterator().next();

                SocketClient client = new SocketClient(peer);
                client.sendFish(state);

            });

            window.setVisible(true);
            animator.start();

        });

    }

}
