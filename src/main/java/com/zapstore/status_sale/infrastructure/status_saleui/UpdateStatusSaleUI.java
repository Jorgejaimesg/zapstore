package com.zapstore.status_sale.infrastructure.status_saleui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;

import com.zapstore.status_sale.application.FindStatusSaleByIdUseCase;
import com.zapstore.status_sale.application.UpdateStatusSaleUseCase;
import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;
import com.zapstore.status_sale.infrastructure.StatusSaleRepository;

import javax.swing.JOptionPane;

public class UpdateStatusSaleUI extends JFrame implements ActionListener {
    private JLabel  labelcode, title, logoImg, labelName;
    private JTextField codestatus_sale, Name;
    private JButton updateButton, findButton, backButton;
    private int gobackindicator;

    StatusSaleService status_saleService = new StatusSaleRepository();
    FindStatusSaleByIdUseCase findStatusSaleByIdUseCase = new FindStatusSaleByIdUseCase(status_saleService);
    UpdateStatusSaleUseCase updateStatusSaleUseCase = new UpdateStatusSaleUseCase(status_saleService);

    public UpdateStatusSaleUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update Status Sale");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/status.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Status Sale");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 23));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codestatus_sale = new JTextField();
        codestatus_sale.setBounds(170, 130, 220, 30);
        codestatus_sale.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codestatus_sale.setForeground(new Color(255,242,45));
        codestatus_sale.setBackground(new Color(10,20,28));
        add(codestatus_sale);

        labelName = new JLabel("Name : ");
        labelName.setBounds(95, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 170, 220, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        Name.setVisible(false);
        add(Name);

        updateButton = new JButton("Update");
        updateButton.setBounds(125, 240, 120, 30);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 240, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

    }

    public void startUpdateStatusSale(int goBack) {
        UpdateStatusSaleUI updateUI = new UpdateStatusSaleUI();
        updateUI.setBounds(0, 0, 520, 350);
        updateUI.setVisible(true);
        updateUI.setResizable(false);
        updateUI.setLocationRelativeTo(null);
        updateUI.gobackindicator = goBack;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                int status_saleCode = Integer.parseInt(codestatus_sale.getText().trim().toUpperCase());
                if (status_saleCode>0){
                    Optional<StatusSale> status_sale = findStatusSaleByIdUseCase.findStatusSaleById(status_saleCode);
                    if (status_sale.isPresent()){
                        Name.setVisible(true);
                        Name.setText(status_sale.get().getDescriptionmode());
                    } else {
                        JOptionPane.showMessageDialog(this, "The StatusSale doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==updateButton){
            int status_saleid = Integer.parseInt(codestatus_sale.getText().trim());
            String status_saleName = Name.getText().trim();
            StatusSale updatedStatusSale = new StatusSale();
            updatedStatusSale.setDescriptionmode(status_saleName);
            updatedStatusSale.setId(status_saleid);
            if (status_saleName.length()>0){
                updateStatusSaleUseCase.execute(updatedStatusSale);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Error to update", "Error", JOptionPane.ERROR_MESSAGE);

            }
            System.out.println(updatedStatusSale);
            Name.setText("");
            codestatus_sale.setText("");
            Name.setVisible(false);
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
