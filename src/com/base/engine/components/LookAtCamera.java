package com.base.engine.components;

import com.base.engine.components.GameComponent;
import com.base.engine.core.Quaternion;
import com.base.engine.core.RenderingEngine;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Shader;

public class LookAtCamera extends GameComponent {
    RenderingEngine renderingEngine;

    @Override
    public void update(float delta) {
        if(renderingEngine != null) {

         //   System.out.println("mainCam :" + renderingEngine.getMainCamera().getTransform().getTransformedPosition());
            Quaternion newRot = getTransform().getLookAtDirection(
                    renderingEngine.getMainCamera().getTransform().getTransformedPosition(),
                    new Vector3f(0,1,0));

          //  System.out.println("newRot: " + newRot);
            //getTransform().setRotation(getTransform().getRotation().nlerp(newRot, delta * 5.0f, true));
            getTransform().setRotation(getTransform().getRotation().slerp(newRot, delta * 5.0f, true));
        }
    }

    @Override
    public void render(Shader shader, RenderingEngine renderingEngine) {
        this.renderingEngine = renderingEngine;
    }
}
