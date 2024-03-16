package com.contact;

import java.util.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactConfig {

        @SuppressWarnings("null")
        @Bean
        CommandLineRunner commandLineRunner(ContactRepository contactRepository) {
                return args -> {
                        ContactDetails Peter = new ContactDetails(
                                        1L,
                                        "Maurizia Ingry",
                                        +486797233458L,
                                        "mingry0@paypal.com",
                                        "32528 Pankratz Plaza");
                        ContactDetails david = new ContactDetails(
                                        1L,
                                        "david peter",
                                        +111_1111_1111L,
                                        "mdbjwwkdkekwjw0@paypal.com",
                                        "32528 Pankratz ghwggw");
                        ContactDetails gift = new ContactDetails(
                                        1L,
                                        "Gift peter",
                                        +412211458L,
                                        "gift2Y@YAHOO.COM",
                                        "waec estate");
                        contactRepository.saveAll(
                                        List.of(Peter, david, gift));

                };

        }
}
