package org.swimming;

import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Coach coach = new Coach("Coach Name", new HashMap<>());

        // Create lessons
        Lesson lesson1 = new Lesson("Grade 1", "4-5pm", "Monday", coach);
        Lesson lesson2 = new Lesson("Grade 2", "5-6pm", "Monday", coach);
        // Create more lessons as needed

        // Create a timetable and add lessons
        Timetable timetable = new Timetable();
        timetable.addLesson("Monday", lesson1);
        timetable.addLesson("Monday", lesson2);
        // Add lessons for other days as needed

        // Create learners
        Learner learner1 = new Learner("Learner 1", "Male", 10, "Emergency Contact 1", 1);
        Learner learner2 = new Learner("Learner 2", "Female", 9, "Emergency Contact 2", 2);
        // Create more learners as needed

        // Book lessons for learners
        lesson1.addLearner(learner1);
        lesson2.addLearner(learner2);

        System.out.println(lesson1);
        System.out.println(lesson2);
        System.out.println(timetable);
        System.out.println(coach);
        System.out.println(learner1);
        System.out.println(learner2);


    }
}