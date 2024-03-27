package org.swimming.domain;

import lombok.ToString;

import java.util.List;
import java.util.Map;
@ToString
public class Coach {
    private String name;
    private Map<String, String> lessonsTaught;

    public Coach(String name, Map<String, String> lessonsTaught) {
        this.name = name;
        this.lessonsTaught = lessonsTaught;
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
}
