package com.constantinople.factory;

import com.artemis.EntityFactory;
import com.artemis.annotations.Bind;
import com.artemis.annotations.UseSetter;
import com.constantinople.component.Position;
import com.constantinople.component.Velocity;

public interface Item extends EntityFactory<Item> {
    @Bind(Position.class) @UseSetter Item position(float x, float y);
    @Bind(Velocity.class) @UseSetter Item velocity(float x, float y);
}
