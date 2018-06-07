package com.freelancer.developer.collabtext.service;

import com.freelancer.developer.collabtext.model.Document;
import com.freelancer.developer.collabtext.repository.DocumentRepository;
import com.freelancer.developer.collabtext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;


public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Page<Document> retrieveAllDocumentsForUserByUserId(Long userId, Pageable pageable) {
        return documentRepository.findByUserId(userId, pageable);
    }

    @Override
    public Document retrieveDocumentForUserById(Long userId, Long documentId) {
        return documentRepository.findByUserId(userId).stream().filter(document -> document.getId().equals(documentId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Document " + documentId + " not found"));
    }

    @Override
    public Document updateDocumentByUserId(Long userId, Long documentId, Document updatedDocument) {
        Document oldDocument = this.retrieveDocumentForUserById(userId, documentId);
        oldDocument.setFilename(updatedDocument.getFilename());
        oldDocument.setFileContent(updatedDocument.getFileContent());
        return documentRepository.save(oldDocument);
    }

    @Override
    public void addDocumentForUserById(Long userId, Document document) {
        userRepository.findById(userId).map(user -> {
            document.setUser(user);
            return documentRepository.save(document);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @Override
    public void deleteAllDocumentsForUserByUserId(Long userId) {
        documentRepository.findByUserId(userId).forEach(document -> documentRepository.delete(document));
    }

    @Override
    public void deleteDocumentById(Long userId, Long documentId) {
        Document documentToDelete = this.retrieveDocumentForUserById(userId, documentId);
        documentRepository.delete(documentToDelete);
    }
}
