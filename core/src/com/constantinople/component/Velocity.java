package com.constantinople.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Velocity extends Component {

    public Vector2 velocity;

    public void velocity(float x, float y) {
        velocity = new Vector2(x, y);
    }
}
