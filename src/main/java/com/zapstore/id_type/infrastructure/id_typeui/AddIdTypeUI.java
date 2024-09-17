package com.zapstore.id_type.infrastructure.id_typeui;

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

import com.zapstore.id_type.application.CreateIdTypeUseCase;
import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;
import com.zapstore.id_type.infrastructure.IdTypeRepository;

public class AddIdTypeUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelName;
    private JButton addButton, backButton;
    private JTextField Name;
    private int gobackindicator;

    public AddIdTypeUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add IdType");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/id_type.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Id Type");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 25));
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

    public void startAddIdType(int gobackindicator) {
        AddIdTypeUI addUI = new AddIdTypeUI();
        addUI.setBounds(0, 0, 500, 250);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                String id_typeName = Name.getText().trim();
                if (id_typeName.length()>0){
                IdType newIdType = new IdType();
                
                newIdType.setName(id_typeName);

                IdTypeService id_typeService = new IdTypeRepository();
                CreateIdTypeUseCase createIdTypeUseCase = new CreateIdTypeUseCase(id_typeService);
                createIdTypeUseCase.execute(newIdType);
                JOptionPane.showMessageDialog(this, "IdType added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
            IdTypeUI id_typeUI = new IdTypeUI();
            id_typeUI.startIdType(gobackindicator);
    }
    }

    }

