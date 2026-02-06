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

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### 1. Add a New Item
- **Method**: `POST`
- **URL**: `http://localhost:8080/items`
- **Request Body**:
```json
{
    "id": 1,
    "name": "Laptop",
    "description": "Dell XPS 13"
}
```
- **Response**: Created item with details

### 2. Get All Items
- **Method**: `GET`
- **URL**: `http://localhost:8080/items`
- **Response**: List of all items

### 3. Get Item by ID
- **Method**: `GET`
- **URL**: `http://localhost:8080/items/{id}`
- **Response**: Specific item details

### 4. Update an Item
- **Method**: `PUT`
- **URL**: `http://localhost:8080/items/{id}`
- **Request Body**:
```json
{
    "id": 1,
    "name": "Updated Laptop",
    "description": "Updated Dell XPS 13"
}
```

### 5. Delete an Item
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/items/{id}`
- **Response**: Success/Failure message

## 🧪 Testing with Postman

1. Import the provided endpoints above into Postman
2. Test each endpoint with sample data
3. Verify responses match expected output

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

## 🎯 Learning Outcomes

This project is perfect for understanding:
- Spring Boot application structure
- RESTful API design patterns
- CRUD operations implementation
- HTTP methods and status codes
- Request/Response handling
- Data validation techniques

## 🤝 Contributing

Feel free to fork this repository and submit pull requests for any improvements.

## 📄 License

This project is open source and available under the MIT License.

## 👨‍💻 Author

**Prachi Khajuria**
- GitHub: [@Prachikhajuria29](https://github.com/Prachikhajuria29)

---

**Made with ❤️ for learning and development**