package com.zapstore.customer_address.infrastructure.customer_addressui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.zapstore.customer_address.application.DeleteCustomerAddressUseCase;
import com.zapstore.customer_address.application.FindCustomerAddressByCustomerUseCase;
import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;
import com.zapstore.customer_address.infrastructure.CustomerAddressRepository;

public class DeleteCustomerAddressUI extends JFrame implements ActionListener{
    CustomerAddressService customer_addressService = new CustomerAddressRepository();
    DeleteCustomerAddressUseCase deleteCustomerAddressUseCase = new DeleteCustomerAddressUseCase(customer_addressService);
    FindCustomerAddressByCustomerUseCase findCustomerAddressByCustomerUseCase = new FindCustomerAddressByCustomerUseCase(customer_addressService);

    private JLabel logoImg, title, labelName, labelAddress;
    private JTextField Name;
    private JButton deleteButton, backButton, Namebutton;
    private JComboBox<String> addresssBox;
    private int gobackindicator;

    public DeleteCustomerAddressUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete CustomerAddress");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/address.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Address");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Customer: ");
        labelName.setBounds(65, 130, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);
        
        Name = new JTextField();
        Name.setBounds(170, 130, 190, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);
        
        Namebutton = new JButton("🔍");
        Namebutton.setBounds(370, 130, 60, 30);
        Namebutton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Namebutton.setForeground(new Color(255,242,45));
        Namebutton.setBackground(new Color(10,20,28));
        add(Namebutton);
        Namebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizaraddresss();
            }
        });
        
        labelAddress = new JLabel("Address: ");
        labelAddress.setBounds(65, 170, 150, 30);
        labelAddress.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelAddress.setForeground(new Color(255,242,45));
        add(labelAddress);
        
        addresssBox = new JComboBox<String>();
        addresssBox.setBounds(170, 170, 260, 30);
        addresssBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addresssBox.setForeground(new Color(255,242,45));
        addresssBox.setBackground(new Color(10,20,28));
        add(addresssBox);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(125, 230, 120, 30);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 230, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startDeleteCustomerAddress(int gobackindicator) {
        DeleteCustomerAddressUI deleteUIA = new DeleteCustomerAddressUI();
        deleteUIA.setBounds(0, 0, 500, 330);
        deleteUIA.setVisible(true);
        deleteUIA.setResizable(false);
        deleteUIA.setLocationRelativeTo(null);
        deleteUIA.gobackindicator = gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButton){

            try {
                int customer_addressid = Integer.parseInt(TextBeforeDot(addresssBox.getSelectedItem().toString()));
                if (customer_addressid>0){   
                deleteCustomerAddressUseCase.deleteCustomerAddress(customer_addressid);
                JOptionPane.showMessageDialog(this, "CustomerAddress deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setText("");
                addresssBox.removeAllItems();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerAddressUI customer_addressUI = new CustomerAddressUI();
            customer_addressUI.startCustomerAddress(gobackindicator);
    }
    }

    private void actualizaraddresss() {
        addresssBox.removeAllItems();
        try {
            String customerID = Name.getText();
            List<CustomerAddress> Addresss = findCustomerAddressByCustomerUseCase.findCustomerAddressByCustomer(customerID);
            for(CustomerAddress customerAddress : Addresss){
                addresssBox.addItem(String.valueOf(customerAddress.getId())+". "+customerAddress.getDetails());
            };
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    private String TextBeforeDot(String text) {
        // Buscar la posición del primer punto en la cadena
        int position = text.indexOf('.');
        if (position != -1) {
            return text.substring(0, position);
        } else {
            return text;
        }
    }
}
