package com.zapstore.status_sale.infrastructure.status_saleui;

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

import com.zapstore.menu.infraestructure.SaleMenu;
import com.zapstore.status_sale.application.FindAllStatusSaleUseCase;
import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;
import com.zapstore.status_sale.infrastructure.StatusSaleRepository;

public class StatusSaleUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allStatusSaleButton, backButton;
    private int gobackindicator;

    public StatusSaleUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Status Sales");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/status.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Status Sales");
        title.setBounds(480, 10, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 75));
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




        allStatusSaleButton = new JButton("All StatusSales");
        allStatusSaleButton.setBounds(520, 415, 330, 60);
        allStatusSaleButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allStatusSaleButton.setForeground(new Color(255,242,45));
        allStatusSaleButton.setBackground(new Color(10,20,28));
        allStatusSaleButton.addActionListener(this);
        add(allStatusSaleButton);
    }
    public void startStatusSale(int gobackindicator) {
        StatusSaleUI status_saleUI = new StatusSaleUI();
        status_saleUI.setBounds(0, 0, 1000, 600);
        status_saleUI.setVisible(true);
        status_saleUI.setResizable(false);
        status_saleUI.setLocationRelativeTo(null);
        status_saleUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddStatusSaleUI addStatusSaleUI = new AddStatusSaleUI();
            addStatusSaleUI.startAddStatusSale(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteStatusSaleUI deleteStatusSaleUI = new DeleteStatusSaleUI();
            deleteStatusSaleUI.startDeleteStatusSale(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindStatusSaleUI FindStatusSaleUI = new FindStatusSaleUI();
            FindStatusSaleUI.startFindStatusSale(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateStatusSaleUI updateStatusSaleUI = new UpdateStatusSaleUI();
            updateStatusSaleUI.startUpdateStatusSale(gobackindicator);
        }

        if (e.getSource()==allStatusSaleButton){
                StatusSaleService status_saleService = new StatusSaleRepository();
                FindAllStatusSaleUseCase findAllStatusSalesUseCase = new FindAllStatusSaleUseCase(status_saleService);
                List<StatusSale> Countries = findAllStatusSalesUseCase.findAllStatusSale();
        
                String[] columns = {"ID", "Name"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(StatusSale -> {
                    Object[] row = {StatusSale.getId(), StatusSale.getDescriptionmode()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "StatusSale List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                    SwingUtilities.invokeLater(() -> {
                        SaleMenu saleMenu = new SaleMenu();
                        saleMenu.startSaleMenu(gobackindicator);
                    });
                });
        }

        }
    }

