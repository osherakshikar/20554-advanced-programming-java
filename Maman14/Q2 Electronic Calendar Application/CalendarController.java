package com.example.codetesterjavafx;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

/**
 * Controller class for the Calendar application.
 * Coordinates between the view (FXML) and the model (CalendarLogic).
 */
public class CalendarController {

    /** Label that displays calendar summaries and appointment details */
    @FXML
    private Label atGlanceLable;

    /** Grid for displaying the calendar days */
    @FXML
    private GridPane grid;

    /** Container for year and month selection controls */
    @FXML
    private HBox hbox;

    /** Array of buttons representing days in the current month */
    private Button[] dayButtons;

    /** The currently displayed date/month */
    private Calendar currentDate;

    /** Business logic for calendar operations */
    private CalendarLogic calendarLogic;

    /** ComboBox for selecting year */
    private ComboBox<Integer> yearComboBox;

    /** ComboBox for selecting month */
    private ComboBox<String> monthComboBox;

    /** Default text shown in the summary label when no day is selected */
    private String originalSummaryText;

    /**
     * Initializes the calendar controller
     */
    public void initialize() {
        calendarLogic = new CalendarLogic();
        currentDate = Calendar.getInstance();
        setupControlArea();
        updateCalendarView();
        originalSummaryText = "Calendar at a Glance\n\nSelect a day to view or\nadd appointments.";
        atGlanceLable.setText(originalSummaryText);
    }

    /**
     * Sets up the year and month selection controls in the top bar.
     */
    private void setupControlArea() {
        hbox.getChildren().clear();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        Label yearLabel = new Label("Year:");
        yearComboBox = new ComboBox<>();
        for (int year = 2020; year <= 2030; year++) {
            yearComboBox.getItems().add(year);
        }
        yearComboBox.setValue(currentDate.get(Calendar.YEAR));
        yearComboBox.setOnAction(e -> {
            currentDate.set(Calendar.YEAR, yearComboBox.getValue());
            updateCalendarView();
        });

        Label monthLabel = new Label("Month:");
        monthComboBox = new ComboBox<>();
        monthComboBox.getItems().addAll(calendarLogic.getMonthNames());
        monthComboBox.setValue(calendarLogic.getMonthNames()[currentDate.get(Calendar.MONTH)]);
        monthComboBox.setOnAction(e -> {
            String monthName = monthComboBox.getValue();
            int monthIndex = Arrays.asList(calendarLogic.getMonthNames()).indexOf(monthName);
            currentDate.set(Calendar.MONTH, monthIndex);
            updateCalendarView();
        });

        hbox.getChildren().addAll(yearLabel, yearComboBox, monthLabel, monthComboBox);
        styleComboBoxes();
    }

    /**
     * Updates the calendar grid to display days of the selected month and year.
     */
    public void updateCalendarView() {
        grid.getChildren().clear();

        // Set day headers
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            dayLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
            dayLabel.setPrefSize(grid.getPrefWidth() / 7, 30);
            dayLabel.setAlignment(Pos.CENTER);
            grid.add(dayLabel, i, 0);
        }

        // Get calendar data from logic class
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int daysInMonth = calendarLogic.getDaysInMonth(year, month);
        int dayOfWeek = calendarLogic.getFirstDayOfWeek(year, month);

        // Create calendar grid
        dayButtons = new Button[daysInMonth];
        int row = 1;

        for (int day = 1; day <= daysInMonth; day++) {
            int column = (dayOfWeek + day - 1) % 7; // Calculate column index
            if (column == 0 && day > 1) {// Move to next row after Sunday
                row++;
            }

            Button btn = new Button(Integer.toString(day));
            btn.setPrefSize(grid.getPrefWidth() / 7, grid.getPrefHeight() / 6);

            // Style the button to be round with transparent background
            btn.setStyle(getDefaultButtonStyle());

            // Create date for this button
            Calendar date = (Calendar) currentDate.clone();
            date.set(Calendar.DAY_OF_MONTH, day);

            // Mark days with appointments with a different style
            if (calendarLogic.hasAppointment(date)) {
                btn.setStyle(getAppointmentButtonStyle());
            }

            // Add mouse hover events for background color change
            setupButtonHoverActions(btn, date);

            // Set action for clicking the button
            btn.setOnAction(e -> handleDayClick(date));

            grid.add(btn, column, row);
            dayButtons[day - 1] = btn;
        }
    }

    /**
     * Setup hover effects for day buttons.
     *
     * @param btn The button to configure
     * @param date The calendar date associated with this button
     */
    private void setupButtonHoverActions(Button btn, Calendar date) {
        btn.setOnMouseEntered(e -> {
            // Save original style
            String originalStyle = btn.getStyle();
            btn.setUserData(originalStyle);

            btn.setStyle(getHoverButtonStyle());
            showAppointmentOnHover(date);
        });

        btn.setOnMouseExited(e -> {
            // Restore original style
            String originalStyle = (String) btn.getUserData();
            btn.setStyle(originalStyle != null ? originalStyle : getDefaultButtonStyle());
            restoreAppointmentSummary();
        });
    }
    /**
     * Applies custom styling to the date selection ComboBoxes.
     * Makes the ComboBoxes more visually consistent with the calendar theme.
     */
    private void styleComboBoxes() {
        // Common style for both ComboBoxes
        String comboBoxStyle =
                "-fx-background-color: white;" +
                        "-fx-background-radius: 4px;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 4px;" +
                        "-fx-padding: 3px;" +
                        "-fx-font-size: 12px;";

        yearComboBox.setStyle(comboBoxStyle);
        monthComboBox.setStyle(comboBoxStyle);

        // Set preferred width for better appearance
        yearComboBox.setPrefWidth(80);
        monthComboBox.setPrefWidth(120);

        // Add hover effect
        yearComboBox.setOnMouseEntered(e -> yearComboBox.setStyle(comboBoxStyle + "-fx-border-color: lightblue;"));
        yearComboBox.setOnMouseExited(e -> yearComboBox.setStyle(comboBoxStyle));

        monthComboBox.setOnMouseEntered(e -> monthComboBox.setStyle(comboBoxStyle + "-fx-border-color: lightblue;"));
        monthComboBox.setOnMouseExited(e -> monthComboBox.setStyle(comboBoxStyle));
    }

    /**
     * Returns the default style for day buttons.
     */
    private String getDefaultButtonStyle() {
        return "-fx-background-color: transparent;" +
                "-fx-background-radius: 50%;" +
                "-fx-border-radius: 50%;" +
                "-fx-padding: 5px;";
    }

    /**
     * Returns the style for buttons with appointments.
     */
    private String getAppointmentButtonStyle() {
        return "-fx-background-color: transparent;" +
                "-fx-background-radius: 50%;" +
                "-fx-border-radius: 50%;" +
                "-fx-padding: 5px;" +
                "-fx-border-color: lightblue;" +
                "-fx-border-width: 2px;";
    }

    /**
     * Returns the style for buttons on hover.
     */
    private String getHoverButtonStyle() {
        return "-fx-background-color: lightblue;" +
                "-fx-background-radius: 50%;" +
                "-fx-border-radius: 50%;" +
                "-fx-padding: 5px;";
    }

    /**
     * Displays appointment details in the summary label when hovering over a day.
     *
     * @param date The calendar date to show appointments for
     */
    private void showAppointmentOnHover(Calendar date) {
        if (calendarLogic.hasAppointment(date)) {
            String formattedDate = calendarLogic.formatDateForDisplay(date);

            StringBuilder content = new StringBuilder();
            content.append("Appointments for ").append(formattedDate).append(":\n\n");
            content.append(calendarLogic.getAppointment(date));

            atGlanceLable.setText(content.toString());
        } else {
            String formattedDate = calendarLogic.formatDateForDisplay(date);
            atGlanceLable.setText("No appointments for " + formattedDate);
        }
    }

    /**
     * Restores the default summary text when mouse exits a day button.
     */
    private void restoreAppointmentSummary() {
        atGlanceLable.setText(originalSummaryText);
    }

    /**
     * Handles click on a day button to add or edit appointments.
     *
     * @param date The calendar date that was clicked
     */
    private void handleDayClick(Calendar date) {
        // Show dialog with existing appointments or create new one
        TextArea appointmentText = new TextArea();
        appointmentText.setPrefRowCount(10);

        // Set existing appointment text if any
        String existingAppointment = calendarLogic.getAppointment(date);
        if (existingAppointment != null) {
            appointmentText.setText(existingAppointment);
        }

        // Create dialog
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Appointments for " + calendarLogic.formatDateForDisplay(date));
        dialog.setHeaderText("Enter appointments for this date:");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.getDialogPane().setContent(appointmentText);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return appointmentText.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(appointmentContent -> {
            int dayIndex = date.get(Calendar.DAY_OF_MONTH) - 1;

            if (!appointmentContent.trim().isEmpty()) {
                // Save appointment in the logic class
                calendarLogic.addAppointment(date, appointmentContent);

                // Update button appearance to show it has appointments
                dayButtons[dayIndex].setStyle(getAppointmentButtonStyle());
            } else {
                // Remove appointment if text is empty
                calendarLogic.removeAppointment(date);

                // Reset button appearance if no appointments
                dayButtons[dayIndex].setStyle(getDefaultButtonStyle());
            }

            updateAppointmentSummary();
        });
    }

    /**
     * Updates the appointment summary in the atGlanceLable.
     * Shows up to 5 upcoming appointments for the current month.
     */
    private void updateAppointmentSummary() {
        StringBuilder summary = new StringBuilder("Calendar at a Glance\n\n");
        summary.append("Upcoming Appointments:\n");

        // Get upcoming appointments from logic class
        List<Map.Entry<String, String>> upcomingAppointments = calendarLogic.getUpcomingAppointments();

        // Display up to 5 appointments
        SimpleDateFormat displayFormat = new SimpleDateFormat("MMM d");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < Math.min(5, upcomingAppointments.size()); i++) {
            Map.Entry<String, String> entry = upcomingAppointments.get(i);
            try {
                Date date = parser.parse(entry.getKey());
                String content = entry.getValue();

                // Get just the first line for the summary
                String firstLine = content.lines().findFirst().orElse("");
                if (firstLine.length() > 30) {
                    firstLine = firstLine.substring(0, 27) + "...";
                }

                summary.append(displayFormat.format(date))
                        .append(": ")
                        .append(firstLine)
                        .append("\n");
            } catch (Exception e) {
                // Skip invalid entries
            }
        }

        if (upcomingAppointments.isEmpty()) {
            summary.append("No upcoming appointments.\n");
        }

        originalSummaryText = summary.toString();
        atGlanceLable.setText(originalSummaryText);
    }
}