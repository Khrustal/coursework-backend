package com.webapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "training_session")
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String name;

    @ManyToMany
    private Set<Word> words = new HashSet<>();

    @OneToMany
    private List<Result> results = new ArrayList<>();

    @NotBlank
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    public TrainingSession(User user, String name) {
        this.user = user;
        this.name = name;
        userId = user.getId();
    }

    public TrainingSession() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public boolean containsWord(Long wordId) {
        for(Word word : words) {
            if(word.getId().equals(wordId))
                return true;
        }
        return false;
    }

    public void removeWord(Long wordId) {
        words.removeIf(word -> word.getId().equals(wordId));
    }
}
