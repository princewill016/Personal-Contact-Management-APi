---Personal Contact Management API
This project is a RESTful API that allows users to manage their personal contacts. It provides endpoints for user registration and authentication, CRUD operations on contacts, search functionality, and security features using JWT (JSON Web Tokens).

---Key Features
User Registration and Authentication
Authenticate User: Endpoint to authenticate users and generate JWT token for secure API access.
--CRUD Operations for Contacts
Create Contact: Endpoint to add new contacts with details like name, email, phone number, and address.
Read Contacts: Endpoints to retrieve all contacts for a user, and a single contact by ID.
Update Contact: Endpoint to update existing contact information.
Delete Contact: Endpoint to remove a contact from the database.

---Search Functionality
Search Contacts: Endpoint to search for contacts by name.

---Security
JWT Authentication: All contact management endpoints require a valid JWT for access.

---Technical Requirements
Backend Framework
Spring Boot: Backend framework for building robust and scalable RESTful APIs.
ORM & Database
Hibernate: Object-Relational Mapping (ORM) framework for interacting with the MySQL database.
Security
Spring Security: Security framework for securing API endpoints and implementing JWT authentication.

---API Documentation
Swagger: API documentation tool for generating interactive API documentation.

---Getting Started
Clone the repository.
Set up MySQL database and configure database connection in application.properties.
Build and run the application.
Access API documentation using Swagger UI.

---API Endpoints
--User Authentication
POST /api/authenticate: Authenticate user and generate JWT token.
--Contact Management
GET /api/contacts: Get all contacts for the authenticated user.
GET /api/contacts/{contactDetailsId}: Get a single contact by ID.
GET /api/contacts/{contactDetailsName}: Search contacts by name
POST /api/newContact: Create a new contact.
POST /api/newContacts: Create new contacts.
PUT /api/contacts/{contactDetailsId}: Update an existing contact.
DELETE /api/contacts/{contactDetailsId}: Delete a contact.

---API Documentation
Access the API documentation and try out the endpoints using Swagger UI.

---Security
Secure all contact management endpoints with JWT authentication.
Generate JWT token upon user authentication and include it in the Authorization header for API access.

---Technologies Used
Spring Boot
Hibernate
MySQL
Spring Security
JWT
Swagger

---Contributors
Peter David.
