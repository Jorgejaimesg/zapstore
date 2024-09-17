package com.zapstore.supplier_order.infrastructure.supplier_orderui;

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


import com.zapstore.id_type.application.FindIdTypeByIdUseCase;
import com.zapstore.id_type.domain.service.IdTypeService;
import com.zapstore.id_type.infrastructure.IdTypeRepository;
import com.zapstore.menu.infraestructure.OrderMenu;
import com.zapstore.order_detail.application.FindOrderDetailBySale;
import com.zapstore.order_detail.domain.entity.OrderDetail;
import com.zapstore.order_detail.domain.service.OrderDetailService;
import com.zapstore.order_detail.infrastructure.OrderDetailRepository;
import com.zapstore.supplier_order.application.FindSupplierOrderByIdUseCase;
import com.zapstore.supplier_order.domain.entity.SupplierOrder;
import com.zapstore.supplier_order.domain.service.SupplierOrderService;
import com.zapstore.supplier_order.infrastructure.SupplierOrderRepository;

public class FindSupplierOrderUI extends JFrame implements ActionListener{
    private JLabel  labelId, title, logoImg, Name, labelDetails, labelTotal, Total, idBox, order_detailBox;
    private JTextField idorder_detail;
    private JButton findButton, backButton, newButton;
    private int gobackindicator;
    private JScrollPane Details;


    
    SupplierOrderService order_detailService = new SupplierOrderRepository();
    FindSupplierOrderByIdUseCase findSupplierByIdUseCase = new FindSupplierOrderByIdUseCase(order_detailService);

    OrderDetailService order_detailDetailService = new OrderDetailRepository();
    FindOrderDetailBySale findOrderDetailBySale = new FindOrderDetailBySale(order_detailDetailService);

    IdTypeService idTypeService = new IdTypeRepository();
    FindIdTypeByIdUseCase findIdTypeByIdUseCase = new FindIdTypeByIdUseCase(idTypeService);

    public FindSupplierOrderUI(){
                setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update SupplierOrder");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zap_logo.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Find Order");
        title.setBounds(400, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelId = new JLabel("Id: ");
        labelId.setBounds(130, 130, 100, 30);
        labelId.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelId.setForeground(new Color(255,242,45));
        add(labelId);

        idorder_detail = new JTextField();
        idorder_detail.setBounds(170, 130, 220, 30);
        idorder_detail.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idorder_detail.setForeground(new Color(255,242,45));
        idorder_detail.setBackground(new Color(10,20,28));
        add(idorder_detail);


        idBox = new JLabel();
        idBox.setBounds(600, 130, 255, 30);
        idBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idBox.setForeground(new Color(255,242,45));
        idBox.setBackground(new Color(10,20,28));
        idBox.setVisible(false);
        add(idBox);

        labelTotal = new JLabel("Total: ");
        labelTotal.setBounds(500, 130, 150, 30);
        labelTotal.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelTotal.setForeground(new Color(255,242,45));
        add(labelTotal);

        Total = new JLabel();
        Total.setBounds(600, 130, 255, 30);
        Total.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Total.setForeground(new Color(255,242,45));
        Total.setBackground(new Color(10,20,28));
        Total.setVisible(false);
        add(Total);

        labelDetails = new JLabel("Details : ");
        labelDetails.setBounds(60, 170, 150, 30);
        labelDetails.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelDetails.setForeground(new Color(255,242,45));
        add(labelDetails);

        Details = new JScrollPane();
        Details.setBounds(170, 170, 700, 100);
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

    public void startFindSupplierOrder(int gobackindicator) {
        FindSupplierOrderUI findUI = new FindSupplierOrderUI();
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
            int order_detailId = Integer.parseInt(idorder_detail.getText().trim());
            Optional<SupplierOrder> order_detail = findSupplierByIdUseCase.findSupplierOrderById(order_detailId);
            
            if (order_detail.isPresent()) {
                List<OrderDetail> order_detaildetails = findOrderDetailBySale.findOrderDetailBySale(order_detailId);
                SupplierOrder foundSupplierOrder = order_detail.get();
                
                
                idBox.setVisible(true);
                Total.setVisible(true);
                Total.setText(String.valueOf(foundSupplierOrder.getTotal_price()));
                Details.setVisible(true);
                
                // Crear un JPanel para contener los detalles de la venta
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Layout vertical
                
                // Iterar sobre los detalles de la venta y agregarlos al JPanel
                for (OrderDetail order_detailDetail : order_detaildetails) {
                    JLabel detailLabel = new JLabel("Product ID: " + order_detailDetail.getProduct_id() + 
                                                    " - Quantity: " + order_detailDetail.getQuantity()+
                                                    " - Supplier Id: "+ order_detailDetail.getSupplier_id()+
                                                    "- Price: " + order_detailDetail.getPurchase_price());
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
                JOptionPane.showMessageDialog(this, "The SupplierOrder doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
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
        order_detailBox.setVisible(false);
        order_detailBox.setText("");
        Total.setVisible(false);
        Total.setText("");
        Details.setVisible(false);
        idorder_detail.setText("");
    }

    if (e.getSource() == backButton) {
        this.setVisible(false);
        OrderMenu order_detailUI = new OrderMenu();
        order_detailUI.startOrderMenu(gobackindicator);
    }
}
    }

