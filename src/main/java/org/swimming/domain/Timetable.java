package org.swimming.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ToString
public class Timetable {
    private Map<String, Map<String, Lesson>> timetable;

    public Timetable() {
        this.timetable = new HashMap<>();
    }

    public void addLesson(String day, Lesson lesson) {
        timetable.computeIfAbsent(day, k -> new HashMap<>()).put(lesson.getTime(), lesson);
    }

    public Map<String, Lesson> getLessons(String day) {
        return timetable.getOrDefault(day, new HashMap<>());
    }

    public Map<String, Map<String, Lesson>> getTimetable() {
        return timetable;
    }

    public void setTimetable(Map<String, Map<String, Lesson>> timetable) {
        this.timetable = timetable;
    }

    public Lesson getLessonById(int lessonId) {
        for (Map<String, Lesson> dayLessons : timetable.values()) {
            for (Lesson lesson : dayLessons.values()) {
                if (lesson.getId() == lessonId) {
                    return lesson;
                }
            }
        }
        return null;
    }
}
