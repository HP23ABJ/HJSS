package org.swimming.ui;

import javax.swing.*;
import java.awt.*;

public class HomeUi {

    public static void main(String[] args) {
        new HomeUi();
    }
    public HomeUi() {

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

        bookBtn.addActionListener(e->{
//            new ReportUi();
            frame.dispose();
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
