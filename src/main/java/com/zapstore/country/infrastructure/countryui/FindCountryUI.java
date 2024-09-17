package com.zapstore.country.infrastructure.countryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import com.zapstore.country.application.FindCountryByCodeUseCase;
import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;

public class FindCountryUI extends JFrame implements ActionListener{
    private JLabel  labelcode, title, logoImg, labelName, Name;
    private JTextField codecountry;
    private JButton findButton, backButton, newButton;
    private int gobackindicator, openPageIndicator;

    CountryService countryService = new CountryRepository();
    FindCountryByCodeUseCase findCountryByCodeUseCase = new FindCountryByCodeUseCase(countryService);

    public FindCountryUI(){
                setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update Country");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/countryImg.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Country");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codecountry = new JTextField();
        codecountry.setBounds(170, 130, 220, 30);
        codecountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codecountry.setForeground(new Color(255,242,45));
        codecountry.setBackground(new Color(10,20,28));
        add(codecountry);

        labelName = new JLabel("Name : ");
        labelName.setBounds(95, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JLabel();
        Name.setBounds(170, 170, 220, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setVisible(false);
        add(Name);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 240, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        newButton = new JButton("New");
        newButton.setBounds(125, 240, 120, 30);
        newButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        newButton.setForeground(new Color(255,242,45));
        newButton.setBackground(new Color(10,20,28));
        newButton.addActionListener(this);
        add(newButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

    }

    public void startFindCountry(int open, int goBack) {
        FindCountryUI findUI = new FindCountryUI();
        findUI.setBounds(0, 0, 520, 350);
        findUI.setVisible(true);
        findUI.setResizable(false);
        findUI.setLocationRelativeTo(null);
        findUI.openPageIndicator=open;
        findUI.gobackindicator=goBack;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                String countryCode = codecountry.getText().trim().toUpperCase();
                if (countryCode.length()>0){
                    Optional<Country> country = findCountryByCodeUseCase.findCountryByCode(countryCode);
                    if (country.isPresent()){
                        Name.setVisible(true);
                        Name.setText(country.get().getName());
                    } else {
                        JOptionPane.showMessageDialog(this, "The Country doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==newButton){
            Name.setVisible(false);
            Name.setText("");
            codecountry.setText("");
        
        }
        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
    
                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    CountryUI CountryUI = new CountryUI();
                    CountryUI.startCountry(openPageIndicator,gobackindicator);
                });
            });
        }

    }

    }

