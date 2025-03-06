package com.example.codetesterjavafx;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.stream.Collectors;

/**
 * Model class containing calendar business logic.
 * Manages appointments and calendar date calculations.
 */
public class CalendarLogic {

    /** Map of appointments where key is date in yyyy-MM-dd format and value is appointment text */
    private Map<String, String> appointments = new HashMap<>();

    /** Month names for displaying in the month selection ComboBox */
    private final String[] monthNames = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    /**
     * Gets the array of month names.
     *
     * @return Array of month name strings
     */
    public String[] getMonthNames() {
        return monthNames;
    }

    /**
     * Adds an appointment for the specified date.
     *
     * @param date Calendar date for the appointment
     * @param appointmentContent Appointment text content
     * @return true if a new appointment was added, false if an existing one was updated
     */
    public boolean addAppointment(Calendar date, String appointmentContent) {
        String dateKey = formatDateKey(date);
        boolean isNew = !appointments.containsKey(dateKey);
        appointments.put(dateKey, appointmentContent);
        return isNew;
    }

    /**
     * Removes an appointment for the specified date.
     *
     * @param date Calendar date to remove appointment from
     * @return true if an appointment was removed, false if none existed
     */
    public boolean removeAppointment(Calendar date) {
        String dateKey = formatDateKey(date);
        return appointments.remove(dateKey) != null;
    }

    /**
     * Gets the appointment for the specified date.
     *
     * @param date Calendar date to get appointment for
     * @return Appointment text or null if none exists
     */
    public String getAppointment(Calendar date) {
        return appointments.get(formatDateKey(date));
    }

    /**
     * Checks if a date has an appointment.
     *
     * @param date Calendar date to check
     * @return true if the date has an appointment
     */
    public boolean hasAppointment(Calendar date) {
        return appointments.containsKey(formatDateKey(date));
    }

    /**
     * Returns a list of upcoming appointments sorted by date
     * @return List of date-appointment entry pairs
     */
    public List<Map.Entry<String, String>> getUpcomingAppointments() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return appointments.entrySet().stream()
                .filter(entry -> {
                    try {
                        // Parse the date from map key
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date appointmentDate = formatter.parse(entry.getKey());
                        Calendar appointmentCal = Calendar.getInstance();
                        appointmentCal.setTime(appointmentDate);

                        // Only include dates that are today or in the future
                        return !appointmentCal.before(today);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .sorted(Map.Entry.comparingByKey())  // Sort by date
                .collect(Collectors.toList());
    }


    /**
     * Calculates the day of the week for the first day of a month.
     *
     * @param year The year
     * @param month The month (0-11)
     * @return Day of week for first day (0-6, where 0 is Sunday)
     */
    public int getFirstDayOfWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1; // Adjust for 0-based index
    }

    /**
     * Gets the number of days in the specified month.
     *
     * @param year The year
     * @param month The month (0-11)
     * @return Number of days in the month
     */
    public int getDaysInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Creates a standardized date key for storing appointments.
     *
     * @param date The calendar date to format
     * @return String in the format yyyy-MM-dd to use as a map key
     */
    public String formatDateKey(Calendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date.getTime());
    }

    /**
     * Formats a date for display in appointments.
     *
     * @param date The calendar date to format
     * @return Formatted date string (e.g., "January 1, 2025")
     */
    public String formatDateForDisplay(Calendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");
        return formatter.format(date.getTime());
    }
}