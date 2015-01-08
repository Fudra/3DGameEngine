package com.base.game;

import com.base.engine.Primitives;
import com.base.engine.components.*;
import com.base.engine.components.lights.DirectionalLight;
import com.base.engine.components.lights.PointLight;
import com.base.engine.components.lights.SpotLight;
import com.base.engine.core.*;
import com.base.engine.rendering.*;

public class TestGame extends Game {

 //   private Camera camera;

    public TestGame() {

    }

    public void init() {
  //    camera = new Camera();

        Material material = Primitives.loadTestMaterial("test2.png");
        Material material2 = Primitives.loadTestMaterial("test.png");
        Material material3 = Primitives.loadTestMaterial("bricks.jpg");
        Material material4 = Primitives.loadTestMaterial("bricks2.jpg");

        Mesh mesh = Primitives.getPlane();
        Mesh mesh2 = Primitives.getPlane(1,1);
        Mesh tempMesh = new Mesh("monkey3.obj");
        MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

        GameObject planeObject = new GameObject();
        planeObject.addComponent(meshRenderer);
        planeObject.getTransform().getPosition().set(0, -1, 5);
        addToScene(planeObject);

        GameObject directionalLightObject1 = new GameObject();
        GameObject directionalLightObject2 = new GameObject();
        GameObject directionalLightObject3 = new GameObject();
        DirectionalLight directionalLight1 = new DirectionalLight(new Vector3f().setZAxis(), 0.1f);
        DirectionalLight directionalLight2 = new DirectionalLight(new Vector3f().setYAxis(), 0.1f);
        DirectionalLight directionalLight3 = new DirectionalLight(new Vector3f().setXAxis(), 0.1f);
        directionalLightObject1.addComponent(directionalLight1);
        directionalLightObject2.addComponent(directionalLight2);
        directionalLightObject3.addComponent(directionalLight3);
        directionalLight1.getTransform().setRotation(new Quaternion().rotate(new Vector3f(1,0,0), -45));
        directionalLight2.getTransform().setRotation(new Quaternion().rotate(new Vector3f(1,0,0), -90));
        directionalLight2.getTransform().getPosition().set(0,1,1);
        directionalLight3.getTransform().setRotation(new Quaternion().rotate(new Vector3f(1,0,0), -90));

        addToScene(directionalLightObject1);
        addToScene(directionalLightObject2);
        addToScene(directionalLightObject3);

        int lightFieldWidth = 5;
        int lightFieldDepth = 5;

        float lightFieldStartX = 0;
        float lightFieldStartY = 0;
        float lightFieldStepX = 5;
        float lightFieldStepY = 5;

        for (int i = 0; i < lightFieldWidth; i++) {
            for (int j = 0; j < lightFieldDepth; j++) {
                float r = (float) Math.random();
                float g = (float) Math.random();
                float b = (float) Math.random();
                float a = (float) Math.random();  /// expo atten
                GameObject pointLightObject = new GameObject();
                PointLight pointLight = new PointLight(new Vector3f(r, g, b), 0.4f,
                        new Attenuation(0, 0, .5f));
                pointLightObject.addComponent(pointLight);
                pointLight.getTransform().getPosition().set(lightFieldStartX + i * lightFieldStepX, 0, lightFieldStartY + j * lightFieldStepY);
                addToScene(pointLightObject);
                pointLightObject.addComponent(new LookAtCamera()).addComponent(new MeshRenderer(new Mesh("monkey3.obj"), material3));
            }
        }

        GameObject testMesh1 = new GameObject().addComponent(new MeshRenderer(mesh2, material2));
        GameObject testMesh2 = new GameObject().addComponent(new MeshRenderer(mesh2, material4));
        GameObject testMesh3 = new GameObject().addComponent(new LookAtCamera()).addComponent(new MeshRenderer(tempMesh, material));

        testMesh1.getTransform().getPosition().set(0,2,0);
        testMesh1.getTransform().setRotation(new Quaternion().rotate(new Vector3f(0,1,0), -45));

       // testMesh2.getTransform().getPosition().set(0, 0, 5);
        GameObject flashLightObject = new GameObject();
        SpotLight spotLight = new SpotLight(new Vector3f(0,1,0), .4f,
                new Attenuation(0,0,0.1f), .7f);
        flashLightObject.addChild(new GameObject().addComponent(new FreeLook()).addComponent(new FreeMove()).addComponent(new Camera()).addComponent(spotLight));
        addToScene(flashLightObject);


        //testMesh1.addChild(testMesh2);

        //testMesh3.getTransform().getPosition().set(5,5,5);
        //estMesh3.getTransform().setRotation(new Quaternion().rotate(new Vector3f(0,1,0), -70.0f));

        //addObject(new GameObject().addComponent(new Camera()));

        //ddObject(testMesh3);
    }
}