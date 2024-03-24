package com.contact;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {
    @Autowired
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDetails> getContactDetails() {
        return contactRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<ContactDetails> getContactDetail(Long contactDetailsId) {
        boolean exists = contactRepository.existsById(contactDetailsId);
        if (!exists) {
            throw new UnsupportedOperationException("There is no Contact with id " +
                    contactDetailsId);
        }
        return contactRepository.findById(contactDetailsId);
    }

    public Optional<ContactDetails> getContactDetailByName(String contactDetailsName) {
        return contactRepository.findByName(contactDetailsName);
    }

    public ContactDetails addContact(ContactDetails contactDetails) {
        Optional<ContactDetails> contactOptional = contactRepository.findByEmail(contactDetails.getEmail());
        if (contactOptional.isPresent()) {
            throw new IllegalStateException("Email already exists");
        } else {
            return contactRepository.save(contactDetails);
        }
    }

    @SuppressWarnings("null")
    @Transactional
    public List<ContactDetails> addContacts(List<ContactDetails> contactDetails) {

        return contactRepository.saveAll(contactDetails);

    }

    @SuppressWarnings("null")
    public void deleteContact(Long contactDetailsId) {

        boolean exists = contactRepository.existsById(contactDetailsId);
        if (!exists) {
            throw new IllegalStateException("There is no Contact with id " + contactDetailsId);
        }
        contactRepository.deleteById(contactDetailsId);
    }

    @Transactional
    public void updateContact(Long contactDetailsId, String name, String email) {
        ContactDetails contact = contactRepository.findById(contactDetailsId)
                .orElseThrow(() -> new IllegalStateException("Contact with id " + contactDetailsId + " not found"));

        if (name != null && !name.isEmpty() && !Objects.equals(contact.getName(), name)) {
            contact.setName(name);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(contact.getEmail(), email)) {
            contact.setEmail(email);
        }
    }
}
