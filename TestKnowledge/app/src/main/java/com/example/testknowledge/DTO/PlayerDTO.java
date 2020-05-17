package com.example.testknowledge.DTO;

public class PlayerDTO {
    private int id;
    private String name;
    private int image;
    private int point;
    public PlayerDTO()
    {

    }
    public PlayerDTO(String name, int image, int point) {
        this.name = name;
        this.image = image;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
