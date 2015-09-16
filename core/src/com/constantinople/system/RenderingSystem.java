package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.constantinople.component.Position;
import com.constantinople.component.Velocity;

@Wire
public class RenderingSystem extends EntityProcessingSystem{
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
    protected void begin(){
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update the camera and the shape renderer
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.identity();
    }

    @Override
    protected void process(Entity e) {
        Position position = pm.get(e);
        shapeRenderer.circle(position.position.x, position.position.y, 10);
    }
}
