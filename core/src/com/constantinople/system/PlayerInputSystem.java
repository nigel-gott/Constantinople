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
    final Vector2 left, right, up, down;
    Vector2 velocity;

    private boolean changed;

    public PlayerInputSystem(){
        super(Aspect.all(Player.class));
        velocity = new Vector2(0,0);
        left = new Vector2(-1,0);
        right = new Vector2(1,0);
        up = new Vector2(0,1);
        down = new Vector2(0,-1);
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
        Vector2 original = velocity.cpy();
        int modifier = isDown ? 1 : -1;
        switch(keycode){
            case Input.Keys.A:
                velocity.add(left.cpy().scl(modifier));
                break;
            case Input.Keys.D:
                velocity.add(right.cpy().scl(modifier));
                break;
            case Input.Keys.W:
                velocity.add(up.cpy().scl(modifier));
                break;
            case Input.Keys.S:
                velocity.add(down.cpy().scl(modifier));
                break;
        }
        changed = !(velocity.x == original.x && velocity.y == original.y);
    }

    @Override
    protected boolean checkProcessing(){
        return changed;
    }

    @Override
    protected void process(Entity e) {
        Movable v = vm.get(e);
        v.acceleration.set(velocity.cpy());
        changed = false;
    }

}
