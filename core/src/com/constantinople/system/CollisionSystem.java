package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.math.Vector2;
import com.constantinople.component.Bounds;
import com.constantinople.component.Movable;
import com.constantinople.component.Positionable;
import com.constantinople.util.SystemContainer;
import net.mostlyoriginal.api.system.core.DualEntityProcessingSystem;

@Wire
public class CollisionSystem extends DualEntityProcessingSystem{

    private ComponentMapper<Positionable> pm;
    private ComponentMapper<Movable> mm;
    private ComponentMapper<Bounds> bm;
    private static final Aspect.Builder aspect = Aspect.all(Movable.class, Bounds.class, Positionable.class);

    public CollisionSystem() {
        super(aspect, aspect);
        setPassive(true);
    }

    protected void process(Entity a, Entity b) {
        if(a.id == b.id || a.id < b.id){
            return;
        }

        Vector2 aPos = pm.get(a).position;
        int aRadius = bm.get(a).radius;
        Vector2 aVel = mm.get(a).velocity;
        Vector2 bPos = pm.get(b).position;
        int bRadius = bm.get(b).radius;
        Vector2 bVel = mm.get(b).velocity;

        Vector2 collision = aPos.cpy().sub(bPos);
        float distance = collision.len();
        float overlap = aRadius + bRadius - distance;
        if(overlap <= 0){
            return;
        }

        System.out.println(overlap);

        collision = collision.nor();
        aPos.mulAdd(collision, overlap / 2);
        bPos.mulAdd(collision, - overlap / 2);

    }


}
