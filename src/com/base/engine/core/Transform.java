package com.base.engine.core;


public class Transform {

    private Transform parent;
    private Matrix4f parentMatrix;

    private Vector3f pos;
    private Quaternion rot;
    private Vector3f scale;

    private Vector3f oldPos;
    private Quaternion oldRot;
    private Vector3f oldScale;

    public Transform(Vector3f position, Quaternion rotation, Vector3f scale) {
        this.pos = position;
        this.rot = rotation;
        this.scale = scale;

        parentMatrix = new Matrix4f().identity();
    }

    public Transform(Vector3f position, Quaternion rotation) {
        this(position, rotation, new Vector3f(1, 1, 1));
    }

    public Transform(Vector3f position, Vector3f scale) {
        this(position, new Quaternion(), scale);
    }

    public Transform(Vector3f position) {
        this(position, new Quaternion(), new Vector3f(1, 1, 1));
    }

    public Transform(Quaternion rotaion) {
        this(new Vector3f(), rotaion, new Vector3f(1, 1, 1));
    }

    public Transform() {
        this(new Vector3f(0, 0, 0),new Quaternion(0, 0, 0, 1),  new Vector3f(1, 1, 1));
    }


    public void update() {
        if(oldPos != null) {
            oldPos.set(pos);
            oldRot.set(rot);
            oldScale.set(scale);
        }
        else {
            oldPos = new Vector3f().set(pos).add(1.0f);
            oldRot = new Quaternion().set(rot).mul(0.5f);
            oldScale = new Vector3f().set(scale).add(1.0f);
        }
    }

    public boolean hasChanged() {

        if(parent != null && parent.hasChanged()) {
            System.out.println("parent & hasChanged: ");
            return true;
        }

        if(!pos.equals(oldPos)) {
            System.out.println("oldPos: ");
            return true;
        }

        if(!rot.equals(oldRot)) {
            System.out.println("oldRot: ");
            return true;
        }

        if(!scale.equals(oldScale)) {
            System.out.println("oldScale: ");
            return true;
        }

        return false;
    }

    public void rotate(Vector3f axis, float angle) {
        rot = new Quaternion().rotate(axis, angle).mul(rot).normalized();
    }

    public void lookAt(Vector3f point, Vector3f up) {
        rot = getLookAtDirection(point, up);
    }

    public Quaternion getLookAtDirection(Vector3f point, Vector3f up) {
        Quaternion result = new Quaternion(new Matrix4f().rotation(point.sub(pos).normalized(), up));
        return  result;
    }

    public Matrix4f getTransformation() {

        Matrix4f translationMatrix = new Matrix4f()
                .translation(
                        pos.getX(),
                        pos.getY(),
                        pos.getZ()
                );

        Matrix4f rotationsMatrix = rot.toRotationMatrix();

        Matrix4f scaleMatrix = new Matrix4f()
                .scale(
                        scale.getX(),
                        scale.getY(),
                        scale.getZ()
                );


        //System.out.println(getParentMatrix());
        // order of computation  scale -> rotation -> translation
        return getParentMatrix().mul(translationMatrix.mul(rotationsMatrix.mul(scaleMatrix)));
    }


//    public Matrix4f getProjectedTransformation(Camera camera) {
//        return camera.getViewProjection().mul(getTransformation());
//    }

//    public Matrix4f getProjectedTransformation() {
//
//        Matrix4f transformationMatrix = getTransformation();
//        Matrix4f projectionMatrix = new Matrix4f().perspective(fov, width, height, zNear, zFar);
//        Matrix4f cameraRotation = new Matrix4f().rotation(camera.getForward(), camera.getUp());
//        Matrix4f cameraTranslation = new Matrix4f().translation(-camera.getPosition().getX(),
//                                                    -camera.getPosition().getY(),
//                                                    -camera.getPosition().getZ());
//
//        return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));
//    }


    private Matrix4f getParentMatrix() {
        if(parent != null ) { // && parent.hasChanged()
            parentMatrix = parent.getTransformation();
        }
        return parentMatrix;
    }

    public void setParent(Transform parent) {
        this.parent = parent;
    }

    public Quaternion getTransformedRotation() {

        Quaternion parentRotation = new Quaternion();

        if(parent != null)
            parentRotation = parent.getTransformedRotation();

        return parentRotation.mul(rot);
    }

    public Vector3f getTransformedPosition() {
        return getParentMatrix().transform(pos);
    }

    public Vector3f getPosition() {
        return pos;
    }

    public void setPosition(Vector3f pos) {
        this.pos = pos;
    }

  //  public void setPosition(float x, float y, float z) {
  //     this.pos = new Vector3f(x, y, z);
  //  }

    public Quaternion getRotation() {
        return rot;
    }

    public void setRotation(Quaternion rotation) {
        this.rot = rotation;
    }

   // public void setRotation(float x, float y, float z, float w) {
   //     this.rot = new Quaternion(x, y, z, w);
    //}

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

   // public void setScale(float x, float y, float z) {
   //     this.scale = new Vector3f(x, y, z);
   // }

}
