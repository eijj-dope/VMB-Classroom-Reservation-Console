# 🏫 Classroom Reservation System (Console Version)

A **Java-based console application** that allows **users** to reserve classrooms and **admins** to manage rooms and reservations.  
This project is a simple text-based version of a classroom reservation system originally built with a GUI.

---

## 🚀 Features

### 👨‍💼 Admin
- Login using secure credentials  
- View all rooms  
- Add new rooms  
- View all reservations made by users  

### 👤 User
- Register a new account  
- Login using username and password  
- View available rooms  
- Reserve a room  
- Cancel reservations  
- View personal reservations  

---

## 🗂️ Project Structure

| File | Description |
|------|--------------|
| `Main.java` | Entry point of the program. Handles main menu navigation. |
| `Admin.java` | Handles admin login credentials and validation. |
| `User.java` | Represents a user with a username and password. |
| `Room.java` | Represents a classroom and its availability. |
| `Reservation.java` | Represents a room reservation (room name + user). |
| `RoomManager.java` | Manages rooms and reservations (add, reserve, cancel, view). |
| `Storage.java` | Stores user data and reservation information in memory. |
| `ConsoleAuth.java` | Handles user registration and login through console. |
| `ConsoleUI.java` | Displays menus and interacts with users via console. |

---

## 🔑 Default Admin Credentials

| Role | Username | Password |
|------|-----------|-----------|
| **Admin** | `admin` | `admin123` |

---

## ⚙️ How to Run

1. Open the project in your preferred IDE (IntelliJ, Eclipse, VS Code).  
2. Compile all `.java` files:
   ```bash
   javac *.java

Example Menu Flow
===== CLASSROOM RESERVATION SYSTEM =====
1. Login as Admin
2. Continue as User
3. Exit
