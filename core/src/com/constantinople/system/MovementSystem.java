package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.constantinople.component.Position;
import com.constantinople.component.Velocity;

@Wire
public class MovementSystem extends EntityProcessingSystem{

    private ComponentMapper<Position> pm;
    private ComponentMapper<Velocity> vm;

    public MovementSystem() {
        super(Aspect.all(Position.class, Velocity.class));
    }

    @Override
    protected void process(Entity e) {
        Position position = pm.get(e);
        Velocity velocity = vm.get(e);

        position.position.mulAdd(velocity.velocity, world.getDelta());
    }
}
