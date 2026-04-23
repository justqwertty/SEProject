/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package me.seproject.mavenproject1;

/**
 *
 * @author iamlu
 */
public class Mavenproject1 {

    public static void main(String[] args) {
        me.seproject.mavenproject1.db.DBConnection.initializeDatabase();
        javax.swing.SwingUtilities.invokeLater(() -> {
            new me.seproject.mavenproject1.boundary.TuitionUI().setVisible(true);
        });
    }
}
