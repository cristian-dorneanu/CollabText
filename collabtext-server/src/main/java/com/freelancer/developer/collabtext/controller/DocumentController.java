package com.freelancer.developer.collabtext.controller;

import com.freelancer.developer.collabtext.model.Document;
import com.freelancer.developer.collabtext.model.User;
import com.freelancer.developer.collabtext.repository.DocumentRepository;
import com.freelancer.developer.collabtext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
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
    public Document getByUserAndDocumentId(@PathVariable(value = "userId") Long userId,
                                                  @PathVariable(value = "documentId") Long documentId) {

    }

    @PostMapping("/users/{userId}/documents")
    public Document createDocument(@PathVariable (value = "userId") Long userId,
                                   @Valid @RequestBody Document document) {
        return userRepository.findById(userId).map(user -> {
            document.setUser(user);
            return documentRepository.save(document);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/documents/{documentId}")
    public Document updateDocument(@PathVariable (value = "documentId") Long documentId,
                                   @PathVariable(value = "userId") Long userId,
                                   @Valid @RequestBody Document document) {
        return documentRepository.findById(documentId).map(doc -> {
            doc.setFilename(document.getFilename());
            doc.setFileContent(document.getFileContent());
            return documentRepository.save(doc);
        }).orElseThrow(() -> new ResourceNotFoundException("DocumentId " + documentId + " not found"));
    }

    @DeleteMapping("users/{userId}/documents")
    public ResponseEntity<?> deleteAllDocumentsByUserId(@PathVariable (value = "userId") Long userId,
                                                        Pageable pageable) {
        documentRepository.findByUserId(userId, pageable).forEach(document -> documentRepository.delete(document));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("users/{userId}/documents/{documentId}")
    public ResponseEntity<?> deleteByUserAndDocumentId(@PathVariable (value = "userId") Long userId,
                                                       @PathVariable (value = "documentId")Long documentId) {
        Document documentToDelete = this.getByUserAndDocumentId(userId, documentId);
        documentRepository.delete(documentToDelete);
        return ResponseEntity.ok().build();
    }

    private Document getDocumentFromUserByDocumentId(User user, Long documentId) {
        return user.getSavedDocuments().stream().filter(document -> document.getId().equals(documentId))
                .findAny().orElseThrow(() -> new ResourceNotFoundException("Document " + documentId + " not found"));
    }
}
