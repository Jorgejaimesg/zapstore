package com.zapstore.category.infrastructure.categoryui;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;

import com.zapstore.category.application.FindAllCategoryUseCase;
import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;
import com.zapstore.category.infrastructure.CategoryRepository;
import com.zapstore.menu.infraestructure.ProductMenu;

public class CategoryUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allCategoryButton, backButton;
    private int gobackindicator;
    public CategoryUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Categorys");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/category.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Categorys");
        title.setBounds(480, 10, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 90));
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




        allCategoryButton = new JButton("All Categorys");
        allCategoryButton.setBounds(520, 415, 330, 60);
        allCategoryButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allCategoryButton.setForeground(new Color(255,242,45));
        allCategoryButton.setBackground(new Color(10,20,28));
        allCategoryButton.addActionListener(this);
        add(allCategoryButton);
    }
    public void startCategory(int gobackindicator) {
        CategoryUI categoryUI = new CategoryUI();
        categoryUI.setBounds(0, 0, 1000, 600);
        categoryUI.setVisible(true);
        categoryUI.setResizable(false);
        categoryUI.setLocationRelativeTo(null);
        categoryUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddCategoryUI addCategoryUI = new AddCategoryUI();
            addCategoryUI.startAddCategory(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteCategoryUI deleteCategoryUI = new DeleteCategoryUI();
            deleteCategoryUI.startDeleteCategory(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindCategoryUI FindCategoryUI = new FindCategoryUI();
            FindCategoryUI.startFindCategory(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateCategoryUI updateCategoryUI = new UpdateCategoryUI();
            updateCategoryUI.startUpdateCategory(gobackindicator);
        }

        if (e.getSource()==allCategoryButton){
                CategoryService categoryService = new CategoryRepository();
                FindAllCategoryUseCase findAllCategorysUseCase = new FindAllCategoryUseCase(categoryService);
                List<Category> Countries = findAllCategorysUseCase.findAllCategory();
        
                String[] columns = {"ID", "Name"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(Category -> {
                    Object[] row = {Category.getId(), Category.getName()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "Category List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                    SwingUtilities.invokeLater(() -> {
                    ProductMenu ProductMenu = new ProductMenu();
                    ProductMenu.startProductMenu(gobackindicator);
                    });

                // Abre la nueva ventana 
            });
        }

        }
    }

