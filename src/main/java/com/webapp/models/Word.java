package com.webapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Entity
@Table( name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String original;

    @NotBlank
    private String translation;

    @NotBlank
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    public Word() {}

    public Word(@NotBlank String original, @NotBlank String translation, User user) {
        this.original = original;
        this.translation = translation;
        this.user = user;
        userId = user.getId();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
}
