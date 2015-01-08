package com.base.game;

import com.base.engine.Primitives;
import com.base.engine.components.*;
import com.base.engine.components.lights.DirectionalLight;
import com.base.engine.components.lights.PointLight;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Vector3f;
import com.base.engine.physics.*;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;

public class PhysicsGame extends Game {

    public PhysicsGame() {

    }

    @Override
    public void init() {
        // basicColliderTest();
        //basicCollisionScene();
        cubeTest();
    }

    // Cube

    public void cubeTest() {

        Material bricks = Primitives.loadTestMaterial("bricks.jpg");
        Material bricks2 = Primitives.loadTestMaterial("bricks2.jpg");

        Mesh primitive = Primitives.getTriangle();

        Camera camera = new Camera();
        addToScene(new GameObject(new Vector3f(-2.4f, 6, -1.6f))
                .addComponent(camera)
                        //.addComponent(new FreeMove())
                .addComponent(new FreeLook()));


        final int CUBE_SIZE = 3;

        PointLight pointLight = new PointLight(new Vector3f().setAll(), CUBE_SIZE * CUBE_SIZE * 2);
        addToScene(new GameObject().addComponent(pointLight).addComponent(new FreeMove()));

        for (int i = -CUBE_SIZE; i <= CUBE_SIZE; i++) {
            for (int j = -CUBE_SIZE; j <= CUBE_SIZE; j++) {
                for (int k = -CUBE_SIZE; k <= CUBE_SIZE; k++) {
                    if(i == -CUBE_SIZE || i == CUBE_SIZE ||
                            j == -CUBE_SIZE || j == CUBE_SIZE ||
                            k == -CUBE_SIZE || k == CUBE_SIZE)
                    {
                        if(i == 0 || j == 0 || k == 0)
                        {
                            addToScene((new GameObject(new Vector3f(i * 2, j * 2, k * 2)))
                                    .addComponent(new MeshRenderer(new Mesh("sphere.obj"),
                                            bricks)));
                        }
                        else
                        {
                            addToScene(new GameObject(new Vector3f(i * 2, j * 2, k * 2))
                                            .addComponent(new MeshRenderer(new Mesh("cube.obj"),
                                                    // .addComponent(new MeshRenderer(primitive,
                                                    bricks2))
                                                    //.addComponent(new LookAtCamera())
                                            .addComponent(new LookAtComponent(pointLight, new Vector3f().setYAxis()))
                            );
                        }

                    }
                }
            }
        }
    }

    private void basicCollisionScene() {
        //Material
        Material bricks = Primitives.loadTestMaterial("bricks.jpg");
        Material bricks2 = Primitives.loadTestMaterial("bricks2.jpg");

        //Plane
        Mesh plane = Primitives.getPlane(1.0f, 1.0f);

        //Directional Light

        addToScene((new GameObject(new Vector3f(),new Quaternion(new Vector3f().setXAxis(), -45)))
                .addComponent(new DirectionalLight(new Vector3f().setAll(),0.4f)));

        // Scene
        addToScene((new GameObject(new Vector3f().setXAxis(2), new Quaternion(new Vector3f().setXAxis(), (float)Math.toDegrees(.4f))))
                .addComponent(new MeshRenderer(plane, bricks2))
                .addChild((new GameObject(new Vector3f().setZAxis(25)))
                        .addComponent(new MeshRenderer(plane, bricks2))
                        .addChild(new GameObject(new Vector3f(-10,1,1),new Quaternion(new Vector3f().setYAxis(), -90))
                                .addComponent(new Camera()).addComponent(new FreeLook()).addComponent(new FreeMove()))));


        //TODO : Temporary Physics Engine Code!
        PhysicsEngine physicsEngine = new PhysicsEngine();

        physicsEngine.addObject(new PhysicsObject(new BoundingSphere(
                new Vector3f(0.0f ,0.0f, 0.0f), 1.0f), new Vector3f(0.0f ,0.0f,  1.141f/2.0f)));

        physicsEngine.addObject(new PhysicsObject(new BoundingSphere(
                new Vector3f(1.414f/2.0f * 7.0f, 0.0f, 1.414f/2.0f * 7.0f), 1.0f), new Vector3f(-1.414f/2.0f, 0.0f, -1.414f/2.0f)));

        PhysicsEngineComponent physicsEngineComponent = new PhysicsEngineComponent(physicsEngine);

        for (int i = 0; i < physicsEngineComponent.getPhysicsEngine().getNumObjects(); i++) {
            addToScene(new GameObject(new Vector3f(), new Quaternion(), 1.0f
                    //, physicsEngineComponent.getPhysicsEngine().getObject(i).getRadius())
            )
                    .addComponent(new PhysicsObjectComponent(physicsEngineComponent.getPhysicsEngine().getObject(i)))
                    .addComponent(new MeshRenderer(new Mesh("sphere.obj"), bricks)));
        }

        addToScene(new GameObject().addComponent(physicsEngineComponent));

    }

    private void basicColliderTest(){
        BoundingSphere sphere1 = new BoundingSphere(new Vector3f(0.0f, 0.0f, 0.0f), 1.0f);
        BoundingSphere sphere2 = new BoundingSphere(new Vector3f(0.0f, 3.0f, 0.0f), 1.0f);
        BoundingSphere sphere3 = new BoundingSphere(new Vector3f(0.0f, 0.0f, 2.0f), 1.0f);
        BoundingSphere sphere4 = new BoundingSphere(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

        IntersectData sphere1IntersectSphere2 = sphere1.intersectBoundingSphere(sphere2);
        IntersectData sphere1IntersectSphere3 = sphere1.intersectBoundingSphere(sphere3);
        IntersectData sphere1IntersectSphere4 = sphere1.intersectBoundingSphere(sphere4);

        System.out.println("Sphere1 intersect Sphere2: " + sphere1IntersectSphere2.getDoesIntersect() + ", Distance: " + sphere1IntersectSphere2.getDirection());
        System.out.println("Sphere1 intersect Sphere3: " + sphere1IntersectSphere3.getDoesIntersect() + ", Distance: " + sphere1IntersectSphere3.getDirection());
        System.out.println("Sphere1 intersect Sphere4: " + sphere1IntersectSphere4.getDoesIntersect() + ", Distance: " + sphere1IntersectSphere4.getDirection());

        System.out.println("");

        AABB aabb1 = new AABB(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f));
        AABB aabb2 = new AABB(new Vector3f(1.0f, 1.0f, 1.0f), new Vector3f(2.0f, 2.0f, 2.0f));
        AABB aabb3 = new AABB(new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(2.0f, 1.0f, 1.0f));
        AABB aabb4 = new AABB(new Vector3f(0.0f, 0.0f, -2.0f), new Vector3f(1.0f, 1.0f, -1.0f));
        AABB aabb5 = new AABB(new Vector3f(0.0f, 0.5f, 0.0f), new Vector3f(1.0f, 1.5f, 1.0f));

        IntersectData aabb1IntersectAabb2 = aabb1.IntersectAABB(aabb2);
        IntersectData aabb1IntersectAabb3 = aabb1.IntersectAABB(aabb3);
        IntersectData aabb1IntersectAabb4 = aabb1.IntersectAABB(aabb4);
        IntersectData aabb1IntersectAabb5 = aabb1.IntersectAABB(aabb5);

        System.out.println("AABB1 intersect AABB2: " + aabb1IntersectAabb2.getDoesIntersect() + ", Distance: " + aabb1IntersectAabb2.getDirection());
        System.out.println("AABB1 intersect AABB3: " + aabb1IntersectAabb3.getDoesIntersect() + ", Distance: " + aabb1IntersectAabb3.getDirection());
        System.out.println("AABB1 intersect AABB4: " + aabb1IntersectAabb4.getDoesIntersect() + ", Distance: " + aabb1IntersectAabb4.getDirection());
        System.out.println("AABB1 intersect AABB5: " + aabb1IntersectAabb5.getDoesIntersect() + ", Distance: " + aabb1IntersectAabb5.getDirection());

        System.out.println("");

        Plane plane1 = new Plane(new Vector3f(0.0f, 1.0f, 0.0f), 0.0f);

        IntersectData plane1IntersectSphere1 = plane1.intersectSphere(sphere1);
        IntersectData plane1IntersectSphere2 = plane1.intersectSphere(sphere2);
        IntersectData plane1IntersectSphere3 = plane1.intersectSphere(sphere3);
        IntersectData plane1IntersectSphere4 = plane1.intersectSphere(sphere4);

        System.out.println("Plane1 intersect Sphere1: " + plane1IntersectSphere1.getDoesIntersect() + ", Distance: " + plane1IntersectSphere1.getDirection());
        System.out.println("Plane1 intersect Sphere2: " + plane1IntersectSphere2.getDoesIntersect() + ", Distance: " + plane1IntersectSphere2.getDirection());
        System.out.println("Plane1 intersect Sphere3: " + plane1IntersectSphere3.getDoesIntersect() + ", Distance: " + plane1IntersectSphere3.getDirection());
        System.out.println("Plane1 intersect Sphere4: " + plane1IntersectSphere4.getDoesIntersect() + ", Distance: " + plane1IntersectSphere4.getDirection());

        System.out.println("");

      /*  PhysicsObject test = new PhysicsObject(new Vector3f(0.0f, 1.0f, 0.0f), new Vector3f(1.0f, 2.0f, 3.0f), 1.0f);

        test.Integrate(20.0f);

        Vector3f testPos = test.getPosition();
        Vector3f testVel = test.getVelocity();

        System.out.println("TestPos: " + testPos);
        System.out.println("TestVal: " + testVel);

        System.out.println("");
        */
    }
}
