package com.zapstore.customer_address.infrastructure.customer_addressui;

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
import com.zapstore.customer_address.application.CreateCustomerAddressUseCase;
import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;
import com.zapstore.customer_address.infrastructure.CustomerAddressRepository;
import com.zapstore.region.application.FindRegionByCountryUseCase;
import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;
import com.zapstore.country.domain.entity.Country;

public class AddCustomerAddressUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelDetails, labelCustomer, labelZipCode, labelCity, labelCountry, labelRegion;
    private JButton addButton, backButton;
    private JComboBox<String> countryBox, regionBox, cityBox;
    private JTextField Details, Customer, ZipCode;
    private int gobackindicator;

    CountryService countryService = new CountryRepository();
    FindAllCountryUseCase findAllCountryUseCase = new FindAllCountryUseCase(countryService);
    List<Country> countries = findAllCountryUseCase.findAllCountry();

    RegionService regionService = new RegionRepository();
    FindRegionByCountryUseCase findRegionByCountryUseCase = new FindRegionByCountryUseCase(regionService);

    CityService cityService = new CityRepository();
    FindCityByRegionUseCase findCityByRegionUseCase = new FindCityByRegionUseCase(cityService);

    CustomerAddressService customerAddressService = new CustomerAddressRepository();
    CreateCustomerAddressUseCase createCustomerAddressUseCase = new CreateCustomerAddressUseCase(customerAddressService);

    public AddCustomerAddressUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add CustomerAddress");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/address.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Customer Address");
        title.setBounds(400, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelCountry = new JLabel("Country: ");
        labelCountry.setBounds(80, 130, 150, 30);
        labelCountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCountry.setForeground(new Color(255,242,45));
        add(labelCountry);

        countryBox = new JComboBox<String>();
        countryBox.setBounds(170, 130, 255, 30);
        countryBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        countryBox.setForeground(new Color(255,242,45));
        countryBox.setBackground(new Color(10,20,28));
        add(countryBox);
        for(Country country : countries){
            countryBox.addItem(country.getCodeCountry()+" - "+country.getName());
        }

        labelRegion = new JLabel("Region: ");
        labelRegion.setBounds(85, 170, 150, 30);
        labelRegion.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelRegion.setForeground(new Color(255,242,45));
        add(labelRegion);

        regionBox = new JComboBox<String>();
        regionBox.setBounds(170, 170, 255, 30);
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
        labelCity.setBounds(100, 210, 150, 30);
        labelCity.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCity.setForeground(new Color(255,242,45));
        add(labelCity);

        cityBox = new JComboBox<String>();
        cityBox.setBounds(170, 210, 255, 30);
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

        labelDetails = new JLabel("Address: ");
        labelDetails.setBounds(500, 130, 150, 30);
        labelDetails.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelDetails.setForeground(new Color(255,242,45));
        add(labelDetails);

        Details = new JTextField();
        Details.setBounds(600, 130, 255, 30);
        Details.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Details.setForeground(new Color(255,242,45));
        Details.setBackground(new Color(10,20,28));
        add(Details);

        labelZipCode = new JLabel("Zip Code: ");
        labelZipCode.setBounds(495, 170, 150, 30);
        labelZipCode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelZipCode.setForeground(new Color(255,242,45));
        add(labelZipCode);

        ZipCode = new JTextField();
        ZipCode.setBounds(600, 170, 255, 30);
        ZipCode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        ZipCode.setForeground(new Color(255,242,45));
        ZipCode.setBackground(new Color(10,20,28));
        add(ZipCode);

        labelCustomer = new JLabel("Customer : ");
        labelCustomer.setBounds(480, 210, 150, 30);
        labelCustomer.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCustomer.setForeground(new Color(255,242,45));
        add(labelCustomer);

        Customer = new JTextField();
        Customer.setBounds(600, 210, 255, 30);
        Customer.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Customer.setForeground(new Color(255,242,45));
        Customer.setBackground(new Color(10,20,28));
        add(Customer);


        addButton = new JButton("Add");
        addButton.setBounds(400, 300, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(540, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startAddCustomerAddress(int gobackindicator) {
        AddCustomerAddressUI addUI = new AddCustomerAddressUI();
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
                String details = Details.getText();
                String customer = Customer.getText();
                int Zip = Integer.parseInt(ZipCode.getText());

                if (customer.length()>0 && details.length()>0){
                CustomerAddress newCustomerAddress = new CustomerAddress();
                
                newCustomerAddress.setCity_id(cityId);
                newCustomerAddress.setDetails(details);
                newCustomerAddress.setCustomer_id(customer);
                newCustomerAddress.setZipCode(Zip);


                CustomerAddressService customer_addressService = new CustomerAddressRepository();
                CreateCustomerAddressUseCase createCustomerAddressUseCase = new CreateCustomerAddressUseCase(customer_addressService);
                createCustomerAddressUseCase.execute(newCustomerAddress);
                JOptionPane.showMessageDialog(this, "CustomerAddress added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Customer.setText("");
                ZipCode.setText(""); 
                Details.setText("");

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerAddressUI customer_addressUI = new CustomerAddressUI();
            customer_addressUI.startCustomerAddress(gobackindicator);
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

