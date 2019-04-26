package com.example.test.activity11;

import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {
    private String name;
    private int id;
    private float jiage;
public Book(){
}
public Book(String name,float jiage){
    this.name=name;
    this.jiage=jiage;
}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getJiage() {
        return jiage;
    }

    public void setJiage(float jiage) {
        this.jiage = jiage;
    }
}
