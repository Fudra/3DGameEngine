package com.base.engine.components.lights;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.Shader;

public class PointLight extends BaseLight {

    private static final int COLOR_DEPTH = 256;

    private Attenuation attenuation;
    private float range;

    /*
    public PointLight(Vector3f color, float intensity,Vector3f position, float range) {
        this(color, intensity, new Attenuation(0, 0, 1));
    }


    public PointLight(Vector3f color, float intensity, float constant, float linear, float exponent, Vector3f position) {
        this(color, intensity, new Attenuation(constant, linear, exponent));
    }
    */

    public PointLight(Vector3f color, float intensity) {
        this(color, intensity, new Attenuation());
    }

    public PointLight(Vector3f color, float intensity, Attenuation attenuation) {
        super(color, intensity);
        this.attenuation = attenuation;

        float b = attenuation.getLinear();
        float a = attenuation.getExponent();
        float c = attenuation.getConstant() - COLOR_DEPTH * getIntensity() * getColor().max();

        this.range = (float)((-b + Math.sqrt(b * b - 4 * a * c))/(2 * a));

        setShader(new Shader("forward-point"));
    }

    public Attenuation getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Attenuation attenuation) {
        this.attenuation = attenuation;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

}
