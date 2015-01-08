package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class IntersectData {

    private boolean doesIntersect;
    private Vector3f direction;

    public IntersectData(boolean doesIntersect, Vector3f direction) {

        this.doesIntersect = doesIntersect;
        this.direction = direction;
    }

    public boolean getDoesIntersect() {
        return doesIntersect;
    }

    /*
    public void setDoesIntersect(boolean doesIntersect) {
        this.doesIntersect = doesIntersect;
    }
    */

    public Vector3f getDirection() {
        return direction;
    }

    public float getDistance() {
        return direction.length();
    }

   /*
    public void setDistance(Vector3f direction) {
        this.direction = direction;
    }
    */

    @Override
    public String toString() {
        return "IntersectData:" + " \ndoesIntersect: " + doesIntersect + " \ndirection: " + direction;
    }
}
