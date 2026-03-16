package com.fishnet.model;

import java.io.Serializable;

public class FishState implements Serializable {

    public int x;
    public int y;
    public int dx;
    public int dy;

    public FishState(int x,int y,int dx,int dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

}
