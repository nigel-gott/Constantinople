package com.constantinople.system;

import com.artemis.*;
import com.artemis.annotations.Wire;
import com.constantinople.component.Controllable;
import com.constantinople.component.Position;
import com.constantinople.component.Velocity;
import com.constantinople.factory.Item;


@Wire
public class SpawningSystem extends BaseSystem{
    private Item item;
    private ComponentMapper<Position> pm;
    private ComponentMapper<Velocity> vm;

    @Override
    public void initialize(){
        this.setPassive(true);
        Archetype playerArchetype = new ArchetypeBuilder()
                .add(Position.class)
                .add(Velocity.class)
                .add(Controllable.class)
                .build(world);
        Entity item1 = item.position(10,10).velocity(1,1).create();
        Entity item2 = world.createEntity(playerArchetype);
        pm.get(item2).position(10,10);
    }

    @Override
    public void processSystem(){

    }
}
