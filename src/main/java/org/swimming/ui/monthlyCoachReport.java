package org.swimming.ui;

import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class monthlyCoachReport {
    private final Timetable timetable;
    private final HashMap<String, Learner> learners;
    private final List<Lesson> lessons;
    private final HashMap<String, Coach> coaches;
    public monthlyCoachReport(Timetable timetable, HashMap<String, Learner> learners, List<Lesson> lessons, HashMap<String, Coach> coaches) {
        this.timetable = timetable;
        this.learners  = learners;
        this.lessons = lessons;
        this.coaches = coaches;
        showCoachReport();
    }
    public void showCoachReport(){
        JFrame frame = new JFrame("Select Learner");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2,10,10));

        JLabel monthLabel = new JLabel("Select Month:");
        JComboBox<String> monthComboBox;
        monthComboBox = new JComboBox<>(new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"});

        panel.add(monthLabel);
        panel.add(monthComboBox);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> {
            String selectedMonth = (String) monthComboBox.getSelectedItem();
            if (selectedMonth != null) {
                viewReport(selectedMonth);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Kindly select Month", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(selectButton);
        JButton backButton = new JButton("Home");
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

    private void viewReport(String selectedMonth) {
        Map<Coach,List<Integer>> filteredCoachRatings = new HashMap<>();
        for(String key : coaches.keySet()){
            Coach coach = coaches.get(key);
            for (String month :coach.getRatings().keySet()) {
                if(month.equalsIgnoreCase(selectedMonth)){
                    List<Integer> ratings = coach.getRatings().get(month);
                    for (Integer rate:ratings) {
                        if (filteredCoachRatings.containsKey(coach)) {
                            List<Integer> ratingList = filteredCoachRatings.get(coach);
                            ratingList.add(rate);
                        } else {
                            List<Integer> ratingList = new ArrayList<>();
                            ratingList.add(rate);
                            filteredCoachRatings.put(coach, ratingList);
                        }
                    }
                }
            }
        }

        JFrame frame = new JFrame();
        frame.setTitle("Monthly Learner Report Table");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"CoachID", "Coach Name", "Average Ratings"};
        Object[][] data = new Object[filteredCoachRatings.size()][columnNames.length];

        int i = 0;
        if(!(filteredCoachRatings.isEmpty())) {
            for (Coach coach : filteredCoachRatings.keySet()) {
                List<Integer> ratings = filteredCoachRatings.get(coach);
                int sum = 0;
                for (int rating : ratings) {
                    sum += rating;
                }
                double average = (double) sum / ratings.size();
                data[i][0] = coach.getId();
                data[i][1] = coach.getName();
                data[i][2] = average;
                i++;
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
