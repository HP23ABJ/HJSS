package org.swimming.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ToString
public class Coach {
    private Integer id;
    private String name;
    private Map<String, String> lessonsTaught;
    private HashMap<String,List<Integer>> ratings;

    public Coach(Integer id,String name, Map<String, String> lessonsTaught,HashMap<String,List<Integer>> ratings) {
        this.id = id;
        this.name = name;
        this.lessonsTaught = lessonsTaught;
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getLessonsTaught() {
        return lessonsTaught;
    }

    public void setLessonsTaught(Map<String, String> lessonsTaught) {
        this.lessonsTaught = lessonsTaught;
    }

    public HashMap<String,List<Integer>> getRatings() {
        return ratings;
    }

    public void setRatings(HashMap<String,List<Integer>> ratings) {
        this.ratings = ratings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addRating(Coach coach, int rating, String month) {

        if (coach.getRatings().containsKey(month)) {
            List<Integer> ratings = coach.getRatings().get(month);
            ratings.add(rating);
        } else {
            List<Integer> ratings = new ArrayList<>();
            ratings.add(rating);
            coach.getRatings().put(month, ratings);
        }
    }
}
