# Calling-Game

The Contact Management System is a Java application that enables users to manage a list of contacts, perform CRUD operations (Create, Read, Update, Delete) on the contacts, and simulate phone calls between contacts.

## Features

- **Insertion**: Users can add number of Contacts to the list by providing a name and a phone number.
- **Display**: View all contacts stored in the contact list, including their names and phone numbers.
- **Update**: Modify the name or phone number of an existing contact in the contact list.
- **Delete**: Remove contacts from the contact list.
- **Checking**: Simulate phone call availability between contacts and determine if a contact is available to receive a call.

## Technologies Used

- Java: The core logic of the Flight Booking System is implemented in Java programming language, providing robustness and platform independence.
- MySQL Database: The system utilizes a MySQL database to store flight information, user data, and booking details, ensuring data integrity and scalability.
- JDBC (Java Database Connectivity): JDBC is used to establish a connection between the Java application and the MySQL database, enabling seamless interaction with the database.

## Installation

- Install Visual Studio Code.
- Install Java Extension Pack in Visual Studio Code.
- Install MySQL.
- create a database named "project1".
- Create a table with name "contact" and with the following details:
  <br>
  ```html
  CREATE TABLE `contact` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(225) NOT NULL,
  `Number` bigint NOT NULL,
  PRIMARY KEY (`ID`)
  )
- Download Connector/J and set it up.
- Create a Java Project.
- Download and replace the project App.java present in file/src with the Repository App.java.
- Run the Java Application: You can run your Java application directly from Visual Studio Code. Open the Java file containing your main method, right-click, and select "Run Java".

  ## Developer: Sheikh Faisal Rayees
  

