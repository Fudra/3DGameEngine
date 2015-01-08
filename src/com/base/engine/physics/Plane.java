package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class Plane {

    private Vector3f normal;
    private float distance;

    public Plane(Vector3f normal, float distance) {
        this.normal = normal;
        this.distance = distance;
    }

    public Plane normalized() {
        float magnitude = normal.length();

        return new Plane(normal.div(magnitude), distance/magnitude);
    }

    public IntersectData intersectSphere(BoundingSphere sphere) {

        float distanceFromSphereCenter = Math.abs(normal.dot(sphere.getCenter()) + distance);
        float distanceFromSphere = distanceFromSphereCenter - sphere.getRadius();

        return new IntersectData(distanceFromSphere < 0, normal.mul(distanceFromSphere));
    }

    public Vector3f getNormal() {
        return normal;
    }

    public float getDistance() {
        return distance;
    }
}
