# Travel Agency Web Application

## Project Overview
This project is a web application for a travel agency, allowing users to find and book hotels in various countries. It has a separate managerial role that can add hotels and rooms to the system and view all users and their orders. The application is built using Maven, Hibernate, Spring MVC (Thymeleaf or JSP + JSTL), and Spring Security.
## Technologies
- Java
- Maven
- Hibernate
- Spring MVC 
- Thymeleaf
- Spring Security
- IntelliJ (IDE)
- MySQL

## Project To-do List

- [x] **Database Setup**:
    - Create a MySQL database for the application.
    - Setup necessary tables (`Role`, `User`, `Country`, `City`, `Hotel`, `Room`, `Booking`).

- [x] **Backend Development**:
    - Implement entities (`Role`, `User`, `Country`, `City`, `Hotel`, `Room`, `Booking`) using Hibernate.
    - Implement DAOs for each entity.
    - Implement Service layer for business logic.

- [x] **User Authentication and Authorization**:
    - Implement user registration and login functionality using Spring Security.
    - Implement role-based access control (User vs Manager).

- [x] **Frontend Development**:
    - Design and implement `Login` page.
    - Design and implement `Home` page for hotel search, room availability checking, and room booking.
    - Design and implement `Management` page for managers to manage hotels and bookings.

- [x] **Integration and Testing**:
    - Implement Controllers using Spring MVC.
    - Test application functionality.
    - Ensure all components work together as expected.
