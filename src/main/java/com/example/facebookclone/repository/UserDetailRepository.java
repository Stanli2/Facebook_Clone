package com.example.facebookclone.repository;

import com.example.facebookclone.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findById(Long id);

    Optional<UserDetails> findByEmail(String email);

    Optional<UserDetails> findByEmailAndPassword(String email, String password);
}
