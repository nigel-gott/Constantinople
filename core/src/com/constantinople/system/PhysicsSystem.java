package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.constantinople.component.Positionable;
import com.constantinople.component.Movable;
import com.constantinople.util.AdaptiveIntervalEntityProcessingSystem;

@Wire
public class PhysicsSystem extends AdaptiveIntervalEntityProcessingSystem {

    private final static float TIMESTEP = 1.0f/120.0f;
    private final static float MAX_FRAMETIME = 0.25f;

    private ComponentMapper<Positionable> pm;
    private ComponentMapper<Movable> vm;

    public PhysicsSystem() {
        super(Aspect.all(Positionable.class, Movable.class), TIMESTEP, MAX_FRAMETIME);
    }

    @Override
    protected void process(Entity e) {
        Positionable position = pm.get(e);
        Movable movable = vm.get(e);

        position.lastPosition = position.position.cpy();
        movable.lastVelocity = movable.velocity.cpy();
        movable.lastAcceleration = movable.acceleration.cpy();

        movable.velocity.add(movable.acceleration);
        position.position.mulAdd(movable.velocity, TIMESTEP);
    }


}
