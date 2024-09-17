package com.zapstore.supplier.infrastructure.supplierui;

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
import com.zapstore.supplier.application.FindAllSupplierUseCase;
import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;
import com.zapstore.supplier.infrastructure.SupplierRepository;

public class SupplierUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allSupplierButton, backButton;
    private int gobackindicator;
    public SupplierUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Suppliers");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/supplier.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Suppliers");
        title.setBounds(520, -50, 500, 300);
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

        allSupplierButton = new JButton("All Address");
        allSupplierButton.setBounds(520, 415, 330, 60);
        allSupplierButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allSupplierButton.setForeground(new Color(255,242,45));
        allSupplierButton.setBackground(new Color(10,20,28));
        allSupplierButton.addActionListener(this);
        add(allSupplierButton);
    }
    public void startSupplier(int gobackindicator) {
        SupplierUI supplierUI = new SupplierUI();
        supplierUI.setBounds(0, 0, 1000, 600);
        supplierUI.setVisible(true);
        supplierUI.setResizable(false);
        supplierUI.setLocationRelativeTo(null);
        supplierUI.gobackindicator= gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddSupplierUI addSupplierUI = new AddSupplierUI();
            addSupplierUI.startAddSupplier(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteSupplierUI deleteSupplierUI = new DeleteSupplierUI();
            deleteSupplierUI.startDeleteSupplier(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindSupplierUI FindSupplierUI = new FindSupplierUI();
            FindSupplierUI.startFindSupplier(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateSupplierUI updateSupplierUI = new UpdateSupplierUI();
            updateSupplierUI.startUpdateSupplier(gobackindicator);
        }

        if (e.getSource()==allSupplierButton){
                SupplierService supplierService = new SupplierRepository();
                FindAllSupplierUseCase findAllSuppliersUseCase = new FindAllSupplierUseCase(supplierService);
                List<Supplier> Countries = findAllSuppliersUseCase.findAllSupplier();
        
                String[] columns = {"nit", "supplier_type", "name", "email", "city_id"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(Supplier -> {
                    Object[] row = {Supplier.getNit(), Supplier.getSupplier_type(), Supplier.getName(), Supplier.getEmail(), Supplier.getCity_id()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "Supplier List", JOptionPane.PLAIN_MESSAGE);
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

