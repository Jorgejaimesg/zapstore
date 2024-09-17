package com.zapstore.status_sale.infrastructure.status_saleui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingUtilities;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.zapstore.status_sale.application.DeleteStatusSaleUseCase;
import com.zapstore.status_sale.application.FindAllStatusSaleUseCase;
import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;
import com.zapstore.status_sale.infrastructure.StatusSaleRepository;

public class DeleteStatusSaleUI extends JFrame implements ActionListener{
    StatusSaleService status_saleService = new StatusSaleRepository();
    DeleteStatusSaleUseCase deleteStatusSaleUseCase = new DeleteStatusSaleUseCase(status_saleService);
    FindAllStatusSaleUseCase findAllStatusSaleUseCase = new FindAllStatusSaleUseCase(status_saleService);
    List<StatusSale> countries = findAllStatusSaleUseCase.findAllStatusSale();

    private JLabel logoImg, title, labelName;
    private JButton deleteButton, backButton;
    private JComboBox<String> Name;
    private int gobackindicator;

    public DeleteStatusSaleUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Status Sale");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/status.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Status Sale");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 23));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Status: ");
        labelName.setBounds(65, 130, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JComboBox<String>();
        Name.setBounds(170, 130, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);
        Name.addItem("");
        for(StatusSale status_sale : countries){
            Name.addItem(status_sale.getDescriptionmode());
        };



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

    public void startDeleteStatusSale( int goBack) {
        DeleteStatusSaleUI deleteUIA = new DeleteStatusSaleUI();
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
                String status_saleName = Name.getSelectedItem().toString();
                if (status_saleName.length()>0){   
                deleteStatusSaleUseCase.execute(status_saleName);
                JOptionPane.showMessageDialog(this, "StatusSale deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setSelectedItem("");
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
                StatusSaleUI StatusSaleUI = new StatusSaleUI();
                StatusSaleUI.startStatusSale(gobackindicator);
            });
        });
    }
    }
    }

