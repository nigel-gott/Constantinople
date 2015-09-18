package com.constantinople.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class Positionable extends Component {

    public Vector2 position;
    public Vector2 lastPosition;

    public void set(float x, float y) {
        position = new Vector2(x, y);
        lastPosition = position.cpy();
    }

}
