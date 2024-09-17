package com.zapstore.supplier.infrastructure.supplierui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import com.zapstore.supplier.application.FindSupplierByIdUseCase;
import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;
import com.zapstore.supplier.infrastructure.SupplierRepository;
import com.zapstore.supplier_type.application.FindSupplierTypeByIdUseCase;
import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;
import com.zapstore.supplier_type.infrastructure.SupplierTypeRepository;
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


public class FindSupplierUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelNit, labelSupplier, SupplierName, labelemail, labelCity, labelCountry, labelRegion, labelType, countryBox, regionBox, cityBox, typeBox, email;
    private JTextField Nit;
    private JButton findButton, backButton, newButton;
    private int gobackindicator;

    CountryService countryService = new CountryRepository();
    FindCountryByCodeUseCase findCountryByCodeUseCase = new FindCountryByCodeUseCase(countryService);

    RegionService regionService = new RegionRepository();
    FindRegionByCodeUseCase findRegionByCodeUseCase = new FindRegionByCodeUseCase(regionService);

    CityService cityService = new CityRepository();
    FindCityByCodeUseCase findCityByCodeUseCase = new FindCityByCodeUseCase(cityService);

    SupplierService supplierService = new SupplierRepository();
    FindSupplierByIdUseCase findSupplierByIdUseCase = new FindSupplierByIdUseCase(supplierService);

    SupplierTypeService supplierTypeService = new SupplierTypeRepository();
    FindSupplierTypeByIdUseCase findSupplierTypeByIdUseCase = new FindSupplierTypeByIdUseCase(supplierTypeService);

    public FindSupplierUI(){
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

        title = new JLabel("Add Supplier");
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

        SupplierName = new JLabel();
        SupplierName.setBounds(170, 170, 255, 30);
        SupplierName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        SupplierName.setForeground(new Color(255,242,45));
        SupplierName.setBackground(new Color(10,20,28));
        add(SupplierName);

        labelType = new JLabel("Type: ");
        labelType.setBounds(100, 210, 150, 30);
        labelType.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelType.setForeground(new Color(255,242,45));
        add(labelType);

        typeBox = new JLabel();
        typeBox.setBounds(170, 210, 255, 30);
        typeBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        typeBox.setForeground(new Color(255,242,45));
        typeBox.setBackground(new Color(10,20,28));
        add(typeBox);

        labelCountry = new JLabel("Country: ");
        labelCountry.setBounds(500, 130, 150, 30);
        labelCountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCountry.setForeground(new Color(255,242,45));
        add(labelCountry);

        countryBox = new JLabel();
        countryBox.setBounds(600, 130, 255, 30);
        countryBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        countryBox.setForeground(new Color(255,242,45));
        countryBox.setBackground(new Color(10,20,28));
        add(countryBox);

        labelRegion = new JLabel("Region: ");
        labelRegion.setBounds(500, 170, 150, 30);
        labelRegion.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelRegion.setForeground(new Color(255,242,45));
        add(labelRegion);

        regionBox = new JLabel();
        regionBox.setBounds(600, 170, 255, 30);
        regionBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        regionBox.setForeground(new Color(255,242,45));
        regionBox.setBackground(new Color(10,20,28));
        add(regionBox);;

        labelCity = new JLabel("City: ");
        labelCity.setBounds(530, 210, 150, 30);
        labelCity.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCity.setForeground(new Color(255,242,45));
        add(labelCity);

        cityBox = new JLabel();
        cityBox.setBounds(600, 210, 255, 30);
        cityBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        cityBox.setForeground(new Color(255,242,45));
        cityBox.setBackground(new Color(10,20,28));
        add(cityBox);

        labelemail = new JLabel("Email: ");
        labelemail.setBounds(90, 250, 150, 30);
        labelemail.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelemail.setForeground(new Color(255,242,45));
        add(labelemail);

        email = new JLabel();
        email.setBounds(170, 250, 685, 30);
        email.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        email.setForeground(new Color(255,242,45));
        email.setBackground(new Color(10,20,28));
        add(email);

        backButton = new JButton("Go Back");
        backButton.setBounds(540, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        newButton = new JButton("New");
        newButton.setBounds(400, 300, 120, 30);
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

    public void startFindSupplier(int gobackindicator) {
        FindSupplierUI findUI = new FindSupplierUI();
        findUI.setBounds(0, 0, 1000, 400);
        findUI.setVisible(true);
        findUI.setResizable(false);
        findUI.setLocationRelativeTo(null);
        findUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                String supplierId = Nit.getText().trim().toUpperCase();
                if (supplierId.length()>0){
                    Optional<Supplier> supplier = findSupplierByIdUseCase.findSupplierById(supplierId);
                    if (supplier.isPresent()){
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
                        typeBox.setText(String.valueOf(foundType.get().getId())+"- "+ foundType.get().getName());
                        cityBox.setVisible(true);
                        cityBox.setText(foundCity.get().getCodecity()+" - "+foundCity.get().getNamecity());
                        regionBox.setVisible(true);
                        regionBox.setText(foundRegion.get().getCodereg()+" - "+ foundRegion.get().getNamereg());
                        countryBox.setVisible(true);
                        countryBox.setText(foundCountry.get().getCodeCountry()+" - "+ foundCountry.get().getName());

                        
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

        if(e.getSource()==newButton){
            Nit.setText("");
            SupplierName.setVisible(false);
            SupplierName.setText("");
            email.setVisible(false);
            email.setText("");
            typeBox.setVisible(false);
            typeBox.setText("");
            cityBox.setVisible(false);
            cityBox.setText("");
            regionBox.setVisible(false);
            regionBox.setText("");
            countryBox.setVisible(false);
            countryBox.setText("");

        
        }
        if(e.getSource()==backButton){
            this.setVisible(false);
            SupplierUI supplierUI = new SupplierUI();
            supplierUI.startSupplier(gobackindicator);
        }

    }

    }

