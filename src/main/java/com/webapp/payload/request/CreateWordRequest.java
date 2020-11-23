package com.webapp.payload.request;

import javax.validation.constraints.NotBlank;

public class CreateWordRequest {
    @NotBlank
    private String original;

    @NotBlank
    private String translation;

    @NotBlank
    private Long userId;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
