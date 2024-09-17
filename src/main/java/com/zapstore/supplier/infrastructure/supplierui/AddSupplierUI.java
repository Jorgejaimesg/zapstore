package com.zapstore.supplier.infrastructure.supplierui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.zapstore.city.application.FindCityByRegionUseCase;
import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;
import com.zapstore.city.infrastructure.CityRepository;
import com.zapstore.country.application.FindAllCountryUseCase;
import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;
import com.zapstore.supplier_type.application.FindAllSupplierTypeUseCase;
import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;
import com.zapstore.supplier_type.infrastructure.SupplierTypeRepository;
import com.zapstore.supplier.application.CreateSupplierUseCase;
import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;
import com.zapstore.supplier.infrastructure.SupplierRepository;
import com.zapstore.region.application.FindRegionByCountryUseCase;
import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;
import com.zapstore.country.domain.entity.Country;

public class AddSupplierUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelNit, labelSupplier, labelemail, labelCity, labelCountry, labelRegion, labelType;
    private JButton addButton, backButton;
    private JComboBox<String> countryBox, regionBox, cityBox, typeBox;
    private JTextField Nit, Supplier, email; 
    private int gobackindicator;

    SupplierTypeService customerTypeService = new SupplierTypeRepository();
    FindAllSupplierTypeUseCase findAllSupplierTypeUseCase = new FindAllSupplierTypeUseCase(customerTypeService);
    List<SupplierType> types = findAllSupplierTypeUseCase.findAllSupplierType();

    CountryService countryService = new CountryRepository();
    FindAllCountryUseCase findAllCountryUseCase = new FindAllCountryUseCase(countryService);
    List<Country> countries = findAllCountryUseCase.findAllCountry();

    RegionService regionService = new RegionRepository();
    FindRegionByCountryUseCase findRegionByCountryUseCase = new FindRegionByCountryUseCase(regionService);

    CityService cityService = new CityRepository();
    FindCityByRegionUseCase findCityByRegionUseCase = new FindCityByRegionUseCase(cityService);

    SupplierService supplierService = new SupplierRepository();
    CreateSupplierUseCase createSupplierUseCase = new CreateSupplierUseCase(supplierService);

    public AddSupplierUI(){

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
        Nit.setBounds(170, 130, 255, 30);
        Nit.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Nit.setForeground(new Color(255,242,45));
        Nit.setBackground(new Color(10,20,28));
        add(Nit);

        labelSupplier = new JLabel("Name : ");
        labelSupplier.setBounds(85, 170, 150, 30);
        labelSupplier.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelSupplier.setForeground(new Color(255,242,45));
        add(labelSupplier);

        Supplier = new JTextField();
        Supplier.setBounds(170, 170, 255, 30);
        Supplier.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Supplier.setForeground(new Color(255,242,45));
        Supplier.setBackground(new Color(10,20,28));
        add(Supplier);

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
        add(email);

        addButton = new JButton("Add");
        addButton.setBounds(400, 320, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(540, 320, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startAddSupplier(int gobackindicator) {
        AddSupplierUI addUI = new AddSupplierUI();
        addUI.setBounds(0, 0, 1000, 400);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator = gobackindicator;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                String cityId = TextBeforeDot(cityBox.getSelectedItem().toString()).trim();
                String nit = Nit.getText();
                String supplier = Supplier.getText();
                String supplierEmail = email.getText();
                int type_id = Integer.parseInt(TextBeforeDot(typeBox.getSelectedItem().toString().trim()).trim());

                if (nit.length()>0 && supplier.length()>0){
                Supplier newSupplier = new Supplier();

                newSupplier.setCity_id(cityId);
                newSupplier.setNit(nit);
                newSupplier.setName(supplier);
                newSupplier.setEmail(supplierEmail);
                newSupplier.setSupplier_type(type_id);
                
                createSupplierUseCase.execute(newSupplier);
                JOptionPane.showMessageDialog(this, "Supplier added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Supplier.setText("");
                email.setText("");
                Nit.setText("");

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
            this.setVisible(false);
            SupplierUI supplierUI = new SupplierUI();
            supplierUI.startSupplier(gobackindicator);
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

