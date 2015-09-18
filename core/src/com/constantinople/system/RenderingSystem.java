package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.constantinople.component.Positionable;
import com.constantinople.component.Movable;

@Wire
public class RenderingSystem extends EntityProcessingSystem{
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private ComponentMapper<Positionable> pm;
    private ComponentMapper<Movable> mm;

    private PhysicsSystem physicsSystem;

    public RenderingSystem(OrthographicCamera camera) {
        super(Aspect.all(Positionable.class, Movable.class));
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
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
        Positionable position = pm.get(e);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.circle(position.position.x, position.position.y, 10);
        shapeRenderer.end();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Vector2 interpolatedPosition = interpolatePosition(e);
        shapeRenderer.circle(interpolatedPosition.x, interpolatedPosition.y, 10);
        shapeRenderer.end();
    }

    private Vector2 interpolatePosition(Entity e){
        float accumulator = physicsSystem.getAccumulator();
        Movable m = mm.get(e);
        Positionable p = pm.get(e);
        Vector2 interpolatedVelocity = m.lastVelocity.cpy().scl(accumulator);
        return p.lastPosition.cpy().add(interpolatedVelocity).add(m.lastAcceleration.cpy().scl(0.5f * accumulator * accumulator));

    }
}
