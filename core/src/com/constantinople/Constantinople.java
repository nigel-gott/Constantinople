package com.constantinople;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.constantinople.system.MovementSystem;
import com.constantinople.system.RenderingSystem;
import com.constantinople.system.SpawningSystem;

public class Constantinople extends ApplicationAdapter {

    World world;
    OrthographicCamera camera;

    @Override
	public void create () {
        camera = new OrthographicCamera();

        WorldConfiguration config = new WorldConfiguration()
                .setSystem(new SpawningSystem())
                .setSystem(new MovementSystem())
                .setSystem(new RenderingSystem(camera));

        world = new World(config);
    }

	@Override
	public void render () {
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
	}

    /**
     * Update the camera if the game screen is resized.
     */
    @Override
    public void resize(int width, int height) {
        float centerX = width / 2.0f;
        float centerY = height / 2.0f;

        this.camera.position.set(centerX, centerY, 0);
        this.camera.viewportWidth = width;
        this.camera.viewportHeight = height;
    }
}
