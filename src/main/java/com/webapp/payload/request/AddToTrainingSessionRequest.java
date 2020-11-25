package com.webapp.payload.request;

import javax.validation.constraints.NotBlank;

public class AddToTrainingSessionRequest {
    @NotBlank
    private Long sessionId;

    @NotBlank
    private Long wordId;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
