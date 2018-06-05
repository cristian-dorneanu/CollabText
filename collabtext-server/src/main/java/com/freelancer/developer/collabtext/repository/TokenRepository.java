package com.freelancer.developer.collabtext.repository;

import com.freelancer.developer.collabtext.model.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Page<Token> findByUserId(Long tokenId, Pageable pageable);
}
