package com.constantinople.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Movable extends Component {

    public Vector2 velocity;
    public Vector2 lastVelocity;

    public Movable(){
        velocity = new Vector2(0,0);
        lastVelocity = velocity.cpy();
    }

    public Vector2 get() {
        return velocity.cpy();
    }

    public void set(float x, float y) {
        velocity.set(x,y);
    }
}
