package com.zapstore.supplier_type.infrastructure.supplier_typeui;

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

import com.zapstore.menu.infraestructure.SupplierMenu;
import com.zapstore.supplier_type.application.FindAllSupplierTypeUseCase;
import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;
import com.zapstore.supplier_type.infrastructure.SupplierTypeRepository;

public class SupplierTypeUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allSupplierTypeButton, backButton;
    private int gobackindicator;
    public SupplierTypeUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Supplier Types");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/supplier_type.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Supplier Types");
        title.setBounds(430, 10, 650, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 70));
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




        allSupplierTypeButton = new JButton("All SupplierTypes");
        allSupplierTypeButton.setBounds(520, 415, 330, 60);
        allSupplierTypeButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allSupplierTypeButton.setForeground(new Color(255,242,45));
        allSupplierTypeButton.setBackground(new Color(10,20,28));
        allSupplierTypeButton.addActionListener(this);
        add(allSupplierTypeButton);
    }
    public void startSupplierType(int gobackindicator) {
        SupplierTypeUI supplier_typeUI = new SupplierTypeUI();
        supplier_typeUI.setBounds(0, 0, 1000, 600);
        supplier_typeUI.setVisible(true);
        supplier_typeUI.setResizable(false);
        supplier_typeUI.setLocationRelativeTo(null);
        supplier_typeUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddSupplierTypeUI addSupplierTypeUI = new AddSupplierTypeUI();
            addSupplierTypeUI.startAddSupplierType(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteSupplierTypeUI deleteSupplierTypeUI = new DeleteSupplierTypeUI();
            deleteSupplierTypeUI.startDeleteSupplierType(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindSupplierTypeUI FindSupplierTypeUI = new FindSupplierTypeUI();
            FindSupplierTypeUI.startFindSupplierType(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateSupplierTypeUI updateSupplierTypeUI = new UpdateSupplierTypeUI();
            updateSupplierTypeUI.startUpdateSupplierType(gobackindicator);
        }

        if (e.getSource()==allSupplierTypeButton){
                SupplierTypeService supplier_typeService = new SupplierTypeRepository();
                FindAllSupplierTypeUseCase findAllSupplierTypesUseCase = new FindAllSupplierTypeUseCase(supplier_typeService);
                List<SupplierType> Countries = findAllSupplierTypesUseCase.findAllSupplierType();
        
                String[] columns = {"ID", "Name"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(SupplierType -> {
                    Object[] row = {SupplierType.getId(), SupplierType.getName()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "SupplierType List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        SupplierMenu supplierMenu = new SupplierMenu();
                        supplierMenu.startSupplierMenu(gobackindicator);
                    });
                });
        }

        }
    }

