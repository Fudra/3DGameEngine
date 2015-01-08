package com.base.engine.rendering;

import com.base.engine.core.Vector3f;

public class Attenuation extends Vector3f {

    public Attenuation() {
        super(0, 0, 1);
    }

    public Attenuation(float constant, float linear, float exponent) {
        super(constant, linear, exponent);
    }

    public float getConstant() {
        return getX();
    }

    public void setConstant(float constant) { setX(constant); }

    public float getLinear() {
        return getY();
    }

    public void setLinear(float linear) { setY(linear); }

    public float getExponent() {
        return getZ();
    }

    public void setExponent(float exponent) { setZ(exponent); }
}
