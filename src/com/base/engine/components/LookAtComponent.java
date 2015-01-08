package com.base.engine.components;

import com.base.engine.core.Quaternion;
import com.base.engine.core.RenderingEngine;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Shader;

public class LookAtComponent extends GameComponent {
    private RenderingEngine renderingEngine;
    private GameComponent target;
    private Vector3f direction;
    private float factor;
    private boolean nlerp;

    public LookAtComponent(GameComponent target, Vector3f direction, float factor, boolean nlerp) {
        this.target = target;
        this.direction = direction;
        this.factor = factor;
        this.nlerp = nlerp;
    }

    public LookAtComponent(GameComponent target, Vector3f direction, float factor) {
       this(target, direction, factor, false);
    }

    public LookAtComponent(GameComponent target, Vector3f direction) {
        this(target, direction, 5.0f, false);
    }

    @Override
    public void update(float delta) {
        if (renderingEngine != null) {
            Quaternion newRot = getTransform().getLookAtDirection(
                    target.getTransform().getTransformedPosition(), direction);

            if (nlerp)
                getTransform().setRotation(getTransform().getRotation().nlerp(newRot, delta * factor, true));
            else
                getTransform().setRotation(getTransform().getRotation().slerp(newRot, delta * factor, true));
        }
    }

    @Override
    public void render(Shader shader, RenderingEngine renderingEngine) {
        this.renderingEngine = renderingEngine;
    }

}
