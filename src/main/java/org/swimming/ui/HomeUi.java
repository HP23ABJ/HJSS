package org.swimming.ui;

import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeUi {
    private final Timetable timetable;
    private final HashMap<String, Learner> learners;
    private final List<Lesson> lessons;
    private final HashMap<String,Coach> coaches;

    public static void main(String[] args) {
        Timetable timetable = new Timetable();
        HashMap<String, Learner> learners = new HashMap<>();
        ArrayList<Lesson> lessons = new ArrayList<>();
        HashMap<String,Coach> coaches = new HashMap<>();
        initializeData(learners,timetable,lessons,coaches);
        new HomeUi(timetable,learners,lessons,coaches);

    }
    public HomeUi(Timetable timetable,HashMap<String,Learner> learners, List<Lesson> lessons,HashMap<String,Coach> coaches) {
        this.timetable = timetable;
        this.learners = learners;
        this.lessons = lessons;
        this.coaches = coaches;
        createAndShowUi();

    }

    private static void initializeData(HashMap<String,Learner> learners, Timetable timetable, ArrayList<Lesson> lessons, HashMap<String, Coach> coaches) {
        Coach Alexander = new Coach(1,"Alexander", new HashMap<>(),new HashMap<>());
        Coach Frank = new Coach(2,"Frank", new HashMap<>(),new HashMap<>());
        Coach Anthony = new Coach(3,"Anthony", new HashMap<>(),new HashMap<>());

        Lesson lesson1 = new Lesson(1,1,"4-5pm","Monday", "2024-04-15",4,Alexander);
        Lesson lesson2 = new Lesson(2,2, "5-6pm","Monday", "2024-04-15", 4,Alexander);
        Lesson lesson2A = new Lesson(3,2, "5-6pm","Wednesday", "2024-04-17",4, Frank);
        Lesson lesson3 = new Lesson(4,3, "5-6pm", "Wednesday","2024-04-17",4, Frank);
        Lesson lesson4 = new Lesson(5,4, "5-6pm", "Friday","2024-04-19",4, Anthony);

        lessons.add(lesson1);
        lessons.add(lesson2);
        lessons.add(lesson2A);
        lessons.add(lesson3);
        lessons.add(lesson4);

        timetable.addLesson("2024-04-15", lesson1);
        timetable.addLesson("2024-04-15", lesson2);
        timetable.addLesson("2024-04-17", lesson2A);
        timetable.addLesson("2024-04-17", lesson3);
        timetable.addLesson("2024-04-19", lesson4);

        Learner learner1 = new Learner(1,"Hamza", "Male", 10, "Emergency Contact 1", 1);
        Learner learner2 = new Learner(2,"Manahil", "Female", 9, "Emergency Contact 2", 2);
        Learner learner3 = new Learner(2,"Mariya", "Female", 11, "Emergency Contact 3", 1);
        Learner learner4 = new Learner(2,"Anusha", "Female", 10, "Emergency Contact 4", 1);
        Learner learner5 = new Learner(2,"Senorita", "Female", 5, "Emergency Contact 5", 1);

        learners.put(learner1.getName(), learner1);
        learners.put(learner2.getName(), learner2);
        learners.put(learner3.getName(), learner3);
        learners.put(learner4.getName(), learner4);
        learners.put(learner5.getName(), learner5);

        coaches.put(Alexander.getName(), Alexander);
        coaches.put(Frank.getName(), Frank);
        coaches.put(Anthony.getName(), Anthony);
    }
    private void createAndShowUi() {
        JFrame frame = new JFrame("Home");
        frame.setSize(1000, 500);
        JPanel buttonPanel=new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3,10,10));

        JButton bookBtn=new JButton("Book A Lesson");
        JButton cancleBookingBtn=new JButton("Change/Cancle A Booking");
        JButton attendLessonBtn=new JButton("Attend A Swimming Lesson");
        JButton monthlyLearnerReportBtn=new JButton("Monthy Learner Report");
        JButton monthlyCoachReportBtn=new JButton("Monthly Coach Report");
        JButton registerLearnerBtn = new JButton("Register A new Learner");
        buttonPanel.add(bookBtn);
        buttonPanel.add(cancleBookingBtn);
        buttonPanel.add(attendLessonBtn);
        buttonPanel.add(registerLearnerBtn);
        buttonPanel.add(monthlyLearnerReportBtn);
        buttonPanel.add(monthlyCoachReportBtn);


        frame.add(buttonPanel);
        bookBtn.addActionListener(e -> {
            new viewTimetableUi(timetable,learners,lessons,coaches,null);
            frame.dispose();
        });


        cancleBookingBtn.addActionListener((event)->{
            new CancelBookingUi(timetable,learners,lessons,coaches);
            frame.dispose();
        });

        attendLessonBtn.addActionListener((event)->{
            new AttendClassUI(timetable,learners,lessons,coaches);
            frame.dispose();

        });

        monthlyLearnerReportBtn.addActionListener((event)->{
            new monthlyLearnerReport(timetable,learners,lessons,coaches);
            frame.dispose();
        });

        monthlyCoachReportBtn.addActionListener((event)->{
            new monthlyCoachReport(timetable,learners,lessons,coaches);
            frame.dispose();

        });

        registerLearnerBtn.addActionListener((event)->{
            new RegisterUserUi(timetable,learners,lessons,coaches);
            frame.dispose();
//            new UserUi();

        });

        bookBtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        bookBtn.setBackground(Color.LIGHT_GRAY);
        bookBtn.setForeground(Color.BLACK);
        cancleBookingBtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        cancleBookingBtn.setBackground(Color.LIGHT_GRAY);
        cancleBookingBtn.setForeground(Color.BLACK);
        monthlyLearnerReportBtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        monthlyLearnerReportBtn.setBackground(Color.LIGHT_GRAY);
        monthlyLearnerReportBtn.setForeground(Color.BLACK);
        attendLessonBtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        attendLessonBtn.setBackground(Color.LIGHT_GRAY);
        attendLessonBtn.setForeground(Color.BLACK);
        monthlyCoachReportBtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        monthlyCoachReportBtn.setBackground(Color.LIGHT_GRAY);
        monthlyCoachReportBtn.setForeground(Color.BLACK);
        registerLearnerBtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        registerLearnerBtn.setBackground(Color.LIGHT_GRAY);
        registerLearnerBtn.setForeground(Color.BLACK);
        frame.setBackground(Color.blue);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,200));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
