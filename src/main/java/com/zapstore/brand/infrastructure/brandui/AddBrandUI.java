package com.zapstore.brand.infrastructure.brandui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.zapstore.brand.application.CreateBrandUseCase;
import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;
import com.zapstore.brand.infrastructure.BrandRepository;

public class AddBrandUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelName;
    private JButton addButton, backButton;
    private JTextField Name;
    private int gobackindicator;
    public AddBrandUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Brand");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/brand.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Brand");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Name : ");
        labelName.setBounds(95, 130, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 130, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);


        addButton = new JButton("Add");
        addButton.setBounds(125, 170, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 170, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startAddBrand(int gobackindicator) {
        AddBrandUI addUI = new AddBrandUI();
        addUI.setBounds(0, 0, 500, 250);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator=gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                String brandName = Name.getText().trim();
                if (brandName.length()>0){
                Brand newBrand = new Brand();
                
                newBrand.setName(brandName);

                BrandService brandService = new BrandRepository();
                CreateBrandUseCase createBrandUseCase = new CreateBrandUseCase(brandService);
                createBrandUseCase.execute(newBrand);
                JOptionPane.showMessageDialog(this, "Brand added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
            this.setVisible(false);
            BrandUI brandUI = new BrandUI();
            brandUI.startBrand(gobackindicator);
    }
    }

    }

