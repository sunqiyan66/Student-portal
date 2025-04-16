/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.highschoolportal;

/**
 *
 * @author ASUS
 */


import java.io.*;
import java.util.*;

public class FileHandler {

    public List<User> readUserData() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/UserData.txt"), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Read and trim each field
                String studentEmail = line.trim();

                line = br.readLine().toLowerCase();
                if (line == null) break; // Exit if end of file is reached
                String matricNumber = line.trim();

                line = br.readLine();
                if (line == null) break; // Exit if end of file is reached
                String password = line.trim();

                line = br.readLine();
                if (line == null) break; // Exit if end of file is reached
                List<String> academicSubjects = Arrays.asList(line.trim().split(","));

                line = br.readLine();
                if (line == null) break; // Exit if end of file is reached
                List<String> coCurricularClubs = Arrays.asList(line.trim().split(","));

                // Create a User object and add it to the list
                User user = new User();
                user.setStudentEmail(studentEmail);
                user.setMatricNumber(matricNumber);
                user.setPassword(password);
                user.setAcademicSubjects(academicSubjects);
                user.setCoCurricularClubs(coCurricularClubs);
                users.add(user);

                // Skip any empty lines after each user
                br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    public Map<String, String> readAcademicSubjects() {
        Map<String, String> subjects = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/AcademicSubjects.txt"), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    subjects.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public Map<String, String> readCoCurricularClubs() {
        Map<String, String> clubs = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/ClubSocieties.txt"), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    clubs.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    public List<Activity> readActivitiesLog() {
        List<Activity> activities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/ActivitiesLog.txt"), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Activity activity = new Activity();
                    activity.setMatricNumber(parts[0].trim().toLowerCase());
                    activity.setClubCode(parts[1].trim());
                    activity.setActivityName(parts[2].trim());
                    activity.setActivityLevel(parts[3].trim());
                    activity.setAchievement(parts[4].trim());
                    activities.add(activity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activities;
    }

    public Map<String, List<String>> readStudentPositions() {
        Map<String, List<String>> positions = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/StudentPositions.txt"), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    List<String> positionList = new ArrayList<>();
                    //in positionList index0=Position for Society, index1=Position for Uniform Body, index2=Position for Sports Club
                    positionList.add(parts[1].trim());
                    positionList.add(parts[2].trim());
                    positionList.add(parts[3].trim());
                    //insert the matric number with its corresponding position 
                    positions.put(parts[0].trim().toLowerCase(), positionList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return positions;
    }
    
//    public void reloadUserData(List<User> users) {
//        List<User> updatedUsers = new ArrayList<>();
//        boolean hasChanges = false; // Track if any passwords were hashed
//        
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/UserData.txt"), "UTF-8"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                // Skip empty lines
//                if (line.trim().isEmpty()) {
//                    continue;
//                }
//                
//                // Read and trim each field
//                String studentEmail = line.trim();
//                
//                line = br.readLine();
//                if (line == null) break; // Exit if end of file is reached
//                String matricNumber = line.trim();
//                
//                line = br.readLine();
//                if (line == null) break; // Exit if end of file is reached
//                String password = line.trim();
//                
//                line = br.readLine();
//                if (line == null) break; // Exit if end of file is reached
//                List<String> academicSubjects = Arrays.asList(line.trim().split(","));
//                
//                line = br.readLine();
//                if (line == null) break; // Exit if end of file is reached
//                List<String> coCurricularClubs = Arrays.asList(line.trim().split(","));
//                
//                // Check if the password is already hashed
//                if (!password.startsWith("HASH:")) {
//                    // Hash the password
//                    password = "HASH:" + PasswordHasher.hashPassword(password);
//                    hasChanges = true; // Mark that changes were made
//                }
//                
//                // Create a User object and add it to the list
//                User user = new User();
//                user.setStudentEmail(studentEmail);
//                user.setMatricNumber(matricNumber);
//                user.setPassword(password);
//                user.setAcademicSubjects(academicSubjects);
//                user.setCoCurricularClubs(coCurricularClubs);
//                updatedUsers.add(user);
//                
//                // Skip any empty lines after each user
//                br.readLine();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: " + e.getMessage());
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//            e.printStackTrace();
//        }
//        
//        // Update the users list
//        users.clear();
//        users.addAll(updatedUsers);
//        
//        // If any passwords were hashed, save the updated data back to the file
//        if (hasChanges) {
//            saveUserData(users);
//        }
//    }
    
    public void reloadUserData(List<User> users) {
    List<User> updatedUsers = new ArrayList<>();
    boolean hasChanges = false; // Track if any passwords were hashed

    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/UserData.txt"), "UTF-8"))) {
        String line;
        while ((line = br.readLine()) != null) {
            // Skip empty lines
            if (line.trim().isEmpty()) {
                continue;
            }

            // Read and trim each field
            String studentEmail = line.trim();

            line = br.readLine();
            if (line == null) break; // Exit if end of file is reached
            String matricNumber = line.trim();

            line = br.readLine();
            if (line == null) break; // Exit if end of file is reached
            String password = line.trim();

            line = br.readLine();
            if (line == null) break; // Exit if end of file is reached
            List<String> academicSubjects = Arrays.asList(line.trim().split(","));

            line = br.readLine();
            if (line == null) break; // Exit if end of file is reached
            List<String> coCurricularClubs = Arrays.asList(line.trim().split(","));

            // Check if the password is already hashed
            if (!password.startsWith("HASH:")) {
                // For existing accounts, keep the password as is (unhashed)
                // No changes needed here
            }

            // Create a User object and add it to the list
            User user = new User();
            user.setStudentEmail(studentEmail);
            user.setMatricNumber(matricNumber);
            user.setPassword(password);
            user.setAcademicSubjects(academicSubjects);
            user.setCoCurricularClubs(coCurricularClubs);
            updatedUsers.add(user);

            // Skip any empty lines after each user
            br.readLine();
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
        e.printStackTrace();
    }

    // Update the users list
    users.clear();
    users.addAll(updatedUsers);
}
//    public void saveUserData(List<User> users) {
//        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/UserData.txt"), "UTF-8"))) {
//            for (User user : users) {
//                bw.write(user.getStudentEmail());
//                bw.newLine();
//                bw.write(user.getMatricNumber());
//                bw.newLine();
//                bw.write(user.getPassword()); // Write the hashed password
//                bw.newLine();
//                bw.write(String.join(",", user.getAcademicSubjects()));
//                bw.newLine();
//                bw.write(String.join(",", user.getCoCurricularClubs()));
//                bw.newLine();
//                bw.newLine(); // Add an empty line between users
//            }
//        } catch (IOException e) {
//            System.out.println("Error writing to file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    
    public void saveUserData(List<User> users) {
    try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/UserData.txt"), "UTF-8"))) {
        for (User user : users) {
            bw.write(user.getStudentEmail());
            bw.newLine();
            bw.write(user.getMatricNumber());
            bw.newLine();
            bw.write(user.getPassword()); // Write the password (hashed or unhashed)
            bw.newLine();
            bw.write(String.join(",", user.getAcademicSubjects()));
            bw.newLine();
            bw.write(String.join(",", user.getCoCurricularClubs()));
            bw.newLine();
            bw.newLine(); // Add an empty line between users
        }
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
        e.printStackTrace();
    }
}
}