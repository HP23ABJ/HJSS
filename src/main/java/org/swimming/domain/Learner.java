package org.swimming.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class Learner {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int gradeLevel;
    private Map<String, Lesson> bookedLessons;
    private Map<String, Lesson> cancelledLessons;
    private Map<String, Lesson> attendedLessons;
    private Map<Lesson, Integer> lessonReviews;

    public Learner(String name, String gender, int age, String emergencyContact, int gradeLevel) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.gradeLevel = gradeLevel;
        this.bookedLessons = new HashMap<>();
        this.cancelledLessons = new HashMap<>();
        this.attendedLessons = new HashMap<>();
        this.lessonReviews = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Map<String, Lesson> getBookedLessons() {
        return bookedLessons;
    }

    public void setBookedLessons(Map<String, Lesson> bookedLessons) {
        this.bookedLessons = bookedLessons;
    }

    public Map<String, Lesson> getCancelledLessons() {
        return cancelledLessons;
    }

    public void setCancelledLessons(Map<String, Lesson> cancelledLessons) {
        this.cancelledLessons = cancelledLessons;
    }

    public Map<String, Lesson> getAttendedLessons() {
        return attendedLessons;
    }

    public void setAttendedLessons(Map<String, Lesson> attendedLessons) {
        this.attendedLessons = attendedLessons;
    }

    public Map<Lesson, Integer> getLessonReviews() {
        return lessonReviews;
    }

    public void setLessonReviews(Map<Lesson, Integer> lessonReviews) {
        this.lessonReviews = lessonReviews;
    }
}
