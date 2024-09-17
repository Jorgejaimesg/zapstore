package com.zapstore.city.infrastructure.cityui;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.zapstore.city.application.FindAllCityUseCase;
import com.zapstore.city.domain.entity.CityShow;
import com.zapstore.city.domain.service.CityService;
import com.zapstore.city.infrastructure.CityRepository;
import com.zapstore.menu.infraestructure.CustomerMenu;
import com.zapstore.menu.infraestructure.SupplierMenu;


public class CityUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allCityButton, backButton;
    private int gobackindicator, openPageIndicator;
    public CityUI(){

        
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cities");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/CityImg.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Cities");
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




        allCityButton = new JButton("All Cities");
        allCityButton.setBounds(520, 415, 330, 60);
        allCityButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allCityButton.setForeground(new Color(255,242,45));
        allCityButton.setBackground(new Color(10,20,28));
        allCityButton.addActionListener(this);
        add(allCityButton);
    }

        public void startCity(int openPageIndicator, int gobackindicator) {
        CityUI CityUI = new CityUI();
        CityUI.setBounds(0, 0, 1000, 600);
        CityUI.setVisible(true);
        CityUI.setResizable(false);
        CityUI.setLocationRelativeTo(null);
        CityUI.gobackindicator=gobackindicator;
        CityUI.openPageIndicator=openPageIndicator;
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddCityUI addCityUI = new AddCityUI();
            addCityUI.startAddCity(openPageIndicator, gobackindicator); 
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteCityUI deleteCityUI = new DeleteCityUI();
            deleteCityUI.startDeleteCity(openPageIndicator, gobackindicator);
        }

        if (e.getSource()==allCityButton){
                CityService CityService = new CityRepository();
                FindAllCityUseCase findAllCityUseCase = new FindAllCityUseCase(CityService);
                List<CityShow> Cities = findAllCityUseCase.findAllCity();
        
                String[] columns = {"ID", "City", "Region", "Country"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Cities.forEach(City -> {
                    Object[] row = {City.getCodecity(), City.getNamecity(), City.getNamereg(), City.getNameCountry() };
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "City List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateCityUI UpdateCityUI = new UpdateCityUI();
            UpdateCityUI.startUpdateCity(openPageIndicator, gobackindicator);
        }
        if(e.getSource()==findButton){
            this.setVisible(false);
            FindCityUI FindCityUI = new FindCityUI();
            FindCityUI.startFindCity(openPageIndicator, gobackindicator);
        }
        if(e.getSource()==backButton){
            // Oculta la ventana actual
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                if (gobackindicator==1){
                    SwingUtilities.invokeLater(() -> {
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.startCustomerMenu(openPageIndicator);
                    });
                } else if (gobackindicator==2) {
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
