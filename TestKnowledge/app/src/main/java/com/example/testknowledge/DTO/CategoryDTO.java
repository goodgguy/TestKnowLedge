package com.example.testknowledge.DTO;

import androidx.annotation.NonNull;

public class CategoryDTO {
    public static final int PROGRAMING=1;
    public static final int ENGLISH=2;
    public static final int HISTORY=3;
    private int id;
    private String name;
    public CategoryDTO()
    {

    }

    public CategoryDTO(String name) {
        this.name = name;
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

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
