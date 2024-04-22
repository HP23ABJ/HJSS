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
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class viewTimetableUi {
    private final Timetable timetable;
    private final HashMap<String, Learner> learners;
    private final List<Lesson> lessons;
    private final HashMap<String, Coach> coaches;
    private final Learner selectedLearner;
    public viewTimetableUi(Timetable timetable, HashMap<String, Learner> learners, List<Lesson> lessons, HashMap<String, Coach> coaches,Learner learner) {
        this.timetable = timetable;
        this.learners  = learners;
        this.lessons = lessons;
        this.coaches = coaches;
        this.selectedLearner = learner;
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
                viewTimetableBy(selectedLearner);
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
    private void viewTimetableBy(Learner selectedLearner) {
        JFrame frame = new JFrame("Select Timetable View");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2,10,10));

        JLabel viewLabel = new JLabel("Select Timetable View:");
        JComboBox<String> selectionComboBox = new JComboBox<>();

            selectionComboBox.addItem("By Day");
            selectionComboBox.addItem("By Grade");
            selectionComboBox.addItem("By Coach");
            selectionComboBox.addItem("All Lessons");

        panel.add(viewLabel);
        panel.add(selectionComboBox);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> {
            String selectedTimetableView = (String) selectionComboBox.getSelectedItem();
            if(selectedTimetableView != null && selectedTimetableView.equalsIgnoreCase("By Day")){
               viewLessons("Day",selectedLearner);
                frame.dispose();
            }else if (selectedTimetableView != null && selectedTimetableView.equalsIgnoreCase("By Grade")) {
               viewLessons("Grade",selectedLearner);
                frame.dispose();
            } else if (selectedTimetableView != null && selectedTimetableView.equalsIgnoreCase("By Coach")) {
               viewLessons("Coach",selectedLearner);
                frame.dispose();
            }else {
               viewLessons("All",selectedLearner);
                frame.dispose();
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
    private void viewLessons(String selection,Learner selectedLearner) {
        if(selection.equalsIgnoreCase("Day")){
            JFrame frame = new JFrame("Select Timetable View");
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2,10,10));

            JLabel viewLabel = new JLabel("Select Day:");
            JComboBox<String> selectionComboBox = new JComboBox<>(new String[]{"Monday","Wednesday","Friday","Saturday"});

            panel.add(viewLabel);
            panel.add(selectionComboBox);

            JButton selectButton = new JButton("Select");
            selectButton.addActionListener(e -> {
                String selectedDay = (String) selectionComboBox.getSelectedItem();
                viewfilteredLessons(getLessonsByDay(lessons, selectedDay),selectedLearner);
                frame.dispose();
            });

            panel.add(selectButton);

            JButton backButton = new JButton("Back");

            backButton.addActionListener(e -> {
                new viewTimetableUi(timetable,learners,lessons,coaches,selectedLearner);
                frame.dispose();
            });
            frame.add(panel, BorderLayout.CENTER);
            frame.add(backButton,BorderLayout.SOUTH);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }else if(selection.equalsIgnoreCase("Coach")){
            JFrame frame = new JFrame("Select Timetable View");
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2,10,10));

            JLabel viewLabel = new JLabel("Select Coach:");
            JComboBox<String> selectionComboBox = new JComboBox<>();
            selectionComboBox.addItem("Frank");
            selectionComboBox.addItem("Alexander");
            selectionComboBox.addItem("Anthony");

            panel.add(viewLabel);
            panel.add(selectionComboBox);

            JButton selectButton = new JButton("Select");
            selectButton.addActionListener(e -> {
                String selectedCoach = (String) selectionComboBox.getSelectedItem();
                viewfilteredLessons(getLessonsByCoach(lessons, selectedCoach),selectedLearner);
                frame.dispose();
            });

            panel.add(selectButton);

            JButton backButton = new JButton("Back");

            backButton.addActionListener(e -> {
                new viewTimetableUi(timetable,learners,lessons,coaches,selectedLearner);
                frame.dispose();
            });
            frame.add(panel, BorderLayout.CENTER);
            frame.add(backButton,BorderLayout.SOUTH);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else if (selection.equalsIgnoreCase("Grade")) {
            JFrame frame = new JFrame("Select Timetable View");
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2,10,10));

            JLabel viewLabel = new JLabel("Select Grade:");
            JComboBox<Integer> selectionComboBox = new JComboBox<>();
            selectionComboBox.addItem(1);
            selectionComboBox.addItem(2);
            selectionComboBox.addItem(3);
            selectionComboBox.addItem(4);
            selectionComboBox.addItem(5);

            panel.add(viewLabel);
            panel.add(selectionComboBox);

            JButton selectButton = new JButton("Select");
            selectButton.addActionListener(e -> {
                String selectedGrade = String.valueOf((int) selectionComboBox.getSelectedItem());
                viewfilteredLessons(getLessonsByGrade(lessons, Integer.valueOf(selectedGrade)),selectedLearner);
                frame.dispose();
            });

            panel.add(selectButton);

            JButton backButton = new JButton("Back");

            backButton.addActionListener(e -> {
                new viewTimetableUi(timetable,learners,lessons,coaches,selectedLearner);
                frame.dispose();
            });
            frame.add(panel, BorderLayout.CENTER);
            frame.add(backButton,BorderLayout.SOUTH);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }else if (selection.equalsIgnoreCase("All")) {
            viewfilteredLessons(lessons,selectedLearner);
        }
    }
    private void viewfilteredLessons(List<Lesson> filteredLessons,Learner selectedLearner) {
        JFrame frame = new JFrame();
        frame.setTitle("Interactive Table");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"ID", "Grade Level", "Time", "Day", "Date", "Coach", "Learner1", "Learner2", "Learner3", "Learner4"};
        Object[][] data = new Object[filteredLessons.size()][columnNames.length];
        DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
        // Populate the data array with lesson information
        for (int i = 0; i < filteredLessons.size(); i++) {
            Lesson lesson = filteredLessons.get(i);
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
            List<String> keys = new ArrayList<>(lesson.getLearners().keySet());
            if(keys != null && !(keys.isEmpty())) {
                for(int j = 0 ; j<keys.size();j++){
                    if(j == 0){
                        data[i][6] = keys.get(0);
                    }
                    if(j == 1){
                        data[i][7] = keys.get(1);
                    }if(j == 2){
                        data[i][8] = keys.get(2);
                    }if(j == 3){
                        data[i][9] = keys.get(3);
                    }
                }
            }else{
                data[i][6] = "Empty";
                data[i][7] = "Empty";
                data[i][8] = "Empty";
                data[i][9] = "Empty";
            }
        }
        // Create the table
        JTable table = new JTable(data, columnNames);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    // Extract data from the selected row
                    String date = (String) target.getValueAt(row,4);
                    Integer grade = (Integer) target.getValueAt(row,1);
                    bookLesson(date,grade,selectedLearner);
                }
            }
        });

        // Add the table to a scroll pane
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
    private void bookLesson(String selectedDate, int selectedGrade, Learner selectedLearner) {
        Map<String, Lesson> lessons = timetable.getLessons(selectedDate);
        if (lessons.isEmpty()) {
            DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
            try {
                JOptionPane.showMessageDialog(null, "No lessons available on " + dateLabelFormatter.valueToString(selectedDate), "No Lessons", JOptionPane.WARNING_MESSAGE);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        Lesson selectedLesson = null;
        for (Lesson lesson : lessons.values()) {
            if (lesson.getGradeLevel().equals(selectedGrade)|| lesson.getGradeLevel().equals(selectedGrade+1)) {
                selectedLesson = lesson;
                break;
            }
        }

        if (selectedLesson == null) {
            DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
            try {
                JOptionPane.showMessageDialog(null, "No lessons available for Grade " + selectedGrade + " on " + dateLabelFormatter.valueToString(selectedDate), "No Lessons", JOptionPane.WARNING_MESSAGE);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
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

        if(checkLessonStatus(selectedLearner,selectedLesson)){
            DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
            try {
                JOptionPane.showMessageDialog(null, "Lesson for " + dateLabelFormatter.valueToString(selectedLesson.getDate()) + " is already booked", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        boolean bookingSuccess = selectedLesson.addLearner(learner);
        if (bookingSuccess) {
            learner.addLesson("Booked",selectedLesson);
            JOptionPane.showMessageDialog(null, "Lesson booked successfully!", "Booking Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to book lesson. Please try again later.", "Booking Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Lesson> getLessonsByDay(List<Lesson> lessons,String Day){
        List<Lesson> filteredLessons = new ArrayList<>();
        for(Lesson lesson : lessons){
            if(lesson.getDay().equalsIgnoreCase(Day)){
                filteredLessons.add(lesson);
            }
        }
        return filteredLessons;
    }
    public List<Lesson> getLessonsByCoach(List<Lesson> lessons,String coachName){
        ArrayList<Lesson> filteredLessons = new ArrayList<>();
        for(Lesson lesson : lessons){
            if(lesson.getCoach().getName().equalsIgnoreCase(coachName)){
                filteredLessons.add(lesson);
            }
        }
        return filteredLessons;
    }
    public List<Lesson> getLessonsByGrade(List<Lesson> lessons,Integer grade){
        ArrayList<Lesson> filteredLessons = new ArrayList<>();
        for(Lesson lesson : lessons){
            if(lesson.getGradeLevel().equals(grade)){
                filteredLessons.add(lesson);
            }
        }
        return filteredLessons;
    }
    public boolean checkLessonStatus(Learner selectedLearner, Lesson selectedLesson) {
        // Retrieve the list of lessons for the selected date
        List<Lesson> lessons = selectedLearner.getLessonStatus().get("Booked");
        if (lessons != null) {
            // Iterate over the lessons
            for (Lesson lesson : lessons) {
                // Check if the lesson is already booked
                if (selectedLesson.getId().equals(lesson.getId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
