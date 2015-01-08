package com.base.engine.components;

import com.base.engine.core.*;
import com.base.engine.rendering.Window;

public class Camera extends GameComponent {

    private Matrix4f projection;

    public Camera() {
        this(70f, (float) Window.getWidth()/Window.getHeight(), 0.1f, 1000);
    }

    public Camera(float fov, float aspect, float zNear, float zFar) {
        this.projection = new Matrix4f().perspective((float)Math.toRadians(fov), aspect, zNear, zFar);
       // this.projection = new Matrix4f().orthographic(-20,20,20,-20, zNear, zFar);

    }

    public Matrix4f getViewProjection()
    {
        Matrix4f cameraRotation = getTransform().getTransformedRotation().conjugate().toRotationMatrix();
        Vector3f cameraPos = getTransform().getTransformedPosition().mul(-1);

        Matrix4f cameraTranslation = new Matrix4f().translation(
                cameraPos.getX(),
                cameraPos.getY(),
                cameraPos.getZ());

        return projection.mul(cameraRotation.mul(cameraTranslation));
    }

    @Override
    public void addToEngine(CoreEngine coreEngine) {
        coreEngine.getRenderingEngine().addCamera(this);
    }
}
