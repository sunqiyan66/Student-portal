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
import java.awt.Component;

public class ErrorHandler {
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent,
            message,
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
} 