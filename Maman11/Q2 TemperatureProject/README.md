# 🌡️ Temperature Visualization Project

## 📊 Overview
This JavaFX application visualizes monthly temperature data across multiple years (2017-2021) using a dynamic bar chart visualization. The application highlights temperature trends, identifying the hottest and coldest months for each year.

## 🔧 Features
- Interactive bar chart displaying temperature data for 12 months
- Visual highlighting of maximum temperature (red) and minimum temperature (blue) months
- Year navigation with "Next" button to cycle through 2017-2021 data
- MVC architecture separating UI, controller logic, and data model
- Dynamic column sizing based on temperature values

## 🧮 Implementation Details
The application follows the Model-View-Controller (MVC) pattern:
- **Model**: `TemperatureProjectLogic` maintains temperature data and current year state
- **View**: FXML file defines the layout with a canvas and navigation button
- **Controller**: Handles drawing the bar chart and responding to user actions

Temperature data is stored as arrays of integers representing the 12 months for each year, with lower values representing higher temperatures (inverted scale for visual representation).

## 📸 Screenshots
![Year 2017](https://github.com/user-attachments/assets/ebc0eaa5-4131-4ffa-b5b4-48716df63960)
![Year 2018](https://github.com/user-attachments/assets/5a90e442-a490-4e77-942f-2d3cb6cc80c0)
![Year 2019](https://github.com/user-attachments/assets/9cef33d7-5d83-4a14-a6b5-40d417633602)
![Year 2020](https://github.com/user-attachments/assets/cc68f4a2-8513-439e-8aa6-0a57798f736b)
![Year 2021](https://github.com/user-attachments/assets/539ae53a-b887-48e9-a3ec-88de9f1706d2)
