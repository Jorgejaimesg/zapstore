package com.zapstore.country.infrastructure.countryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;

import com.zapstore.country.application.FindAllCountryUseCase;
import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;
import com.zapstore.country.infrastructure.CountryRepository;
import com.zapstore.menu.infraestructure.CustomerMenu;
import com.zapstore.menu.infraestructure.SupplierMenu;

public class CountryUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allCountryButton, backButton;
    private int gobackindicator, openPageIndicator;

    public CountryUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Countries");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/CountryImg.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Countries");
        title.setBounds(480, 10, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 90));
        title.setForeground(new Color(255,242,45));
        add(title);
        
        addButton = new JButton("Add");
        addButton.setBounds(520, 255, 150, 60);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("🔙");
        backButton.setBounds(0, 0, 60, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(700, 255, 150, 60);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(520, 335, 150, 60);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        findButton = new JButton("Find");
        findButton.setBounds(700, 335, 150, 60);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);




        allCountryButton = new JButton("All countries");
        allCountryButton.setBounds(520, 415, 330, 60);
        allCountryButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allCountryButton.setForeground(new Color(255,242,45));
        allCountryButton.setBackground(new Color(10,20,28));
        allCountryButton.addActionListener(this);
        add(allCountryButton);
    }
    public void startCountry(int openPageIndicator, int gobackindicator) {
        CountryUI countryUI = new CountryUI();
        countryUI.setBounds(0, 0, 1000, 600);
        countryUI.setVisible(true);
        countryUI.setResizable(false);
        countryUI.setLocationRelativeTo(null);
        countryUI.gobackindicator = gobackindicator;
        countryUI.openPageIndicator = openPageIndicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddCountryUI addCountryUI = new AddCountryUI();
            addCountryUI.startAddCountry(openPageIndicator, gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteCountryUI deleteCountryUI = new DeleteCountryUI();
            deleteCountryUI.startDeleteCountry(openPageIndicator, gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindCountryUI FindCountryUI = new FindCountryUI();
            FindCountryUI.startFindCountry(openPageIndicator, gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateCountryUI updateCountryUI = new UpdateCountryUI();
            updateCountryUI.startUpdateCountry(openPageIndicator, gobackindicator);
        }

        if (e.getSource()==allCountryButton){
                CountryService countryService = new CountryRepository();
                FindAllCountryUseCase findAllCountrysUseCase = new FindAllCountryUseCase(countryService);
                List<Country> Countries = findAllCountrysUseCase.findAllCountry();
        
                String[] columns = {"ID", "Name"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(Country -> {
                    Object[] row = {Country.getCodeCountry(), Country.getName()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "Country List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
            // Oculta la ventana actual
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                if (gobackindicator==1){ // abierto desde customer
                    SwingUtilities.invokeLater(() -> {
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.startCustomerMenu(openPageIndicator);
                    });
                } else if (gobackindicator==2) { // abierto desde supplier 
                    SwingUtilities.invokeLater(() -> {
                        SupplierMenu cash = new SupplierMenu();
                        cash.startSupplierMenu(openPageIndicator);
                    });
                }
                // Abre la nueva ventana 
            });
        }

        }
    }

