package com.zapstore.supplier.infrastructure.supplierui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zapstore.supplier.application.FindSupplierByIdUseCase;
import com.zapstore.supplier.application.UpdateSupplierUseCase;
import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;
import com.zapstore.supplier.infrastructure.SupplierRepository;
import com.zapstore.supplier_type.application.FindAllSupplierTypeUseCase;
import com.zapstore.supplier_type.application.FindSupplierTypeByIdUseCase;
import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;
import com.zapstore.supplier_type.infrastructure.SupplierTypeRepository;
import com.zapstore.city.application.FindCityByCodeUseCase;
import com.zapstore.city.application.FindCityByRegionUseCase;
import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;
import com.zapstore.city.infrastructure.CityRepository;
import com.zapstore.country.application.FindAllCountryUseCase;
import com.zapstore.country.application.FindCountryByCodeUseCase;
import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;
import com.zapstore.region.application.FindRegionByCodeUseCase;
import com.zapstore.region.application.FindRegionByCountryUseCase;
import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;

import javax.swing.JOptionPane;

public class UpdateSupplierUI extends JFrame implements ActionListener {
    private JLabel logoImg, title, labelNit, labelSupplier, labelemail, labelCity, labelCountry, labelRegion, labelType;
    private JComboBox<String> countryBox, regionBox, cityBox, typeBox;
    private JTextField Nit, SupplierName, email; 
    private JButton updateButton, findButton, backButton, resetButton;
    private int gobackindicator;

    SupplierService supplierService = new SupplierRepository();
    FindSupplierByIdUseCase findSupplierByIdUseCase = new FindSupplierByIdUseCase(supplierService);
    UpdateSupplierUseCase updateSupplierUseCase = new UpdateSupplierUseCase(supplierService);

    SupplierTypeService customerTypeService = new SupplierTypeRepository();
    FindAllSupplierTypeUseCase findAllSupplierTypeUseCase = new FindAllSupplierTypeUseCase(customerTypeService);
    List<SupplierType> types = findAllSupplierTypeUseCase.findAllSupplierType();
    FindSupplierTypeByIdUseCase findSupplierTypeByIdUseCase = new FindSupplierTypeByIdUseCase(customerTypeService);

    CountryService countryService = new CountryRepository();
    FindCountryByCodeUseCase findCountryByCodeUseCase = new FindCountryByCodeUseCase(countryService);
    FindAllCountryUseCase findAllCountryUseCase = new FindAllCountryUseCase(countryService);
    List<Country> countries = findAllCountryUseCase.findAllCountry();

    RegionService regionService = new RegionRepository();
    FindRegionByCodeUseCase findRegionByCodeUseCase = new FindRegionByCodeUseCase(regionService);
    FindRegionByCountryUseCase findRegionByCountryUseCase = new FindRegionByCountryUseCase(regionService);

    CityService cityService = new CityRepository();
    FindCityByCodeUseCase findCityByCodeUseCase = new FindCityByCodeUseCase(cityService);
    FindCityByRegionUseCase findCityByRegionUseCase = new FindCityByRegionUseCase(cityService);


    public UpdateSupplierUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Supplier");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/supplier.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Supplier");
        title.setBounds(400, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelNit = new JLabel("Nit: ");
        labelNit.setBounds(120, 130, 150, 30);
        labelNit.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelNit.setForeground(new Color(255,242,45));
        add(labelNit);

        Nit = new JTextField();
        Nit.setBounds(170, 130, 230, 30);
        Nit.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Nit.setForeground(new Color(255,242,45));
        Nit.setBackground(new Color(10,20,28));
        add(Nit);

        labelSupplier = new JLabel("Name : ");
        labelSupplier.setBounds(85, 170, 150, 30);
        labelSupplier.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelSupplier.setForeground(new Color(255,242,45));
        add(labelSupplier);

        SupplierName = new JTextField();
        SupplierName.setBounds(170, 170, 255, 30);
        SupplierName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        SupplierName.setForeground(new Color(255,242,45));
        SupplierName.setBackground(new Color(10,20,28));
        SupplierName.setVisible(false);
        add(SupplierName);

        labelType = new JLabel("Type: ");
        labelType.setBounds(100, 210, 150, 30);
        labelType.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelType.setForeground(new Color(255,242,45));
        add(labelType);

        typeBox = new JComboBox<String>();
        typeBox.setBounds(170, 210, 255, 30);
        typeBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        typeBox.setForeground(new Color(255,242,45));
        typeBox.setBackground(new Color(10,20,28));
        typeBox.setVisible(false);
        add(typeBox);
        for(SupplierType type : types){
            typeBox.addItem(type.getId()+" - "+type.getName());
        }

        labelCountry = new JLabel("Country: ");
        labelCountry.setBounds(500, 130, 150, 30);
        labelCountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCountry.setForeground(new Color(255,242,45));
        add(labelCountry);

        countryBox = new JComboBox<String>();
        countryBox.setBounds(600, 130, 255, 30);
        countryBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        countryBox.setForeground(new Color(255,242,45));
        countryBox.setBackground(new Color(10,20,28));
        countryBox.setVisible(false);
        add(countryBox);
        for(Country country : countries){
            countryBox.addItem(country.getCodeCountry()+" - "+country.getName());
        }

        labelRegion = new JLabel("Region: ");
        labelRegion.setBounds(500, 170, 150, 30);
        labelRegion.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelRegion.setForeground(new Color(255,242,45));
        add(labelRegion);

        regionBox = new JComboBox<String>();
        regionBox.setBounds(600, 170, 255, 30);
        regionBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        regionBox.setForeground(new Color(255,242,45));
        regionBox.setBackground(new Color(10,20,28));
        regionBox.setVisible(false);
        add(regionBox);
        countryBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateRegionBox();
            }

        });

        labelCity = new JLabel("City: ");
        labelCity.setBounds(530, 210, 150, 30);
        labelCity.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCity.setForeground(new Color(255,242,45));
        add(labelCity);

        cityBox = new JComboBox<String>();
        cityBox.setBounds(600, 210, 255, 30);
        cityBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        cityBox.setForeground(new Color(255,242,45));
        cityBox.setBackground(new Color(10,20,28));
        cityBox.setVisible(false);
        add(cityBox);
        regionBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateCityBox();
            }

        });

        labelemail = new JLabel("Email: ");
        labelemail.setBounds(90, 250, 150, 30);
        labelemail.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelemail.setForeground(new Color(255,242,45));
        add(labelemail);

        email = new JTextField();
        email.setBounds(170, 250, 685, 30);
        email.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        email.setForeground(new Color(255,242,45));
        email.setBackground(new Color(10,20,28));
        email.setVisible(false);
        add(email);

        backButton = new JButton("Go Back");
        backButton.setBounds(580, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(440, 300, 120, 30);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(300, 300, 120, 30);
        resetButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        resetButton.setForeground(new Color(255,242,45));
        resetButton.setBackground(new Color(10,20,28));
        resetButton.addActionListener(this);
        add(resetButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);


    }

    public void startUpdateSupplier(int gobackindicator) {
        UpdateSupplierUI updateUI = new UpdateSupplierUI();
        updateUI.setBounds(0, 0, 1000, 400);
        updateUI.setVisible(true);
        updateUI.setResizable(false);
        updateUI.setLocationRelativeTo(null);
        updateUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                String supplierId = Nit.getText().trim().toUpperCase();
                if (supplierId.length()>0){
                    Optional<Supplier> supplier = findSupplierByIdUseCase.findSupplierById(supplierId);
                    if (supplier.isPresent()){
                        Nit.setEnabled(false);
                        Supplier foundSupplier = supplier.get();
                        Optional<SupplierType> foundType = findSupplierTypeByIdUseCase.findSupplierTypeById(foundSupplier.getSupplier_type());
                        Optional<City> foundCity = findCityByCodeUseCase.findCityByCode(foundSupplier.getCity_id());
                        Optional<Region> foundRegion = findRegionByCodeUseCase.findRegionByCode(foundCity.get().getCodereg());
                        Optional<Country> foundCountry = findCountryByCodeUseCase.findCountryByCode(foundRegion.get().getCodeCountry());
                        SupplierName.setVisible(true);
                        SupplierName.setText(foundSupplier.getName());
                        email.setVisible(true);
                        email.setText(foundSupplier.getEmail());
                        typeBox.setVisible(true);
                        typeBox.setSelectedItem(String.valueOf(foundType.get().getId())+" - "+ foundType.get().getName());
                        cityBox.setVisible(true);
                        cityBox.setSelectedItem(foundCity.get().getCodecity()+" - "+foundCity.get().getNamecity());
                        regionBox.setVisible(true);
                        regionBox.setSelectedItem(foundRegion.get().getCodereg()+" - "+ foundRegion.get().getNamereg());
                        countryBox.setVisible(true);
                        countryBox.setSelectedItem(foundCountry.get().getCodeCountry()+" - "+ foundCountry.get().getName());

                        
                    } else {
                        JOptionPane.showMessageDialog(this, "The Supplier doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==updateButton){

            try {
                String cityId = TextBeforeDot(cityBox.getSelectedItem().toString()).trim();
                String nit = Nit.getText();
                String supplier = SupplierName.getText();
                String supplierEmail = email.getText();
                int type_id = Integer.parseInt(TextBeforeDot(typeBox.getSelectedItem().toString().trim()).trim());

                if (nit.length()>0 && supplier.length()>0){
                Supplier updatedSupplier = new Supplier();

                updatedSupplier.setCity_id(cityId);
                updatedSupplier.setNit(nit);
                updatedSupplier.setName(supplier);
                updatedSupplier.setEmail(supplierEmail);
                updatedSupplier.setSupplier_type(type_id);

                updateSupplierUseCase.execute(updatedSupplier);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
                resetButton.doClick();
            }}catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Error to update", "Error", JOptionPane.ERROR_MESSAGE);

            }

    }

        if(e.getSource()==backButton){
            this.setVisible(false);
            SupplierUI supplierUI = new SupplierUI();
            supplierUI.startSupplier(gobackindicator);
        }
        if(e.getSource()==resetButton){
            Nit.setText("");
            Nit.setEnabled(true);
            SupplierName.setVisible(false);
            SupplierName.setText("");
            email.setVisible(false);
            email.setText("");
            typeBox.setVisible(false);
            cityBox.setVisible(false);
            regionBox.setVisible(false);
            countryBox.setVisible(false);
        }

        
    }
    private void updateRegionBox(){
        regionBox.removeAllItems();
        try {
            String CountryId = TextBeforeDot(countryBox.getSelectedItem().toString()).trim();
            List<Region> Regions = findRegionByCountryUseCase.findAllRegionByCountry(CountryId);
            for(Region region : Regions){
                regionBox.addItem(region.getCodereg()+ " - "+ region.getNamereg());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void updateCityBox(){
        cityBox.removeAllItems();
        try {
            String RegionId = TextBeforeDot(regionBox.getSelectedItem().toString()).trim();
            List<City> cities = findCityByRegionUseCase.findAllCityByRegion(RegionId);
            for(City city : cities){
                cityBox.addItem(city.getCodecity()+ " - "+ city.getNamecity());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private String TextBeforeDot(String text) {
        // Buscar la posición del primer punto en la cadena
        int position = text.indexOf('-');
        if (position != -1) {
            return text.substring(0, position);
        } else {
            return text;
        }
    }

    }
