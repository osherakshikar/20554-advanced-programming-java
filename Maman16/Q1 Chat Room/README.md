# Java Chat Application

A lightweight multi-user chat app built with Java and JavaFX. Clients connect to a central server to exchange messages in real time.

## Features

- Real-time group messaging  
- Join/leave notifications  
- Active users list  
- Responsive JavaFX UI  
- Cross-platform support  
- Server-client architecture (Sockets + Threads)

## Tech Stack

- Java  
- JavaFX  
- Socket Programming  
- Multi-threading

## Screenshots

![Chat UI](https://github.com/user-attachments/assets/83d1c1ec-8267-476c-941a-0fa6cef73240)  
![More UI](https://github.com/user-attachments/assets/dbbc8ed4-c1c9-4ec3-93d0-52e77b073116)  
![Chat Window](https://github.com/user-attachments/assets/30686ea5-b149-4453-8503-4bf617638163)  
![Users List](https://github.com/user-attachments/assets/7154f411-c493-489b-8b13-434c8deee95d)

## Getting Started

### Prerequisites

- Java JDK 8 or newer with JavaFX  
- (Optional) Maven for building

### Run the Server

1. Compile and run `Server.java`  
2. The server listens on port `9090`

### Run the Client

1. Compile and run `HelloApplication.java`  
2. Enter the server IP (use `localhost` if local)  
3. Enter your username and connect

### Network Setup

- Ensure all devices are on the same network  
- Allow TCP traffic on port `9090`  
- Use the server's IP address to connect

## Project Structure


- ` Server.java` - The chat server that accepts client connections  
- ` ServerThread.java` - Handles individual client communication  
- ` HelloApplication.java` - JavaFX client application entry point  
- ` ChatClientController.java` - Controller for the JavaFX UI  
- ` ChatClientThread.java` - Handles network communication for the client  
