package com.base.engine.core;

import com.base.engine.components.lights.BaseLight;
import com.base.engine.components.Camera;
import com.base.engine.rendering.*;
import com.base.engine.rendering.resourceManagement.MappedValues;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine extends MappedValues {

    private HashMap<String, Integer> samplerMap;
    private ArrayList<BaseLight> lights;
    private BaseLight activeLight;

    private Shader forwardAmbient;
    private Camera mainCamera;

    public RenderingEngine() {

        super();
        lights = new ArrayList<BaseLight>();
        samplerMap = new HashMap<String, Integer>();
        samplerMap.put("diffuse", 0);

        addVector3f("ambient", new Vector3f(0.1f));

        forwardAmbient = new Shader("forward-ambient");

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_DEPTH_CLAMP);
        glEnable(GL_TEXTURE_2D);
    }

    public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType) {
        throw new IllegalArgumentException(uniformName + " is not a valid component of Transform");
    }

    public void render(GameObject object) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        object.renderAll(forwardAmbient, this);

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(false);
        glDepthFunc(GL_EQUAL);

        for (BaseLight light : lights) {
            activeLight = light;
            object.renderAll(light.getShader(), this);
        }

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    protected static void setClearColor(Vector3f color) {
        glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    }

    public static String getOpenGLVersion() {
        return glGetString(GL_VERSION);
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(Camera mainCamera) {
        this.mainCamera = mainCamera;
    }

    public void addLight(BaseLight light) {
        lights.add(light);
    }

    public void addCamera(Camera camera) {
        this.mainCamera = camera;
    }

    public void removeLight(BaseLight light) {
        lights.remove(light);
    }

    public BaseLight getActiveLight() {
        return activeLight;
    }

    public int getSamplerSlot(String samplerName) {
        return  samplerMap.get(samplerName);
    }
}