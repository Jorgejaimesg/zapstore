package com.zapstore.region.infrastructure.regionui;

import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;
import com.zapstore.country.application.FindAllCountryUseCase;
import com.zapstore.country.application.FindCountryByNameUseCase;
import com.zapstore.country.domain.entity.Country;

import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;
import com.zapstore.region.application.CreateRegionUseCase;
import com.zapstore.region.domain.entity.Region;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class AddRegionUI extends JFrame implements ActionListener {
    private JLabel logoImg, title, labelName, labelCountry, labelCode;
    private JButton addButton, backButton;
    private JTextField Name, Code;
    private JComboBox<String> Country;
    private int gobackindicator, openPageIndicator;

    RegionService RegionService = new RegionRepository();
    CountryService CountryService = new CountryRepository();
    FindAllCountryUseCase findAllCountryUseCase = new FindAllCountryUseCase(CountryService);
    FindCountryByNameUseCase findCountryByNameUseCase = new FindCountryByNameUseCase(CountryService);
    CreateRegionUseCase createRegionUseCase = new CreateRegionUseCase(RegionService);
    List<Country> countries = findAllCountryUseCase.findAllCountry();

    public AddRegionUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Region");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/Regions.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(60, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Region");
        title.setBounds(250, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 30));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelCode = new JLabel("Code : ");
        labelCode.setBounds(35, 130, 150, 30);
        labelCode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCode.setForeground(new Color(255,242,45));
        add(labelCode);

        Code = new JTextField();
        Code.setBounds(190, 130, 255, 30);
        Code.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Code.setForeground(new Color(255,242,45));
        Code.setBackground(new Color(10,20,28));
        add(Code);

        labelName = new JLabel("Name : ");
        labelName.setBounds(35, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(190, 170, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);

        labelCountry = new JLabel("Country : ");
        labelCountry.setBounds(35, 210, 150, 30);
        labelCountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCountry.setForeground(new Color(255,242,45));
        add(labelCountry);

        Country = new JComboBox<String>();
        Country.setBounds(190, 210, 255, 30);
        Country.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Country.setForeground(new Color(255,242,45));
        Country.setBackground(new Color(10,20,28));
        add(Country);
        Country.addItem("");
        for(Country Countryitem : countries){
            Country.addItem(Countryitem.getName());
        };

        addButton = new JButton("Add");
        addButton.setBounds(125, 260, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 260, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }
    public void startAddRegion(int open, int goBack) {
        AddRegionUI addRegionUI = new AddRegionUI();
        addRegionUI.setBounds(0, 0, 500, 350);
        addRegionUI.setVisible(true);
        addRegionUI.setResizable(false);
        addRegionUI.setLocationRelativeTo(null);
        addRegionUI.openPageIndicator = open;
        addRegionUI.gobackindicator = goBack;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            try {
                String countryName = Country.getSelectedItem().toString();
                Optional<Country> countryFound = findCountryByNameUseCase.findCountryByName(countryName);
                String countryID =countryFound.get().getCodeCountry();
                System.out.println(countryID);
                String RegionName = Name.getText().trim();
                String CodeReg = Code.getText().trim();
                if (RegionName.length()>0){
                    Region newRegion = new Region();
                
                    newRegion.setCodereg(CodeReg);
                    newRegion.setNamereg(RegionName);
                    newRegion.setCodeCountry(countryID);
                    
                    createRegionUseCase.execute(newRegion);
                
                JOptionPane.showMessageDialog(this, "Region added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setText("");
                Code.setText("");
                Country.setSelectedItem("");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Region", "Error", JOptionPane.ERROR_MESSAGE);

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
                RegionUI RegionUI = new RegionUI();
                RegionUI.startRegion(openPageIndicator,gobackindicator);
            });
        });
    }
    }

}
