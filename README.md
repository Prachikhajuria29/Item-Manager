# 📦 Item Manager

A simple yet powerful RESTful item management system built with Spring Boot. This application demonstrates fundamental CRUD operations and best practices for building modern Java web applications.

## ✨ Features

- ✅ **In-Memory Storage**: Fast, lightweight ArrayList-based data storage
- ✅ **Input Validation**: Robust validation to ensure data integrity
- ✅ **RESTful API**: Clean and intuitive REST endpoints
- ✅ **Easy to Use**: Simple setup and straightforward API design

## 🛠️ Tech Stack

| Technology | Version |
|-----------|---------|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Build Tool | Maven |

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher installed
- Maven installed

### Installation

1. Clone the repository:
```bash
git clone https://github.com/Prachikhajuria29/Item-Manager.git
cd Item-Manager
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

## 📁 Project Structure

```
Item-Manager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/itemmanager/
│   │   │       ├── controller/     # REST Controllers
│   │   │       ├── service/        # Business Logic
│   │   │       ├── model/          # Entity Classes
│   │   │       └── Application.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       │   └── com/itemmanager/
│       │       └──controller/     # REST Controllers
│       │        
│       │         
│       │         
└── pom.xml
```

## 💡 Key Concepts Demonstrated

- **Spring Boot Fundamentals**: Project setup and configuration
- **REST API Design**: Best practices for API endpoints
- **CRUD Operations**: Complete Create, Read, Update, Delete functionality
- **Input Validation**: Data validation before processing
- **In-Memory Storage**: ArrayList implementation for data persistence (Note: This is not persistent across restarts)

## 📝 Usage Examples (cURL)

### Add a New Item with ID
```bash
curl -X POST http://localhost:8080/items \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "Laptop", "description": "Dell XPS 13"}'
```

### Get All Items
```bash
curl http://localhost:8080/items
```

### Get Item by ID
```bash
curl http://localhost:8080/items/1
```

### Update an Item
```bash
curl -X PUT http://localhost:8080/items/1 \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "Updated Laptop", "description": "Updated Description"}'
```

### Delete an Item
```bash
curl -X DELETE http://localhost:8080/items/1
```

## Run Junit Test Cases
```bash
mvn clean install
```


## 🎯 Learning Outcomes

This project is perfect for understanding:
- Spring Boot application structure
- RESTful API design patterns
- CRUD operations implementation
- HTTP methods and status codes
- Request/Response handling
- Data validation techniques


## Next Steps
1. Integrating with actual persistence database [postgresql]
2. API to add search API [fuzzy search, semantic search]
3. JPA Auditable [created_at, created_by, updated_at, updated_by]
4. Spring security [Basic Authentication]
5. In-Memory cache for Reducing database calls

---

## 👨‍💻 Author

**Prachi Khajuria**
- GitHub: [@Prachikhajuria29](https://github.com/Prachikhajuria29)

---