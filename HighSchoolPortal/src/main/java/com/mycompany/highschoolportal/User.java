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

public class User {
    private String studentEmail;
    private String matricNumber;
    private String password;
    private List<String> academicSubjects;
    private List<String> coCurricularClubs;

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAcademicSubjects() {
        return academicSubjects;
    }

    public void setAcademicSubjects(List<String> academicSubjects) {
        this.academicSubjects = academicSubjects;
    }

    public List<String> getCoCurricularClubs() {
        return coCurricularClubs;
    }

    public void setCoCurricularClubs(List<String> coCurricularClubs) {
        this.coCurricularClubs = coCurricularClubs;
    }
}