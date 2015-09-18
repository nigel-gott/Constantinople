package com.constantinople.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Movable extends Component {


    public Vector2 velocity;
    public Vector2 acceleration;
    public Vector2 lastVelocity;
    public Vector2 lastAcceleration;

    public Movable(){
        velocity = new Vector2(0,0);
        lastVelocity = velocity.cpy();
        acceleration = new Vector2(0,0);
        lastAcceleration = acceleration.cpy();
    }
}
