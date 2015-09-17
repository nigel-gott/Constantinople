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
import com.constantinople.component.Controllable;
import com.constantinople.component.Velocity;

@Wire
public class ControlSystem  extends EntityProcessingSystem{

    ComponentMapper<Velocity> vm;
    final Vector2 left, right, up, down;
    Vector2 velocity;

    private boolean changed;

    public ControlSystem(){
        super(Aspect.all(Controllable.class));
        velocity = new Vector2(0,0);
        left = new Vector2(-1,0);
        right = new Vector2(1,0);
        up = new Vector2(0,1);
        down = new Vector2(0,-1);
        changed = false;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode){
                boolean W = false;
                Vector2 original = velocity.cpy();
                switch(keycode){
                    case Input.Keys.A:
                        velocity.add(left);
                        break;
                    case Input.Keys.D:
                        velocity.add(right);
                        break;
                    case Input.Keys.W:
                        velocity.add(up);
                        break;
                    case Input.Keys.S:
                        velocity.add(down);
                        break;
                }

                changed = !(velocity.x == original.x && velocity.y == original.y);
                return true;
            }
            @Override
            public boolean keyUp(int keycode){
                Vector2 original = velocity.cpy();
                switch(keycode){
                    case Input.Keys.A:
                        velocity.sub(left);
                        break;
                    case Input.Keys.D:
                        velocity.sub(right);
                        break;
                    case Input.Keys.W:
                        velocity.sub(up);
                        break;
                    case Input.Keys.S:
                        velocity.sub(down);
                        break;
                }
                changed = !(velocity.x == original.x && velocity.y == original.y);
                return true;
            }
        });
    }

    @Override
    protected boolean checkProcessing(){
        return changed;
    }

    @Override
    protected void process(Entity e) {
        Velocity v = vm.get(e);
        v.getVelocity().set(velocity.cpy().nor().scl(50));
        System.out.println(v.getVelocity());
        changed = false;
    }

}
