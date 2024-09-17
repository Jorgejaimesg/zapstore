package com.zapstore.supplier_order.infrastructure.supplier_orderui;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.zapstore.payment_method.application.FindAllPaymentMethodUseCase;
import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;
import com.zapstore.payment_method.infrastructure.PaymentMethodRepository;
import com.zapstore.product.application.FindProductByIdUseCase;
import com.zapstore.product.application.UpdateProductUseCase;
import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;
import com.zapstore.product.infrastructure.ProductRepository;
import com.zapstore.supplier.application.FindSupplierByIdUseCase;
import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;
import com.zapstore.supplier.infrastructure.SupplierRepository;
import com.zapstore.supplier_order.application.CreateSupplierOrderUseCase;
import com.zapstore.supplier_order.application.FindLastSupplierOrderUseCase;
import com.zapstore.supplier_order.domain.entity.SupplierOrder;
import com.zapstore.supplier_order.domain.service.SupplierOrderService;
import com.zapstore.supplier_order.infrastructure.SupplierOrderRepository;
import com.zapstore.supplier_product.application.FindSupplierProductByIdUseCase;
import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;
import com.zapstore.supplier_product.infrastructure.SupplierProductRepository;
import com.zapstore.menu.infraestructure.OrderMenu;
import com.zapstore.order_detail.application.CreateOrderDetailUseCase;
import com.zapstore.order_detail.domain.entity.OrderDetail;
import com.zapstore.order_detail.infrastructure.OrderDetailRepository;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSupplierOrderUI extends JFrame implements ActionListener {

    private JTextField productField, quantityField, PriceField;
    private JComboBox<String> paymentMethodBox, supplierIdField;
    private JLabel subtotalLabel, logoImg, QuantityLabel, PriceLabel;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JButton addProductButton, findproductbutton, goBackButton;
    private int gobackindicator;

    ProductService productService = new ProductRepository();
    FindProductByIdUseCase findProductByIdUseCase = new FindProductByIdUseCase(productService);

    PaymentMethodService paymentMethodService = new PaymentMethodRepository();
    FindAllPaymentMethodUseCase findAllPaymentMethodUseCase = new FindAllPaymentMethodUseCase(paymentMethodService);
    List<PaymentMethod> allmethods = findAllPaymentMethodUseCase.findAllPaymentMethod();

    SupplierOrderService supplier_orderService = new SupplierOrderRepository();
    CreateSupplierOrderUseCase createSupplierOrderUseCase = new CreateSupplierOrderUseCase(supplier_orderService);
    FindLastSupplierOrderUseCase findLastSupplierOrderUseCase = new FindLastSupplierOrderUseCase(supplier_orderService);

    OrderDetailRepository supplier_orderDetailRepository = new OrderDetailRepository();
    CreateOrderDetailUseCase createOrderDetailUseCase = new CreateOrderDetailUseCase(supplier_orderDetailRepository);

    UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(productService);

    SupplierProductService supplierProductService = new SupplierProductRepository();
    FindSupplierProductByIdUseCase findSupplierProductByIdUseCase = new FindSupplierProductByIdUseCase(supplierProductService);

    SupplierService supplierService = new SupplierRepository();
    FindSupplierByIdUseCase findSupplierByIdUseCase = new FindSupplierByIdUseCase(supplierService);
    public NewSupplierOrderUI() {
        // Constructor vacío para poder iniciar la interfaz desde otro lugar
    }

    public void startNewSupplierOrder(int goback) {
        // Lógica para inicializar la UI
        this.gobackindicator=goback;
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("New SupplierOrder");
        setSize(650, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(10, 20, 28)); // Fondo de la ventana

        initializeComponents();
        initializeTable();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initializeComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SupplierOrders");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zaporders.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(400, 370, 200, 200);
        add(logoImg);

        productField = new JTextField();
        productField.setBounds(120, 20, 180, 30);
        productField.setBackground(new Color(10, 20, 28)); // Fondo
        productField.setForeground(new Color(255, 242, 45)); // Texto

        findproductbutton = new JButton("🔍");
        findproductbutton.setBounds(302, 20, 48, 30);
        findproductbutton.setBackground(new Color(10, 20, 28)); // Fondo
        findproductbutton.setForeground(new Color(255, 242, 45)); // Texto
        findproductbutton.addActionListener(this);
        add(findproductbutton);

        supplierIdField = new JComboBox<String>();
        supplierIdField.setBounds(120, 60, 230, 30);
        supplierIdField.setBackground(new Color(10, 20, 28)); // Fondo
        supplierIdField.setForeground(new Color(255, 242, 45)); // Texto

        QuantityLabel = new JLabel("Quantity: ");
        QuantityLabel.setBounds(380, 60, 100, 30);
        QuantityLabel.setBackground(new Color(10, 20, 28)); // Fondo
        QuantityLabel.setForeground(new Color(255, 242, 45)); // Texto
        

        quantityField = new JTextField();
        quantityField.setBounds(440, 60, 100, 30);
        quantityField.setBackground(new Color(10, 20, 28)); // Fondo
        quantityField.setForeground(new Color(255, 242, 45)); // Texto

        PriceLabel = new JLabel("Price: ");
        PriceLabel.setBounds(380, 20, 100, 30);
        PriceLabel.setBackground(new Color(10, 20, 28)); // Fondo
        PriceLabel.setForeground(new Color(255, 242, 45)); // Texto
        
        PriceField = new JTextField();
        PriceField.setBounds(440, 20, 100, 30);
        PriceField.setBackground(new Color(10, 20, 28)); // Fondo
        PriceField.setForeground(new Color(255, 242, 45)); // Texto

        addProductButton = new JButton("➕");
        addProductButton.setBounds(550, 60, 50, 30);
        addProductButton.addActionListener(this);
        addProductButton.setBackground(new Color(10, 20, 28)); // Fondo
        addProductButton.setForeground(new Color(0, 242, 0)); // Texto

        goBackButton = new JButton("🔙");
        goBackButton.setBounds(570, 520, 50, 30);
        goBackButton.addActionListener(this);
        goBackButton.setBackground(new Color(10, 20, 28)); // Fondo
        goBackButton.setForeground(new Color(255, 242, 45)); // Texto

        
        paymentMethodBox = new JComboBox<>();
        paymentMethodBox.setBounds(180, 270, 200, 30);
        paymentMethodBox.setBackground(new Color(10, 20, 28)); // Fondo
        paymentMethodBox.setForeground(new Color(255, 242, 45)); // Texto
        for (PaymentMethod paymentMethod : allmethods){
            paymentMethodBox.addItem(paymentMethod.getId()+". "+ paymentMethod.getName());
        }

        subtotalLabel = new JLabel("0.00");
        subtotalLabel.setBounds(180, 320, 100, 30);
        subtotalLabel.setForeground(new Color(255, 242, 45)); // Texto
    }

    private void initializeTable() {
    // Añadimos la nueva columna "Name" a los nombres de las columnas
    String[] columnNames = {"ID", "Name", "Cantidad","Supplier", "Precio", "Subtotal", "Delete"};
    tableModel = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 6; // Solo la columna "Delete" es editable
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 6 ? JButton.class : Object.class;
        }
    };

    productTable = new JTable(tableModel);
    productTable.setRowHeight(30);
    productTable.setBackground(new Color(10, 20, 28)); // Fondo
    productTable.setForeground(new Color(255, 242, 45)); // Texto
    
    // Ajustar el ancho de las columnas
    TableColumnModel columnModel = productTable.getColumnModel();
    
    TableColumn idColumn = columnModel.getColumn(0);
    idColumn.setPreferredWidth(10); // Ancho pequeño para ID
    
    TableColumn nameColumn = columnModel.getColumn(1);
    nameColumn.setPreferredWidth(80); // Ancho mediano para Name
    
    TableColumn supplierColumn = columnModel.getColumn(2);
    supplierColumn.setPreferredWidth(10); // Ancho pequeño para Delete

    TableColumn quantityColumn = columnModel.getColumn(3);
    quantityColumn.setPreferredWidth(20); // Ancho pequeño para Cantidad
    
    TableColumn priceColumn = columnModel.getColumn(4);
    priceColumn.setPreferredWidth(40); // Ancho un poco más grande para Precio
    
    TableColumn subtotalColumn = columnModel.getColumn(5);
    subtotalColumn.setPreferredWidth(40); // Ancho un poco más grande para Subtotal
    
    TableColumn deleteColumn = columnModel.getColumn(6);
    deleteColumn.setPreferredWidth(10); // Ancho pequeño para Delete

    // Configurar la columna "Delete" para usar botones
    TableColumn quitarColumn = productTable.getColumnModel().getColumn(6);
    quitarColumn.setCellRenderer(new ButtonRenderer());
    quitarColumn.setCellEditor(new ButtonEditor(new JCheckBox(), productTable));
}

    private void addComponentsToFrame() {
        JLabel productLabel = new JLabel("Product ID:");
        productLabel.setBounds(20, 20, 100, 30);
        productLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(productLabel);
        add(productField);

        JLabel supplierLabel = new JLabel("Select Supplier:");
        supplierLabel.setBounds(20, 60, 100, 30);
        supplierLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(supplierLabel);
        add(supplierIdField);

        add(QuantityLabel);
        add(quantityField);

        add(PriceField);
        add(PriceLabel);
        add(addProductButton);
        add(goBackButton);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(20, 100, 600, 150);
        scrollPane.getViewport().setBackground(new Color(10, 20, 28)); // Fondo del Scroll
        add(scrollPane);

        JLabel paymentMethodLabel = new JLabel("Payment method:");
        paymentMethodLabel.setBounds(20, 270, 150, 30);
        paymentMethodLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(paymentMethodLabel);
        add(paymentMethodBox);

        JLabel subtotalTextLabel = new JLabel("Total:");
        subtotalTextLabel.setBounds(20, 320, 100, 30);
        subtotalTextLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(subtotalTextLabel);
        add(subtotalLabel);

        JButton checkoutButton = new JButton("CHECK OUT");
        checkoutButton.setBounds(20, 470, 150, 40);
        checkoutButton.setBackground(new Color(255, 242, 45)); // Fondo
        checkoutButton.setForeground(new Color(10, 20, 28)); // Texto
        checkoutButton.addActionListener(this);
        add(checkoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addProductButton) { // añade el producto a la tabla
            addProductRow();
        } else if(e.getSource()== findproductbutton){
            supplierIdField.removeAllItems();
            int productId = Integer.parseInt(productField.getText());
            List<SupplierProduct> supplierProducts = findSupplierProductByIdUseCase.findSupplierProductById(productId);
            for (SupplierProduct supplierProduct : supplierProducts ){
                Optional<Supplier> foundSupplier = findSupplierByIdUseCase.findSupplierById(supplierProduct.getName());
                supplierIdField.addItem(foundSupplier.get().getNit()+". "+foundSupplier.get().getName());

            }

        }else if (e.getActionCommand().equals("CHECK OUT")) { // registra la compra
                UIManager.put("Panel.background", new Color(10, 20, 28)); // Color de fondo del panel
                UIManager.put("OptionPane.background", new Color(10, 20, 28)); // Color de fondo del JOptionPane
                UIManager.put("Button.background", new Color(255, 242, 45)); // Color de fondo de los botones
                UIManager.put("Button.foreground", new Color(10, 20, 28)); // Color del texto de los botones
                UIManager.put("OptionPane.messageForeground", new Color(255, 242, 45)); // Color del texto del mensaje

                Object[] options = {"Requested", "Completed", "Cancelled"};
                int choice = JOptionPane.showOptionDialog(null,
                    "Proceeding to checkout...",
                    "The Customer already pay",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
            int Status = 0;
            // Verificar cuál opción fue seleccionada
            if (choice == 0) {
                System.out.println("Requested");
                Status = 1; // Requested
                checkoutSupplierOrder(Status);
            } else if (choice == 1) {
                System.out.println("Completed");
                Status = 2; // Completed
                checkoutSupplierOrder(Status);
            } else if (choice == 2) {
                System.out.println("Canceled");
                Status = 3; // Cancelled
                checkoutSupplierOrder(Status);
            } else {
                System.out.println("nothing");
            }

        }if (e.getSource()==goBackButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        OrderMenu orderMenu = new OrderMenu();
                        orderMenu.startOrderMenu(gobackindicator);
                    });
                });
        }
    }

    private void addProductRow() {
        Optional<Product> foundProduct = findProductByIdUseCase.findProductById(Integer.parseInt(productField.getText()));
        int quantity = Integer.parseInt(quantityField.getText());
        float price = Float.parseFloat(PriceField.getText());
        String supplierid = TextBeforeDot(supplierIdField.getSelectedItem().toString());
        if (foundProduct.isEmpty()|| quantity<0 || supplierid.isEmpty()|| price<=0 || price>foundProduct.get().getSale_price()) {
            JOptionPane.showMessageDialog(this, "Please check product ID, quantity and price.");
            return;
        }
        
        // Aquí deberías buscar la información real del producto basada en el ID y nombre
        // Por ahora, usaremos datos de ejemplo
        int ProductId = foundProduct.get().getId(); // id del producto
        String name = foundProduct.get().getName(); // nombre producto
        float subtotal = price * quantity; // calculo directo del subtotal
        Object[] newRow = {ProductId, name, quantity,supplierid, price,subtotal, "❌"};
        tableModel.addRow(newRow);


        // Limpiar campos después de añadir
        supplierIdField.removeAllItems();
        quantityField.setText("");
        productField.setText("");
        PriceField.setText("");

        updateTotals();
    }

    private void removeProductRow(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            // Para evitar problemas, forzamos a detener la edición
            if (productTable.isEditing()) {
                productTable.getCellEditor().stopCellEditing();
            }

            // Remover fila y actualizar la tabla
            tableModel.removeRow(row);
            updateTotals();
        }
    }

    private void updateTotals() {
        float subtotal = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            subtotal += Float.parseFloat(tableModel.getValueAt(i,5).toString());
        }
        subtotalLabel.setText(String.valueOf(subtotal));

    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(10, 20, 28)); // Fondo del botón
            setForeground(new Color(250, 0, 0)); // Texto del botón
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());


            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(new Color(10, 20, 28)); // Fondo del botón
            button.setForeground(new Color(255, 242, 45)); // Texto del botón
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "-" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
            
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                int modelRow = table.convertRowIndexToModel(table.getEditingRow());
                removeProductRow(modelRow); // Elimina la fila seleccionada
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
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
    private void checkoutSupplierOrder(int status) {
        // Registrar SupplierOrder
        SupplierOrder NewSupplierOrder = new SupplierOrder();
        NewSupplierOrder.setTotal_price(Float.parseFloat(subtotalLabel.getText())); // total
        NewSupplierOrder.setPayment_method_id(Integer.parseInt(TextBeforeDot(paymentMethodBox.getSelectedItem().toString())));
        NewSupplierOrder.setStatus_id(status);

        createSupplierOrderUseCase.execute(NewSupplierOrder);
        // Buscar ultimo Id añadido
        Optional<SupplierOrder> LastSupplierOrder = findLastSupplierOrderUseCase.findLastSupplierOrder();
        int SupplierOrderId = LastSupplierOrder.get().getId();
        System.out.println(SupplierOrderId);
        //Registrar SupplierOrder Details
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int productid = (int) tableModel.getValueAt(i, 0); // Obtener valor de la columna "ID"
            int quantity = (int) tableModel.getValueAt(i, 2); // Obtener valor de la columna "quantity"
            String supplier = (String) tableModel.getValueAt(i, 3); // Obtener valor de la columna "price"
            float price = (float) tableModel.getValueAt(i, 4); // Obtener valor de la columna "price"
            // Mostrar los valores de cada fila
            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setProduct_id(productid);
            newOrderDetail.setQuantity(quantity);
            newOrderDetail.setPurchase_price(price);
            newOrderDetail.setOrder_id(SupplierOrderId);
            newOrderDetail.setSupplier_id(supplier);;

            createOrderDetailUseCase.execute(newOrderDetail);
            System.out.println("supplier_order detail created");
            //Modificar Stock
            if (status==2){
                Optional<Product> supplier_orderProduct = findProductByIdUseCase.findProductById(productid);
                Product modifyStockProduct = supplier_orderProduct.get();
                modifyStockProduct.setStock(supplier_orderProduct.get().getStock()+quantity);
                updateProductUseCase.execute(modifyStockProduct);

            }
        }

        tableModel.setRowCount(0);
        productField.setText("");
        subtotalLabel.setText("0.00");
    }
}
