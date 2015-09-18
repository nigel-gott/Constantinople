package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.constantinople.Constantinople;
import com.constantinople.component.Positionable;
import com.constantinople.component.Movable;
import com.constantinople.util.ManualTriggerSystem;
import com.constantinople.util.PhysicsStepSystem;

@Wire
public class MovementSystem extends ManualTriggerSystem {

    private ComponentMapper<Positionable> pm;
    private ComponentMapper<Movable> vm;

    public MovementSystem() {
        super(Aspect.all(Positionable.class, Movable.class));
    }

    @Override
    protected void process(Entity e) {
        Positionable position = pm.get(e);
        Movable movable = vm.get(e);

        position.lastPosition = position.position.cpy();
        movable.lastVelocity = movable.velocity.cpy();
        position.position.mulAdd(movable.velocity, PhysicsStepSystem.STEP_TIME);
    }


}
