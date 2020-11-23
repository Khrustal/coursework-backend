package com.webapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Entity
@Table( name = "words",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "original")
        })
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String original;

    @NotBlank
    private String translation;

    @ManyToOne
    private User user;

    public Word() {}

    public Word(@NotBlank String original, @NotBlank String translation, User user) {
        this.original = original;
        this.translation = translation;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
