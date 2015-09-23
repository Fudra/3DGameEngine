package com.base.engine.components.terrain.shader;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

/**
 * Created by Simon on 19.06.2015.
 */
public abstract class ShaderProgram {

    private int programmID;
    private int vertexShaderID;
    private int fragmentShaderID;

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public ShaderProgram(String vertexFile, String fragmentFile){
        vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
        programmID = GL20.glCreateProgram();
        GL20.glAttachShader(programmID,vertexShaderID);
        GL20.glAttachShader(programmID,fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programmID);
        GL20.glValidateProgram(programmID);
        getAllUniformLocations();
    }

    protected int getUniformLocation(String UniformName){
        return GL20.glGetUniformLocation(programmID,UniformName);
    }

    protected abstract void getAllUniformLocations();

    protected abstract void bindAttributes();

    public void start(){
        GL20.glUseProgram(programmID);
    }

    public void stop(){
        GL20.glUseProgram(0);
    }

    public void cleanUp(){
        stop();
        GL20.glDetachShader(programmID,vertexShaderID);
        GL20.glDetachShader(programmID,fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programmID);

    }

    protected void bindAttribute(int attributes, String variableName){
        GL20.glBindAttribLocation(programmID, attributes, variableName);
    }

    protected void loadFloat(int location, float value){
        GL20.glUniform1f(location, value);
    }

    protected void loadInt(int location, int value){
        GL20.glUniform1i(location, value);
    }

    protected void loadVector(int location, Vector3f vector){
        GL20.glUniform3f(location, vector.x, vector.y, vector.z);
    }
    protected void load2DVector(int location, Vector2f vector){
        GL20.glUniform2f(location,vector.x,vector.y);
    }

    protected void loadBoolean(int location, boolean value){

        float toLoad = 0;

        if (value){
            toLoad = 1;
        }

        GL20.glUniform1f(location,toLoad);
    }


    protected void loadMatrix(int location, Matrix4f matrix){
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location,false,matrixBuffer);
    }

    private static int loadShader(String file,int type){

        StringBuilder shaderSource = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine())!=null){
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open fiele!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("could not read file!");
            e.printStackTrace();
        }

        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID,shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS)== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID,500));
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }
        return shaderID;

    }
}
