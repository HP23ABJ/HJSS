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

    public static void main(String[] args) {
        Timetable timetable = new Timetable();
        HashMap<String, Learner> learners = new HashMap<>();
        ArrayList<Lesson> lessons = new ArrayList<>();
        initializeData(learners,timetable,lessons);
        new HomeUi(timetable,learners,lessons);

    }
    public HomeUi(Timetable timetable,HashMap<String,Learner> learners, List<Lesson> lessons) {
        this.timetable = timetable;
        this.learners = learners;
        this.lessons = lessons;
        createAndShowUi();

    }
    private static void initializeData(HashMap<String,Learner> learners, Timetable timetable, ArrayList<Lesson> lessons) {
        Coach coach = new Coach("Coach Name", new HashMap<>());

        Lesson lesson1 = new Lesson(1,1, "4-5pm", "2024-04-15", coach);
        Lesson lesson2 = new Lesson(2,2, "5-6pm", "2024-04-15", coach);
        Lesson lesson2A = new Lesson(3,2, "5-6pm", "2024-04-17", coach);
        Lesson lesson3 = new Lesson(4,3, "5-6pm", "2024-04-17", coach);
        Lesson lesson4 = new Lesson(5,4, "5-6pm", "2024-04-19", coach);

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
        buttonPanel.add(monthlyLearnerReportBtn);
        buttonPanel.add(monthlyCoachReportBtn);
        buttonPanel.add(registerLearnerBtn);


        frame.add(buttonPanel);
        bookBtn.addActionListener(e -> {

            new BookingUi(timetable,learners,lessons);
            frame.dispose();
        });


        cancleBookingBtn.addActionListener((event)->{
            new CancelBookingUi(timetable,learners,lessons);
            frame.dispose();
        });

        attendLessonBtn.addActionListener((event)->{
            new AttendClassUI(timetable,learners,lessons);
            frame.dispose();

        });

        monthlyLearnerReportBtn.addActionListener((event)->{

            frame.dispose();
//            new VehicleOwnerUi();

        });

        monthlyCoachReportBtn.addActionListener((event)->{

            frame.dispose();
//            new BookingUi();

        });

        registerLearnerBtn.addActionListener((event)->{

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
//private void createAndShowUi() {
//    JFrame frame = new JFrame("Home");
//    frame.setSize(1000, 500);
//
//    // Load the image
//    ImageIcon logoIcon = new ImageIcon("src/main/resources/360_F_585864419_kgIYUcDQ0yiLOCo1aRjeu7kRxndcoitz.jpg");
//    JLabel logoLabel = new JLabel(logoIcon);
//
//    JPanel buttonPanel = new JPanel();
//    buttonPanel.setLayout(new GridLayout(3, 3, 10, 10));
//
//    // Add buttons to the button panel
//    JButton bookBtn = new JButton("Book A Lesson");
//    JButton cancelBookingBtn = new JButton("Change/Cancel A Booking");
//    JButton attendLessonBtn = new JButton("Attend A Swimming Lesson");
//    JButton monthlyLearnerReportBtn = new JButton("Monthly Learner Report");
//    JButton monthlyCoachReportBtn = new JButton("Monthly Coach Report");
//    JButton registerLearnerBtn = new JButton("Register A New Learner");
//    buttonPanel.add(bookBtn);
//    buttonPanel.add(cancelBookingBtn);
//    buttonPanel.add(attendLessonBtn);
//    buttonPanel.add(monthlyLearnerReportBtn);
//    buttonPanel.add(monthlyCoachReportBtn);
//    buttonPanel.add(registerLearnerBtn);
//
//    // Add the logo label and button panel to the frame
//    frame.add(logoLabel, BorderLayout.NORTH); // Add the logo label to the top of the frame
//    frame.add(buttonPanel, BorderLayout.CENTER); // Add the button panel to the center of the frame
//
//    // Add action listeners to the buttons
//    bookBtn.addActionListener(e -> {
//        new BookingUi(timetable, learners);
//        frame.dispose();
//    });
//    cancelBookingBtn.addActionListener(e -> {
//        new CancelBookingUi(timetable, learners);
//        frame.dispose();
//    });
//    // Add action listeners for other buttons...
//
//    // Set fonts and colors for the buttons
//    Font buttonFont = new Font("Copperplate Gothic Light", Font.BOLD, 16);
//    Color buttonBackground = Color.LIGHT_GRAY;
//    Color buttonForeground = Color.BLACK;
//    for (Component component : buttonPanel.getComponents()) {
//        if (component instanceof JButton) {
//            JButton button = (JButton) component;
//            button.setFont(buttonFont);
//            button.setBackground(buttonBackground);
//            button.setForeground(buttonForeground);
//        }
//    }
//
//    // Set frame properties
//    frame.setBackground(Color.BLUE);
//    frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 200));
//    frame.setVisible(true);
//    frame.setLocationRelativeTo(null);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//}

    private static void addImageOnButton(JButton button,String imagePath,int height,int width){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));

    }

}
