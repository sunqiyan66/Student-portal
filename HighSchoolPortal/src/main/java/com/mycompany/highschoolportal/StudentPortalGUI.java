/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.highschoolportal;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;

public class StudentPortalGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private DashboardPanel dashboardPanel;
    
    private LoginRegistrationsSystem loginSystem;
    private User currentUser;

    public StudentPortalGUI() {
        // Initialize systems
        loginSystem = new LoginRegistrationsSystem();
        
        // Setup frame
        setTitle("Student Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Setup main panel with card layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Initialize panels
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        dashboardPanel = new DashboardPanel(this);
        
        // Add panels to card layout
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(registerPanel, "REGISTER");
        mainPanel.add(dashboardPanel, "DASHBOARD");
        
        // Add main panel to frame
        add(mainPanel);
        
        // Show login panel first
        cardLayout.show(mainPanel, "LOGIN");
    }
    
    public void showLogin() {
        cardLayout.show(mainPanel, "LOGIN");
    }
    
    public void showRegister() {
        cardLayout.show(mainPanel, "REGISTER");
    }
    
    public void showDashboard() {
        cardLayout.show(mainPanel, "DASHBOARD");
        dashboardPanel.updateUserInfo(currentUser);
    }
    
    public LoginRegistrationsSystem getLoginSystem() {
        return loginSystem;
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
}