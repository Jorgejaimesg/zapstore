package com.zapstore.country.infrastructure.countryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.zapstore.country.application.CreateCountryUseCase;
import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;

public class AddCountryUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelName, labelcode;
    private JButton addButton, backButton;
    private JTextField Name, codecountry;
    private int gobackindicator, openPageIndicator;

    public AddCountryUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Country");
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
        codecountry.setBounds(170, 130, 255, 30);
        codecountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codecountry.setForeground(new Color(255,242,45));
        codecountry.setBackground(new Color(10,20,28));
        add(codecountry);

        labelName = new JLabel("Name : ");
        labelName.setBounds(95, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 170, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);


        addButton = new JButton("Add");
        addButton.setBounds(125, 240, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 240, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startAddCountry(int open, int goBack) {
        AddCountryUI addUI = new AddCountryUI();
        addUI.setBounds(0, 0, 500, 350);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.openPageIndicator=open;
        addUI.gobackindicator=goBack;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                String countryName = Name.getText().trim();
                String countryCode = codecountry.getText().trim().toUpperCase();
                if (countryName.length()>0 && countryCode.length()>0){
                Country newCountry = new Country();
                
                newCountry.setName(countryName);
                newCountry.setCodeCountry(countryCode);
                
                CountryService countryService = new CountryRepository();
                CreateCountryUseCase createCountryUseCase = new CreateCountryUseCase(countryService);
                createCountryUseCase.execute(newCountry);
                JOptionPane.showMessageDialog(this, "Country added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setText("");
                codecountry.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            dispose(); // Asegúrate de liberar recursos

            // Abre la nueva ventana 
            SwingUtilities.invokeLater(() -> {
                CountryUI CountryUI = new CountryUI();
                CountryUI.startCountry(openPageIndicator, gobackindicator);
            });
        });
    }
    }

    }

