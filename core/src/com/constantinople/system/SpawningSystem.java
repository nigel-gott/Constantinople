package com.constantinople.system;

import com.artemis.*;
import com.artemis.annotations.Wire;
import com.constantinople.component.Player;
import com.constantinople.component.Positionable;
import com.constantinople.component.Movable;


@Wire
public class SpawningSystem extends BaseSystem{
    private ComponentMapper<Positionable> pm;
    private ComponentMapper<Movable> vm;

    @Override
    public void initialize(){
        this.setPassive(true);
        Archetype playerArchetype = new ArchetypeBuilder()
                .add(Positionable.class)
                .add(Movable.class)
                .add(Player.class)
                .build(world);
        Entity item2 = world.createEntity(playerArchetype);
        pm.get(item2).set(10, 10);
    }

    @Override
    public void processSystem(){

    }
}
