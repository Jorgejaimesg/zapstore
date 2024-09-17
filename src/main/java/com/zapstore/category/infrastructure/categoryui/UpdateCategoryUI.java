package com.zapstore.category.infrastructure.categoryui;
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
import javax.swing.JTextField;

import com.zapstore.category.application.FindCategoryByIdUseCase;
import com.zapstore.category.application.UpdateCategoryUseCase;
import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;
import com.zapstore.category.infrastructure.CategoryRepository;

import javax.swing.JOptionPane;

public class UpdateCategoryUI extends JFrame implements ActionListener {
    private JLabel  labelcode, title, logoImg, labelName;
    private JTextField codecategory, Name;
    private JButton updateButton, findButton, backButton;
    private int gobackindicator;

    CategoryService categoryService = new CategoryRepository();
    FindCategoryByIdUseCase findCategoryByIdUseCase = new FindCategoryByIdUseCase(categoryService);
    UpdateCategoryUseCase updateCategoryUseCase = new UpdateCategoryUseCase(categoryService);

    public UpdateCategoryUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update Category");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/category.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Category");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codecategory = new JTextField();
        codecategory.setBounds(170, 130, 220, 30);
        codecategory.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codecategory.setForeground(new Color(255,242,45));
        codecategory.setBackground(new Color(10,20,28));
        add(codecategory);

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

    public void startUpdateCategory(int gobackindicator) {
        UpdateCategoryUI updateUI = new UpdateCategoryUI();
        updateUI.setBounds(0, 0, 520, 350);
        updateUI.setVisible(true);
        updateUI.setResizable(false);
        updateUI.setLocationRelativeTo(null);
        updateUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                int categoryCode = Integer.parseInt(codecategory.getText().trim().toUpperCase());
                if (categoryCode>0){
                    Optional<Category> category = findCategoryByIdUseCase.findCategoryById(categoryCode);
                    if (category.isPresent()){
                        Name.setVisible(true);
                        Name.setText(category.get().getName());
                    } else {
                        JOptionPane.showMessageDialog(this, "The Category doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==updateButton){
            int categoryid = Integer.parseInt(codecategory.getText().trim());
            String categoryName = Name.getText().trim();
            Category updatedCategory = new Category();
            updatedCategory.setName(categoryName);
            updatedCategory.setId(categoryid);
            if (categoryName.length()>0){
                updateCategoryUseCase.execute(updatedCategory);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Error to update", "Error", JOptionPane.ERROR_MESSAGE);

            }
            System.out.println(updatedCategory);
            Name.setText("");
            codecategory.setText("");
            Name.setVisible(false);
    }

        if(e.getSource()==backButton){
            this.setVisible(false);
            CategoryUI categoryUI = new CategoryUI();
            categoryUI.startCategory(gobackindicator);
        }
    }
    }
