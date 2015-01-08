package com.base.engine.core;

import com.base.engine.rendering.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Util {

    public static FloatBuffer createFloatBuffer(int size) {
        return BufferUtils.createFloatBuffer(size);
    }

    public static IntBuffer createIntBuffer(int size) {
        return BufferUtils.createIntBuffer(size);
    }

    public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);

        for (Vertex v : vertices) {
            buffer.put(v.getPos().getX());
            buffer.put(v.getPos().getY());
            buffer.put(v.getPos().getZ());
            buffer.put(v.getTexCoord().getX());
            buffer.put(v.getTexCoord().getY());
            buffer.put(v.getNormal().getX());
            buffer.put(v.getNormal().getY());
            buffer.put(v.getNormal().getZ());
        }

        buffer.flip();

        return buffer;
    }

    public static FloatBuffer createFlippedBuffer(Matrix4f value) {
        FloatBuffer buffer = createFloatBuffer(4 * 4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buffer.put(value.get(i, j));
            }
        }

        buffer.flip();

        return buffer;
    }

    public static IntBuffer createFlippedBuffer(int... values) {
        IntBuffer buffer = createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();

        return buffer;
    }

    public static String[] removeEmptyStrings(String[] data) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < data.length; i++) {
            if(!data[i].equals(" "))
                result.add(data[i]);
        }

        String[] res = new String[result.size()];
        result.toArray(res);

        return res;
    }

    public static int[] toIntArray(Integer[] data) {
        int [] result = new int[data.length];

        for (int i = 0; i < data.length; i++) {
            result[i] = data[i].intValue();
        }

        return result;
    }

    public static ByteBuffer createByteBuffer(int size) {
        return BufferUtils.createByteBuffer(size);
    }
}
