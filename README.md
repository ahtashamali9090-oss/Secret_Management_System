# 🔐 Secure Secrets Management System

A secure backend application built using **Java Spring Boot** and **MySQL** for storing and managing sensitive user credentials such as passwords, API keys, and tokens.

---

## 🚀 Features

- User Registration & Login
- Add, Update, Delete Secrets
- View All Secrets & Get by ID
- Search Secrets by Service Name or Category
- Input Validation (e.g., email format, required fields)
- Global Exception Handling with proper HTTP responses

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Lombok
- Postman (for API testing)

---

## 📡 API Endpoints

### 👤 User APIs

| Method | Endpoint | Description |
|--------|--------|-------------|
| POST | `/api/users/register` | Register a new user |
| POST | `/api/users/login` | Login user |

---

### 🔑 Secret APIs

| Method | Endpoint | Description |
|--------|--------|-------------|
| POST | `/api/secrets/{userId}` | Add a secret |
| GET | `/api/secrets` | Get all secrets |
| GET | `/api/secrets/{id}` | Get secret by ID |
| PUT | `/api/secrets/{id}` | Update secret |
| DELETE | `/api/secrets/{id}` | Delete secret |
| GET | `/api/secrets/search/service` | Search by service name |
| GET | `/api/secrets/search/category` | Search by category |

---

## 🗄️ Database Design

### Users Table
- id
- name
- email (unique)
- password_hash
- created_at

### Secrets Table
- id
- service_name
- login_username
- secret_value
- category
- notes
- created_at
- updated_at
- user_id (foreign key)

---

## ▶️ How to Run

1. Clone the repository:
```bash
git clone https://github.com/yourusername/secrets-manager.git
