package com.contact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactDetails, Long> {
    @SuppressWarnings("null")
    Optional<ContactDetails> findById(Long contactDetailsId);

    Optional<ContactDetails> findByName(String name);

    Optional<ContactDetails> findByEmail(String email);
}
