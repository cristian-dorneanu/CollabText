package com.freelancer.developer.collabtext.service;

import com.freelancer.developer.collabtext.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface DocumentService {
    Page<Document> retrieveAllDocumentsForUserByUserId(Long userId, Pageable pageable);
    Document retrieveDocumentForUserById(Long userId, Long documentId);
    Document updateDocumentByUserId(Long userId, Long documentId, Document document);
    void addDocumentForUserById(Long userId, Document document);
    void deleteAllDocumentsForUserByUserId(Long userId);
    void deleteDocumentById(Long userId, Long documentId);
}
