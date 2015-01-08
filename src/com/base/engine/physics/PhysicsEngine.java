package com.base.engine.physics;

import com.base.engine.core.Vector3f;

import java.util.ArrayList;

public class PhysicsEngine {

    private ArrayList<PhysicsObject>  objects;

    public PhysicsEngine() {
        objects = new ArrayList<PhysicsObject>();
    }

    public void addObject(PhysicsObject object) {
        objects.add(object);
    }

    public void removeObject(PhysicsObject object) {
        objects.remove(object);
    }

    public void simulate(float delta) {
        for (PhysicsObject object : objects) {
            object.Integrate(delta);
        }
    }

    public void handleCollisions() {
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                IntersectData intersectData =
                        objects.get(i).getCollider().intersect(
                                objects.get(j).getCollider()
                        );

                if (intersectData.getDoesIntersect()) {

                    Vector3f direction  = intersectData.getDirection().normalized();
                    Vector3f otherDirection = direction.reflect(objects.get(i).getVelocity().normalized());

                    System.out.println("direction : " + direction);
                    System.out.println("otherDirection : " + otherDirection);


                    objects.get(i).setVelocity(
                            (Vector3f)objects.get(i).getVelocity().reflect(direction));
                    objects.get(j).setVelocity(
                            (Vector3f)objects.get(j).getVelocity().reflect(otherDirection));
                }
            }
        }
    }

    //TODO: Temporary Getters
    public PhysicsObject getObject(int index) {
        return objects.get(index);
    }

    public int getNumObjects () {
        return objects.size();
    }
}
