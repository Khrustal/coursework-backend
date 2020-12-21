package com.webapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @JoinColumn(name = "session_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private TrainingSession session;

    @Column(name = "session_id")
    private Long sessionId;

    @OneToMany
    private List<Answer> answer = new ArrayList<>();

    @NotBlank
    private double rightAnswers;

    @NotBlank
    private LocalDate date;

    @NotBlank
    private LocalTime time;

    public Result(@NotBlank TrainingSession session, Long sessionId, List<Answer> answer, @NotBlank double rightAnswers, @NotBlank LocalDate date, @NotBlank LocalTime time) {
        this.session = session;
        this.sessionId = sessionId;
        this.answer = answer;
        this.rightAnswers = rightAnswers;
        this.date = date;
        this.time = time;
    }

    public Result() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setSession(TrainingSession session) {
        this.session = session;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public double getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(double rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public boolean containsAnswer(Long id) {
        for(Answer ans : answer) {
            if(ans.getId().equals(id))
                return true;
        }
        return false;
    }

}
