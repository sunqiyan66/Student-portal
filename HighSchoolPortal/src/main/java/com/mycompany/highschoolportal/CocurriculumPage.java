/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.highschoolportal;

/**
 *
 * @author ASUS
 */
import java.util.*;

public class CocurriculumPage {
    private FileHandler fileHandler;

    public CocurriculumPage() {
        fileHandler = new FileHandler();
    }
    private String getCategory(String clubCode) {
        // Determine the category based on the club code prefix
        if (clubCode.startsWith("P")) {
            return "Societies";
        } else if (clubCode.startsWith("B")) {
            return "Uniform Body";
        } else if (clubCode.startsWith("S")) {
            return "Sports Club";
        } else {
            return "Others"; // Default category for unknown prefixes
        }
    }
    public void displayCocurriculumPage(User user) {
        if (user == null) return;

        Map<String, String> clubs = fileHandler.readCoCurricularClubs();
        Map<String, List<String>> positions = fileHandler.readStudentPositions();
        List<Activity> activities = fileHandler.readActivitiesLog();

        System.out.println("\nCo-curricular Information for " + user.getStudentEmail());
        System.out.println("============================================================================");

        // Display clubs
        System.out.println("Your Cocurricular Clubs:");
        System.out.println("============================================================================");
        for (String clubCode : user.getCoCurricularClubs()) {
            String clubName = clubs.get(clubCode);
            if (clubName != null) {
                String category = getCategory(clubCode); // Determine the category
                System.out.printf("%-14s : %-3s - %s%n",category,clubCode,clubName);
            }
        }

        //Expanding Functionalities on Existing Features,Display positions
        List<String> userPositions = positions.get(user.getMatricNumber().toLowerCase());
        if (userPositions != null) {
            System.out.println("\nPositions Held:");
            System.out.println("Society: " + userPositions.get(0));
            System.out.println("Uniform Body: " + userPositions.get(1));
            System.out.println("Sports Club: " + userPositions.get(2));
        }

        //Expanding Functionalities on Existing Features,Display activities
        System.out.println("\nActivities and Achievements:");
        for (Activity activity : activities) {
            if (activity.getMatricNumber().equalsIgnoreCase(user.getMatricNumber())) {
                System.out.println("- " + activity.getActivityName() + 
                                 " (" + activity.getActivityLevel() + ") - " + 
                                 activity.getAchievement());
            }
        }
    }
}
