package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class PhysicsObject {

    private Vector3f position;
    private Vector3f oldPosition;
    private Vector3f velocity;
    private Collider collider;

    public PhysicsObject(Collider collider, Vector3f velocity) {
        this.position = collider.getCenter();
        this.oldPosition = position;
        this.velocity = velocity;
        this.collider = collider;
        this.collider.addReference();
  //      this.collider = new BoundingSphere(new Vector3f(), 0.0f);
    }

    public void Integrate(float delta) {
       position = position.add(velocity.mul(delta));
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3f velocity) {
        this.velocity = velocity;
    }

    public Collider getCollider() {
        Vector3f translation = position.sub(oldPosition);
        oldPosition = position;
        collider.transform(translation);
        return collider;
    }

    @Override
    protected void finalize() {
        try {
            super.finalize();
            if(collider.removeReference())
            {
                position = null;
                oldPosition = null;
                velocity = null;
                collider = null;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
