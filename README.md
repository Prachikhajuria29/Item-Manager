# Item Manager

## Overview
This application is a simple item management system that allows users to manage items using an in-memory storage solution.

## Technologies Used
- **Java version**: 17
- **Spring Boot version**: 3.2.5

## Features
- In-memory storage using ArrayList
- Validation of input data before processing

## Postman Testing Examples
- **Add an Item**: 
    - **Method**: POST
    - **URL**: `http://localhost:8080/items`
    - **Body**: 
      ```json
      {
          "name": "Item Name",
          "description": "Item Description"
      }
      ```
- **Get All Items**: 
    - **Method**: GET
    - **URL**: `http://localhost:8080/items`
- **Get Item by ID**: 
    - **Method**: GET
    - **URL**: `http://localhost:8080/items/{id}`
- **Delete an Item**: 
    - **Method**: DELETE
    - **URL**: `http://localhost:8080/items/{id}`

## Conclusion
This project provides a foundational understanding of building RESTful services using Spring Boot with basic CRUD operations.