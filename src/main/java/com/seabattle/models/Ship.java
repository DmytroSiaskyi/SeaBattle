package com.seabattle.models;

public class Ship {
    private Integer id;
    private Integer size;
    private Integer health;
    private Boolean horizontal;
    public Ship(Integer id, Integer size){
        this.id = id;
        this.size = this.health = size;
        horizontal = false;
    }
    public void minusHealth(){
        health--;
    }
    public Integer getId() {
        return id;
    }

    public Boolean getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Boolean horizontal) {
        this.horizontal = horizontal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
    public Boolean isAlive(){
        if(health!=0)
            return true;
        return false;
    }
}
