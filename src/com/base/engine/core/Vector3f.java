package com.base.engine.core;

public class Vector3f {
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector2f vector2f, float z){
        this.x = vector2f.getX();
        this.y = vector2f.getY();
        this.z = z;
    }

    public Vector3f(float xyz) {
        this(xyz, xyz, xyz);
    }

    public Vector3f() {
        this(0.0f,0.0f,0.0f);
    }

    public Vector3f setXAxis() {
        return new Vector3f(1,0,0);
    }

    public Vector3f setXAxis(float value) {
        return new Vector3f(value,0,0);
    }

    public Vector3f setYAxis() {
        return new Vector3f(0,1,0);
    }

    public Vector3f setYAxis(float value) {
        return new Vector3f(0,value,0);
    }

    public Vector3f setZAxis() {
        return new Vector3f(0,0,1);
    }

    public Vector3f setZAxis(float value) {
        return new Vector3f(value,0,0);
    }

    public Vector3f setAll(){
        return new Vector3f(1,1,1);
    }

    public Vector3f setAll(float value){
        return new Vector3f(value,value,value);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float min() {
        return Math.min(x, Math.min(y, z));
    }

    public float max() {
        return Math.max(x, Math.max(y, z));
    }

    public Vector3f max(Vector3f r) {
        Vector3f result = new Vector3f();
        result.setX(this.getX() > r.getX() ? this.getX() : r.getX());
        result.setY(this.getY() > r.getY() ? this.getY() : r.getY());
        result.setZ(this.getZ() > r.getZ() ? this.getZ() : r.getZ());
        return result;
    }

    public float dot(Vector3f r) {
        return x * r.getX() + y * r.getY() + z * r.getZ();
    }

    public Vector3f normalized() {
        float length = length();

        return new Vector3f(x / length, y / length, z / length);
    }

    // TODO:
    public Vector3f reflect(Vector3f other) {
        return this.sub((other.mul(this.dot(other) * 2)));
    }

    public Vector3f rotate(Vector3f axis, float angle) {
//        float sinHalfAngle = (float)Math.sin(Math.toRadians( angle / 2));
//        float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
//
//        float rX = axis.getX() * sinHalfAngle;
//        float rY = axis.getY() * sinHalfAngle;
//        float rZ = axis.getZ() * sinHalfAngle;
//        float rW = cosHalfAngle;

//        Quaternion rotation =  new Quaternion().rotate(axis, angle);//new Quaternion(rX, rY, rZ, rW);
//        Quaternion conjugate = rotation.conjugate();
//
//        Quaternion w = rotation.mul(this).mul(conjugate);
//
//        return new Vector3f( w.getX(), w.getY(), w.getZ());

//        return this.rotate(new Quaternion().rotate(axis, angle));

        float sinAngle = (float)Math.sin(Math.toRadians(-angle));
        float cosAngle = (float)Math.cos(Math.toRadians(-angle));

        return this.cross(axis.mul(sinAngle))                       // Rotate on local X
                .add(this.mul(cosAngle))                            // Rotate on local Z
                .add(axis.mul(this.dot(axis.mul(1 - cosAngle))));   // Rotate on local Y
    }

    public Vector3f rotate(Quaternion rotation){
        Quaternion conjugate = rotation.conjugate();

        Quaternion w = rotation.mul(this).mul(conjugate);

        return new Vector3f( w.getX(), w.getY(), w.getZ());
    }

    public Vector3f cross(Vector3f r) {
        float x_ = y * r.getZ() - z * r.getY();
        float y_ = z * r.getX() - x * r.getZ();
        float z_ = x * r.getY() - y * r.getX();

        return new Vector3f(x_, y_, z_);
    }

    public Vector3f lerp(Vector3f destination, Vector3f lerpFactpr) {
        return destination.sub(this).mul(lerpFactpr).add(this);
    }

    public Vector3f add(Vector3f r) {
        return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
    }

    public Vector3f add(float r) {
        return new Vector3f(x + r, y + r, z + r);
    }

    public Vector3f sub(Vector3f r) {
        return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
    }

    public Vector3f sub(float r) {
        return new Vector3f(x - r, y - r, z - r);
    }

    public Vector3f mul(Vector3f r) {
        return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
    }

    public Vector3f mul(float r) {
        return new Vector3f(x * r, y * r, z * r);
    }

    public Vector3f div(Vector3f r) {
        return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
    }

    public Vector3f div(float r) {
        return new Vector3f(x / r, y / r, z / r);
    }

    public Vector3f abs() {
        return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vector2f getXY() { return  new Vector2f(x, y); }

    public Vector2f getYZ() { return  new Vector2f(y, z); }

    public Vector2f getZX() { return new Vector2f(z, x); }

    public Vector2f getYX() { return new Vector2f(y, x); }

    public Vector2f getZY() { return new Vector2f(z, y); }

    public Vector2f getXZ() { return new Vector2f(x, z); }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
    public Vector3f set(Vector3f r) {
        return this.set(r.getX(), r.getY(), r.getZ());
    }


    @Override
    public boolean equals(Object obj) {
        Vector3f other = (Vector3f)obj;
        return x == other.getX() && y == other.getY()  && z == other.getZ() ;
    }

    @Override
    public String toString() {
      return "(" + this.getX() + " " + this.getY() + " " +this.getZ() + ")";
    }

}
