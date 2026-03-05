# Telehealth Platform

A full-stack **Telehealth Platform** designed to enable online healthcare consultations by managing users, appointments, schedules, and medical interactions.  
The project focuses on **clean backend architecture**, **role-based access control**, and **real-world healthcare workflows**.

---

## 📌 Overview

The Telehealth Platform allows:
- Patients to book and manage appointments
- Healthcare providers to manage availability and consultations
- Administrators to manage platform users

The system is designed using **industry-standard backend practices** with scalability, security, and maintainability in mind.

---

## 🛠️ Tech Stack

### Backend
- Java  
- Spring Boot  
- Spring MVC  
- Spring Data JPA (Hibernate)  
- Spring Security  
- RESTful APIs  
- Maven  

### Database
- MySQL  

### Tools
- Postman (API testing)  
- STS (IDE)  
- MySQL Workbench  

---

## 🏗️ Application Architecture

The backend follows a **layered architecture**:

### Key Design Principles
- Separation of concerns
- DTO-based request and response handling
- Enum-driven domain modeling
- Centralized exception handling
- Secure access using Spring Security

---

## 🔐 Security & Authentication

- User authentication using **Spring Security**
- Role-based access control (PATIENT, PROVIDER, ADMIN)
- Password encryption
- Secured REST endpoints

---

## ✨ Features & Functionalities

### User Management
- User registration and login
- Single `User` entity with role-based differentiation
- Profile creation and profile updates
- Role-based access handling

---

### Patient Features
- View healthcare providers
- View available appointment slots
- Book appointment slots
- View booked appointments
- Reschedule appointments
- Cancel appointments
- View appointment status
- View medical records
- Secure messaging with providers
- View prescriptions issued by providers

---

### Healthcare Provider Features
- Provider profile management
- Add availability slots (date & time based)
- Manage appointment schedules
- View booked appointments
- Access patient details **only for booked slots**
- Issue prescriptions
- View patient medical history

---

### Admin Features
- Admin login
- User management (patients and providers)
- Provider account approval and management
- Role and access monitoring

---

### Appointment & Slot Management
- Slot creation by providers
- Slot booking by patients
- Enum-based slot status management
- Appointment status handling:
  - `SCHEDULED`
  - `CANCELED`
  - `RESCHEDULED`
- Controlled visibility of patient data

---

## 🧾 Database Design

- Relational schema using MySQL
- JPA entity relationships
- Enum usage for:
  - User roles
  - Appointment status
  - Slot status
- Referential integrity and validation rules

---

## 📡 API Endpoint Documentation

### 🔐 Authentication APIs
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Authenticate user |

---

### 👤 User APIs
| Method | Endpoint | Description |
|------|---------|------------|
| GET | `/api/users/profile` | Get logged-in user profile |
| PUT | `/api/users/profile` | Update user profile |

---

### 🧑‍⚕️ Provider APIs
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/api/providers/slots` | Add availability slots |
| GET | `/api/providers/appointments` | View booked appointments |

---

### 🧍 Patient APIs
| Method | Endpoint | Description |
|------|---------|------------|
| GET | `/api/patients/providers` | View providers |
| GET | `/api/patients/slots` | View available slots |
| POST | `/api/patients/appointments` | Book appointment |
| PUT | `/api/patients/appointments/reschedule` | Reschedule appointment |
| PUT | `/api/patients/appointments/cancel` | Cancel appointment |

---

### 📄 Prescription APIs
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/api/prescriptions` | Issue prescription |
| GET | `/api/prescriptions/{appointmentId}` | View prescription |

---

## 🧪 Testing

- API testing using **Postman**
- Role-based access testing
- Validation and error scenario testing

---

## 🚀 How to Run the Project

### Backend Setup
1. Clone the repository
2. Configure MySQL database
3. Update `application.properties`
4. Run the Spring Boot application

