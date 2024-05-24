package manager.ContactRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import manager.ContactModel.ContactDetails;

@Repository
public interface ContactRepository extends JpaRepository<ContactDetails, Long> {
    Optional<ContactDetails> findByName(String name);

    Optional<ContactDetails> findByEmail(String email);
}