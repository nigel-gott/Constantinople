package com.constantinople;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.constantinople.system.*;
import com.constantinople.util.PhysicsStepSystem;
import com.constantinople.util.SystemContainer;

public class Constantinople extends ApplicationAdapter {


    World world;
    OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();

        WorldConfiguration config = new WorldConfiguration();

        SystemContainer physicsSubSystems = new SystemContainer(config)
                .add(new CollisionSystem())
                .add(new PlayerMovementSystem())
                .add(new MovementSystem());
        PhysicsStepSystem physicsStepper = new PhysicsStepSystem(physicsSubSystems);

        config.setSystem(new SpawningSystem())
                .setSystem(new PlayerInputSystem())
                .setSystem(physicsStepper)
                .setSystem(new RenderingSystem(camera));

        world = new World(config);
    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    /**
     * Update the camera if the game screen is resized.
     */
    @Override
    public void resize(int width, int height) {
        float centerX = width / 2.0f;
        float centerY = height / 2.0f;

        camera.position.set(centerX, centerY, 0);
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }
}
