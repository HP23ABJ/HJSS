package org.swimming.ui;
import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import org.swimming.service.DateLabelFormatter;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public class CancelBookingUi {
    private final Timetable timetable;
    private final HashMap<String, Learner> learners;
    private final List<Lesson> lessons;
    HashMap<String, Coach> coaches;
    public CancelBookingUi(Timetable timetable, HashMap<String, Learner> learners, List<Lesson> lessons, HashMap<String, Coach> coaches) {
        this.timetable = timetable;
        this.learners  = learners;
        this.lessons = lessons;
        this.coaches=coaches;
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
                displayBookings(selectedLearner);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to find learner with name: " + selectedLearnerName,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(selectButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new HomeUi(timetable,learners,lessons,coaches);
            frame.dispose();
        });
        frame.add(panel, BorderLayout.CENTER);
        frame.add(backButton,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void displayBookings(Learner learner){
        Learner selectedLearner = learner;
        DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
        List<Lesson> bookedLessons;
        if(selectedLearner.getLessonStatus() != null && !(selectedLearner.getLessonStatus().isEmpty())){
            bookedLessons = selectedLearner.getLessonStatus().get("Booked");
        }else{
            bookedLessons = null;
        }
        if( bookedLessons == null || bookedLessons.isEmpty()){
            JOptionPane.showMessageDialog(null, "No Booked Lessons for "+selectedLearner.getName(), "No Lessons", JOptionPane.WARNING_MESSAGE);
            new HomeUi(timetable,learners,lessons,coaches);
        }else{

            JFrame frame = new JFrame("Select Booking");
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 2,10,10));

            JLabel bookingLabel = new JLabel("Select Booking:");
            JComboBox<String> bookingComboBox = new JComboBox<>();
            for (Lesson lesson : bookedLessons) {
                try {
                    bookingComboBox.addItem("Lesson ID: " +lesson.getId()+ " | Date: " +dateLabelFormatter.valueToString(lesson.getDate()));
                } catch (ParseException e) {
                    System.out.println("Error in Cancle Booking Date conversion :)");
                    throw new RuntimeException(e);
                }
            }

            panel.add(bookingLabel);
            panel.add(bookingComboBox);

            JButton selectButton = new JButton("Select");
            selectButton.addActionListener(e -> {
                String selectedBookingString = (String) bookingComboBox.getSelectedItem();
                String[] parts = selectedBookingString.split("\\|");
                String idPart = parts[0].trim();
                String[] idParts = idPart.split(":");
                String lessonIdString = idParts[1].trim();

                new UpdateBookingUi(timetable,selectedLearner,learners,Integer.parseInt(lessonIdString),lessons,coaches);
                frame.dispose();
            });
            panel.add(selectButton);
            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> {
                new CancelBookingUi(timetable,learners,lessons,coaches);
                frame.dispose();
            });
            frame.add(panel, BorderLayout.CENTER);
            frame.add(backButton,BorderLayout.SOUTH);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }
}
