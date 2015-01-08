package com.base.engine.core;

import com.base.engine.components.GameComponent;
import com.base.engine.rendering.Shader;

import java.util.ArrayList;

public class GameObject {
    private static String depth = "";

    private ArrayList<GameObject> children;
    private ArrayList<GameComponent> components;
    private Transform transform;
    private CoreEngine engine;

    public GameObject(Vector3f positon, Quaternion rotation, Vector3f scale) {
        children = new ArrayList<GameObject>();
        components = new ArrayList<GameComponent>();
        transform = new Transform(positon, rotation, scale);
        engine = null;
    }

    public GameObject(Vector3f positon, Quaternion rotation, float scale) {
       this(positon, rotation, new Vector3f().setAll(scale));
    }

    public GameObject(Vector3f positon, Quaternion rotation) {
        this(positon, rotation, new Vector3f(1, 1, 1));
    }

    public GameObject(Vector3f positon, Vector3f scale) {
        this(positon, new Quaternion(),scale);
    }

    public GameObject(Vector3f positon, float scale) {
        this(positon, new Quaternion(),scale);
    }

    public GameObject(Vector3f positon) {
        this(positon, new Quaternion(), new Vector3f().setAll());
    }

    public GameObject(Quaternion rotion) {
        this(new Vector3f(), rotion, new Vector3f().setAll());
    }

    public GameObject() {
        this(new Vector3f(), new Quaternion(), new Vector3f(1, 1, 1));
    }


    public void inputAll(float delta) {

        input(delta);

        for (GameObject child : children)
            child.inputAll(delta);
    }

    public void updateAll(float delta) {

        update(delta);

        for (GameObject child : children)
            child.updateAll(delta);
    }

    public void renderAll(Shader shader, RenderingEngine renderingEngine) {

        render(shader, renderingEngine);

        for (GameObject child : children)
            child.renderAll(shader, renderingEngine);
    }

    public void input(float delta) {

        transform.update();

        for (GameComponent component : components)
            component.input(delta);
    }

    public void update(float delta) {
        for (GameComponent component : components)
            component.update(delta);
    }

    public void render(Shader shader, RenderingEngine renderingEngine) {
        for (GameComponent component : components)
            component.render(shader, renderingEngine);
    }

    public GameObject addChild(GameObject child) {
        children.add(child);
        child.setEngine(engine);
        child.getTransform().setParent(transform);
        return this;
    }

    public GameObject removeChild(GameObject child) {
        children.remove(child);
        return this;
    }

    public GameObject addComponent(GameComponent component) {

        components.add(component);
        component.setParent(this);

        return this;
    }

    public GameObject removeComponent(GameComponent component) {
        components.remove(component);
        return this;
    }

    public ArrayList<GameObject> getAllAttached() {
        ArrayList<GameObject> result = new ArrayList<GameObject>();

        for (GameObject child : children)
            result.addAll(child.getAllAttached());


        result.add(this);
        return result;
    }


    public Transform getTransform() {
        return transform;
    }

    public void setEngine(CoreEngine engine) {
        if (this.engine != engine) {
            this.engine = engine;

            for (GameComponent component : components)
                component.addToEngine(engine);

            for (GameObject child : children)
                child.setEngine(engine);

        }
    }

    /*
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( depth + "GameObject\n");
        sb.append("-" + depth + "GameComponent: " + components.size() +"\n");
        for (GameComponent gc : components)
            sb.append(gc + "\n");

        sb.append("-" + depth + "GameObject: " + children.size() + "\n");
        for (GameObject go : children) {
            sb.append(go.toString() + "\n");
        }

        depth += "-";

        return sb.toString();
    }
    */
}
