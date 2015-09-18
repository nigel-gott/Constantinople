package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.constantinople.component.Movable;
import com.constantinople.component.Player;
import com.constantinople.util.ManualTriggerSystem;

@Wire
public class PlayerMovementSystem extends ManualTriggerSystem {

    private ComponentMapper<Player> pm;
    private ComponentMapper<Movable> vm;

    public PlayerMovementSystem() {
        super(Aspect.all(Player.class, Movable.class));
    }

    @Override
    protected void process(Entity e) {
        Movable movable = vm.get(e);
        Player player = pm.get(e);

        player.rotation += player.rotationSpeed;
        movable.velocity.set(0, player.speed).rotate(player.rotation);
    }


}
