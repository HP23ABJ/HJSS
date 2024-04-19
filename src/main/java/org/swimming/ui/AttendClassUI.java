package org.swimming.ui;

import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import org.swimming.service.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendClassUI {
    private final Timetable timetable;
    HashMap<String,Learner> learners;
    List<Lesson> lessons;
    public AttendClassUI(Timetable timetable,HashMap<String, Learner> learners,List<Lesson> lessons) {
        this.learners = learners;
        this.timetable = timetable;
        this.lessons = lessons;
        displayLearnerSelection();
    }
    private void displayLearnerSelection() {
        JFrame frame = new JFrame("Select Learner");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2,10,10));

        JLabel learnerLabel = new JLabel("Select Learner:");
        JComboBox<String> learnerComboBox = new JComboBox<>();

        for (String learnerName : learners.keySet()) {
            learnerComboBox.addItem(learnerName);
        }

        panel.add(learnerLabel);
        panel.add(learnerComboBox);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> {
            String selectedLearnerName = (String) learnerComboBox.getSelectedItem();
            Learner selectedLearner = learners.get(selectedLearnerName);
            if (selectedLearner != null) {
                displayBookings(selectedLearner,frame);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to find learner with name: " + selectedLearnerName,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(selectButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new HomeUi(timetable,learners,lessons);
            frame.dispose();
        });
        frame.add(panel, BorderLayout.CENTER);
        frame.add(backButton,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void displayBookings(Learner selectedLearner, Frame frame) {
        DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
        List<Lesson> lessons = selectedLearner.getLearnersByStatus("Booked",selectedLearner);
        if(lessons != null && !(lessons.isEmpty())) {
            frame.dispose();
            JFrame frame1 = new JFrame("Select Lesson");
            frame1.setSize(400, 200);
            frame1.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 2, 10, 10));

            JLabel bookLessonLabel = new JLabel("Select Booked Lesson to be Attended:");
            JComboBox<String> bookLessonComboBox = new JComboBox<>();

            for (Lesson lesson : lessons) {
                try {
                    bookLessonComboBox.addItem(lesson.getId() + " | " + lesson.getGradeLevel() + " | " + dateLabelFormatter.valueToString(lesson.getDate()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

            panel.add(bookLessonLabel);
            panel.add(bookLessonComboBox);

            JButton selectButton = new JButton("Select");
            selectButton.addActionListener(e -> {
                String selectLesson = (String) bookLessonComboBox.getSelectedItem();
                int indexOfPipe = selectLesson.indexOf("|");
                String lessonId = selectLesson.substring(0, indexOfPipe).trim();
                Lesson selectedLesson = null;
                for(Lesson lesson: lessons){
                    if(lessonId.equals(lessonId)){
                        selectedLesson = lesson;
                    }
                }
                if(selectedLearner != null && selectLesson != null) {
                    frame.dispose();
                    attendClass(selectedLearner, selectedLesson);
                }
            });
            panel.add(selectButton);
            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> {
                new AttendClassUI(timetable, learners,lessons);
                frame1.dispose();
            });
            frame1.add(panel, BorderLayout.CENTER);
            frame1.add(backButton, BorderLayout.SOUTH);
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }else{
            JOptionPane.showMessageDialog(null, "The learner have no Booked Lessons", "Attend A Class failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void attendClass(Learner selectedLearner,Lesson selectedLesson) {
        JFrame frame = new JFrame("Attend Class");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        JTextArea reviewTextArea = new JTextArea(3, 20);
        JLabel reviewLabel = new JLabel("Lesson Review:");
        JComboBox<Integer> ratingComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        JButton selectButton = new JButton("Select");
        JButton backButton = new JButton("Back");

        // Create panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(reviewLabel);
        panel.add(reviewTextArea);
        panel.add(new JLabel("Coach Rating:"));
        panel.add(ratingComboBox);
        panel.add(selectButton);
        panel.add(backButton);

        // Add panel to frame
        frame.add(panel);

        // Add action listeners
        selectButton.addActionListener(e -> {
            String review = reviewTextArea.getText();
            int rating = (int) ratingComboBox.getSelectedItem();

            selectedLearner.getLessonReviews().put(selectedLesson,review);
            selectedLearner.getCoachRatings().put(selectedLesson,rating);
            selectedLearner.removeLesson("Booked",selectedLesson);
            selectedLearner.addLesson("Attended",selectedLesson);
            JOptionPane.showMessageDialog(null, "Lesson Attended successfully!", "lesson Attended Successful", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new HomeUi(timetable,learners,lessons);
        });

        backButton.addActionListener(e -> {
            new AttendClassUI(timetable, learners,lessons);
            frame.dispose();
        });

        // Make the frame visible
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
