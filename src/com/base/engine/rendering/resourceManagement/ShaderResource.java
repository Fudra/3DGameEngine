package com.base.engine.rendering.resourceManagement;

import com.base.engine.core.ReferenceCounter;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.glCreateProgram;

public class ShaderResource extends ReferenceCounter{

    private int program;
    private HashMap<String, Integer> uniforms;
    private ArrayList<String> uniformNames;
    private ArrayList<String> uniformTypes;

    public ShaderResource() {
        super();
        uniforms = new HashMap<String, Integer>();
        uniformNames = new ArrayList<String>();
        uniformTypes = new ArrayList<String>();
        this.program = glCreateProgram();

        if(program == 0) {
            System.err.println("Shader creation failed: could not find valid memory location in constructor");
            System.exit(1);
        }
    }

    public int getProgram() {
        return program;
    }

    public HashMap<String, Integer> getUniforms() {
        return uniforms;
    }

    public ArrayList<String> getUniformNames() {
        return uniformNames;
    }

    public ArrayList<String> getUniformTypes() {
        return uniformTypes;
    }

    @Override
    protected void finalize() {
        glDeleteBuffers(program);
    }
}
