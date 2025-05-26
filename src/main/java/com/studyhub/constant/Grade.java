package com.studyhub.constant;

// Enum for assignment grades (A to F)
public enum Grade {
    A, B, C, D, E, F;

    public static Grade fromString(String grade) {
        try {
            return Grade.valueOf(grade.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid grade: " + grade + ". Must be A, B, C, D, E, or F.");
        }
    }
}