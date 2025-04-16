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

public class LoginPanel extends JPanel {
    private StudentPortalGUI mainFrame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private Image backgroundImage;
    
    public LoginPanel(StudentPortalGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
         // Load background image
        backgroundImage = new ImageIcon("resources/image/background.jpg").getImage();
        
        // Panel with transparent background for components
        JPanel loginBox = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        loginBox.setBackground(new Color(255, 255, 255, 200)); // 200 for Semi-transparent background
        loginBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
//        setBackground(new Color(240, 240, 240));
//        
//        JPanel loginBox = new JPanel(new GridBagLayout());
//        loginBox.setBackground(Color.WHITE);
//        loginBox.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
//            BorderFactory.createEmptyBorder(20, 30, 20, 30)
//        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Load and resize logo
        ImageIcon originalLogoIcon = new ImageIcon("resources/image/logo.png");
        Image originalLogoImage = originalLogoIcon.getImage();
        Image resizedLogoImage = originalLogoImage.getScaledInstance(75, -1, Image.SCALE_SMOOTH); // Set width to 75, height is calculated to maintain aspect ratio
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage);
        
        // Logo
        // gbc.insets = new Insets(top, left, bottom, right);,margin spaces
        JLabel logoLabel = new JLabel(resizedLogoIcon);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 280);
        loginBox.add(logoLabel, gbc);
        
        // Title
        JLabel titleLabel = new JLabel("Student Portal Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 153, 255));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 90, 20, 0);
        loginBox.add(titleLabel, gbc);
        
        // Email field
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.insets = new Insets(5, 5, 5, 5);
        loginBox.add(createLabel("Email:"), gbc);
        
        emailField = createTextField();
        gbc.gridx = 1;
        loginBox.add(emailField, gbc);
        
        // Password field
        gbc.gridx = 0;
        gbc.gridy++;
        loginBox.add(createLabel("Password:"), gbc);
        
        passwordField = createPasswordField();
        gbc.gridx = 1;
        loginBox.add(passwordField, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        
        JButton loginButton = createStyledButton("Login", new Color(51, 153, 255));
        loginButton.addActionListener(e -> handleLogin());
        
        JButton registerButton = createStyledButton("Register", new Color(70, 70, 70));
        registerButton.addActionListener(e -> mainFrame.showRegister());
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        loginBox.add(buttonPanel, gbc);
        
        // Add login box to main panel
        add(loginBox);
    } 
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }
    
    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(200, 30));
        return field;
    }
    
    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setPreferredSize(new Dimension(200, 30));
        return field;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 35));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
    
    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        
        User user = mainFrame.getLoginSystem().login(email, password);
        if (user != null) {
            mainFrame.setCurrentUser(user);
            mainFrame.showDashboard();
            emailField.setText("");
            passwordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this,"Invalid email or password","Login Error",JOptionPane.ERROR_MESSAGE);
        }
    }
} 
