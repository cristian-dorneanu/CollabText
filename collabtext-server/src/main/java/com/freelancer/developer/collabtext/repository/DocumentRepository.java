package com.freelancer.developer.collabtext.repository;

import com.freelancer.developer.collabtext.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Page<Document> findByUserId(Long documentId, Pageable pageable);
}
