package com.constantinople.util;

import com.artemis.BaseSystem;
import com.artemis.WorldConfiguration;

import java.util.ArrayList;
import java.util.List;

public class SystemContainer{
    final List<BaseSystem> systems;
    private final WorldConfiguration worldConfiguration;

    public SystemContainer(WorldConfiguration worldConfiguration){
        this.worldConfiguration = worldConfiguration;
        systems = new ArrayList<BaseSystem>();
    }

    public SystemContainer add(BaseSystem system){
        worldConfiguration.setSystem(system);
        systems.add(system);
        return this;
    }

    public void process(){
        for(BaseSystem system : systems){
            if(system.isEnabled()) {
                system.process();
            }
        }
    }
}
