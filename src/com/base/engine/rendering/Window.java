package com.base.engine.rendering;

import com.base.engine.InputOld;
import com.base.engine.core.Vector2f;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {

    public static void createWindow(int width, int height, String title){
        try {
            Display.setTitle(title);
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
            InputOld.init();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void render(){
        Display.update();
    }

    public static boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    public static int getWidth(){
        return Display.getDisplayMode().getWidth();
    }

    public static int getHeight(){
        return Display.getDisplayMode().getHeight();
    }

    public static String getTitle() {
        return Display.getTitle();
    }

    public static void dispose() {
        Display.destroy();
        InputOld.dispose();
    }

    public static Vector2f getCenter () {
        return  new Vector2f(getWidth()/2, getHeight()/2);
    }
}
