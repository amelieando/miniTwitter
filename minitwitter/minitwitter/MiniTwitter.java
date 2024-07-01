/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.minitwitter;
import javax.swing.SwingUtilities;

/**
 *
 * @author amelieando
 */
public class MiniTwitter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminControlPanel.getInstance().setVisible(true);
        });
    }
}
