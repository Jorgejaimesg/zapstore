package com.zapstore.supplier_order.infrastructure.supplier_orderui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.zapstore.menu.infraestructure.OrderMenu;
import com.zapstore.supplier_order.application.DeleteSupplierOrderUseCase;
import com.zapstore.supplier_order.application.FindSupplierOrderByIdUseCase;
import com.zapstore.supplier_order.domain.entity.SupplierOrder;
import com.zapstore.supplier_order.domain.service.SupplierOrderService;
import com.zapstore.supplier_order.infrastructure.SupplierOrderRepository;
import com.zapstore.order_detail.application.DeleteOrderDetailUseCase;
import com.zapstore.order_detail.domain.service.OrderDetailService;
import com.zapstore.order_detail.infrastructure.OrderDetailRepository;

public class DeleteSupplierOrderUI extends JFrame implements ActionListener{
    SupplierOrderService supplier_orderService = new SupplierOrderRepository();
    DeleteSupplierOrderUseCase deleteSupplierOrderUseCase = new DeleteSupplierOrderUseCase(supplier_orderService);
    FindSupplierOrderByIdUseCase findSupplierOrderByIdUseCase = new FindSupplierOrderByIdUseCase(supplier_orderService);
    OrderDetailService orderDetailService = new OrderDetailRepository();
    DeleteOrderDetailUseCase deleteOrderDetailUseCase = new DeleteOrderDetailUseCase(orderDetailService);

    private JLabel logoImg, title, labelName;
    private JButton deleteButton, backButton;
    private JTextField Name;
    private int gobackindicator;

    public DeleteSupplierOrderUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete SupplierOrder");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zaporders.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Order");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Order: ");
        labelName.setBounds(65, 130, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 130, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);



        deleteButton = new JButton("Delete");
        deleteButton.setBounds(125, 200, 120, 30);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 200, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startDeleteSupplierOrder(int goBack) {
        DeleteSupplierOrderUI deleteUIA = new DeleteSupplierOrderUI();
        deleteUIA.setBounds(0, 0, 500, 300);
        deleteUIA.setVisible(true);
        deleteUIA.setResizable(false);
        deleteUIA.setLocationRelativeTo(null);
        deleteUIA.gobackindicator = goBack;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButton){

            try {
                int supplier_orderName = Integer.parseInt(Name.getText());
                Optional<SupplierOrder> deletedsupplier_order = findSupplierOrderByIdUseCase.findSupplierOrderById(supplier_orderName);
                if (deletedsupplier_order.isPresent()){   
                deleteOrderDetailUseCase.execute(supplier_orderName);
                deleteSupplierOrderUseCase.execute(supplier_orderName);
                JOptionPane.showMessageDialog(this, "supplier_order successfully deleted");
                Name.setText("");
                
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            dispose(); // Asegúrate de liberar recursos

            // Abre la nueva ventana 
            SwingUtilities.invokeLater(() -> {
                OrderMenu OrderMenu = new OrderMenu();
                OrderMenu.startOrderMenu(gobackindicator);
            });
        });
    }
    }
    }

