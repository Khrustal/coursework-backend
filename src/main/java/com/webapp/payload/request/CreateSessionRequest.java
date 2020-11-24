package com.webapp.payload.request;

import javax.validation.constraints.NotBlank;

public class CreateSessionRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
