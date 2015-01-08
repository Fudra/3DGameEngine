package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class BoundingSphere extends  Collider{

    private Vector3f center;
    private float    radius;

    public BoundingSphere(Vector3f center, float radius) {
        super(ColliderType.SPHERE);
        this.center = center;
        this.radius = radius;
    }

    public IntersectData intersectBoundingSphere(BoundingSphere other) {
        float radiusDistance = radius + other.radius;
        Vector3f direction = other.getCenter().sub(center);
        float centerDistance = direction.length();
        direction =  direction.div(centerDistance);

        float distance = centerDistance - radiusDistance;
        return new IntersectData(distance < 0, direction.mul(distance));
    }

    //TODO : IntersectAABB(AABB other)
    public float getRadius() {
        return radius;
    }

    @Override
    public Vector3f getCenter() {
        return center;
    }

    @Override
    public void transform(Vector3f tranlation) {
       center =  center.add(tranlation);
    }

}
