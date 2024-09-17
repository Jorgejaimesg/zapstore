package com.zapstore.id_type.infrastructure.id_typeui;

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

import com.zapstore.id_type.application.FindAllIdTypeUseCase;
import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;
import com.zapstore.id_type.infrastructure.IdTypeRepository;
import com.zapstore.menu.infraestructure.CustomerMenu;
import javax.swing.SwingUtilities;

public class IdTypeUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allIdTypeButton, backButton;
    private int gobackindicator;
    public IdTypeUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Id Types");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/id_type.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Id Types");
        title.setBounds(550, 10, 500, 300);
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




        allIdTypeButton = new JButton("All IdTypes");
        allIdTypeButton.setBounds(520, 415, 330, 60);
        allIdTypeButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allIdTypeButton.setForeground(new Color(255,242,45));
        allIdTypeButton.setBackground(new Color(10,20,28));
        allIdTypeButton.addActionListener(this);
        add(allIdTypeButton);
    }
    public void startIdType(int gobackindicator) {
        IdTypeUI id_typeUI = new IdTypeUI();
        id_typeUI.setBounds(0, 0, 1000, 600);
        id_typeUI.setVisible(true);
        id_typeUI.setResizable(false);
        id_typeUI.setLocationRelativeTo(null);
        id_typeUI.gobackindicator = gobackindicator;
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddIdTypeUI addIdTypeUI = new AddIdTypeUI();
            addIdTypeUI.startAddIdType(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteIdTypeUI deleteIdTypeUI = new DeleteIdTypeUI();
            deleteIdTypeUI.startDeleteIdType(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindIdTypeUI FindIdTypeUI = new FindIdTypeUI();
            FindIdTypeUI.startFindIdType(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateIdTypeUI updateIdTypeUI = new UpdateIdTypeUI();
            updateIdTypeUI.startUpdateIdType(gobackindicator);
        }

        if (e.getSource()==allIdTypeButton){
                IdTypeService id_typeService = new IdTypeRepository();
                FindAllIdTypeUseCase findAllIdTypesUseCase = new FindAllIdTypeUseCase(id_typeService);
                List<IdType> Countries = findAllIdTypesUseCase.findAllIdType();
        
                String[] columns = {"ID", "Name"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(IdType -> {
                    Object[] row = {IdType.getId(), IdType.getName()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "IdType List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.startCustomerMenu(gobackindicator);
                });
            });
        }

        }
    }

