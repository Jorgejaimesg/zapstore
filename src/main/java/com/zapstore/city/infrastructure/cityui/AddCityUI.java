package com.zapstore.city.infrastructure.cityui;

import java.util.List;

import com.zapstore.city.application.CreateCityUseCase;
import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;
import com.zapstore.city.infrastructure.CityRepository;
import com.zapstore.country.application.FindAllCountryUseCase;
import com.zapstore.country.application.FindCountryByNameUseCase;
import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;
import com.zapstore.region.application.FindAllregionUseCase;
import com.zapstore.region.application.FindRegionByCountryUseCase;
import com.zapstore.region.application.FindRegionByNameUseCase;
import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import javax.swing.SwingUtilities;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddCityUI extends JFrame implements ActionListener {
    private JLabel logoImg, title, labelName, labelCountry, labelCode, labelRegion;
    private JButton addButton, backButton;
    private JTextField Name, Code;
    private JComboBox<String> Countrylist, Region;
    private String countryID;
    private int gobackindicator, openPageIndicator;

    RegionService RegionService = new RegionRepository();
    CountryService CountryService = new CountryRepository();
    CityService cityService = new CityRepository();
    CreateCityUseCase createCityUseCase = new CreateCityUseCase(cityService);
    FindAllCountryUseCase findAllCountryUseCase = new FindAllCountryUseCase(CountryService);
    FindCountryByNameUseCase findCountryByNameUseCase = new FindCountryByNameUseCase(CountryService);
    List<Country> countries = findAllCountryUseCase.findAllCountry();
    FindAllregionUseCase findAllregionUseCase = new FindAllregionUseCase(RegionService);
    FindRegionByNameUseCase findRegionByNameUseCase = new FindRegionByNameUseCase(RegionService);
    FindRegionByCountryUseCase findRegionByCountryUseCase = new FindRegionByCountryUseCase(RegionService);

    public AddCityUI(){

    setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add City");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/CityImg.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(60, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add City");
        title.setBounds(250, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 30));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelCode = new JLabel("Code : ");
        labelCode.setBounds(35, 210, 150, 30);
        labelCode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCode.setForeground(new Color(255,242,45));
        add(labelCode);

        Code = new JTextField();
        Code.setBounds(190, 210, 255, 30);
        Code.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Code.setForeground(new Color(255,242,45));
        Code.setBackground(new Color(10,20,28));
        add(Code);

        labelName = new JLabel("Name : ");
        labelName.setBounds(35, 250, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(190, 250, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);

        labelCountry = new JLabel("Country : ");
        labelCountry.setBounds(35, 130, 150, 30);
        labelCountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCountry.setForeground(new Color(255,242,45));
        add(labelCountry);

        Countrylist = new JComboBox<String>();
        Countrylist.setBounds(190, 130, 255, 30);
        Countrylist.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Countrylist.setForeground(new Color(255,242,45));
        Countrylist.setBackground(new Color(10,20,28));
        add(Countrylist);
        Countrylist.addItem("");
        for(Country Countryitem : countries){
            Countrylist.addItem(Countryitem.getName());
        };

        labelRegion = new JLabel("Region : ");
        labelRegion.setBounds(35, 170, 150, 30);
        labelRegion.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelRegion.setForeground(new Color(255,242,45));
        add(labelRegion);

        Region = new JComboBox<String>();
        Region.setBounds(190, 170, 255, 30);
        Region.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Region.setForeground(new Color(255,242,45));
        Region.setBackground(new Color(10,20,28));
        add(Region);
        Countrylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarRegion();
            }
        });


        

        addButton = new JButton("Add");
        addButton.setBounds(125, 300, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }
    public void startAddCity(int open, int goBack) {
        AddCityUI addCityUI = new AddCityUI();
        addCityUI.setBounds(0, 0, 500, 400);
        addCityUI.setVisible(true);
        addCityUI.setResizable(false);
        addCityUI.setLocationRelativeTo(null);
        addCityUI.openPageIndicator=open;
        addCityUI.gobackindicator=goBack;
    }

    private void actualizarRegion() {
        Region.removeAllItems(); 
        String countryName = Countrylist.getSelectedItem().toString();
        Optional<Country> countryFound = findCountryByNameUseCase.findCountryByName(countryName);
        if (countryFound.isPresent()){
        this.countryID =countryFound.get().getCodeCountry();
        List<Region> Regions = findRegionByCountryUseCase.findAllRegionByCountry(countryID);
        for(Region Regionitem : Regions){
            Region.addItem(Regionitem.getNamereg());
        };
    }}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            try {
                String countryName = Countrylist.getSelectedItem().toString();
                Optional<Country> countryFound = findCountryByNameUseCase.findCountryByName(countryName);
                if(countryFound.isPresent()){
                    String RegionName = Region.getSelectedItem().toString();
                    String countryCode = countryFound.get().getCodeCountry();
                    Optional<Region> RegionFound = findRegionByNameUseCase.execute(countryCode, RegionName);
                    if(RegionFound.isPresent()){

                        String RegionID = RegionFound.get().getCodereg();
                        String CityName = Name.getText().trim();
                        String Codecity = Code.getText().trim().toUpperCase();
                        if (CityName.length()>0){
                            City newCity = new City();
                        
                            newCity.setCodecity(Codecity);
                            newCity.setNamecity(CityName);
                            newCity.setCodereg(RegionID);
                            
                            createCityUseCase.execute(newCity);
                        
                        JOptionPane.showMessageDialog(this, "City added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        Name.setText("");
                        Code.setText("");
                        Region.setSelectedItem("");
                        Countrylist.setSelectedItem("");
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid City", "Error", JOptionPane.ERROR_MESSAGE);
        
                        }
                    }


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
                CityUI CityUI = new CityUI();
                CityUI.startCity(openPageIndicator,gobackindicator);
            });
        });
    }

}
}