package com.princerajgroup.model;

public class Candidate {
    private int id;
    private String name;
    private String logoUrl;

    public Candidate(int id, String name, String logoUrl) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
    }

    public Candidate() {
    }

    // Getters and Setters
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
