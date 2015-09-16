package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.constantinople.component.Position;
import com.constantinople.component.Velocity;

@Wire
public class RenderingSystem extends EntitySystem{
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private ComponentMapper<Position> pm;

    public RenderingSystem(OrthographicCamera camera) {
        super(Aspect.all(Position.class, Velocity.class));
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
    }

    @Override
    protected void processSystem() {
        int[] entityIDs = getSubscription().getEntities().getData();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update the camera and the shape renderer
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.identity();

        Entity entity = createFlyweightEntity();
        // render each entity
        for (int id : entityIDs) {
            entity.id = id;
            Position position = pm.get(entity);
            shapeRenderer.circle(position.position.x, position.position.y, 10);
        }

    }
}
