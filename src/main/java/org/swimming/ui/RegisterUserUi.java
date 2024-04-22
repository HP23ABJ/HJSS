package org.swimming.ui;
import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;

public class RegisterUserUi {
    private final Timetable timetable;
    private final HashMap<String, Learner> learners;
    private final List<Lesson> lessons;
    HashMap<String, Coach> coaches;
    public RegisterUserUi(Timetable timetable, HashMap<String, Learner> learners, List<Lesson> lessons, HashMap<String, Coach> coaches) {
        this.timetable = timetable;
        this.learners  = learners;
        this.lessons = lessons;
        this.coaches = coaches;
        registerUser(learners);
    }

    private void registerUser(HashMap<String, Learner> learners) {
        JFrame frame = new JFrame();
        frame.setTitle("Register New Learner");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 5, 5));

        JLabel nameLabel = new JLabel("UserName:");
        JTextField nameField = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male","Female"});

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();

        JLabel emergencyContactLabel = new JLabel("Emergency Contact:");
        JTextField emergencyContactField = new JTextField();

        JLabel gradeLevelLabel = new JLabel("Grade Level:");
        JTextField gradeLevelField = new JTextField();

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent e) -> {
            new HomeUi(timetable,learners,lessons,coaches);
            frame.dispose();
        });
        registerButton.addActionListener((ActionEvent e) -> {
            String name = nameField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            int age = Integer.parseInt(ageField.getText());
            String emergencyContact = emergencyContactField.getText();
            int gradeLevel = Integer.parseInt(gradeLevelField.getText());
            int learnersCount=learners.size();
            Boolean status = true;
            for(String learnerName : learners.keySet()){
                if(learnerName.equals(name)){
                    JOptionPane.showMessageDialog(null, name+"This UserName is already registered", "Username Error", JOptionPane.ERROR_MESSAGE);
                    status = false;
                }
            }

            if((age < 4 || age > 11 )){
                JOptionPane.showMessageDialog(null, name+" age is not in our criteria", "Age Error", JOptionPane.ERROR_MESSAGE);
                status = false;
            }else if((gradeLevel < 0) || (gradeLevel > 5)){
                JOptionPane.showMessageDialog(null, "We only have 0-5 grade levels", "Grade Error", JOptionPane.ERROR_MESSAGE);
                status = false;
            }else if(status) {
                Learner newLearner = new Learner();
                newLearner.setId(learnersCount + 1);
                newLearner.setName(name);
                newLearner.setGender(gender);
                newLearner.setAge(age);
                newLearner.setEmergencyContact(emergencyContact);
                newLearner.setGradeLevel(gradeLevel);
                newLearner.setLessonStatus(new HashMap<>());
                newLearner.setLessonReviews(new HashMap<>());
                newLearner.setCoachRatings(new HashMap<>());

                learners.put(newLearner.getName(), newLearner);

                JOptionPane.showMessageDialog(null, newLearner.getName() + " is Registered Succesfully", "Registration Succesfull", JOptionPane.INFORMATION_MESSAGE);

                nameField.setText("");
                ageField.setText("");
                emergencyContactField.setText("");
                gradeLevelField.setText("");
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(genderLabel);
        panel.add(genderComboBox);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(emergencyContactLabel);
        panel.add(emergencyContactField);
        panel.add(gradeLevelLabel);
        panel.add(gradeLevelField);
        panel.add(new JLabel());
        panel.add(registerButton);
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);
    }


}
