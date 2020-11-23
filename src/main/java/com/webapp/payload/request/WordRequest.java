package com.webapp.payload.request;

import javax.validation.constraints.NotBlank;

public class WordRequest {
    @NotBlank
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
