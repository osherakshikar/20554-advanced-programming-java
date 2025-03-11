# 💬 Java Chat Application

A simple multi-user chat application built with Java and JavaFX that allows multiple users to connect to a central server and exchange messages in real-time.  

## ✨ Features

- 🗣️ Multi-user chat with real-time messaging  
- 🚪 User join/leave notifications  
- 📋 Active users list panel  
- 🎨 Clean and responsive JavaFX UI  
- 💻 Cross-platform (Windows, macOS, Linux)  
- 🔌 Server-client architecture  

## 🛠️ Technology Stack

-  Java  
-  JavaFX for the UI  
-  Socket programming for networking  
-  Multi-threading for concurrent connections  

## 📸 Screenshots
![Screenshot 2025-03-11 140607](https://github.com/user-attachments/assets/83d1c1ec-8267-476c-941a-0fa6cef73240)
![Screenshot 2025-03-11 140640](https://github.com/user-attachments/assets/dbbc8ed4-c1c9-4ec3-93d0-52e77b073116)
![Screenshot 2025-03-11 140711](https://github.com/user-attachments/assets/30686ea5-b149-4453-8503-4bf617638163)
![Screenshot 2025-03-11 140735](https://github.com/user-attachments/assets/7154f411-c493-489b-8b13-434c8deee95d)

## ▶️ How to Run

### 📌 Prerequisites

-  Java JDK 8 or newer with JavaFX support  
-  Maven (optional, if you want to build from source)  

### 🚀 Running the Server

1.  Compile and run the Server class  
2.  The server will display its IP address and will listen on port **9090**  

### 🎮 Running the Client

1.  Compile and run the HelloApplication class  
2.  Enter the server's IP address (or use `"localhost"` if running on the same machine)  
3.  Click "Connect" and enter your username when prompted  
4.  Start chatting!  

## 🌍 Network Setup

To connect multiple devices:  
-  Make sure all devices are on the same network  
-  Allow TCP traffic on port **9090**  
-  Use the server's IP address when connecting  

## 📂 Project Structure

- ` Server.java` - The chat server that accepts client connections  
- ` ServerThread.java` - Handles individual client communication  
- ` HelloApplication.java` - JavaFX client application entry point  
- ` ChatClientController.java` - Controller for the JavaFX UI  
- ` ChatClientThread.java` - Handles network communication for the client  
