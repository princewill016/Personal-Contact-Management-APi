package com.ContactRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ContactModel.ContactDetails;

@Repository
public interface ContactRepository extends JpaRepository<ContactDetails, Long> {
    Optional<ContactDetails> findByName(String name);

    Optional<ContactDetails> findByEmail(String email);
}
