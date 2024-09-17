package com.zapstore.menu.infraestructure;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class LoandingMenu extends JFrame {
    private JLabel logoImg;

    public LoandingMenu() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Zap Store");
        getContentPane().setBackground(new Color(10, 20, 28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/presentation.gif"));

        logoImg = new JLabel(imagenOriginal);
        logoImg.setBounds(0, 0, 1000, 600);
        add(logoImg);
    }

    public void showLoadingScreen() {
        setBounds(0, 0, 1000, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        startGif();
    }

    private void startGif() {
        try {
            // Espera un tiempo igual a la duración del GIF
            Thread.sleep(2800); // Ajusta el tiempo según la duración del GIF
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
            // Oculta la ventana actual
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.startMainMenu();
                });
            });
        
    }

}
