package com.constantinople.util;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;

public class PhysicsStepSystem extends BaseSystem{

    public final static float STEP_TIME = 1.0f/120.0f;
    private final static float MAX_FRAME_TIME = 0.25f;

    private final Steppable toStep;
    private float accumulator;


    public PhysicsStepSystem(Steppable toStep){
        this.toStep = toStep;
    }

    @Override
    protected void processSystem() {
        float lastFrameTime = Math.min(Gdx.graphics.getDeltaTime(), MAX_FRAME_TIME);
        accumulator += lastFrameTime;

        while(accumulator >= STEP_TIME){
            accumulator -= STEP_TIME;
            toStep.step();
        }
    }

    public float getAccumulator(){
        return accumulator;
    }
}
