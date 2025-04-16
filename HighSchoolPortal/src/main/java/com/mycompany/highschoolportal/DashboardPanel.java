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

public class DashboardPanel extends JPanel {
    private StudentPortalGUI mainFrame;
    private JTextArea academicArea;
    private JTextArea cocurricularArea;
    private JTextArea transcriptArea;
    private Image backgroundImage;

    public DashboardPanel(StudentPortalGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        // Add welcome panel at top
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(51, 153, 255));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel welcomeLabel = new JLabel("Welcome to Student Portal");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomePanel.add(welcomeLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> handleLogout());
        welcomePanel.add(logoutButton, BorderLayout.EAST);

        add(welcomePanel, BorderLayout.NORTH);

        // Main content panel with improved styling
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Style the text areas
        academicArea = createStyledTextArea();
        cocurricularArea = createStyledTextArea();
        transcriptArea = createStyledTextArea();

        // Left panel with academic info
        JPanel leftPanel = createStyledPanel("Academic Information", academicArea);

        // Right panel with co-curricular info and transcript
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 15));
        rightPanel.setOpaque(false);

        rightPanel.add(createStyledPanel("Co-curricular Information", cocurricularArea));

        // Transcript panel with generate and email buttons
        JPanel transcriptPanel = createStyledPanel("Transcript", transcriptArea);
        JButton generateButton = new JButton("Generate Transcript");
        generateButton.setBackground(new Color(51, 153, 255));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.addActionListener(e -> generateTranscript());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(generateButton);
        transcriptPanel.add(buttonPanel, BorderLayout.SOUTH);

        rightPanel.add(transcriptPanel);

        contentPanel.add(leftPanel);
        contentPanel.add(rightPanel);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JTextArea createStyledTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setBackground(new Color(250, 250, 250));
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return textArea;
    }

    private JPanel createStyledPanel(String title, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                title,
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.add(new JScrollPane(component), BorderLayout.CENTER);
        return panel;
    }

    public void updateUserInfo(User user) {
        if (user == null) return;

        // Update academic information
        AcademicPage academicPage = new AcademicPage();
        CocurriculumPage cocurriculumPage = new CocurriculumPage();

        // Redirect System.out to capture the output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream academicOutput = new ByteArrayOutputStream();
        ByteArrayOutputStream cocurricularOutput = new ByteArrayOutputStream();

        // Capture academic info
        System.setOut(new PrintStream(academicOutput));
        academicPage.displayAcademicPage(user);
        academicArea.setText(academicOutput.toString());

        // Capture co-curricular info
        System.setOut(new PrintStream(cocurricularOutput));
        cocurriculumPage.displayCocurriculumPage(user);
        cocurricularArea.setText(cocurricularOutput.toString());

        // Restore original System.out
        System.setOut(originalOut);
    }

    private void generateTranscript() {
        User user = mainFrame.getCurrentUser();
        if (user == null) return;

        // Capture transcript generation output
        ByteArrayOutputStream transcriptOutput = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(transcriptOutput));

        CocurriculumMarkCalculator calculator = new CocurriculumMarkCalculator();
        calculator.calculateMarks(user);

        System.setOut(originalOut);
        transcriptArea.setText(transcriptOutput.toString());
    }

    private void handleLogout() {
        mainFrame.setCurrentUser(null);
        mainFrame.showLogin();
        academicArea.setText("");
        cocurricularArea.setText("");
        transcriptArea.setText("");
    }
}
