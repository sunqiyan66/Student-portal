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

public class AcademicPage {
    private FileHandler fileHandler;
    
    public AcademicPage() {
        fileHandler = new FileHandler();
    }
    
    public void displayAcademicPage(User user) {
        if (user == null) return;
        
        //Map<String, String> subjects = fileHandler.readAcademicSubjects();
        Map<String, String> subjectsMap = fileHandler.readAcademicSubjects();
        List<String> enrolledSubjects = user.getAcademicSubjects();
        //new arraylist,initially the subjects arent in ascending order,but after .sort it turn into ascending order
        List<String> sortedSubjects = new ArrayList<>(enrolledSubjects);
        sortedSubjects.sort(Comparator.comparing(subjectsMap::get));
        System.out.println("\nEnrolled subjects for :" + user.getStudentEmail());
        System.out.println("============================================================================");
        System.out.println("Enrolled Subjects:");
        for (String subjectCode : sortedSubjects) {
            System.out.println(subjectCode + ": " + subjectsMap.get(subjectCode));
        }
    }
}
