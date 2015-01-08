package com.base.engine;

import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;

public class Primitives {

    public static Material loadTestMaterial(){
        Material m =  new Material();
        m.addTexture("diffuse",new Texture("test.png"));
        m.addFloat("specularIntensity", 1);
        m.addFloat("specularPower", 8);
        return m; // new Texture("test.png"),  new Vector3f(1,1,1), 1, 8
    }
    public static Material loadTestMaterial(String materialName){
        Material m =  new Material();
        m.addTexture("diffuse",new Texture(materialName));
        m.addFloat("specularIntensity", 1);
        m.addFloat("specularPower", 8);
        return m; // new Texture("test.png"),  new Vector3f(1,1,1), 1, 8
    }

    public static Mesh getPlane(float fieldWidth, float fieldDepth){
        // plane
        Vertex[] vertices = new Vertex[] {
                new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex( new Vector3f( fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f( fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f)),
        };

        int indices[] = {
                0, 1, 2,
                2, 1, 3
        };


        return new Mesh(vertices, indices, true);
    }

    public static Mesh getPlane(){
       return getPlane(10.0f, 10.0f);
    }

    public static Mesh getTriangle(){
        Vertex[] vertices = new Vertex[]{
                new Vertex(new Vector3f(-1.0f, -1.0f, 0.5773f), new Vector2f(0.0f, 0.0f)),
                new Vertex(new Vector3f(0.0f, -1.0f, -1.15475f), new Vector2f(0.5f, 0.0f)),
                new Vertex(new Vector3f(1.0f, -1.0f, 0.5773f), new Vector2f(1.0f, 0.0f)),
                new Vertex(new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.5f, 1.0f)),
        };

        int indices[] = {
                0, 3, 1,
                1, 3, 2,
                2, 3, 0,
                1, 2, 0
        };
        return new Mesh(vertices, indices, true);
    }



}
