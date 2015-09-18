package com.constantinople.component;


import com.artemis.Component;

public class Player extends Component {
    public int rotation;
    public int rotationSpeed;
    public int speed;

    public Player() {
        speed = 0;
        rotation = 0;
        rotationSpeed = 0;
    }
}
