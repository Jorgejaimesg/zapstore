package com.zapstore.payment_method.infrastructure.payment_methodui;

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

import com.zapstore.menu.infraestructure.OrderMenu;
import com.zapstore.menu.infraestructure.SaleMenu;
import com.zapstore.payment_method.application.FindAllPaymentMethodUseCase;
import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;
import com.zapstore.payment_method.infrastructure.PaymentMethodRepository;

public class PaymentMethodUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allPaymentMethodButton, backButton;
    private int gobackindicator, openPageIndicator;

    public PaymentMethodUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Payment Methods");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/payment_method.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Payment Methods");
        title.setBounds(400, 10, 600, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 65));
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




        allPaymentMethodButton = new JButton("All PaymentMethods");
        allPaymentMethodButton.setBounds(520, 415, 330, 60);
        allPaymentMethodButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allPaymentMethodButton.setForeground(new Color(255,242,45));
        allPaymentMethodButton.setBackground(new Color(10,20,28));
        allPaymentMethodButton.addActionListener(this);
        add(allPaymentMethodButton);
    }
    public void startPaymentMethod(int openPageIndicator, int gobackindicator) {
        PaymentMethodUI payment_methodUI = new PaymentMethodUI();
        payment_methodUI.setBounds(0, 0, 1000, 600);
        payment_methodUI.setVisible(true);
        payment_methodUI.setResizable(false);
        payment_methodUI.setLocationRelativeTo(null);
        payment_methodUI.gobackindicator = gobackindicator;
        payment_methodUI.openPageIndicator = openPageIndicator;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddPaymentMethodUI addPaymentMethodUI = new AddPaymentMethodUI();
            addPaymentMethodUI.startAddPaymentMethod(openPageIndicator, gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeletePaymentMethodUI deletePaymentMethodUI = new DeletePaymentMethodUI();
            deletePaymentMethodUI.startDeletePaymentMethod(openPageIndicator, gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindPaymentMethodUI FindPaymentMethodUI = new FindPaymentMethodUI();
            FindPaymentMethodUI.startFindPaymentMethod(openPageIndicator, gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdatePaymentMethodUI updatePaymentMethodUI = new UpdatePaymentMethodUI();
            updatePaymentMethodUI.startUpdatePaymentMethod(openPageIndicator, gobackindicator);
        }

        if (e.getSource()==allPaymentMethodButton){
                PaymentMethodService payment_methodService = new PaymentMethodRepository();
                FindAllPaymentMethodUseCase findAllPaymentMethodsUseCase = new FindAllPaymentMethodUseCase(payment_methodService);
                List<PaymentMethod> Countries = findAllPaymentMethodsUseCase.findAllPaymentMethod();
        
                String[] columns = {"ID", "Name"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(PaymentMethod -> {
                    Object[] row = {PaymentMethod.getId(), PaymentMethod.getName()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "PaymentMethod List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                if (gobackindicator==1){ // abierto desde customer
                    SwingUtilities.invokeLater(() -> {
                    SaleMenu SaleMenu = new SaleMenu();
                    SaleMenu.startSaleMenu(openPageIndicator);
                    });
                } else if (gobackindicator==2) { // abierto desde supplier 
                    SwingUtilities.invokeLater(() -> {
                        OrderMenu orderMenu = new OrderMenu();
                        orderMenu.startOrderMenu(openPageIndicator);
                    });
                }
                // Abre la nueva ventana 
            });
        }

        }
    }

