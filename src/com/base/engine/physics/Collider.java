package com.base.engine.physics;

import com.base.engine.core.ReferenceCounter;
import com.base.engine.core.Vector3f;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public  abstract  class Collider extends ReferenceCounter{
    private ColliderType type;

    public Collider(ColliderType type) {
        super();
        this.type = type;
    }

    public IntersectData intersect(Collider other) {

        if(type == ColliderType.SPHERE && other.getType() == ColliderType.SPHERE){
            BoundingSphere self = (BoundingSphere)this;
            return self.intersectBoundingSphere((BoundingSphere)other);
        }
        System.out.println("Error: Collisions not implemented between specified colliders.");
        new NotImplementedException().printStackTrace();

        // Control should never reach this point
        return new IntersectData(false, new Vector3f());
    }

    public void transform(Vector3f tranlation) {}

    public ColliderType getType() {
        return type;
    }

    public Vector3f getCenter() {
        return  new Vector3f();
    }

}
