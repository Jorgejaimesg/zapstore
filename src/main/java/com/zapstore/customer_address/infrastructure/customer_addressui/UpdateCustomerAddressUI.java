package com.zapstore.customer_address.infrastructure.customer_addressui;
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
import com.zapstore.customer_address.application.FindCustomerAddressByCustomerUseCase;
import com.zapstore.customer_address.application.FindCustomerAddressByIdUseCase;
import com.zapstore.customer_address.application.UpdateCustomerAddressUseCase;
import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;
import com.zapstore.customer_address.infrastructure.CustomerAddressRepository;
import com.zapstore.region.application.FindRegionByCodeUseCase;
import com.zapstore.region.application.FindRegionByCountryUseCase;
import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;

import javax.swing.JOptionPane;

public class UpdateCustomerAddressUI extends JFrame implements ActionListener {
    private JLabel  labelCountry,  labelRegion,  labelCity, title, logoImg, labelDetails, labelZipCode, labelCustomer;
    private JTextField customer,ZipCode, DetailsTxt;
    private JComboBox<String> Details,countryBox,regionBox,cityBox;
    private JButton updateButton, findButton, backButton, selectButton, newButton;
    private int gobackindicator;

    CityService cityService = new CityRepository();
    FindCityByCodeUseCase  findCityByCodeUseCase = new FindCityByCodeUseCase(cityService);
    FindCityByRegionUseCase findCityByRegionUseCase = new FindCityByRegionUseCase(cityService);

    CountryService countryService = new CountryRepository();
    FindCountryByCodeUseCase findCountryByCodeUseCase = new FindCountryByCodeUseCase(countryService);
    FindAllCountryUseCase findAllCountryUseCase = new FindAllCountryUseCase(countryService);
    List<Country> countries = findAllCountryUseCase.findAllCountry();

    RegionService regionService = new RegionRepository();
    FindRegionByCodeUseCase findRegionByCodeUseCase = new FindRegionByCodeUseCase(regionService);
    FindRegionByCountryUseCase findRegionByCountryUseCase = new FindRegionByCountryUseCase(regionService);

    CustomerAddressService customerAddressService = new CustomerAddressRepository();
    FindCustomerAddressByCustomerUseCase findCustomerAddressByCustomerUseCase = new FindCustomerAddressByCustomerUseCase(customerAddressService);
    FindCustomerAddressByIdUseCase findCustomerAddressByIdUseCase = new FindCustomerAddressByIdUseCase(customerAddressService);
    UpdateCustomerAddressUseCase updateCustomerAddressUseCase = new UpdateCustomerAddressUseCase(customerAddressService);

    public UpdateCustomerAddressUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update CustomerAddress");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/address.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Find Customer Address");
        title.setBounds(350, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

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
        for (Country country : countries){
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
        labelCity.setBounds(500, 210, 150, 30);
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

        labelDetails = new JLabel("Address: ");
        labelDetails.setBounds(70, 170, 150, 30);
        labelDetails.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelDetails.setForeground(new Color(255,242,45));
        add(labelDetails);

        DetailsTxt = new JTextField();
        DetailsTxt.setBounds(170, 170, 290, 30);
        DetailsTxt.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        DetailsTxt.setForeground(new Color(255,242,45));
        DetailsTxt.setBackground(new Color(10,20,28));
        DetailsTxt.setVisible(false);
        DetailsTxt.setEnabled(false);
        add(DetailsTxt);

        Details = new JComboBox<String>();
        Details.setBounds(170, 170, 290, 30);
        Details.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Details.setForeground(new Color(255,242,45));
        Details.setBackground(new Color(10,20,28));
        Details.setVisible(false);
        add(Details);
        Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("its working");
                findCustomerAddress();
            }
            
        });

        labelZipCode = new JLabel("Zip Code: ");
        labelZipCode.setBounds(65, 210, 150, 30);
        labelZipCode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelZipCode.setForeground(new Color(255,242,45));
        add(labelZipCode);

        ZipCode = new JTextField();
        ZipCode.setBounds(170, 210, 290, 30);
        ZipCode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        ZipCode.setForeground(new Color(255,242,45));
        ZipCode.setBackground(new Color(10,20,28));
        ZipCode.setVisible(false);
        add(ZipCode);

        labelCustomer = new JLabel("Customer : ");
        labelCustomer.setBounds(55, 130, 150, 30);
        labelCustomer.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCustomer.setForeground(new Color(255,242,45));
        add(labelCustomer);

        customer = new JTextField();
        customer.setBounds(170, 130, 230, 30);
        customer.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        customer.setForeground(new Color(255,242,45));
        customer.setBackground(new Color(10,20,28));
        add(customer);

        backButton = new JButton("Go Back");
        backButton.setBounds(650, 290, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        selectButton = new JButton("Select");
        selectButton.setBounds(350, 290, 120, 30);
        selectButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        selectButton.setForeground(new Color(255,242,45));
        selectButton.setBackground(new Color(10,20,28));
        selectButton.addActionListener(this);
        selectButton.setEnabled(false);
        add(selectButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(500, 290, 120, 30);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        updateButton.setEnabled(false);
        add(updateButton);

        newButton = new JButton("New");
        newButton.setBounds(200, 290, 120, 30);
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

    public void startUpdateCustomerAddress(int gobackindicator) {
        UpdateCustomerAddressUI updateUI = new UpdateCustomerAddressUI();
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
                updateDetailBox();
            } catch (Exception ex) {
                // TODO: handle exception
                } 
        }

        if(e.getSource()==newButton){
            customer.setText("");
            cityBox.setVisible(false);
            regionBox.setVisible(false);
            countryBox.setVisible(false);
            ZipCode.setText("");
            ZipCode.setVisible(false);
            findButton.setEnabled(true);
            selectButton.setEnabled(false);
            updateButton.setEnabled(false);
            Details.setVisible(false);
            DetailsTxt.setVisible(false);
            DetailsTxt.setText("");
            customer.setEnabled(true);
            Details.removeAllItems();
        
        }

        if(e.getSource()==updateButton){
                String cityId = TextBefore(cityBox.getSelectedItem().toString()).trim();
                String details = DetailsTxt.getText();
                String customerid = customer.getText();
                int Zip = Integer.parseInt(ZipCode.getText());
                int id = Integer.parseInt(TextBeforeDot(Details.getSelectedItem().toString()));
                System.out.println(id);
            CustomerAddress updatedCustomerAddress = new CustomerAddress();
                updatedCustomerAddress.setId(id);
                updatedCustomerAddress.setCity_id(cityId);
                updatedCustomerAddress.setDetails(details);
                updatedCustomerAddress.setCustomer_id(customerid);
                updatedCustomerAddress.setZipCode(Zip);
                updateCustomerAddressUseCase.execute(updatedCustomerAddress);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
                newButton.doClick();
    
            }
            

        if(e.getSource()==selectButton){
            DetailsTxt.setVisible(true);
            DetailsTxt.setEnabled(true);
            Details.setVisible(false);
            updateButton.setEnabled(true);
            selectButton.setEnabled(false);
        }
        if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerAddressUI customer_addressUI = new CustomerAddressUI();
            customer_addressUI.startCustomerAddress(gobackindicator);
        }
    }
    

    private void updateDetailBox(){
        Details.removeAllItems();
        try {
            String customerId = customer.getText().trim().toUpperCase();
            List<CustomerAddress> Alladdress = findCustomerAddressByCustomerUseCase.findCustomerAddressByCustomer(customerId);
            if(Alladdress.size()>0){
                Details.setVisible(true);
                Details.setEnabled(true);
                for(CustomerAddress customerAddress : Alladdress){
                    Details.addItem(customerAddress.getId()+". "+customerAddress.getDetails());
                }
                customer.setEnabled(false);
                findButton.setEnabled(false);
            } else{
                customer.setText("");
                JOptionPane.showMessageDialog(this, "Invalid Customer.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
        }
    }

    private void findCustomerAddress(){
        try {
            int AddressId = Integer.parseInt(TextBeforeDot(Details.getSelectedItem().toString()));
            Optional<CustomerAddress> foundAddres = findCustomerAddressByIdUseCase.findCustomerAddressById(AddressId);
            if(foundAddres.isPresent()){
                CustomerAddress AddressInfo = foundAddres.get();
                Optional<City> foundCity = findCityByCodeUseCase.findCityByCode(AddressInfo.getCity_id());
                Optional<Region> foundRegion = findRegionByCodeUseCase.findRegionByCode(foundCity.get().getCodereg().toString());
                Optional<Country> foundCountry = findCountryByCodeUseCase.findCountryByCode(foundRegion.get().getCodeCountry());
                            
                cityBox.setVisible(true);
                regionBox.setVisible(true);
                countryBox.setVisible(true);
                ZipCode.setVisible(true);
                selectButton.setEnabled(true);

                DetailsTxt.setText(AddressInfo.getDetails());
                cityBox.setSelectedItem(foundCity.get().getCodecity() + " - " + foundCity.get().getNamecity());
                regionBox.setSelectedItem(foundRegion.get().getCodereg() + " - " + foundRegion.get().getNamereg());
                countryBox.setSelectedItem(foundCountry.get().getCodeCountry() + " - " + foundCountry.get().getName());
                ZipCode.setText(String.valueOf(AddressInfo.getZipCode()));

        }} catch (Exception e) {
            // TODO: handle exception
        }

        }

        private void updateRegionBox(){
        regionBox.removeAllItems();
        try {
            String CountryId = TextBefore(countryBox.getSelectedItem().toString()).trim();
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
            String RegionId = TextBefore(regionBox.getSelectedItem().toString()).trim();
            List<City> cities = findCityByRegionUseCase.findAllCityByRegion(RegionId);
            for(City city : cities){
                cityBox.addItem(city.getCodecity()+ " - "+ city.getNamecity());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    private String TextBefore(String text) {
        // Buscar la posición del primer punto en la cadena
        int position = text.indexOf('-');
        if (position != -1) {
            return text.substring(0, position);
        } else {
            return text;
        }
}
    private String TextBeforeDot(String text) {
        // Buscar la posición del primer punto en la cadena
        int position = text.indexOf('.');
        if (position != -1) {
            return text.substring(0, position);
        } else {
            return text;
        }
}


}
