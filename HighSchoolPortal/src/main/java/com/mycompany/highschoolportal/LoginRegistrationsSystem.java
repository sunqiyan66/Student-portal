/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.highschoolportal;

/**
 *
 * @author ASUS
 */
import java.util.List;

public class LoginRegistrationsSystem {
    private FileHandler fileHandler;
    private List<User> users;
    
    public LoginRegistrationsSystem() {
        fileHandler = new FileHandler();
        users = fileHandler.readUserData();
    }
    
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getStudentEmail().equalsIgnoreCase(email) &&
                    PasswordHasher.verifyPassword(password, user.getPassword())) {
                return user; // Login successful
            }
        }
        return null; // Login failed
    }
    
    public synchronized User register(String email, String matric, String password, String subjects, String clubs) {
        // Reload user data to ensure it's up-to-date
        reloadUserData();
        
        // Check if the user already exists
        for (User user : users) {
            if (user.getStudentEmail().equalsIgnoreCase(email) || user.getMatricNumber().equalsIgnoreCase(matric)) {
                return null; // User already exists
            }
        }
        
        // Create a new user with hashed password
        User newUser = new User();
        newUser.setStudentEmail(email);
        newUser.setMatricNumber(matric);
        newUser.setPassword(PasswordHasher.hashPassword(password)); // Hash the password for new accounts
        newUser.setAcademicSubjects(List.of(subjects.split(","))); // Set subjects
        newUser.setCoCurricularClubs(List.of(clubs.split(","))); // Set clubs
        
        // Add the new user to the list
        users.add(newUser);
        
        // Save the updated user data to the file
        fileHandler.saveUserData(users);
        
        return newUser; // Registration successful
    }
    
    // Reload the user data from the file
    public void reloadUserData() {
        fileHandler.reloadUserData(users);
    }
}