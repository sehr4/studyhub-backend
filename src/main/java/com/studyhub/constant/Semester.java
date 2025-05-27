package com.studyhub.constant;

import java.time.LocalDate;

// Enum for semesters (Spring and Fall)
public enum Semester {
    SPRING, FALL;

    // Determines the semester based on a date (Spring: Jan-Jun, Fall: Jul-Dec)
    public static Semester fromDate(LocalDate date) {
        int month = date.getMonthValue();
        if (month >= 1 && month <= 6) {
            return SPRING;
        } else {
            return FALL;
        }
    }
}