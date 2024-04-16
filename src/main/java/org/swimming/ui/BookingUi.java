package org.swimming.ui;

import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.swimming.service.DateLabelFormatter;

public class BookingUi {
    private final Timetable timetable;
    private final HashMap<String, Learner> learners;
    public BookingUi(Timetable timetable, HashMap<String,Learner> learners) {
        this.timetable = timetable;
        this.learners  = learners;
        displayLearnerSelection();
    }

    private void displayLearnerSelection() {
        JFrame frame = new JFrame("Select Learner");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel learnerLabel = new JLabel("Select Learner:");
        JComboBox<String> learnerComboBox = new JComboBox<>();

        // Populate the combo box with learner names
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
                displayBookingInterface(selectedLearner);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to find learner with name: " + selectedLearnerName,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(selectButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new HomeUi(timetable,learners);
            frame.dispose();
        });
        frame.add(panel, BorderLayout.CENTER);
        frame.add(backButton,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void displayBookingInterface(Learner learner) {
        JFrame frame = new JFrame("Book a Swimming Lesson");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel dayLabel = new JLabel("Select Date:");

        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JLabel gradeLabel = new JLabel("Select Grade Level:");
        JComboBox<String> gradeComboBox = new JComboBox<>(new String[]{"Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5"});

        panel.add(dayLabel);
        panel.add(datePicker);
        panel.add(gradeLabel);
        panel.add(gradeComboBox);

        JButton bookButton = new JButton("Book Lesson");
        bookButton.addActionListener(e -> {
            DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
            String selectedDate =  datePicker.getJFormattedTextField().getText();
            String selectedGrade = (String) gradeComboBox.getSelectedItem();
            int grade = 0;
            if (selectedGrade != null) {
                grade = convertGradeToInteger(selectedGrade);
            }

            bookLesson(selectedDate, grade, learner);
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            displayLearnerSelection();
            frame.dispose();
        });

        panel.add(backButton);
        panel.add(bookButton);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private Integer convertGradeToInteger(String selectedGrade) {
        int grade;
        switch (selectedGrade) {
            case "Grade 1":
                grade = 1;
                break;
            case "Grade 2":
                grade = 2;
                break;
            case "Grade 3":
                grade = 3;
                break;
            case "Grade 4":
                grade = 4;
                break;
            case "Grade 5":
                grade = 5;
                break;
            default:
                grade = -1;
                break;

        }
        return grade;
    }

    private void bookLesson(String selectedDate, int selectedGrade, Learner selectedLearner) {
        Map<String, Lesson> lessons = timetable.getLessons(selectedDate);
        if (lessons.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No lessons available on " + selectedDate, "No Lessons", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Lesson selectedLesson = null;
        for (Lesson lesson : lessons.values()) {
            if (lesson.getGradeLevel().equals(selectedGrade)) {
                selectedLesson = lesson;
                break;
            }
        }

        if (selectedLesson == null) {
            JOptionPane.showMessageDialog(null, "No lessons available for Grade " + selectedGrade + " on " + selectedDate, "No Lessons", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(selectedGrade > selectedLearner.getGradeLevel()+1){
            JOptionPane.showMessageDialog(null, "You are not eligible for grade "+selectedGrade, "Not Eligible", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedLesson.getLearners().size() >= 4) {
            JOptionPane.showMessageDialog(null, "Selected lesson is already full. Please choose another lesson.", "Lesson Full", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Learner learner = selectedLearner;  
        if (learner == null) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve learner information.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean bookingSuccess = selectedLesson.addLearner(learner);
        if (bookingSuccess) {
            JOptionPane.showMessageDialog(null, "Lesson booked successfully!", "Booking Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to book lesson. Please try again later.", "Booking Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Implement this method to get the learner by name from your system
    private Learner getLearnerByName(String learnerName) {
        return learners.get(learnerName);
    }

}
