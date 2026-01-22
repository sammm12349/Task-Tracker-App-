Task Tracker App

A backend REST API for task management built with Spring Boot and PostgreSQL.
Features

Create, read, update, and delete tasks
RESTful API architecture
PostgreSQL database integration
Spring Data JPA for data persistence

Technologies that were used

Backend: Spring Boot 4.0.1
Language: Java 23
Database: PostgreSQL 18.1
ORM: Hibernate 7.2.0
Build Tool: Maven
Server: Apache Tomcat 11.0.15

Prerequisites
Before running this application, make sure you have the following installed:

Java 23 or higher
Maven
PostgreSQL
Git

Installation

Clone the repository:

bashgit clone https://github.com/sammm12349/Task-Tracker-App-.git
cd Task-Tracker-App-

Configure the database:

Create a PostgreSQL database
Update src/main/resources/application.properties with your database credentials:



propertiesspring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

Build the project:

bashmvn clean install

Run the application:

bashmvn spring-boot:run
The application will start on http://localhost:8080
API Endpoints
Tasks

GET /api/tasks - Get all tasks
GET /api/tasks/{id} - Get task by ID
POST /api/tasks - Create a new task
PUT /api/tasks/{id} - Update a task
DELETE /api/tasks/{id} - Delete a task


Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.
License
This project was made with the help of Devtiro 
Author
Sam Spear - GitHub
Acknowledgments

Spring Boot Documentation
PostgreSQL Community
Tutorial by Devtiro - https://www.youtube.com/watch?v=brnazVsG4cY&list=LL&index=26&t=2453s
