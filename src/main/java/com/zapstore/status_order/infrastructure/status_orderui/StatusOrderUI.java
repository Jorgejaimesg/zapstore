package com.zapstore.status_order.infrastructure.status_orderui;

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

import com.zapstore.menu.infraestructure.OrderMenu;
import com.zapstore.status_order.application.FindAllStatusOrderUseCase;
import com.zapstore.status_order.domain.entity.StatusOrder;
import com.zapstore.status_order.domain.service.StatusOrderService;
import com.zapstore.status_order.infrastructure.StatusOrderRepository;

public class StatusOrderUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allStatusOrderButton, backButton;
    private int gobackindicator;

    public StatusOrderUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Status Orders");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/status.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Status Orders");
        title.setBounds(420, 10, 500, 300);
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




        allStatusOrderButton = new JButton("All StatusOrders");
        allStatusOrderButton.setBounds(520, 415, 330, 60);
        allStatusOrderButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allStatusOrderButton.setForeground(new Color(255,242,45));
        allStatusOrderButton.setBackground(new Color(10,20,28));
        allStatusOrderButton.addActionListener(this);
        add(allStatusOrderButton);
    }
    public void startStatusOrder(int gobackindicator) {
        StatusOrderUI status_orderUI = new StatusOrderUI();
        status_orderUI.setBounds(0, 0, 1000, 600);
        status_orderUI.setVisible(true);
        status_orderUI.setResizable(false);
        status_orderUI.setLocationRelativeTo(null);
        status_orderUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddStatusOrderUI addStatusOrderUI = new AddStatusOrderUI();
            addStatusOrderUI.startAddStatusOrder(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteStatusOrderUI deleteStatusOrderUI = new DeleteStatusOrderUI();
            deleteStatusOrderUI.startDeleteStatusOrder(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindStatusOrderUI FindStatusOrderUI = new FindStatusOrderUI();
            FindStatusOrderUI.startFindStatusOrder(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateStatusOrderUI updateStatusOrderUI = new UpdateStatusOrderUI();
            updateStatusOrderUI.startUpdateStatusOrder(gobackindicator);
        }

        if (e.getSource()==allStatusOrderButton){
                StatusOrderService status_orderService = new StatusOrderRepository();
                FindAllStatusOrderUseCase findAllStatusOrdersUseCase = new FindAllStatusOrderUseCase(status_orderService);
                List<StatusOrder> Countries = findAllStatusOrdersUseCase.findAllStatusOrder();
        
                String[] columns = {"ID", "Name"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(StatusOrder -> {
                    Object[] row = {StatusOrder.getId(), StatusOrder.getName()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "StatusOrder List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                    SwingUtilities.invokeLater(() -> {
                        OrderMenu orderMenu = new OrderMenu();
                        orderMenu.startOrderMenu(gobackindicator);
                    });
                });
        }

        }
    }

