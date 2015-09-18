package com.constantinople.util;

import com.artemis.Aspect;
import com.artemis.systems.EntityProcessingSystem;

public abstract class ManualTriggerSystem extends EntityProcessingSystem{
    public ManualTriggerSystem(Aspect.Builder aspect){
        super(aspect);
        setPassive(true);
    }
}
