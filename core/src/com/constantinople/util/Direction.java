package com.constantinople.util;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
    UP(0,1),
    RIGHT(1,0),
    DOWN(0,-1),
    LEFT(-1,0);

    private final int x,y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2 get(){
        return new Vector2(this.x, this.y);
    }
}
