package com.zapstore.category.infrastructure.categoryui;

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

import com.zapstore.category.application.DeleteCategoryUseCase;
import com.zapstore.category.application.FindAllCategoryUseCase;
import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;
import com.zapstore.category.infrastructure.CategoryRepository;

public class DeleteCategoryUI extends JFrame implements ActionListener{
    CategoryService categoryService = new CategoryRepository();
    DeleteCategoryUseCase deleteCategoryUseCase = new DeleteCategoryUseCase(categoryService);
    FindAllCategoryUseCase findAllCategoryUseCase = new FindAllCategoryUseCase(categoryService);
    List<Category> countries = findAllCategoryUseCase.findAllCategory();

    private JLabel logoImg, title, labelName;
    private JButton deleteButton, backButton;
    private JComboBox<String> Name;
    private int gobackindicator;
    public DeleteCategoryUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Category");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/category.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Category");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Category: ");
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
        for(Category category : countries){
            Name.addItem(category.getName());
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

    public void startDeleteCategory(int gobackindicator) {
        DeleteCategoryUI deleteUIA = new DeleteCategoryUI();
        deleteUIA.setBounds(0, 0, 500, 300);
        deleteUIA.setVisible(true);
        deleteUIA.setResizable(false);
        deleteUIA.setLocationRelativeTo(null);
        deleteUIA.gobackindicator = gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButton){

            try {
                String categoryName = Name.getSelectedItem().toString();
                if (categoryName.length()>0){   
                deleteCategoryUseCase.execute(categoryName);
                JOptionPane.showMessageDialog(this, "Category deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
            CategoryUI categoryUI = new CategoryUI();
            categoryUI.startCategory(gobackindicator);
    }
    }
    }

