package com.ContactModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_infos")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Contact_sequence")
    @SequenceGenerator(name = "Contact_sequence", sequenceName = "Contact_sequence", allocationSize = 1)
    private Long id;

    private String name;
    private Long phone;
    private String email;
    private String address;

    public ContactDetails(String name, Long phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;

    }

}
