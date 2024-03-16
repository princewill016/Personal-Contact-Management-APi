package com.contact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactDetails, Long> {
    Optional<ContactDetails> findContactById(Long contactDetailsId);
    Optional<ContactDetails> findContactByEmail(String email);

    // Optional<ContactDetails> findContactByName(String name);

    // boolean existsByEmail(String email);

    // void saveAll(ContactDetails contactDetails);

}
