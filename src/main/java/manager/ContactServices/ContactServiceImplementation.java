package manager.ContactServices;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import manager.ContactRepository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manager.ContactModel.ContactDetails;

@Service
public class ContactServiceImplementation implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Override
    @Cacheable(value = "contacts", key = "#pageable")
    public Page<ContactDetails> getContactDetails(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "contact", key = "#contactDetailsId")
    public Optional<ContactDetails> getContactDetail(Long contactDetailsId) {
        boolean exists = contactRepository.existsById(contactDetailsId);
        if (!exists) {
            throw new UnsupportedOperationException("There is no Contact with id " +
                  contactDetailsId);
        }
        return contactRepository.findById(contactDetailsId);
    }

    @Override
    @Cacheable(value = "contactByName", key = "#contactDetailsName")
    public Optional<ContactDetails> getContactDetailByName(String contactDetailsName) {
        return contactRepository.findByName(contactDetailsName);
    }

    @Override
    @CacheEvict(value = "contact", allEntries = true)
    public ContactDetails addContact(ContactDetails contactDetails) {
        String cont = contactDetails.getName();
        Optional<ContactDetails> contactOptional = contactRepository.findByEmail(contactDetails.getEmail());
        if (contactOptional.isPresent()) {
            throw new IllegalStateException("Email already exists");
        } else {
            emailService.sendEmail(contactDetails.getEmail(), "Registration Confirmation", "Welcome to our platform!");
            logger.info("new contact saved!! contact name is: " + cont );
            return contactRepository.save(contactDetails);
        }
    }

    @Override
    @CacheEvict(value = "contacts", allEntries = true)
    @Transactional
    public void addContacts(List<ContactDetails> contactDetails)
    {
        contactRepository.saveAll(contactDetails);
    }

    @Override

    @CacheEvict(value = "contact", key = "#contactDetailsId")
    public void deleteContact(Long contactDetailsId) {
        boolean exists = contactRepository.existsById(contactDetailsId);
        if (!exists) {
            throw new IllegalStateException("There is no Contact with id " + contactDetailsId);
        }
        contactRepository.deleteById(contactDetailsId);
    }

    @Override
    @CacheEvict(value = "contactByName", allEntries = true)
    @Transactional
    public void updateContact(Long contactDetailsId, String name, String email) {
        logger.info("Updating contact with ID: {}", contactDetailsId);
        logger.debug("Received parameters: name={}, email={}", name, email);
        ContactDetails contact = contactRepository.findById(contactDetailsId)
              .orElseThrow(() -> new IllegalStateException("Contact with id " + contactDetailsId + " not found"));

        if (name != null && !name.isEmpty() && !Objects.equals(contact.getName(), name)) {
            contact.setName(name);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(contact.getEmail(), email)) {
            contact.setEmail(email);
        }
        emailService.sendEmail(contact.getEmail(), "Update successful", "You have successfully updated your profile!");

        contactRepository.save(contact);
        logger.info("Contact updated successfully");
    }
}