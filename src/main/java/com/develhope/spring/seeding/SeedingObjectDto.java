package com.develhope.spring.seeding;

public class SeedingObjectDto {
    private String key;

    public SeedingObjectDto(String key) {
        this.key = key;
    }

    public SeedingObjectDto() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

