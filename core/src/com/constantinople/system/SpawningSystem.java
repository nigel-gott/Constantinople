package com.constantinople.system;

import com.artemis.*;
import com.artemis.annotations.Wire;
import com.constantinople.component.Bounds;
import com.constantinople.component.Player;
import com.constantinople.component.Positionable;
import com.constantinople.component.Movable;


@Wire
public class SpawningSystem extends BaseSystem{
    private ComponentMapper<Positionable> pm;
    private ComponentMapper<Movable> vm;
    private ComponentMapper<Bounds> bm;

    @Override
    public void initialize(){
        this.setPassive(true);
        Archetype objectArchetype = new ArchetypeBuilder()
                .add(Positionable.class)
                .add(Movable.class)
                .add(Bounds.class)
                .build(world);
        Entity item1 = world.createEntity(objectArchetype);
        pm.get(item1).set(300, 300);
        bm.get(item1).radius = 20;

        Entity player = world.createEntity(objectArchetype);
        player.edit().create(Player.class);
        pm.get(player).set(10, 10);
        bm.get(player).radius = 15;

    }

    @Override
    public void processSystem(){

    }
}
