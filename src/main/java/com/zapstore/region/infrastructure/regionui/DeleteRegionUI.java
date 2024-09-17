package com.zapstore.region.infrastructure.regionui;

import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;
import com.zapstore.country.application.FindAllCountryUseCase;
import com.zapstore.country.application.FindCountryByNameUseCase;
import com.zapstore.country.domain.entity.Country;

import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;
import com.zapstore.region.application.DeleteRegionByNameUseCase;
import com.zapstore.region.application.FindRegionByCountryUseCase;
import com.zapstore.region.domain.entity.Region;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DeleteRegionUI extends JFrame implements ActionListener {
    RegionService RegionService = new RegionRepository();
    CountryService countryService = new CountryRepository();
    FindCountryByNameUseCase findCountryByNameUseCase = new FindCountryByNameUseCase(countryService);
    FindAllCountryUseCase findAllCountryUseCase = new FindAllCountryUseCase(countryService);
    FindRegionByCountryUseCase findRegionByCountryUseCase = new FindRegionByCountryUseCase(RegionService);
    DeleteRegionByNameUseCase deleteRegionByNameUseCase = new DeleteRegionByNameUseCase(RegionService);
    List<Country> cities = findAllCountryUseCase.findAllCountry();
    

    private JLabel logoImg, title, labelCountry, labelRegion;
    private JButton deleteButton, backButton;
    private JComboBox<String> Country, Region;
    private String countryID;
    private int gobackindicator, openPageIndicator;

    public DeleteRegionUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Region");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/Regions.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(30, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Region");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 33));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelCountry = new JLabel("Country : ");
        labelCountry.setBounds(35, 130, 150, 30);
        labelCountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCountry.setForeground(new Color(255,242,45));
        add(labelCountry);

        Country = new JComboBox<String>();
        Country.setBounds(180, 130, 255, 30);
        Country.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Country.setForeground(new Color(255,242,45));
        Country.setBackground(new Color(10,20,28));
        add(Country);
        Country.addItem("");
        for(Country country : cities){
            Country.addItem(country.getName());
        };

        labelRegion = new JLabel("Region : ");
        labelRegion.setBounds(35, 170, 150, 30);
        labelRegion.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelRegion.setForeground(new Color(255,242,45));
        add(labelRegion);

        Region = new JComboBox<String>();
        Region.setBounds(180, 170, 255, 30);
        Region.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Region.setForeground(new Color(255,242,45));
        Region.setBackground(new Color(10,20,28));
        add(Region);
        Country.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarRegion();
            }
        });


        deleteButton = new JButton("Delete");
        deleteButton.setBounds(125, 230, 120, 30);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 230, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        
    }

    private void actualizarRegion() {
        Region.removeAllItems(); 
        String countryName = Country.getSelectedItem().toString();
        Optional<Country> countryFound = findCountryByNameUseCase.findCountryByName(countryName);
        if (countryFound.isPresent()){
        this.countryID =countryFound.get().getCodeCountry();
        List<Region> Regions = findRegionByCountryUseCase.findAllRegionByCountry(countryID);
        for(Region Regionitem : Regions){
            Region.addItem(Regionitem.getNamereg());
        };
    }}

    
    public void startDeleteRegion(int open, int goBack) {
        DeleteRegionUI deleteRegionUI = new DeleteRegionUI();
        deleteRegionUI.setBounds(0, 0, 500, 350);
        deleteRegionUI.setVisible(true);
        deleteRegionUI.setResizable(false);
        deleteRegionUI.setLocationRelativeTo(null);
        deleteRegionUI.openPageIndicator = open;
        deleteRegionUI.gobackindicator = goBack;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButton){
            try {
            String RegionName = Region.getSelectedItem().toString();
            if (RegionName.length()>0){
            
            
            deleteRegionByNameUseCase.execute(countryID, RegionName);


            JOptionPane.showMessageDialog(this, "Region added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            Country.setSelectedItem("");
            Region.removeAllItems(); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Region.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    if (e.getSource()== backButton){
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            dispose(); // Asegúrate de liberar recursos

            // Abre la nueva ventana 
            SwingUtilities.invokeLater(() -> {
                RegionUI RegionUI = new RegionUI();
                RegionUI.startRegion(openPageIndicator,gobackindicator);
            });
        });
    }
    }
}
