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
import java.io.*;

public class RegisterPanel extends JPanel {
    private StudentPortalGUI mainFrame;
    private JTextField emailField;
    private JTextField matricField;
    private JPasswordField passwordField;
    private JTextField academicSubjectsField;
    private JTextField clubsField;
    private Image backgroundImage;
    
    public RegisterPanel(StudentPortalGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        setupUI();
        // Load background image
        backgroundImage = new ImageIcon("resources/image/registerbackground.jpg").getImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the background image to cover the entire panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    private void setupUI() {
        // Create form panel with a semi-transparent background
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Add padding
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Consistent spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Load and resize logo
        ImageIcon originalLogoIcon = new ImageIcon("resources/image/logo.png");
        Image originalLogoImage = originalLogoIcon.getImage();
        Image resizedLogoImage = originalLogoImage.getScaledInstance(75, -1, Image.SCALE_SMOOTH); // Set width to 75, height is calculated to maintain aspect ratio
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage);
        
        // Logo
        JLabel logoLabel = new JLabel(resizedLogoIcon);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // Margin below logo
        gbc.anchor = GridBagConstraints.CENTER; // Center the logo
        formPanel.add(logoLabel, gbc);
        
        // Title
        JLabel titleLabel = new JLabel("Student Portal Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 153, 255));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0); // Margin below title
        formPanel.add(titleLabel, gbc);
        
        // Reset to single column width
        gbc.gridwidth = 1;
        
        // Add form fields
        addFormField(formPanel, "Email:", emailField = new JTextField(20), gbc, 2);
        addFormField(formPanel, "Matric Number:", matricField = new JTextField(20), gbc, 3);
        addFormField(formPanel, "Password:", passwordField = new JPasswordField(20), gbc, 4);
        addFormField(formPanel, "Academic Subjects:", academicSubjectsField = new JTextField(20), gbc, 5);
        addFormField(formPanel, "Co-curricular Clubs:", clubsField = new JTextField(20), gbc, 6);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Add horizontal and vertical spacing
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");
        
        registerButton.addActionListener(e -> handleRegister());
        backButton.addActionListener(e -> mainFrame.showLogin());
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0); // Margin above and below buttons
        formPanel.add(buttonPanel, gbc);
        
        // Help text
        JLabel helpText = new JLabel("<html>Enter a valid email,example:s200277@gmail.com<br>"+"Enter subjects and clubs as comma-separated codes<br>"+
                "#No space after each filling of code for clubs and subjects<br>"+"Example subjects: 1103,1119,1223,1249,1449<br>" +"Example clubs: B01,P27,S15</html>");
        helpText.setForeground(Color.GRAY);
        helpText.setFont(new Font("Arial", Font.PLAIN, 12));
        helpText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 0, 0); // No margin for help text
        formPanel.add(helpText, gbc);
        
        // Add formPanel to the main panel
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1;
        mainGbc.weighty = 1;
        mainGbc.anchor = GridBagConstraints.CENTER;
        add(formPanel, mainGbc);
    }
    
    private void addFormField(JPanel panel, String label, JComponent field,
            GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        
        gbc.gridx = 1;
        panel.add(field, gbc);
    }
    
    private void handleRegister() {
        String email = emailField.getText().trim();
        String matric = matricField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String subjects = academicSubjectsField.getText().trim();
        String clubs = clubsField.getText().trim().toUpperCase();
        
        if (!validateInputFormat(subjects, clubs)) {
            ErrorHandler.showError(this, "Invalid format for subjects or clubs.\nPlease use comma-separated codes.");
            return;
        }
        
        if (email.isEmpty() || matric.isEmpty() || password.isEmpty() ||
                subjects.isEmpty() || clubs.isEmpty()) {
            ErrorHandler.showError(this, "All fields are required");
            return;
        }
        User existingUser = mainFrame.getLoginSystem().register(email, matric, password, subjects, clubs);
        if (existingUser == null) {
            ErrorHandler.showError(this, "Email or matric number already exists");
            return;
        } else {
            ErrorHandler.showSuccess(this, "Registration successful! You can now login.");
            clearFields();
            mainFrame.showLogin();
        }
    }
    
    private void clearFields() {
        emailField.setText("");
        matricField.setText("");
        passwordField.setText("");
        academicSubjectsField.setText("");
        clubsField.setText("");
    }
    
    private boolean validateInputFormat(String subjects, String clubs) {
        // Validate that subjects and clubs are in correct format (comma-separated codes)
        String pattern = "^[\\w]+(,[\\w]+)*$";
        return subjects.matches(pattern) && clubs.matches(pattern);
    }
}