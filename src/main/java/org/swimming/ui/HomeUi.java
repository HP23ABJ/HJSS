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


    private void createAndShowUi() {
        JFrame frame = new JFrame("Home");
        frame.setSize(1000, 500);
        JPanel buttonPanel=new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3,10,10));

        JButton bookBtn=new JButton("Book A Lesson");
        JButton cancelBookingBtn=new JButton("Change/Cancle A Booking");
        JButton attendLessonBtn=new JButton("Attend A Swimming Lesson");
        JButton monthlyLearnerReportBtn=new JButton("Monthy Learner Report");
        JButton monthlyCoachReportBtn=new JButton("Monthly Coach Report");
        JButton registerLearnerBtn = new JButton("Register A new Learner");
        buttonPanel.add(bookBtn);
        buttonPanel.add(cancelBookingBtn);
        buttonPanel.add(attendLessonBtn);
        buttonPanel.add(registerLearnerBtn);
        buttonPanel.add(monthlyLearnerReportBtn);
        buttonPanel.add(monthlyCoachReportBtn);


        frame.add(buttonPanel);
        bookBtn.addActionListener(e -> {
            new viewTimetableUi(timetable,learners,lessons,coaches,null);
            frame.dispose();
        });


        cancelBookingBtn.addActionListener((event)->{
            new CancelBookingUi(timetable,learners,lessons,coaches);
            frame.dispose();
        });

        attendLessonBtn.addActionListener((event)->{
            new AttendClassUI(timetable,learners,lessons,coaches);
            frame.dispose();

        });

        monthlyLearnerReportBtn.addActionListener((event)->{
            new MonthlyLearnerReport(timetable,learners,lessons,coaches);
            frame.dispose();
        });

        monthlyCoachReportBtn.addActionListener((event)->{
            new MonthlyCoachReport(timetable,learners,lessons,coaches);
            frame.dispose();

        });

        registerLearnerBtn.addActionListener((event)->{
            new RegisterUserUi(timetable,learners,lessons,coaches);
            frame.dispose();

        });

        bookBtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        bookBtn.setBackground(Color.LIGHT_GRAY);
        bookBtn.setForeground(Color.BLACK);
        cancelBookingBtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
        cancelBookingBtn.setBackground(Color.LIGHT_GRAY);
        cancelBookingBtn.setForeground(Color.BLACK);
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

    public static void initializeData(HashMap<String, Learner> learners, Timetable timetable, ArrayList<Lesson> lessons, HashMap<String, Coach> coaches) {
        Coach Alexander = new Coach(1,"Alexander", new HashMap<>(),new HashMap<>());
        Coach Frank = new Coach(2,"Frank", new HashMap<>(),new HashMap<>());
        Coach Anthony = new Coach(3,"Anthony", new HashMap<>(),new HashMap<>());
        Coach Andrew = new Coach(4,"Andrew", new HashMap<>(),new HashMap<>());
        Coach Samantha = new Coach(5,"Samantha", new HashMap<>(),new HashMap<>());

        Lesson lesson1 = new Lesson(1,1,"4-5pm","Monday", "2024-04-15",4,Alexander);
        Lesson lesson2 = new Lesson(2,1, "5-6pm","Monday", "2024-04-15", 4,Alexander);
        Lesson lesson3 = new Lesson(3,1, "6-7pm","Monday", "2024-04-15", 4,Alexander);
        Lesson lesson4 = new Lesson(4,2, "4-5pm","Wednesday", "2024-04-17",4, Frank);
        Lesson lesson5 = new Lesson(5,2, "5-6pm", "Wednesday","2024-04-17",4, Frank);
        Lesson lesson6 = new Lesson(6,2, "6-7pm", "Wednesday","2024-04-17",4, Frank);
        Lesson lesson7 = new Lesson(7,3, "4-5pm", "Friday","2024-04-19",4, Anthony);
        Lesson lesson8 = new Lesson(8,3, "5-6pm", "Friday","2024-04-19",4, Anthony);
        Lesson lesson9 = new Lesson(9,3, "6-7pm", "Friday","2024-04-19",4, Anthony);
        Lesson lesson10 = new Lesson(10,4, "2-3pm", "Saturday","2024-04-20",4, Anthony);
        Lesson lesson11 = new Lesson(11,4, "3-4pm", "Saturday","2024-04-20",4, Anthony);
        Lesson lesson12 = new Lesson(12,5,"4-5pm","Monday", "2024-04-22",4,Andrew);
        Lesson lesson13 = new Lesson(13,5, "5-6pm","Monday", "2024-04-22", 4,Andrew);
        Lesson lesson14 = new Lesson(14,5, "6-7pm","Monday", "2024-04-22", 4,Andrew);
        Lesson lesson15 = new Lesson(15,1, "4-5pm","Wednesday", "2024-04-24",4, Samantha);
        Lesson lesson16 = new Lesson(16,1, "5-6pm", "Wednesday","2024-04-24",4, Samantha);
        Lesson lesson17 = new Lesson(17,1, "6-7pm", "Wednesday","2024-04-24",4, Samantha);
        Lesson lesson18 = new Lesson(18,3, "4-5pm", "Friday","2024-04-26",4, Anthony);
        Lesson lesson19 = new Lesson(19,3, "5-6pm", "Friday","2024-04-26",4, Anthony);
        Lesson lesson20 = new Lesson(20,3, "6-7pm", "Friday","2024-04-26",4, Anthony);
        Lesson lesson21 = new Lesson(21,4, "2-3pm", "Saturday","2024-04-27",4, Anthony);
        Lesson lesson22 = new Lesson(22,4, "3-4pm", "Saturday","2024-04-27",4, Anthony);
        Lesson lesson23 = new Lesson(23,5,"4-5pm","Monday", "2024-04-29",4,Andrew);
        Lesson lesson24 = new Lesson(24,5, "5-6pm","Monday", "2024-04-29", 4,Andrew);
        Lesson lesson25 = new Lesson(25,5, "6-7pm","Monday", "2024-04-29", 4,Andrew);
        Lesson lesson26 = new Lesson(26,1, "4-5pm","Wednesday", "2024-05-01",5, Samantha);
        Lesson lesson27 = new Lesson(27,1, "5-6pm", "Wednesday","2024-05-01",5, Samantha);
        Lesson lesson28 = new Lesson(28,1, "6-7pm", "Wednesday","2024-05-01",5, Samantha);
        Lesson lesson29 = new Lesson(29,1,"4-5pm","Monday", "2024-05-06",5,Alexander);
        Lesson lesson30 = new Lesson(30,1, "5-6pm","Monday", "2024-05-06", 5,Alexander);
        Lesson lesson31 = new Lesson(31,1, "6-7pm","Monday", "2024-05-06", 5,Alexander);
        Lesson lesson32 = new Lesson(32,2, "4-5pm","Wednesday", "2024-05-08",5, Frank);
        Lesson lesson33 = new Lesson(33,2, "5-6pm", "Wednesday","2024-05-08",5, Frank);
        Lesson lesson34 = new Lesson(34,2, "6-7pm", "Wednesday","2024-05-08",5, Frank);
        Lesson lesson35 = new Lesson(35,3, "4-5pm", "Friday","2024-05-10",5, Anthony);
        Lesson lesson36 = new Lesson(36,3, "5-6pm", "Friday","2024-05-10",5, Anthony);
        Lesson lesson37 = new Lesson(37,3, "6-7pm", "Friday","2024-05-10",5, Anthony);
        Lesson lesson38 = new Lesson(38,4, "2-3pm", "Saturday","2024-05-11",5, Anthony);
        Lesson lesson39 = new Lesson(39,4, "3-4pm", "Saturday","2024-05-12",5, Anthony);
        Lesson lesson40 = new Lesson(40,3, "4-5pm", "Friday","2024-05-17",5, Anthony);
        Lesson lesson41 = new Lesson(41,3, "5-6pm", "Friday","2024-05-17",5, Anthony);
        Lesson lesson42 = new Lesson(42,3, "6-7pm", "Friday","2024-05-17",5, Anthony);
        Lesson lesson43 = new Lesson(43,4, "2-3pm", "Saturday","2024-05-18",5, Anthony);
        Lesson lesson44 = new Lesson(44,4, "3-4pm", "Saturday","2024-05-18",5, Anthony);



        lessons.add(lesson1);
        lessons.add(lesson2);
        lessons.add(lesson3);
        lessons.add(lesson4);
        lessons.add(lesson5);
        lessons.add(lesson6);
        lessons.add(lesson7);
        lessons.add(lesson8);
        lessons.add(lesson9);
        lessons.add(lesson10);
        lessons.add(lesson11);
        lessons.add(lesson12);
        lessons.add(lesson13);
        lessons.add(lesson14);
        lessons.add(lesson15);
        lessons.add(lesson16);
        lessons.add(lesson17);
        lessons.add(lesson18);
        lessons.add(lesson19);
        lessons.add(lesson20);
        lessons.add(lesson21);
        lessons.add(lesson22);
        lessons.add(lesson23);
        lessons.add(lesson24);
        lessons.add(lesson25);
        lessons.add(lesson26);
        lessons.add(lesson27);
        lessons.add(lesson28);
        lessons.add(lesson29);
        lessons.add(lesson30);
        lessons.add(lesson31);
        lessons.add(lesson32);
        lessons.add(lesson33);
        lessons.add(lesson34);
        lessons.add(lesson35);
        lessons.add(lesson36);
        lessons.add(lesson37);
        lessons.add(lesson38);
        lessons.add(lesson39);
        lessons.add(lesson40);
        lessons.add(lesson41);
        lessons.add(lesson42);
        lessons.add(lesson43);
        lessons.add(lesson44);

        timetable.addLesson("2024-04-15", lesson1);
        timetable.addLesson("2024-04-15", lesson2);
        timetable.addLesson("2024-04-15", lesson3);
        timetable.addLesson("2024-04-17", lesson4);
        timetable.addLesson("2024-04-17", lesson5);
        timetable.addLesson("2024-04-17", lesson6);
        timetable.addLesson("2024-04-19", lesson7);
        timetable.addLesson("2024-04-19", lesson8);
        timetable.addLesson("2024-04-19", lesson9);
        timetable.addLesson("2024-04-20", lesson10);
        timetable.addLesson("2024-04-20", lesson11);
        timetable.addLesson("2024-04-22", lesson12);
        timetable.addLesson("2024-04-22", lesson13);
        timetable.addLesson("2024-04-22", lesson14);
        timetable.addLesson("2024-04-24", lesson15);
        timetable.addLesson("2024-04-24", lesson16);
        timetable.addLesson("2024-04-24", lesson17);
        timetable.addLesson("2024-04-26", lesson18);
        timetable.addLesson("2024-04-26", lesson19);
        timetable.addLesson("2024-04-26", lesson20);
        timetable.addLesson("2024-04-27", lesson21);
        timetable.addLesson("2024-04-27", lesson22);
        timetable.addLesson("2024-04-29", lesson23);
        timetable.addLesson("2024-04-29", lesson24);
        timetable.addLesson("2024-04-29", lesson25);
        timetable.addLesson("2024-05-01", lesson26);
        timetable.addLesson("2024-05-01", lesson27);
        timetable.addLesson("2024-05-01", lesson28);
        timetable.addLesson("2024-05-06", lesson29);
        timetable.addLesson("2024-05-06", lesson30);
        timetable.addLesson("2024-05-06", lesson31);
        timetable.addLesson("2024-05-08", lesson32);
        timetable.addLesson("2024-05-08", lesson33);
        timetable.addLesson("2024-05-08", lesson34);
        timetable.addLesson("2024-05-10", lesson35);
        timetable.addLesson("2024-05-10", lesson36);
        timetable.addLesson("2024-05-10", lesson37);
        timetable.addLesson("2024-05-11", lesson38);
        timetable.addLesson("2024-05-12", lesson39);
        timetable.addLesson("2024-05-17", lesson40);
        timetable.addLesson("2024-05-17", lesson41);
        timetable.addLesson("2024-05-17", lesson42);
        timetable.addLesson("2024-05-18", lesson43);
        timetable.addLesson("2024-05-18", lesson44);

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
}
