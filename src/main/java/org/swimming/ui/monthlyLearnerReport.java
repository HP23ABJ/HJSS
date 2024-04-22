package org.swimming.ui;

import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import org.swimming.service.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class monthlyLearnerReport {
    private final Timetable timetable;
    private final HashMap<String, Learner> learners;
    private final Learner learner;
    private final List<Lesson> lessons;
    HashMap<String, Coach> coaches;
    public monthlyLearnerReport(Timetable timetable, HashMap<String, Learner> learners, List<Lesson> lessons, HashMap<String, Coach> coaches) {
        this.timetable = timetable;
        this.learners  = learners;
        this.learner = null;
        this.lessons = lessons;
        this.coaches = coaches;
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
        JLabel monthLabel = new JLabel("Select Month:");
        JComboBox<Integer> monthComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5,6,7,8,9,10,11,12});

        for (String learnerName : learners.keySet()) {
            learnerComboBox.addItem(learnerName);
        }

        panel.add(learnerLabel);
        panel.add(learnerComboBox);
        panel.add(monthLabel);
        panel.add(monthComboBox);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> {
            String selectedLearnerName = (String) learnerComboBox.getSelectedItem();
            Learner selectedLearner = learners.get(selectedLearnerName);
            Integer selectedMonth = (Integer) monthComboBox.getSelectedItem();
            if (selectedLearner != null && selectedMonth != null) {
                displayLearnerReport(selectedLearner,selectedMonth);
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

    private void displayLearnerReport(Learner selectedLearner, Integer selectedMonth) {
        Map<String, List<Lesson>> lessonStatus = selectedLearner.getLessonStatus();
        Map<String,List<Lesson>> filteredLessons = new HashMap<>();

        for (String key: lessonStatus.keySet()){
            List<Lesson> lessons1 = lessonStatus.get(key);
            for(Lesson lesson : lessons1){
                if(lesson.getMonth().equals(selectedMonth)){
                    if (filteredLessons.containsKey(key)) {
                        List<Lesson> lessonsList = filteredLessons.get(key);
                        lessonsList.add(lesson);
                    } else {
                        List<Lesson> lessonsList = new ArrayList<>();
                        lessonsList.add(lesson);
                        filteredLessons.put(key, lessonsList);
                    }
                }
            }
        }
        JFrame frame = new JFrame();
        frame.setTitle("Monthly Learner Report Table");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"ID", "Grade Level", "Time", "Day", "Date", "Coach","Status"};
        Object[][] data = new Object[100][columnNames.length];
        DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
        int i = 0;
        // Populate the data array with lesson information
        if(!(filteredLessons.isEmpty())) {
            for (String key : filteredLessons.keySet()) {
                List<Lesson> lessons = filteredLessons.get(key);
                for (Lesson lesson : lessons) {
                    data[i][0] = lesson.getId();
                    data[i][1] = lesson.getGradeLevel();
                    data[i][2] = lesson.getTime();
                    data[i][3] = lesson.getDay();
                    try {
                        data[i][4] = dateLabelFormatter.valueToString(lesson.getDate());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    data[i][5] = lesson.getCoach().getName();
                    data[i][6] = key;
                    i++;
                }
            }
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> {
            new HomeUi(timetable,learners,lessons,coaches);
            frame.dispose();
        });
        frame.getContentPane().add(homeButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
