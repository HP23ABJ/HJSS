package org.swimming.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ToString
public class Lesson {
    private String gradeLevel;
    private String time;
    private String day;
    private Coach coach;
    private Map<String, Learner> learners;

    public Lesson(String gradeLevel, String time, String day, Coach coach) {
        this.gradeLevel = gradeLevel;
        this.time = time;
        this.day = day;
        this.coach = coach;
        this.learners = new HashMap<>();
    }

    // Getters and setters for Lesson attributes

    public boolean addLearner(Learner learner) {
        if (learners.size() < 4) {
            learners.put(learner.getName(), learner);
            return true;
        }
        return false;
    }

    public boolean removeLearner(Learner learner) {
        return learners.remove(learner.getName()) != null;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Map<String, Learner> getLearners() {
        return learners;
    }

    public void setLearners(Map<String, Learner> learners) {
        this.learners = learners;
    }
// Other methods as needed
}
