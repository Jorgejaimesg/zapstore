package com.zapstore.city.infrastructure.cityui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zapstore.city.application.FindCityByCodeUseCase;
import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;
import com.zapstore.city.infrastructure.CityRepository;
import com.zapstore.country.application.FindCountryByCodeUseCase;
import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;
import com.zapstore.region.application.FindRegionByCodeUseCase;
import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FindCityUI extends JFrame implements ActionListener{
    private JLabel  labelcode, title, logoImg, labelName, labelRegion, Name, Region, labelCountry, country;
    private JButton NewButton, findButton, backButton;
    private JTextField codecity;
    private int gobackindicator, openPageIndicator;

    CityService CityService = new CityRepository();
    FindCityByCodeUseCase findCityByCodeUseCase = new FindCityByCodeUseCase(CityService);
    RegionService RegionService = new RegionRepository();
    FindRegionByCodeUseCase findRegionByCodeUseCase = new FindRegionByCodeUseCase(RegionService);
    CountryService countryService = new CountryRepository();
    FindCountryByCodeUseCase findCountryByCodeUseCase = new FindCountryByCodeUseCase(countryService);

    public FindCityUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Find City");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/CityImg.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Find City");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(80, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codecity = new JTextField();
        codecity.setBounds(170, 130, 220, 30);
        codecity.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codecity.setForeground(new Color(255,242,45));
        codecity.setBackground(new Color(10,20,28));
        add(codecity);

        labelRegion = new JLabel("Region : ");
        labelRegion.setBounds(80, 210, 150, 30);
        labelRegion.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelRegion.setForeground(new Color(255,242,45));
        add(labelRegion);

        Region = new JLabel();
        Region.setBounds(170, 210, 220, 30);
        Region.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Region.setForeground(new Color(255,242,45));
        Region.setVisible(false);
        add(Region);
        
        labelCountry = new JLabel("Country : ");
        labelCountry.setBounds(80, 170, 150, 30);
        labelCountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCountry.setForeground(new Color(255,242,45));
        add(labelCountry);

        country = new JLabel();
        country.setBounds(170, 170, 220, 30);
        country.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        country.setForeground(new Color(255,242,45));
        country.setVisible(false);
        add(country);

        labelName = new JLabel("Name : ");
        labelName.setBounds(80, 250, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JLabel();
        Name.setBounds(170, 250, 220, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setVisible(false);
        add(Name);

        NewButton = new JButton("New");
        NewButton.setBounds(125, 310, 120, 30);
        NewButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        NewButton.setForeground(new Color(255,242,45));
        NewButton.setBackground(new Color(10,20,28));
        NewButton.addActionListener(this);
        add(NewButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 310, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

    }


    public void startFindCity(int open, int goBack) {
        FindCityUI FindUI = new FindCityUI();
        FindUI.setBounds(0, 0, 520, 400);
        FindUI.setVisible(true);
        FindUI.setResizable(false);
        FindUI.setLocationRelativeTo(null);
        FindUI.openPageIndicator=open;
        FindUI.gobackindicator=goBack;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                String CityCode = codecity.getText().trim().toUpperCase();
                if (CityCode.length()>0){
                    Optional<City> City = findCityByCodeUseCase.findCityByCode(CityCode);
                    if (City.isPresent()){
                        Name.setVisible(true);
                        Name.setText(City.get().getNamecity());
                        Optional<Region> foundRegion = findRegionByCodeUseCase.findRegionByCode(City.get().getCodereg());
                        Region.setVisible(true);
                        Region.setText(foundRegion.get().getNamereg());
                        Optional<Country> foundCountry = findCountryByCodeUseCase.findCountryByCode(foundRegion.get().getCodeCountry());
                        country.setVisible(true);
                        country.setText(foundCountry.get().getName());
                    } else {
                        JOptionPane.showMessageDialog(this, "The City doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }  

        if(e.getSource()==NewButton){
            codecity.setText("");
            Name.setVisible(false);
            Name.setText("");
            Region.setText("");
            Region.setVisible(false);
            country.setText("");
            country.setVisible(false);
        
        }
        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
    
                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    CityUI CityUI = new CityUI();
                    CityUI.startCity(openPageIndicator,gobackindicator);
                });
            });
        }
    }

}
