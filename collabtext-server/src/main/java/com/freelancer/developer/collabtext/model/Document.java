package com.freelancer.developer.collabtext.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Specify a filename")
    @Size(min = 1, max = 128)
    private String filename;

    @Column(name = "file_content")
    private String fileContent;

    @Column(name = "size_in_bytes")
    private Integer sizeInBytes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_documents", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "document_id", referencedColumnName = "id")})
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public Integer getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(Integer sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
