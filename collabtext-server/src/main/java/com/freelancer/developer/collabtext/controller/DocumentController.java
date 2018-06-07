package com.freelancer.developer.collabtext.controller;

import com.freelancer.developer.collabtext.model.Document;
import com.freelancer.developer.collabtext.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("users/{userId}/documents")
    public Page<Document> getAllDocumentsByUserId(@PathVariable Long userId, Pageable pageable) {
        return documentService.retrieveAllDocumentsForUserByUserId(userId, pageable);
    }

    @GetMapping("users/{userId}/documents/{documentId}")
    public Document getByUserAndDocumentId(@PathVariable Long userId, @PathVariable Long documentId) {
        return documentService.retrieveDocumentForUserById(userId, documentId);
    }

    @PostMapping("/users/{userId}/documents")
    @Transactional
    public ResponseEntity<?> createDocument(@PathVariable Long userId, @Valid @RequestBody Document document) {
        documentService.addDocumentForUserById(userId, document);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userId}/documents/{documentId}")
    public Document updateDocument(@PathVariable Long userId, @PathVariable Long documentId,
                                   @Valid @RequestBody Document documentToUpdate) {
        return documentService.updateDocumentByUserId(userId, documentId, documentToUpdate);
    }

    @DeleteMapping("users/{userId}/documents")
    public ResponseEntity<?> deleteAllDocumentsByUserId(@PathVariable Long userId) {
        documentService.deleteAllDocumentsForUserByUserId(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("users/{userId}/documents/{documentId}")
    public ResponseEntity<?> deleteByUserAndDocumentId(@PathVariable Long userId, @PathVariable Long documentId) {
        documentService.deleteDocumentById(userId, documentId);
        return ResponseEntity.ok().build();
    }

}
