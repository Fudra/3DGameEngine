package com.base.engine.core;

public class Vector2f {
    private float x;
    private float y;


    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }


    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float max() {
        return Math.max(x, y);
    }

    public float min() {
        return Math.min(x, y);
    }

    public Vector2f max(Vector2f r) {
        Vector2f result = new Vector2f();
        result.setX(this.getX() > r.getX() ? this.getX() : r.getX());
        result.setY(this.getY() > r.getY() ? this.getY() : r.getY());
        return result;
    }

    public float dot(Vector2f r) {
        return x * r.getX() + y * r.getY();
    }

    public Vector2f normalized() {
        float length = length();

        return new Vector2f( x / length, y / length);
    }

    public Vector2f rotate(float angle) {

        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        return new Vector2f((float)(x * cos - y * sin), (float)(x * sin * y * cos));
    }

    public Vector2f lerp(Vector2f destination, Vector2f lerpFactpr) {
        return destination.sub(this).mul(lerpFactpr).add(this);
    }

    public Vector2f add(Vector2f r) {
        return new Vector2f(x + r.getX(), y + r.getY());
    }

    public Vector2f add(float r) {
        return new Vector2f(x + r, y + r);
    }

    public Vector2f sub(Vector2f r) {
        return new Vector2f(x - r.getX(), y - r.getY());
    }

    public Vector2f sub(float r) {
        return new Vector2f(x - r, y - r);
    }

    public Vector2f mul(Vector2f r) {
        return new Vector2f(x * r.getX(), y * r.getY());
    }

    public Vector2f mul(float r) {
        return new Vector2f(x * r, y * r);
    }

    public Vector2f div(Vector2f r) {
        return new Vector2f(x / r.getX(), y / r.getY());
    }

    public Vector2f div(float r) {
        return new Vector2f(x / r, y / r);
    }

    public Vector2f abs() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    public float cross(Vector2f r) {
        return x * r.getY() - y * r.getX();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }
    public Vector2f set(Vector2f r) {
        return this.set(r.getX(), r.getY());
    }

    @Override
    public boolean equals(Object obj) {
       Vector2f other = (Vector2f)obj;
        return x == other.getX() && y == other.getY() ;
    }

    @Override
    public String toString() {
        return "(" + x + " " + y + ")";
    }
}
