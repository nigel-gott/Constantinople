package com.constantinople.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.constantinople.component.Player;
import com.constantinople.component.Movable;

@Wire
public class PlayerInputSystem extends EntityProcessingSystem{
    ComponentMapper<Movable> vm;
    ComponentMapper<Player> pm;

    final static int rotationSpeed = 1;
    final static int speedModifier = 100;

    int rotationDelta;
    int speed;

    private boolean changed;

    public PlayerInputSystem(){
        super(Aspect.all(Player.class));

        rotationDelta = 0;
        speed = 0;

        changed = false;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode){
                keyChange(keycode, true);
                return true;
            }
            @Override
            public boolean keyUp(int keycode){
                keyChange(keycode, false);
                return true;
            }
        });
    }

    private void keyChange(int keycode, boolean isDown){
        float orgSpeed = speed;
        int orgRotationDelta = rotationDelta;
        int modifier = isDown ? 1 : -1;
        switch(keycode){
            case Input.Keys.A:
                rotationDelta += modifier * rotationSpeed;
                break;
            case Input.Keys.D:
                rotationDelta += modifier * -rotationSpeed;
                break;
            case Input.Keys.W:
                speed += modifier * speedModifier;
                break;
            case Input.Keys.S:
                speed -= modifier * speedModifier;
                break;
        }
        changed = !(orgSpeed == speed && orgRotationDelta == rotationDelta);
    }

    @Override
    protected boolean checkProcessing(){
        return changed;
    }

        @Override
        protected void process(Entity e) {
            Player p = pm.get(e);
            p.rotationSpeed = rotationDelta;
            p.speed = speed;
        changed = false;
    }

}
