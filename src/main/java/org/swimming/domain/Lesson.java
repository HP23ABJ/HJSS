package org.swimming.domain;

import lombok.ToString;
import org.swimming.service.DateLabelFormatter;

import java.text.ParseException;
import java.util.*;

@ToString
public class Lesson {
    private Integer id;
    private Integer gradeLevel;
    private String time;
    private Coach coach;
    private Object date;
    private Map<String, Learner> learners;

    public Lesson(Integer id, Integer gradeLevel, String time, String Date, Coach coach) {
        DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
        this.id = id;
        this.gradeLevel = gradeLevel;
        this.time = time;
        this.coach = coach;
        try {
            this.date = dateLabelFormatter.stringToValue(Date);
        } catch (ParseException e) {
            System.out.println("Problem In date Conversion :)");
            throw new RuntimeException(e);
        }
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean removeLearner(Learner learner) {
        return learners.remove(learner.getName()) != null;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Map<String, Learner> getLearners() {
        return learners;
    }

    public void setLearners(Map<String, Learner> learners) {
        this.learners = learners;
    }

}
