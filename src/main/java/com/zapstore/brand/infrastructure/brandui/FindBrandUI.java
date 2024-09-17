package com.zapstore.brand.infrastructure.brandui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import com.zapstore.brand.application.FindBrandByIdUseCase;
import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;
import com.zapstore.brand.infrastructure.BrandRepository;

public class FindBrandUI extends JFrame implements ActionListener{
    private JLabel  labelcode, title, logoImg, labelName, Name;
    private JTextField codebrand;
    private JButton findButton, backButton, newButton;
    private int gobackindicator;
    BrandService brandService = new BrandRepository();
    FindBrandByIdUseCase findBrandByIdUseCase = new FindBrandByIdUseCase(brandService);

    public FindBrandUI(){
                setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Find Brand");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/brand.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Find Brand");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codebrand = new JTextField();
        codebrand.setBounds(170, 130, 220, 30);
        codebrand.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codebrand.setForeground(new Color(255,242,45));
        codebrand.setBackground(new Color(10,20,28));
        add(codebrand);

        labelName = new JLabel("Name : ");
        labelName.setBounds(95, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JLabel();
        Name.setBounds(170, 170, 220, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setVisible(false);
        add(Name);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 240, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        newButton = new JButton("New");
        newButton.setBounds(125, 240, 120, 30);
        newButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        newButton.setForeground(new Color(255,242,45));
        newButton.setBackground(new Color(10,20,28));
        newButton.addActionListener(this);
        add(newButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

    }

    public void startFindBrand(int gobackindicator) {
        FindBrandUI findUI = new FindBrandUI();
        findUI.setBounds(0, 0, 520, 350);
        findUI.setVisible(true);
        findUI.setResizable(false);
        findUI.setLocationRelativeTo(null);
        findUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                int brandCode = Integer.parseInt(codebrand.getText().trim().toUpperCase());
                if (brandCode>0){
                    Optional<Brand> brand = findBrandByIdUseCase.findBrandById(brandCode);
                    if (brand.isPresent()){
                        Name.setVisible(true);
                        Name.setText(brand.get().getName());
                    } else {
                        JOptionPane.showMessageDialog(this, "The Brand doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==newButton){
            Name.setVisible(false);
            Name.setText("");
            codebrand.setText("");
        
        }
        if(e.getSource()==backButton){
            this.setVisible(false);
            BrandUI brandUI = new BrandUI();
            brandUI.startBrand(gobackindicator);
        }

    }

    }

