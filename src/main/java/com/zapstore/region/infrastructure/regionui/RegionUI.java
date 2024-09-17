package com.zapstore.region.infrastructure.regionui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.zapstore.region.application.FindAllregionUseCase;
import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import com.zapstore.region.infrastructure.RegionRepository;
import com.zapstore.menu.infraestructure.CustomerMenu;
import com.zapstore.menu.infraestructure.SupplierMenu;

public class RegionUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allRegionButton, backButton;
    private int gobackindicator, openPageIndicator;

    public RegionUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Regions");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/regions.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Regions");
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
        backButton.setBackground(new Color(10, 20, 28));
        backButton.addActionListener(this);
        add(backButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(700, 255, 150, 60);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10, 20, 28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(520, 335, 150, 60);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10, 20, 28));
        updateButton.addActionListener(this);
        add(updateButton);

        findButton = new JButton("Find");
        findButton.setBounds(700, 335, 150, 60);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10, 20, 28));
        findButton.addActionListener(this);
        add(findButton);




        allRegionButton = new JButton("All Regions");
        allRegionButton.setBounds(520, 415, 330, 60);
        allRegionButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allRegionButton.setForeground(new Color(255,242,45));
        allRegionButton.setBackground(new Color(10, 20, 28));
        allRegionButton.addActionListener(this);
        add(allRegionButton);
    }

        public void startRegion(int openPageIndicator, int gobackindicator) {
        RegionUI RegionUI = new RegionUI();
        RegionUI.setBounds(0, 0, 1000, 600);
        RegionUI.setVisible(true);
        RegionUI.setResizable(false);
        RegionUI.setLocationRelativeTo(null);
        RegionUI.openPageIndicator = openPageIndicator;
        RegionUI.gobackindicator = gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddRegionUI addRegionUI = new AddRegionUI();
            addRegionUI.startAddRegion(openPageIndicator, gobackindicator); 
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteRegionUI deleteRegionUI = new DeleteRegionUI();
            deleteRegionUI.startDeleteRegion(openPageIndicator, gobackindicator);
        }

        if (e.getSource()==allRegionButton){
                RegionService RegionService = new RegionRepository();
                FindAllregionUseCase findAllRegionUseCase = new FindAllregionUseCase(RegionService);
                List<Region> Regions = findAllRegionUseCase.findAllRegion();
        
                String[] columns = {"ID", "Region", "City"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Regions.forEach(Region -> {
                    Object[] row = {Region.getCodereg(), Region.getNamereg(), Region.getCodeCountry() };
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "Region List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateRegionUI UpdateRegionUI = new UpdateRegionUI();
            UpdateRegionUI.startUpdateRegion(openPageIndicator, gobackindicator);
        }
        if(e.getSource()==findButton){
            this.setVisible(false);
            FindRegionUI FindRegionUI = new FindRegionUI();
            FindRegionUI.startFindRegion(openPageIndicator, gobackindicator);
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
