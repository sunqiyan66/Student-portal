/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.highschoolportal;

/**
 *
 * @author ASUS
 */

import javax.swing.SwingUtilities;

public class highschoolportal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentPortalGUI().setVisible(true);
        });
    }
    
}