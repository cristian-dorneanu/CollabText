package com.freelancer.developer.collabtext.repository;

import com.freelancer.developer.collabtext.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
