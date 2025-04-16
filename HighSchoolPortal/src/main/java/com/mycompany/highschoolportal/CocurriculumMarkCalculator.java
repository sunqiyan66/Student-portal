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

public class CocurriculumMarkCalculator {
    private FileHandler fileHandler;
    
    public CocurriculumMarkCalculator() {
        this.fileHandler = new FileHandler();
    }
    public void calculateMarks(User user) {
        List<Activity> activities = fileHandler.readActivitiesLog();
        Map<String, List<String>> positionsMap = fileHandler.readStudentPositions();
        List<String> enrolledClubs = user.getCoCurricularClubs();
        List<Integer> clubMarks = new ArrayList<>();
        System.out.println("Co-curriculum Transcript for " + user.getMatricNumber());
        System.out.println("============================================================================\n");
        
        for (String clubCode : enrolledClubs) {
            int attendanceMarks = 50;
            int positionMarks = 0;
            int activityLevelMarks = 0;
            int achievementMarks = 0;
            
            // Get positions for the user
            List<String> positions = positionsMap.get(user.getMatricNumber());
            String position = getPositionForClub(positions, clubCode);
            positionMarks = calculatePositionMarks(position);
            
            // Get all activities for the club
            List<Activity> clubActivities = getActivitiesForClub(activities, user.getMatricNumber(), clubCode);
            
            // Select the activity with the highest marks for activity level and corresponding achievement
            Activity highestActivity = selectHighestActivity(clubActivities);
            if (highestActivity != null) {
                activityLevelMarks = calculateActivityLevelMarks(highestActivity.getActivityLevel());
                achievementMarks = calculateAchievementMarks(highestActivity.getAchievement());
            }
            
            int totalMarks = attendanceMarks + positionMarks + activityLevelMarks + achievementMarks;
            clubMarks.add(totalMarks);
            
            // Print transcript for the club
            printClubTranscript(clubCode, totalMarks, positionsMap, user, highestActivity,  clubActivities, activityLevelMarks, achievementMarks);
        }
        
        // Calculate and print final marks
        double finalMarks = calculateFinalMarks(clubMarks);
        System.out.print("FINAL MARKS: " + finalMarks + " marks");
    }
    
    private int calculateClubMarks(User user, List<Activity> activities, Map<String, List<String>> positionsMap, String clubCode) {
        int attendanceMarks = 50; // Default attendance marks
        int positionMarks = calculatePositionMarks(getPositionForClub(positionsMap.get(user.getMatricNumber()), clubCode));
        int activityLevelMarks = 0;
        int achievementMarks = 0;
        
        // Get all activities for the club
        List<Activity> clubActivities = getActivitiesForClub(activities, user.getMatricNumber(), clubCode);
        
        // Select the activity with the highest marks
        Activity highestActivity = selectHighestActivity(clubActivities);
        
        // Calculate activity and achievement marks if activities exist
        if (highestActivity != null) {
            activityLevelMarks = calculateActivityLevelMarks(highestActivity.getActivityLevel());
            achievementMarks = calculateAchievementMarks(highestActivity.getAchievement());
        }
        
        // Calculate total marks for the club
        return attendanceMarks + positionMarks + activityLevelMarks + achievementMarks;
    }
    private String getPositionForClub(List<String> positions, String clubCode) {
        // If positions is null, return a default position
        if (positions == null) {
            return "Active Member"; // Default position
        }
        
        // Determine the position based on the club code
        if (clubCode.startsWith("P")) {
            return positions.get(0); // Society position
        } else if (clubCode.startsWith("B")) {
            return positions.get(1); // Uniform Body position
        } else if (clubCode.startsWith("S")) {
            return positions.get(2); // Sports Club position
        } else {
            return "Active Member"; // Default position
        }
    }
    
    private int calculatePositionMarks(String position) {
        switch (position) {
            case "President":
                return 10;
            case "Vice President":
            case "Secretary":
            case "Treasurer":
                return 9;
            case "Vice Secretary":
            case "Vice Treasurer":
                return 8;
            case "Committee":
                return 7;
            default:
                return 6;
        }
    }
    
    private int calculateActivityLevelMarks(String activityLevel) {
        switch (activityLevel) {
            case "International":
                return 20;
            case "National":
                return 15;
            case "State":
                return 12;
            case "School":
                return 10;
            default:
                return 0;
        }
    }
    
    private int calculateAchievementMarks(String achievement) {
        switch (achievement) {
            case "Gold":
                return 20;
            case "Silver":
                return 19;
            case "Bronze":
                return 18;
            default:
                return 0;
        }
    }
    //extra feature(Handling Selection From Multiple Activities)
    private Activity selectHighestActivity(List<Activity> activities) {
        if (activities.isEmpty()) {
            return null;
        }
        
        Activity highestActivity = activities.get(0);
        int highestMarks = calculateActivityLevelMarks(highestActivity.getActivityLevel()) +
                calculateAchievementMarks(highestActivity.getAchievement());
        
        for (Activity activity : activities) {
            int currentMarks = calculateActivityLevelMarks(activity.getActivityLevel()) +
                    calculateAchievementMarks(activity.getAchievement());
            if (currentMarks > highestMarks) {
                highestActivity = activity;
                highestMarks = currentMarks;
            }
        }
        
        return highestActivity;
    }
    private List<Activity> getActivitiesForClub(List<Activity> activities, String matricNumber, String clubCode) {
        List<Activity> clubActivities = new ArrayList<>();
        for (Activity activity : activities) {
            if (activity.getMatricNumber().equals(matricNumber) && activity.getClubCode().equals(clubCode)) {
                clubActivities.add(activity);
            }
        }
        return clubActivities;
    }
    
    private double calculateFinalMarks(List<Integer> clubMarks) {
        if (clubMarks.isEmpty()) {
            return 0.0; // No clubs, return 0
        } else if (clubMarks.size() == 1) {
            return clubMarks.get(0); // Only one club, return its marks
        } else {
            Collections.sort(clubMarks, Collections.reverseOrder()); // Sort in descending order
            return (clubMarks.get(0) + clubMarks.get(1)) / 2.0; // Average of top two clubs
        }
    }
    //whole line of data from ActivitiesLog is defined as a single object
    //detect whether in each object the matric number and club code are equal
    //if equal means the activity is
    private Activity getActivityForClub(List<Activity> activities, String matricNumber, String clubCode) {
        for (Activity activity : activities) {
            if (activity.getMatricNumber().equals(matricNumber) && activity.getClubCode().equals(clubCode)) {
                return activity;
            }
        }
        return null;
    }
    private void printClubTranscript(String clubCode, int totalMarks,
            Map<String, List<String>> positionsMap,
            User user,
            Activity highestActivity,
            List<Activity> clubActivities, // Pass all club activities
            int activityLevelMarks,
            int achievementMarks) {
        Map<String, String> clubs = fileHandler.readCoCurricularClubs();
        String clubName = clubs.get(clubCode);
        System.out.println("[" + clubCode + " - "+clubName+"]");
        System.out.println("Attendance: assume full ------------> 50/50 marks");
        
        // Calculate position and position marks
        String position = getPositionForClub(positionsMap.get(user.getMatricNumber()), clubCode);
        int positionMarks = calculatePositionMarks(position);
        
        System.out.println("Position: " + position + " ------------> " + positionMarks + "/10 marks");
        
        // Print selected activity if it exists
        if (highestActivity != null) {
            System.out.println("Selected Activity: " + highestActivity.getActivityName());
        } else {
            System.out.println("Selected Activity: None");
        }
        
        // Print activity level and achievement marks
        System.out.println("Level of Activities: " + (highestActivity != null ? highestActivity.getActivityLevel() : "None") + " ------------> " + activityLevelMarks + "/20 marks");
        System.out.println("Achievement Level: " + (highestActivity != null ? highestActivity.getAchievement() : "None") + " ------------> " + achievementMarks + "/20 marks");
        //extra feature:Expanding Functionalities on Existing Features
        // Print unselected activities
        System.out.print("Unselected Activities:");
        if (clubActivities != null && !clubActivities.isEmpty()) {
            boolean hasUnselectedActivities = false;
            // Check if there are any unselected activities
            for (Activity activity : clubActivities) {
                if (activity != highestActivity) {
                    hasUnselectedActivities = true;
                    break; // Exit the loop early since we only need to know if there are any unselected activities
                }
            }
            
            // Print a newline only if there are unselected activities
            if (hasUnselectedActivities) {
                System.out.println(); // Print the newline once
            }
            for (Activity activity : clubActivities) {
                if (activity != highestActivity) { // Skip the selected activity
                    System.out.println("  - " + activity.getActivityName() +
                            " (Level: " + activity.getActivityLevel() +
                            ", Achievement: " + activity.getAchievement() + ")");
                    hasUnselectedActivities = true;
                }
            }
            if (!hasUnselectedActivities) {
                System.out.println(" None");
            }
        } else {
            System.out.println(" None");
        }
        
        System.out.println("============================================================================");
        System.out.println("TOTAL: " + totalMarks + "/100 marks");
        System.out.println("============================================================================\n");
    }   
}
