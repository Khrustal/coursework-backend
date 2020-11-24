package com.webapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany
    private Set<Word> words = new HashSet<>();

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
