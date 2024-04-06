package com.ContactServices;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ContactModel.ContactDetails;
import com.ContactRepository.ContactRepository;

@Service
public class ContactServiceImplementation implements ContactService {
    @Autowired
    private final ContactRepository contactRepository;

    public ContactServiceImplementation(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Override
    public List<ContactDetails> getContactDetails() {
        return contactRepository.findAll();
    }

    @Override
    public Page<ContactDetails> getContactDetails(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<ContactDetails> getContactDetail(Long contactDetailsId) {
        boolean exists = contactRepository.existsById(contactDetailsId);
        if (!exists) {
            throw new UnsupportedOperationException("There is no Contact with id " +
                    contactDetailsId);
        }
        return contactRepository.findById(contactDetailsId);
    }

    @Override
    public Optional<ContactDetails> getContactDetailByName(String contactDetailsName) {
        return contactRepository.findByName(contactDetailsName);
    }

    @Override
    public ContactDetails addContact(ContactDetails contactDetails) {
        Optional<ContactDetails> contactOptional = contactRepository.findByEmail(contactDetails.getEmail());
        if (contactOptional.isPresent()) {
            throw new IllegalStateException("Email already exists");
        } else {
            return contactRepository.save(contactDetails);
        }
    }

    @Override
    @SuppressWarnings("null")
    @Transactional
    public List<ContactDetails> addContacts(List<ContactDetails> contactDetails) {
        return contactRepository.saveAll(contactDetails);
    }

    @Override
    @SuppressWarnings("null")
    public void deleteContact(Long contactDetailsId) {

        boolean exists = contactRepository.existsById(contactDetailsId);
        if (!exists) {
            throw new IllegalStateException("There is no Contact with id " + contactDetailsId);
        }
        contactRepository.deleteById(contactDetailsId);
    }

    @Override
    @SuppressWarnings("null")
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
        contactRepository.save(contact);
        logger.info("Contact updated successfully");
    }
}
