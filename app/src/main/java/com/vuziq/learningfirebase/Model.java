package com.vuziq.learningfirebase;

/**
 * Created by bruno on 8/5/16.
 */
public class Model {

    private String hello;
    private String world;

    public Model() {
    }

    public Model(String hello, String world) {
        this.hello = hello;
        this.world = world;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
