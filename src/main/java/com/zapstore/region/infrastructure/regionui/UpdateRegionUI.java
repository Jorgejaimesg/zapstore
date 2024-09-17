package com.zapstore.region.infrastructure.regionui;

import com.zapstore.country.application.FindAllCountryUseCase;
import com.zapstore.country.application.FindCountryByCodeUseCase;
import com.zapstore.country.application.FindCountryByNameUseCase;
import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;
import com.zapstore.region.application.FindRegionByCodeUseCase;
import com.zapstore.region.application.UpdateRegionUseCase;
import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;

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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UpdateRegionUI extends JFrame implements ActionListener{
    private JLabel  labelcode, title, logoImg, labelName, labelCountry;
    private JTextField codeRegion, Name;
    private JButton updateButton, findButton, backButton;
    private JComboBox<String> Countries;
    private int gobackindicator, openPageIndicator;

    RegionService RegionService = new RegionRepository();
    FindRegionByCodeUseCase findRegionByCodeUseCase = new FindRegionByCodeUseCase(RegionService);
    UpdateRegionUseCase updateRegionUseCase = new UpdateRegionUseCase(RegionService);
    CountryService countryService = new CountryRepository();
    FindCountryByNameUseCase findCountryByNameUseCase = new FindCountryByNameUseCase(countryService);
    FindCountryByCodeUseCase findCountryByCodeUseCase = new FindCountryByCodeUseCase(countryService);
    FindAllCountryUseCase findAllCountryUseCase = new FindAllCountryUseCase(countryService);
    List<Country> cities = findAllCountryUseCase.findAllCountry();

    public UpdateRegionUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update Region");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/regions.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Region");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(80, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codeRegion = new JTextField();
        codeRegion.setBounds(170, 130, 220, 30);
        codeRegion.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codeRegion.setForeground(new Color(255,242,45));
        codeRegion.setBackground(new Color(10,20,28));
        add(codeRegion);

        labelCountry = new JLabel("Country : ");
        labelCountry.setBounds(80, 170, 150, 30);
        labelCountry.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCountry.setForeground(new Color(255,242,45));
        add(labelCountry);

        Countries = new JComboBox<String>();
        Countries.setBounds(170, 170, 220, 30);
        Countries.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Countries.setForeground(new Color(255,242,45));
        Countries.setBackground(new Color(10,20,28));
        Countries.setVisible(false);
        add(Countries);
        Countries.addItem("");
        for(Country country : cities){
            Countries.addItem(country.getName());
        };


        labelName = new JLabel("Name : ");
        labelName.setBounds(80, 210, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 210, 220, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        Name.setVisible(false);
        add(Name);

        updateButton = new JButton("Update");
        updateButton.setBounds(125, 270, 120, 30);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 270, 120, 30);
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

    public void startUpdateRegion(int open, int goBack) {
        UpdateRegionUI updateUI = new UpdateRegionUI();
        updateUI.setBounds(0, 0, 520, 350);
        updateUI.setVisible(true);
        updateUI.setResizable(false);
        updateUI.setLocationRelativeTo(null);
        updateUI.openPageIndicator=open;
        updateUI.gobackindicator=goBack;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                String RegionCode = codeRegion.getText().trim().toUpperCase();
                if (RegionCode.length()>0){
                    Optional<Region> Region = findRegionByCodeUseCase.findRegionByCode(RegionCode);
                    if (Region.isPresent()){
                        Name.setVisible(true);
                        Name.setText(Region.get().getNamereg());
                        Optional<Country> foundCountry = findCountryByCodeUseCase.findCountryByCode(Region.get().getCodeCountry());
                        Countries.setVisible(true);
                        Countries.setSelectedItem(foundCountry.get().getName());
                    } else {
                        JOptionPane.showMessageDialog(this, "The Region doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }   
        if(e.getSource()==updateButton){

            String RegionName = Name.getText().trim();
            String Regioncode = codeRegion.getText().trim().toUpperCase();
            String countryName = Countries.getSelectedItem().toString();
            Optional<Country> foundCountry = findCountryByNameUseCase.findCountryByName(countryName);
            if(foundCountry.isPresent()){
                Region updatedRegion = new Region();
                updatedRegion.setCodeCountry(foundCountry.get().getCodeCountry());
                updatedRegion.setNamereg(RegionName);
                updatedRegion.setCodereg(Regioncode);
                if (RegionName.length()>0){
                    updateRegionUseCase.execute(updatedRegion);
                    JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(this, "Error to update", "Error", JOptionPane.ERROR_MESSAGE);
    
                }
                System.out.println(updatedRegion);
                Name.setText("");
                codeRegion.setText("");
                Name.setVisible(false);
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
