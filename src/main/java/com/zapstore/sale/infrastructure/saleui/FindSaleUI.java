package com.zapstore.sale.infrastructure.saleui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import com.zapstore.sale.application.FindSaleByIdUseCase;
import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;
import com.zapstore.sale.infrastructure.SaleRepository;
import com.zapstore.sale_detail.application.FindSaleDetailBySale;
import com.zapstore.sale_detail.domain.entity.SaleDetail;
import com.zapstore.sale_detail.domain.service.SaleDetailService;
import com.zapstore.sale_detail.infrastructure.SaleDetailRepository;
import com.zapstore.id_type.application.FindIdTypeByIdUseCase;
import com.zapstore.id_type.domain.service.IdTypeService;
import com.zapstore.id_type.infrastructure.IdTypeRepository;
import com.zapstore.menu.infraestructure.SaleMenu;

public class FindSaleUI extends JFrame implements ActionListener{
    private JLabel  labelId, title, logoImg, labelName, Name, labelIdType, labelSaleType, labelEmail, labelLastName, LastName, idBox, saleBox;
    private JTextField idsale;
    private JButton findButton, backButton, newButton;
    private int gobackindicator;
    private JScrollPane Details;


    SaleService saleService = new SaleRepository();
    FindSaleByIdUseCase findSaleByIdUseCase = new FindSaleByIdUseCase(saleService);

    SaleDetailService saleDetailService = new SaleDetailRepository();
    FindSaleDetailBySale findSaleDetailBySale = new FindSaleDetailBySale(saleDetailService);

    IdTypeService idTypeService = new IdTypeRepository();
    FindIdTypeByIdUseCase findIdTypeByIdUseCase = new FindIdTypeByIdUseCase(idTypeService);

    public FindSaleUI(){
                setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update Sale");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zapsales.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Find Sale");
        title.setBounds(400, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelId = new JLabel("Id: ");
        labelId.setBounds(130, 130, 100, 30);
        labelId.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelId.setForeground(new Color(255,242,45));
        add(labelId);

        idsale = new JTextField();
        idsale.setBounds(170, 130, 220, 30);
        idsale.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idsale.setForeground(new Color(255,242,45));
        idsale.setBackground(new Color(10,20,28));
        add(idsale);

        labelIdType = new JLabel("SubTotal: ");
        labelIdType.setBounds(500, 130, 150, 30);
        labelIdType.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelIdType.setForeground(new Color(255,242,45));
        add(labelIdType);

        idBox = new JLabel();
        idBox.setBounds(600, 130, 255, 30);
        idBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idBox.setForeground(new Color(255,242,45));
        idBox.setBackground(new Color(10,20,28));
        idBox.setVisible(false);
        add(idBox);

        labelSaleType = new JLabel("Discount: ");
        labelSaleType.setBounds(500, 170, 150, 30);
        labelSaleType.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelSaleType.setForeground(new Color(255,242,45));
        add(labelSaleType);

        saleBox = new JLabel();
        saleBox.setBounds(600, 170, 255, 30);
        saleBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        saleBox.setForeground(new Color(255,242,45));
        saleBox.setBackground(new Color(10,20,28));
        saleBox.setVisible(false);
        add(saleBox);

        labelName = new JLabel("Customer: ");
        labelName.setBounds(60, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JLabel();
        Name.setBounds(170, 170, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        Name.setVisible(false);
        add(Name);

        labelLastName = new JLabel("Total: ");
        labelLastName.setBounds(110, 210, 150, 30);
        labelLastName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelLastName.setForeground(new Color(255,242,45));
        add(labelLastName);

        LastName = new JLabel();
        LastName.setBounds(170, 210, 255, 30);
        LastName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        LastName.setForeground(new Color(255,242,45));
        LastName.setBackground(new Color(10,20,28));
        LastName.setVisible(false);
        add(LastName);

        labelEmail = new JLabel("Detail : ");
        labelEmail.setBounds(500, 210, 150, 30);
        labelEmail.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelEmail.setForeground(new Color(255,242,45));
        add(labelEmail);

        Details = new JScrollPane();
        Details.setBounds(600, 210, 255, 30);
        Details.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Details.setForeground(new Color(255,242,45));
        Details.setBackground(new Color(10,20,28));
        Details.setVisible(false);
        add(Details);

        backButton = new JButton("Go Back");
        backButton.setBounds(540, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        newButton = new JButton("New");
        newButton.setBounds(400, 300, 120, 30);
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

    public void startFindSale(int gobackindicator) {
        FindSaleUI findUI = new FindSaleUI();
        findUI.setBounds(0, 0, 1000, 400);
        findUI.setVisible(true);
        findUI.setResizable(false);
        findUI.setLocationRelativeTo(null);
        findUI.gobackindicator =gobackindicator;
        
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == findButton) {
        try {
            int saleId = Integer.parseInt(idsale.getText().trim());
            Optional<Sale> sale = findSaleByIdUseCase.findSaleById(saleId);
            
            if (sale.isPresent()) {
                List<SaleDetail> saledetails = findSaleDetailBySale.findSaleDetailBySale(saleId);
                Sale foundSale = sale.get();
                
                // Mostrar información de la venta
                Name.setVisible(true);
                Name.setText(foundSale.getCustomer_id());
                idBox.setVisible(true);
                idBox.setText(String.valueOf(foundSale.getDiscount_amount()));
                saleBox.setVisible(true);
                saleBox.setText(String.valueOf(foundSale.getDiscount_percent()));
                LastName.setVisible(true);
                LastName.setText(String.valueOf(foundSale.getTotal_price()));
                Details.setVisible(true);
                
                // Crear un JPanel para contener los detalles de la venta
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Layout vertical
                
                // Iterar sobre los detalles de la venta y agregarlos al JPanel
                for (SaleDetail saleDetail : saledetails) {
                    JLabel detailLabel = new JLabel("Product ID: " + saleDetail.getProduct_id() + 
                                                    " - Quantity: " + saleDetail.getQuantity());
                    detailLabel.setForeground(new Color(255, 242, 45));  // Ajustar estilo
                    detailsPanel.setBackground(new Color(10,20,28));
                    detailsPanel.add(detailLabel); // Agregar cada etiqueta al panel
                }
                
                // Limpiar el JScrollPane antes de actualizar
                Details.setViewportView(null);
                
                // Añadir el JPanel con los detalles al JScrollPane
                Details.setViewportView(detailsPanel);
                Details.revalidate();  // Refrescar el JScrollPane
                
            } else {
                JOptionPane.showMessageDialog(this, "The Sale doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    if (e.getSource() == newButton) {
        // Limpiar los campos y ocultar etiquetas
        Name.setVisible(false);
        Name.setText("");
        idBox.setVisible(false);
        idBox.setText("");
        saleBox.setVisible(false);
        saleBox.setText("");
        LastName.setVisible(false);
        LastName.setText("");
        Details.setVisible(false);
        idsale.setText("");
    }

    if (e.getSource() == backButton) {
        this.setVisible(false);
        SaleMenu saleUI = new SaleMenu();
        saleUI.startSaleMenu(gobackindicator);
    }
}
    }

