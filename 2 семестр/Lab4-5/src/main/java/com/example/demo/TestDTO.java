package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDTO {

    private Integer id;
    private String name;
    private String img;

    public TestDTO() {

    }

    @JsonCreator
    public TestDTO(
            @JsonProperty(value = "id") Integer id,
            @JsonProperty(value = "name") String name,
            @JsonProperty(value = "img") String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    // GET

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    // SET

    public void setId(Integer Id) {
        id = Id;
    }

    public void setName(String Name) {
        name = Name;
    }

    public void setImg(String Img) {
        img = Img;
    }
}
