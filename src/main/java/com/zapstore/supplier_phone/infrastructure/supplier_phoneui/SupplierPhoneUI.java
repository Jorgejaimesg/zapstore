package com.zapstore.supplier_phone.infrastructure.supplier_phoneui;

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
import com.zapstore.supplier_phone.application.FindAllSupplierPhoneUseCase;
import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;
import com.zapstore.supplier_phone.infrastructure.SupplierPhoneRepository;

public class SupplierPhoneUI extends JFrame implements ActionListener{
    private JLabel title, title1, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allSupplierPhoneButton, backButton;
    private int gobackindicator;

    public SupplierPhoneUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SupplierPhones");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/phone.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Supplier");
        title.setBounds(520, -50, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 70));
        title.setForeground(new Color(255,242,45));
        add(title);

        title1 = new JLabel("Phones");
        title1.setBounds(550, 10, 500, 300);
        title1.setFont(new Font("Andale Mono", Font.BOLD, 70));
        title1.setForeground(new Color(255,242,45));
        add(title1);
        
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

        allSupplierPhoneButton = new JButton("All Phones");
        allSupplierPhoneButton.setBounds(520, 415, 330, 60);
        allSupplierPhoneButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allSupplierPhoneButton.setForeground(new Color(255,242,45));
        allSupplierPhoneButton.setBackground(new Color(10,20,28));
        allSupplierPhoneButton.addActionListener(this);
        add(allSupplierPhoneButton);
    }
    public void startSupplierPhone(int gobackindicator) {
        SupplierPhoneUI supplier_phoneUI = new SupplierPhoneUI();
        supplier_phoneUI.setBounds(0, 0, 1000, 600);
        supplier_phoneUI.setVisible(true);
        supplier_phoneUI.setResizable(false);
        supplier_phoneUI.setLocationRelativeTo(null);
        supplier_phoneUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddSupplierPhoneUI addSupplierPhoneUI = new AddSupplierPhoneUI();
            addSupplierPhoneUI.startAddSupplierPhone(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteSupplierPhoneUI deleteSupplierPhoneUI = new DeleteSupplierPhoneUI();
            deleteSupplierPhoneUI.startDeleteSupplierPhone(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindSupplierPhoneUI FindSupplierPhoneUI = new FindSupplierPhoneUI();
            FindSupplierPhoneUI.startFindSupplierPhone(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateSupplierPhoneUI updateSupplierPhoneUI = new UpdateSupplierPhoneUI();
            updateSupplierPhoneUI.startUpdateSupplierPhone(gobackindicator);
        }

        if (e.getSource()==allSupplierPhoneButton){
                SupplierPhoneService supplier_phoneService = new SupplierPhoneRepository();
                FindAllSupplierPhoneUseCase findAllSupplierPhonesUseCase = new FindAllSupplierPhoneUseCase(supplier_phoneService);
                List<SupplierPhone> Countries = findAllSupplierPhonesUseCase.findAllSupplierPhone();
        
                String[] columns = {"ID", "Supplier", "Phone"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(SupplierPhone -> {
                    Object[] row = {SupplierPhone.getId(), SupplierPhone.getSupplier_id(), SupplierPhone.getPhone_number()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "SupplierPhone List", JOptionPane.PLAIN_MESSAGE);
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

