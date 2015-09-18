package com.constantinople.util;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;

public abstract class AdaptiveIntervalSystem extends EntitySystem{

    private final float timestep, maxFrameTime;
    private Entity flyweight;
    private float accumulator;

    public AdaptiveIntervalSystem(Aspect.Builder aspect, float timestep, float maxFrameTime){
        super(aspect);
        this.timestep = timestep;
        this.maxFrameTime = maxFrameTime;
    }

    @Override
    protected void setWorld(World world) {
        super.setWorld(world);
        flyweight = createFlyweightEntity();
    }

    @Override
    protected void processSystem() {
        IntBag entities = getSubscription().getEntities();

        float lastFrameTime = Math.min(Gdx.graphics.getDeltaTime(), maxFrameTime);
        accumulator += lastFrameTime;

        int count = 0;
        while(accumulator >= timestep){
            accumulator -= timestep;
            processEntities(entities);
            count++;
        }
        float timeStepsLeft = accumulator / timestep;
        if(count > 1) {
            System.out.println("MultiStep of " + count + " with remainder " + (100*timeStepsLeft) + "%");
        }

    }

    private void processEntities(IntBag entities){
        int[] ids = entities.getData();
        for(int i = 0; i < entities.size(); i++){
            flyweight.id = ids[i];
            process(flyweight);
        }
    }

    public float getAccumulator(){
        return accumulator;
    }

    protected abstract void process(Entity e);
}
