package org.swimming.ui;

import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeUi {
    private final Timetable timetable;
    private final HashMap<String, Learner> learners;
    public static void main(String[] args) {
        Timetable timetable = new Timetable();
        HashMap<String, Learner> learners = new HashMap<>();
        initializeData(learners,timetable);
        new HomeUi(timetable,learners);

    }
    public HomeUi(Timetable timetable,HashMap<String,Learner> learners) {
        this.timetable = timetable;
        this.learners = learners;
        createAndShowUi();

    }
    private static void initializeData(HashMap<String,Learner> learners, Timetable timetable) {
        Coach coach = new Coach("Coach Name", new HashMap<>());

        Lesson lesson1 = new Lesson(1, "4-5pm", "2024-04-15", coach);
        Lesson lesson2 = new Lesson(2, "5-6pm", "2024-04-15", coach);
        Lesson lesson2A = new Lesson(2, "5-6pm", "2024-04-17", coach);
        Lesson lesson3 = new Lesson(3, "5-6pm", "2024-04-17", coach);
        Lesson lesson4 = new Lesson(4, "5-6pm", "2024-04-19", coach);

        timetable.addLesson("2024-04-15", lesson1);
        timetable.addLesson("2024-04-15", lesson2);
        timetable.addLesson("2024-04-17", lesson2A);
        timetable.addLesson("2024-04-17", lesson3);
        timetable.addLesson("2024-04-19", lesson4);

        Learner learner1 = new Learner("Learner 1", "Male", 10, "Emergency Contact 1", 1);
        Learner learner2 = new Learner("Learner 2", "Female", 9, "Emergency Contact 2", 2);

        learners.put(learner1.getName(), learner1);
        learners.put(learner2.getName(), learner2);
    }
    private void createAndShowUi() {
        JFrame frame = new JFrame("Home");
        frame.setSize(1500, 1000);

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


//        addImageOnButton(customerBtn,"src/main/resources/return-customer-icon.png",50,50);
//        addImageOnButton(vehicleBtn,"src/main/resources/car-vehicle-insurance-icon.png",50,50);
//        addImageOnButton(vehicleOwnerBtn,"src/main/resources/owner.png",50,50);
//        addImageOnButton(bookingBtn,"src/main/resources/booking-reservation-icon.png",50,50);
//        addImageOnButton(userBtn,"src/main/resources/employees-users-icon.png",50,50);
//        addImageOnButton(logoutBtn,"src/main/resources/shutdown-icon.png",50,50);

        frame.add(buttonPanel);

        bookBtn.addActionListener(e -> {

            new BookingUi(timetable,learners);
//            frame.dispose();
        });


        cancleBookingBtn.addActionListener((event)->{

            frame.dispose();
//            new CustomerUi();
        });

        attendLessonBtn.addActionListener((event)->{

            frame.dispose();
//            new VehicleUi();

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



        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,200));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static void addImageOnButton(JButton button,String imagePath,int height,int width){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));

    }

}
