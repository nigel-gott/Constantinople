package com.constantinople.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Position extends Component {

    public Vector2 position;

    public void position(float x, float y) {
        position = new Vector2(x, y);
    }

}
