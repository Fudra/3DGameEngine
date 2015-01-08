package com.base.engine.rendering.resourceManagement;

import com.base.engine.core.ReferenceCounter;

import static org.lwjgl.opengl.GL15.*;

public class MeshResource extends ReferenceCounter {

    private int vbo; // vertex buffered object
    private int ibo; // index buffered object
    private int size;


    public MeshResource(int size) {
        super();
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        this.size = size;
    }

    public int getVbo() {
        return vbo;
    }

    public int getIbo() {
        return ibo;
    }

    public int getSize() {
        return size;
    }


    @Override
    protected void finalize() {
        glDeleteBuffers(vbo);
        glDeleteBuffers(ibo);
    }

}
