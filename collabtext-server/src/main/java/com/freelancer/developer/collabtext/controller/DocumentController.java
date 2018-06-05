package com.freelancer.developer.collabtext.controller;

import com.freelancer.developer.collabtext.model.Document;
import com.freelancer.developer.collabtext.repository.DocumentRepository;
import com.freelancer.developer.collabtext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DocumentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("users/{userId}/documents")
    public Page<Document> getAllDocumentsByUserId(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return documentRepository.findByUserId(userId, pageable);
    }

    @GetMapping("users/{userId}/documents/{documentId}")
    public Document getAllDocumentsByUserId(@PathVariable(value = "userId") Long userId,
                                                  @PathVariable(value = "documentId") Long documentId,
                                                  Pageable pageable) {
        return null;
    }

    @PostMapping("/users/{userId}/documents")
    public Document createComment(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Document document) {
        return userRepository.findById(userId).map(user -> {
            document.setUser(user);
            return documentRepository.save(document);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
}
