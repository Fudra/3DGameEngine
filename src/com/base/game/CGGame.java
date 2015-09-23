package com.base.game;

import com.base.engine.Primitives;
import com.base.engine.components.*;
import com.base.engine.components.lights.DirectionalLight;
import com.base.engine.components.lights.PointLight;
import com.base.engine.components.lights.SpotLight;
import com.base.engine.components.terrain.Terrain;
import com.base.engine.components.terrain.TerrainTexture;
import com.base.engine.components.terrain.TerrainTexturePack;
import com.base.engine.components.terrain.loader.Loader;
import com.base.engine.core.*;
import com.base.engine.rendering.*;


public class CGGame extends Game {

    public CGGame() {
        GameObject terrainGo = new GameObject();

        Loader loader = new Loader();

        TerrainTexture tt1 = new TerrainTexture(loader.loadTexture("terrainTextures/desert"));
        TerrainTexture tt2 = new TerrainTexture(loader.loadTexture("png/mud"));
        TerrainTexture tt3 = new TerrainTexture(loader.loadTexture("png/grassFlowers"));
        TerrainTexture tt4 = new TerrainTexture(loader.loadTexture("terrainTextures/sidewalk"));

        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

        TerrainTexturePack ttp = new TerrainTexturePack(tt1,tt2,tt3,tt4);

        Terrain terrain = new Terrain(new Vector2f(0,0), loader,ttp, blendMap, "heightmap" );

        terrainGo.addComponent(terrain);

        addToScene(terrainGo);

        GameObject directionalLightObject1 = new GameObject(); //
        DirectionalLight directionalLight1 = new DirectionalLight(new Vector3f().setZAxis(), 0.1f); //
        directionalLightObject1.addComponent(directionalLight1); //
        directionalLight1.getTransform().setRotation(new Quaternion().rotate(new Vector3f(1, 0, 0), -45)); //
        addToScene(directionalLightObject1); //


        GameObject flashLightObject = new GameObject();
        SpotLight spotLight = new SpotLight(new Vector3f(0,1,0), .4f,
                new Attenuation(0,0,0.1f), .7f);
        flashLightObject.addChild(new GameObject().addComponent(new FreeLook()).addComponent(new FreeMove()).addComponent(new Camera()).addComponent(spotLight));
        addToScene(flashLightObject);


    }

    @Override
    public void init() {

    }
}
