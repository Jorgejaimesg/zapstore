package com.zapstore.sale.infrastructure.saleui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.zapstore.menu.infraestructure.SaleMenu;
import com.zapstore.sale.application.FindSaleByIdUseCase;
import com.zapstore.sale.application.UpdateSaleUseCase;
import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;
import com.zapstore.sale.infrastructure.SaleRepository;
import com.zapstore.status_sale.application.FindAllStatusSaleUseCase;
import com.zapstore.status_sale.application.FindStatusSaleByIdUseCase;
import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;
import com.zapstore.status_sale.infrastructure.StatusSaleRepository;

public class UpdateStatusSaleStockUI extends JFrame implements ActionListener{
    SaleService saleService = new SaleRepository();
    FindSaleByIdUseCase findSaleByIdUseCase = new FindSaleByIdUseCase(saleService);
    UpdateSaleUseCase updateSaleUseCase = new UpdateSaleUseCase(saleService);

    StatusSaleService statusSaleService = new StatusSaleRepository();
    FindAllStatusSaleUseCase findAllStatusSaleUseCase = new FindAllStatusSaleUseCase(statusSaleService);
    FindStatusSaleByIdUseCase findStatusSaleByIdUseCase = new FindStatusSaleByIdUseCase(statusSaleService);
    List<StatusSale> allstatus = findAllStatusSaleUseCase.findAllStatusSale();

    private JLabel logoImg, title, labelName, labelState;
    private JButton deleteButton, backButton, findButton;
    private JComboBox<String> stateBox;
    private JTextField Name;
    private int gobackindicator;

    public UpdateStatusSaleStockUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Sale");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zapsales.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Status");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Sale: ");
        labelName.setBounds(65, 130, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 130, 230, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);

        labelState = new JLabel("Status: ");
        labelState.setBounds(65, 170, 150, 30);
        labelState.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelState.setForeground(new Color(255,242,45));
        add(labelState);


        stateBox = new JComboBox<String>();
        stateBox.setBounds(170, 170, 285, 30);
        stateBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        stateBox.setForeground(new Color(255,242,45));
        stateBox.setBackground(new Color(10,20,28));
        stateBox.setVisible(false);
        add(stateBox);
        for (StatusSale statusSale : allstatus){
            stateBox.addItem(statusSale.getId()+". "+statusSale.getDescriptionmode());
        }



        deleteButton = new JButton("Update");
        deleteButton.setBounds(125, 220, 120, 30);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 55, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 220, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startUpdateSale(int goBack) {
        UpdateStatusSaleStockUI deleteUIA = new UpdateStatusSaleStockUI();
        deleteUIA.setBounds(0, 0, 500, 300);
        deleteUIA.setVisible(true);
        deleteUIA.setResizable(false);
        deleteUIA.setLocationRelativeTo(null);
        deleteUIA.gobackindicator = goBack;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){

            try {
                
                Optional<Sale> newSale =findSaleByIdUseCase.findSaleById(Integer.parseInt(Name.getText()));
                Optional<StatusSale> newStatus = findStatusSaleByIdUseCase.findStatusSaleById(newSale.get().getStatus_id());
                if (newSale.isPresent()){
                    stateBox.setVisible(true);

                    stateBox.setSelectedItem(newStatus.get().getId()+". "+newStatus.get().getDescriptionmode());
                

            }} catch (Exception ex) {
                // TODO: handle exception
            }
            
        }
    
        if (e.getSource()==deleteButton){

            try {
                Optional<Sale> newSale =findSaleByIdUseCase.findSaleById(Integer.parseInt(Name.getText()));
                if(newSale.isPresent()){

                    Sale updatedSale = newSale.get();
                    updatedSale.setStatus_id(Integer.parseInt(TextBeforeDot(stateBox.getSelectedItem().toString())));
                    updateSaleUseCase.execute(updatedSale);
                    stateBox.setVisible(false);
                    Name.setText("");
                }


                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }}
    

    if(e.getSource()==backButton){
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            dispose(); // Asegúrate de liberar recursos

            // Abre la nueva ventana 
            SwingUtilities.invokeLater(() -> {
                SaleMenu SaleMenu = new SaleMenu();
                SaleMenu.startSaleMenu(gobackindicator);
            });
        });
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
