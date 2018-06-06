package com.freelancer.developer.collabtext.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @Column(name = "saved_documents")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",
            orphanRemoval = true, fetch = FetchType.LAZY)
    @Transient
    private Set<Document> savedDocuments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Document> getSavedDocuments() {
        return savedDocuments;
    }

    public void setSavedDocuments(Set<Document> savedDocuments) {
        this.savedDocuments = savedDocuments;
    }

}
