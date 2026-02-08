
# 📦 Item Manager

A simple yet powerful item management system built with Spring Boot. This application provides both a RESTful API and a web-based UI, demonstrating fundamental CRUD operations and best practices for building modern Java web applications.

## ✨ Features

### Core Features
* ✅ **Dual Interface**: Both REST API and Modern Thymeleaf-based Web UI
* ✅ **In-Memory Storage**: Fast, lightweight ArrayList-based data storage
* ✅ **Input Validation**: Robust validation with Bean Validation annotations
* ✅ **Exception Handling**: Comprehensive error handling with custom exceptions
* ✅ **RESTful API**: Clean and intuitive REST endpoints with proper HTTP status codes
* ✅ **DTO Pattern**: Separate DTOs for create, update, and response operations
* ✅ **Comprehensive Testing**: Full test coverage with MockMvc and JUnit 5

## 🛠️ Tech Stack

| Technology  | Version |
| ----------- | ----- |
| Java        | 17 |
| Spring Boot | 3.2.5 |
| Thymeleaf   | 3.2.5 |
| Build Tool  | Maven |
| Testing     | JUnit 5, Mockito |

## 🚀 Getting Started

### Prerequisites

* Java 17 or higher installed (Java 24 supported)
* Maven installed

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Prachikhajuria29/Item-Manager.git
cd Item-Manager
```

2. Build and run tests:

```bash
mvn clean install
```

3. Run the application locally:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

---

## 🌐 Deployment

The application is deployed on **AWS Elastic Beanstalk**:

**Base URL:** [http://item-manager-env.eba-xmy2ggt2.eu-north-1.elasticbeanstalk.com/](http://item-manager-env.eba-xmy2ggt2.eu-north-1.elasticbeanstalk.com/)

You can directly test the API using this URL.

### Available Endpoints

#### REST API Endpoints (`/items`)

| HTTP Method | Endpoint      | Description             |
| ----------- | ------------- | ----------------------- |
| POST        | `/items`      | Add a new item          |
| GET         | `/items`      | Get all items           |
| GET         | `/items/{id}` | Get an item by ID       |
| PUT         | `/items/{id}` | Update an existing item |
| DELETE      | `/items/{id}` | Delete an item by ID    |

#### Web UI Endpoints (`/items-ui`)

| HTTP Method | Endpoint              | Description             |
| ----------- | --------------------- | ----------------------- |
| GET         | `/items-ui`           | List all items (supports `?search=` query param) |
| GET         | `/items-ui/add`       | Show add item form      |
| POST        | `/items-ui/save`      | Save new item (with validation) |
| GET         | `/items-ui/edit/{id}` | Show edit form          |
| POST        | `/items-ui/update/{id}` | Update item (with validation) |
| GET         | `/items-ui/view/{id}` | View item details       |
| POST        | `/items-ui/delete/{id}` | Delete item (with confirmation) |

**🔍 Search Example:**
```
http://localhost:8080/items-ui?search=laptop
```
Searches across item ID, name, and description fields.

---

## 📝 Usage Examples (cURL) — **Using Deployed URL**

### Add a New Item with ID

```bash
curl -X POST http://item-manager-env.eba-xmy2ggt2.eu-north-1.elasticbeanstalk.com/items \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "Laptop", "description": "Dell XPS 13"}'
```

### Get All Items

```bash
curl http://item-manager-env.eba-xmy2ggt2.eu-north-1.elasticbeanstalk.com/items
```

### Get Item by ID

```bash
curl http://item-manager-env.eba-xmy2ggt2.eu-north-1.elasticbeanstalk.com/items/1
```

### Update an Item

```bash
curl -X PUT http://item-manager-env.eba-xmy2ggt2.eu-north-1.elasticbeanstalk.com/items/1 \
  -H "Content-Type: application/json" \
  -d '{"name": "Updated Laptop", "description": "Updated Description"}'
```

### Delete an Item

```bash
curl -X DELETE http://item-manager-env.eba-xmy2ggt2.eu-north-1.elasticbeanstalk.com/items/1
```

---

## Run JUnit Test Cases

```bash
mvn clean install
```

---

## 📁 Project Structure

```
Item-Manager/
├── src/
│   ├── main/
│   │   ├── java/org/e_commerce/
│   │   │   ├── controller/
│   │   │   │   ├── ItemController.java        # REST API Controller
│   │   │   │   └── ItemViewController.java    # Thymeleaf UI Controller
│   │   │   ├── service/
│   │   │   │   └── ItemService.java           # Business Logic
│   │   │   ├── repository/
│   │   │   │   └── ItemRepository.java        # In-Memory Data Store
│   │   │   ├── model/
│   │   │   │   └── Item.java                  # Domain Entity
│   │   │   ├── dto/
│   │   │   │   ├── ItemCreateDTO.java         # Create Request DTO
│   │   │   │   ├── ItemUpdateDTO.java         # Update Request DTO
│   │   │   │   └── ItemResponseDTO.java       # Response DTO
│   │   │   ├── Mapper/
│   │   │   │   └── ItemMapper.java            # DTO-Entity Mapper
│   │   │   ├── Exception/
│   │   │   │   ├── ApiExceptionHandler.java   # REST API Exception Handler
│   │   │   │   ├── UiExceptionHandler.java    # UI Exception Handler
│   │   │   │   ├── ItemNotFoundException.java
│   │   │   │   ├── DuplicateItemException.java
│   │   │   │   └── ApplicationException.java
│   │   │   └── Application.java
│   │   └── resources/
│   │       ├── static/
│   │       │   └── css/
│   │       │       └── style.css              # Modern UI Styling
│   │       ├── templates/
│   │       │   ├── layout.html                # Reusable Layout Fragments
│   │       │   ├── items.html                 # Item List + Search
│   │       │   ├── add-item.html              # Add Item Form
│   │       │   ├── edit-item.html             # Edit Item Form
│   │       │   ├── view-item.html             # Item Detail View
│   │       │   └── error.html                 # Error Page
│   │       └── application.properties
│   └── test/
│       └── java/org/e_commerce/
│           └── controller/
│               └── ItemControllerTest.java    # Unit Tests
└── pom.xml
```

---

## 💡 Key Concepts Demonstrated

### Backend Architecture
* **Spring Boot Fundamentals**: Project setup and configuration
* **REST API Design**: Best practices for API endpoints
* **CRUD Operations**: Complete Create, Read, Update, Delete functionality
* **Input Validation**: Bean Validation with `@Valid` annotations
* **Exception Handling**: Dual exception handlers for API vs UI
* **DTO Pattern**: Separate DTOs for different operations
* **In-Memory Storage**: ArrayList implementation (Note: Not persistent across restarts)

### Frontend Implementation
* **Thymeleaf Templates**: Server-side rendering with fragments and layouts
---

## 🔮 Next Steps / Planned Features

### High Priority
1. Better Search Functionality (multi-field search, partial matches)
2. Pagination for large item lists
3. Sort functionality (by ID, name, date)
4. Export/Import items (CSV, JSON)

### Future Enhancements
1. **Database Integration**: PostgreSQL with JPA/Hibernate
2. **Advanced Search**: Fuzzy search, semantic search
3. **Audit Fields**: JPA Auditable [created_at, created_by, updated_at, updated_by]
4. **Security**: Spring Security with Basic/JWT Authentication
5. **Caching**: Redis or in-memory cache for performance

---

## 👨‍💻 Author

**Prachi Khajuria**

* GitHub: [@Prachikhajuria29](https://github.com/Prachikhajuria29)

---


