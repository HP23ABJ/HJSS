package org.swimming.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Learner {
    private Integer id;
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private Integer gradeLevel;
    private Map<String, List<Lesson>> lessonStatus;
    private Map<Lesson,String> lessonReviews;
    private Map<Lesson,Integer> coachRatings;

    public Learner(Integer id, String name, String gender, int age, String emergencyContact, int gradeLevel) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.gradeLevel = gradeLevel;
        this.lessonStatus= new HashMap<>();
        this.lessonReviews = new HashMap<>();
        this.coachRatings = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    // Method to add entries to the HashMap
    public void addLesson(String key, Lesson lesson) {
        // Check if the key already exists in the map
        if (!lessonStatus.containsKey(key)) {
            // If the key doesn't exist, create a new list and add the lesson
            List<Lesson> lessons = new ArrayList<>();
            lessons.add(lesson);
            lessonStatus.put(key, lessons);
        } else {
            // If the key exists, retrieve the list and add the lesson to it
            List<Lesson> lessons = lessonStatus.get(key);
            lessons.add(lesson);
        }
    }

    // Getter method to access the HashMap from outside
    public Map<String, List<Lesson>> getLessonStatus() {
        return lessonStatus;
    }

    public Map<Lesson, String> getLessonReviews() {
        return lessonReviews;
    }

    public void setLessonReviews(Map<Lesson, String> lessonReviews) {
        this.lessonReviews = lessonReviews;
    }

    public Map<Lesson, Integer> getCoachRatings() {
        return coachRatings;
    }

    public void setCoachRatings(Map<Lesson,Integer> coachRatings) {
        this.coachRatings = coachRatings;
    }

    public Boolean removeLesson(String status, Lesson lessonToRemove) {
        List<Lesson> lessons = lessonStatus.get(status);
        if (lessons != null) {
            return lessons.remove(lessonToRemove);
        }
        return false;
    }

    public List<Lesson> getLearnersByStatus (String status, Learner learner){
            return learner.getLessonStatus().get(status);
    }
}
