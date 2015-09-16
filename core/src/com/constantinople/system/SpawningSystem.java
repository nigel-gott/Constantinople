package com.constantinople.system;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.constantinople.factory.Item;


@Wire
public class SpawningSystem extends BaseSystem{
    private Item item;

    @Override
    public void initialize(){
        this.setPassive(true);
        Entity item1 = item.position(10,10).velocity(1,1).create();
    }

    @Override
    public void processSystem(){

    }
}
