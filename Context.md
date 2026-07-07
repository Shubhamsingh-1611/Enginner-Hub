# Engineer Hub - Project Context

Version: 1.0

---

# Project Name

Engineer Hub

---

# Project Description

Engineer Hub is a full-stack marketplace platform where engineering students can buy and sell second-hand engineering items.

Examples:

- Engineering Books
- Calculators
- Lab Equipment
- Electronic Components
- Project Kits
- Laptops
- Raspberry Pi
- Arduino
- Sensors
- Mechanical Tools
- Instruments

The platform is similar to OLX but focused only on engineering students.

---

# Technology Stack

## Backend

- Java 21
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Hibernate
- JWT Authentication
- Spring Validation
- Spring WebSocket
- Maven

## Database

MySQL 8+

## Frontend

- React
- Vite
- React Router
- Axios
- Tailwind CSS

---

# Project Architecture

The project follows a layered architecture.

```
Controller

↓

Service

↓

Repository

↓

Database
```

Business logic must NEVER be placed inside controllers.

Controllers should only:

- Receive Request
- Validate DTO
- Call Service
- Return Response

---

# Folder Structure

```
Engineer-Hub

│

├── backend

│     ├── src/main/java/com/engineerhub

│     │      ├── controller

│     │      ├── service

│     │      │      └── impl

│     │      ├── repository

│     │      ├── entity

│     │      ├── dto

│     │      │      ├── request

│     │      │      └── response

│     │      ├── mapper

│     │      ├── config

│     │      ├── security

│     │      ├── exception

│     │      └── util

│     └── resources

│             └── application.properties

│

├── frontend

├── docs

└── README.md
```

---

# Coding Principles

Follow SOLID principles.

Use Constructor Injection.

Never use field injection.

Prefer composition over inheritance.

Write clean, readable code.

Avoid duplicate logic.

Follow REST API conventions.

---

# Database

MySQL

Database Name

```
engineer_hub
```

Hibernate

```
spring.jpa.hibernate.ddl-auto=update
```

---

# Entities

The project currently contains the following entities.

---

## User

Stores registered users.

Fields include:

- id
- firstName
- lastName
- username
- email
- password
- phoneNumber
- profileImage
- role
- enabled
- createdAt
- updatedAt

Relationships

One User

↓

Many Products

One User

↓

Many Orders

One User

↓

Many Notifications

One User

↓

Many Sent Messages

One User

↓

Many Received Messages

---

## Category

Stores product categories.

Examples

- Books
- Electronics
- Instruments
- Components
- Calculators

Relationship

One Category

↓

Many Products

---

## Product

Represents a product listing.

Fields

- id
- title
- description
- price
- imageUrl
- condition
- negotiable
- sold
- createdAt
- updatedAt

Relationships

Many Products

↓

One Seller

Many Products

↓

One Category

One Product

↓

Many OrderItems

---

## Order

Represents a purchase.

Fields

- id
- buyer
- totalAmount
- status
- shippingAddress
- orderDate
- updatedAt

Relationships

Many Orders

↓

One Buyer

One Order

↓

Many OrderItems

One Order

↓

One Payment

---

## OrderItem

Stores products inside an order.

Fields

- id
- order
- product
- quantity
- unitPrice
- subtotal

Relationships

Many OrderItems

↓

One Order

Many OrderItems

↓

One Product

---

## Payment

Stores payment information.

Fields

- id
- order
- paymentMethod
- paymentStatus
- transactionId
- amount
- paymentDate
- gatewayName
- gatewayResponse

Relationship

One Payment

↓

One Order

---

## Message

Private chat between users.

Fields

- id
- sender
- receiver
- content
- isRead
- deletedBySender
- deletedByReceiver
- sentAt

Relationships

Many Messages

↓

One Sender

Many Messages

↓

One Receiver

---

## Notification

Stores notifications.

Fields

- id
- user
- title
- message
- type
- isRead
- isDeleted
- createdAt

Relationship

Many Notifications

↓

One User

---

# Enums

Role

```
USER
ADMIN
```

OrderStatus

```
PENDING
CONFIRMED
PROCESSING
SHIPPED
OUT_FOR_DELIVERY
DELIVERED
CANCELLED
RETURN_REQUESTED
RETURNED
REFUNDED
```

PaymentMethod

```
UPI
CREDIT_CARD
DEBIT_CARD
NET_BANKING
WALLET
PAYPAL
CASH_ON_DELIVERY
```

PaymentStatus

```
PENDING
SUCCESS
FAILED
CANCELLED
REFUNDED
```

NotificationType

```
ORDER
PAYMENT
MESSAGE
SYSTEM
PRODUCT
```

---

# Repository Layer

Repositories extend JpaRepository.

Example

```
UserRepository
CategoryRepository
ProductRepository
OrderRepository
OrderItemRepository
PaymentRepository
MessageRepository
NotificationRepository
```

Custom query methods should use Spring Data JPA naming conventions whenever possible.

---

# DTO Layer

Never expose Entity classes through REST APIs.

Create Request DTOs

Examples

```
RegisterRequest

LoginRequest

CreateProductRequest

UpdateProductRequest

CreateOrderRequest

PaymentRequest

MessageRequest
```

Create Response DTOs

Examples

```
UserResponse

ProductResponse

OrderResponse

PaymentResponse

MessageResponse

NotificationResponse
```

---

# Mapper Layer

Responsible for converting

DTO

↓

Entity

and

Entity

↓

DTO

Mapping should remain outside controllers.

---

# Service Layer

Contains all business logic.

Examples

Register User

Login

Create Product

Update Product

Delete Product

Search Product

Place Order

Process Payment

Send Message

Create Notification

Controllers must never contain business logic.

---

# Security

Authentication

JWT

Password Encryption

Spring Security

BCrypt Password Encoder

Roles

```
USER

ADMIN
```

Only authenticated users can:

- Create Products
- Place Orders
- Send Messages

Only Admin can

- Manage Categories
- Remove Products
- Manage Users

---

# API Style

RESTful APIs

Examples

```
GET

POST

PUT

DELETE
```

Responses should use ResponseEntity.

Use proper HTTP Status Codes.

---

# Validation

Use Jakarta Validation.

Examples

```
@NotNull

@NotBlank

@Email

@Size

@Positive

@Min

@Max
```

Validate request DTOs instead of entities.

---

# Exception Handling

Use Global Exception Handler.

Examples

```
ResourceNotFoundException

DuplicateResourceException

UnauthorizedException

ValidationException
```

Never expose stack traces to API consumers.

---

# WebSocket

Used for

Real-time Chat

Future Notifications

Do not use polling.

---

# Frontend

React + Vite

Suggested folders

```
src

components

pages

layouts

services

hooks

context

utils

assets

routes
```

Axios should communicate with Spring Boot APIs.

JWT stored securely (prefer HttpOnly cookies if implemented; otherwise handle tokens carefully).

---

# Future Features

Possible future additions

- Wishlist
- Seller Ratings
- Product Reports
- Admin Dashboard
- Email Verification
- Forgot Password
- Image Upload
- Search Filters
- Pagination
- Product Recommendations
- Analytics

These are NOT part of Version 1.

---

# Development Order

The project should be developed in this sequence.

1. Entity
2. Repository
3. DTO
4. Mapper
5. Service Interface
6. Service Implementation
7. Exception Handling
8. Spring Security
9. JWT Authentication
10. Authentication APIs
11. Category APIs
12. Product APIs
13. Order APIs
14. Payment APIs
15. Message APIs
16. Notification APIs
17. WebSocket Chat
18. React Frontend
19. Testing
20. Deployment

---

# Coding Standards

Always write clean code.

Prefer descriptive variable names.

Avoid hardcoded values.

Use constants where appropriate.

Keep methods small.

One class should have one responsibility.

Follow Java naming conventions.

Document complex logic.

Write scalable and maintainable code.

---

# Goal

Build a production-ready engineering marketplace using modern Spring Boot and React best practices.

The code should prioritize:

- Clean Architecture
- Scalability
- Maintainability
- Security
- Readability
- Performance

The project should be structured as if it were intended for real-world deployment.