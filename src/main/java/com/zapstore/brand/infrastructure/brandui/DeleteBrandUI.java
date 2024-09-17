package com.zapstore.brand.infrastructure.brandui;

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

import com.zapstore.brand.application.DeleteBrandUseCase;
import com.zapstore.brand.application.FindAllBrandUseCase;
import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;
import com.zapstore.brand.infrastructure.BrandRepository;

public class DeleteBrandUI extends JFrame implements ActionListener{
    BrandService brandService = new BrandRepository();
    DeleteBrandUseCase deleteBrandUseCase = new DeleteBrandUseCase(brandService);
    FindAllBrandUseCase findAllBrandUseCase = new FindAllBrandUseCase(brandService);
    List<Brand> countries = findAllBrandUseCase.findAllBrand();

    private JLabel logoImg, title, labelName;
    private JButton deleteButton, backButton;
    private JComboBox<String> Name;
    private int gobackindicator;

    public DeleteBrandUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Brand");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/brand.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Brand");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Brand: ");
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
        for(Brand brand : countries){
            Name.addItem(brand.getName());
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

    public void startDeleteBrand(int gobackindicator) {
        DeleteBrandUI deleteUIA = new DeleteBrandUI();
        deleteUIA.setBounds(0, 0, 500, 300);
        deleteUIA.setVisible(true);
        deleteUIA.setResizable(false);
        deleteUIA.setLocationRelativeTo(null);
        deleteUIA.gobackindicator= gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButton){

            try {
                String brandName = Name.getSelectedItem().toString();
                if (brandName.length()>0){   
                deleteBrandUseCase.execute(brandName);
                JOptionPane.showMessageDialog(this, "Brand deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setSelectedItem("");
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

