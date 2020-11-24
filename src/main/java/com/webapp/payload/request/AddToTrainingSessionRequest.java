package com.webapp.payload.request;

import javax.validation.constraints.NotBlank;

public class AddToTrainingSessionRequest {
    @NotBlank
    private Long sessionId;

    @NotBlank
    private String word;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
