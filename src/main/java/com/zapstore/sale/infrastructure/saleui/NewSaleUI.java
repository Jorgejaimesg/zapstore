package com.zapstore.sale.infrastructure.saleui;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.zapstore.menu.infraestructure.SaleMenu;
import com.zapstore.payment_method.application.FindAllPaymentMethodUseCase;
import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;
import com.zapstore.payment_method.infrastructure.PaymentMethodRepository;
import com.zapstore.product.application.FindProductByIdUseCase;
import com.zapstore.product.application.UpdateProductUseCase;
import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;
import com.zapstore.product.infrastructure.ProductRepository;
import com.zapstore.sale.application.CreateSaleUseCase;
import com.zapstore.sale.application.FindLastSaleUseCase;
import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;
import com.zapstore.sale.infrastructure.SaleRepository;
import com.zapstore.sale_detail.application.CreateSaleDetailUseCase;
import com.zapstore.sale_detail.domain.entity.SaleDetail;
import com.zapstore.sale_detail.domain.service.SaleDetailService;
import com.zapstore.sale_detail.infrastructure.SaleDetailRepository;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSaleUI extends JFrame implements ActionListener {

    private JTextField customerIdField, productIdField, quantityField, discountField;
    private JComboBox<String> paymentMethodBox;
    private JLabel subtotalLabel, totalPriceLabel, logoImg, QuantityLabel;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JButton addProductButton, goBackButton;
    private int gobackindicator;

    ProductService productService = new ProductRepository();
    FindProductByIdUseCase findProductByIdUseCase = new FindProductByIdUseCase(productService);
    UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(productService);

    PaymentMethodService paymentMethodService = new PaymentMethodRepository();
    FindAllPaymentMethodUseCase findAllPaymentMethodUseCase = new FindAllPaymentMethodUseCase(paymentMethodService);
    List<PaymentMethod> allmethods = findAllPaymentMethodUseCase.findAllPaymentMethod();

    SaleService saleService = new SaleRepository();
    CreateSaleUseCase createSaleUseCase = new CreateSaleUseCase(saleService);
    FindLastSaleUseCase findLastSaleUseCase = new FindLastSaleUseCase(saleService);

    SaleDetailService saleDetailService = new SaleDetailRepository();
    CreateSaleDetailUseCase createSaleDetailUseCase = new CreateSaleDetailUseCase(saleDetailService);


    public NewSaleUI() {
        // Constructor vacío para poder iniciar la interfaz desde otro lugar
    }

    public void startNewSale(int goback) {
        // Lógica para inicializar la UI
        this.gobackindicator=goback;
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("New Sale");
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
        setTitle("Sales");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zapsales.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(400, 370, 200, 200);
        add(logoImg);

        customerIdField = new JTextField();
        customerIdField.setBounds(120, 20, 150, 30);
        customerIdField.setBackground(new Color(10, 20, 28)); // Fondo
        customerIdField.setForeground(new Color(255, 242, 45)); // Texto

        productIdField = new JTextField();
        productIdField.setBounds(120, 60, 150, 30);
        productIdField.setBackground(new Color(10, 20, 28)); // Fondo
        productIdField.setForeground(new Color(255, 242, 45)); // Texto

        QuantityLabel = new JLabel("Quantity: ");
        QuantityLabel.setBounds(300, 60, 100, 30);
        QuantityLabel.setBackground(new Color(10, 20, 28)); // Fondo
        QuantityLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(QuantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(360, 60, 120, 30);
        quantityField.setBackground(new Color(10, 20, 28)); // Fondo
        quantityField.setForeground(new Color(255, 242, 45)); // Texto

        addProductButton = new JButton("➕");
        addProductButton.setBounds(480, 60, 50, 30);
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

        discountField = new JTextField();
        discountField.setBounds(180, 360, 100, 30);
        discountField.setBackground(new Color(10, 20, 28)); // Fondo
        discountField.setForeground(new Color(255, 242, 45)); // Texto

        totalPriceLabel = new JLabel("0.00");
        totalPriceLabel.setBounds(180, 400, 100, 30);
        totalPriceLabel.setForeground(new Color(255, 242, 45)); // Texto
    }

    private void initializeTable() {
    // Añadimos la nueva columna "Name" a los nombres de las columnas
    String[] columnNames = {"ID", "Name", "Cantidad", "Precio", "Subtotal", "Delete"};
    tableModel = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 5; // Solo la columna "Delete" es editable
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 5 ? JButton.class : Object.class;
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
    
    TableColumn quantityColumn = columnModel.getColumn(2);
    quantityColumn.setPreferredWidth(20); // Ancho pequeño para Cantidad
    
    TableColumn priceColumn = columnModel.getColumn(3);
    priceColumn.setPreferredWidth(40); // Ancho un poco más grande para Precio
    
    TableColumn subtotalColumn = columnModel.getColumn(4);
    subtotalColumn.setPreferredWidth(40); // Ancho un poco más grande para Subtotal
    
    TableColumn deleteColumn = columnModel.getColumn(5);
    deleteColumn.setPreferredWidth(10); // Ancho pequeño para Delete

    // Configurar la columna "Delete" para usar botones
    TableColumn quitarColumn = productTable.getColumnModel().getColumn(5);
    quitarColumn.setCellRenderer(new ButtonRenderer());
    quitarColumn.setCellEditor(new ButtonEditor(new JCheckBox(), productTable));
}

    private void addComponentsToFrame() {
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setBounds(20, 20, 100, 30);
        customerIdLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(customerIdLabel);
        add(customerIdField);

        JLabel productIdLabel = new JLabel("Add product ID:");
        productIdLabel.setBounds(20, 60, 100, 30);
        productIdLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(productIdLabel);
        add(productIdField);

        add(quantityField);
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

        JLabel subtotalTextLabel = new JLabel("Subtotal:");
        subtotalTextLabel.setBounds(20, 320, 100, 30);
        subtotalTextLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(subtotalTextLabel);
        add(subtotalLabel);

        JLabel discountLabel = new JLabel("Discount:");
        discountLabel.setBounds(20, 360, 100, 30);
        discountLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(discountLabel);
        add(discountField);

        JButton applyButton = new JButton("%");
        applyButton.setBounds(280, 360, 45, 30);
        applyButton.setForeground(new Color(255, 242, 45)); // Texto
        applyButton.setBackground(new Color(10, 20, 28));
        applyButton.addActionListener(this);
        add(applyButton);
        

        JLabel totalPriceTextLabel = new JLabel("Total Price:");
        totalPriceTextLabel.setBounds(20, 400, 100, 30);
        totalPriceTextLabel.setForeground(new Color(255, 242, 45)); // Texto
        add(totalPriceTextLabel);
        add(totalPriceLabel);

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
        } else if (e.getActionCommand().equals("CHECK OUT")) { // registra la compra
            if (customerIdField.getText().length()>0){
                UIManager.put("Panel.background", new Color(10, 20, 28)); // Color de fondo del panel
                UIManager.put("OptionPane.background", new Color(10, 20, 28)); // Color de fondo del JOptionPane
                UIManager.put("Button.background", new Color(255, 242, 45)); // Color de fondo de los botones
                UIManager.put("Button.foreground", new Color(10, 20, 28)); // Color del texto de los botones
                UIManager.put("OptionPane.messageForeground", new Color(255, 242, 45)); // Color del texto del mensaje

                Object[] options = {"Complete", "In process", "Cancelled"};
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
                System.out.println("Complete");
                Status = 2; // Completed
                checkoutSale(Status);
            } else if (choice == 1) {
                System.out.println("In process");
                Status = 1; // Pending
                checkoutSale(Status);
            } else if (choice == 2) {
                System.out.println("Canceled");
                Status = 3; // Cancelled
                checkoutSale(Status);
            } else {
                System.out.println("nothing");
            }


            } else{
                JOptionPane.showMessageDialog(this, "Please fill the customer ID");
            }

        } else if (e.getActionCommand().equals("%")){ // actualiza el descuento
            float discount = 0;
            try {
                discount = Float.parseFloat(discountField.getText())/100;
            } catch (NumberFormatException ex) {
                // No hay descuento o es inválido
            }
            float subtotal=Float.parseFloat(subtotalLabel.getText()); 
            float total = subtotal - discount*subtotal;
            totalPriceLabel.setText(String.valueOf(total));
        }if (e.getSource()==goBackButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        SaleMenu saleMenu = new SaleMenu();
                        saleMenu.startSaleMenu(gobackindicator);
                    });
                });
        }
    }

    private void addProductRow() {
        Optional<Product> foundProduct = findProductByIdUseCase.findProductById(Integer.parseInt(productIdField.getText()));
        int quantity = Integer.parseInt(quantityField.getText());
        if (foundProduct.isEmpty()|| quantity<0) {
            JOptionPane.showMessageDialog(this, "Please fill in product ID, name, and quantity.");
            return;
        }
        
        // Aquí deberías buscar la información real del producto basada en el ID y nombre
        // Por ahora, usaremos datos de ejemplo
        int ProductId = foundProduct.get().getId(); // id del producto
        String name = foundProduct.get().getName(); // nombre producto
        float precio = foundProduct.get().getSale_price(); // Precio 
        float subtotal = precio * quantity; // calculo directo del subtotal
        int stock = foundProduct.get().getStock();
        int stockmin = foundProduct.get().getStock_min();
        Object[] newRow = {ProductId, name, quantity, precio,subtotal, "❌"};
        if (stock>quantity && (stockmin<(stock-quantity))){
            tableModel.addRow(newRow);
        } else if(stock>quantity && (stockmin>(stock-quantity))){
            tableModel.addRow(newRow); // advertencia pero puede venderlo
            JOptionPane.showMessageDialog(this, "you can add this product, but you must already restock");
            
        } else if (stock<quantity){
            JOptionPane.showMessageDialog(this, "there is not enough stock");
            // no se añade
        }

        // Limpiar campos después de añadir
        productIdField.setText("");
        quantityField.setText("");

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
            subtotal += Float.parseFloat(tableModel.getValueAt(i,4).toString());
        }
        subtotalLabel.setText(String.valueOf(subtotal));

        // Aplicar descuento si existe
        float discount = 0;
        try {
            discount = Float.parseFloat(discountField.getText())/100;
        } catch (NumberFormatException e) {
            // No hay descuento o es inválido
        }

        float total = subtotal - discount*subtotal;
        totalPriceLabel.setText(String.valueOf(total));
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
    private void checkoutSale(int status) {
        // Registrar Sale
        Sale NewSale = new Sale();
        NewSale.setCustomer_id(customerIdField.getText());
        NewSale.setTotal_price(Float.parseFloat(totalPriceLabel.getText())); // total
        NewSale.setDiscount_amount(Float.parseFloat(subtotalLabel.getText())); // subtotal
        if(discountField.getText().isEmpty()){
            NewSale.setDiscount_percent(0);
        }else{
            NewSale.setDiscount_percent(Float.parseFloat(discountField.getText()));
        }
        NewSale.setPayment_method_id(Integer.parseInt(TextBeforeDot(paymentMethodBox.getSelectedItem().toString())));
        NewSale.setStatus_id(status);

        createSaleUseCase.execute(NewSale);
        // Buscar ultimo Id añadido
        Optional<Sale> LastSale = findLastSaleUseCase.findLastSale();
        int SaleId = LastSale.get().getId();
        System.out.println(SaleId);
        //Registrar Sale Details
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int productid = (int) tableModel.getValueAt(i, 0); // Obtener valor de la columna "ID"
            int quantity = (int) tableModel.getValueAt(i, 2); // Obtener valor de la columna "quantity"
            float price = (float) tableModel.getValueAt(i, 3); // Obtener valor de la columna "price"
            // Mostrar los valores de cada fila
            SaleDetail newSaleDetail = new SaleDetail();
            newSaleDetail.setProduct_id(productid);
            newSaleDetail.setQuantity(quantity);
            newSaleDetail.setSale_price(price);
            newSaleDetail.setSale_id(SaleId);

            createSaleDetailUseCase.execute(newSaleDetail);
            System.out.println("sale detail created");
            //Modificar Stock
            if (status==2){
                Optional<Product> saleProduct = findProductByIdUseCase.findProductById(productid);
                Product modifyStockProduct = saleProduct.get();
                modifyStockProduct.setStock(saleProduct.get().getStock()-quantity);
                updateProductUseCase.execute(modifyStockProduct);

            }
        }

        tableModel.setRowCount(0);
        customerIdField.setText("");
        totalPriceLabel.setText("0.00");
        subtotalLabel.setText("0.00");
        discountField.setText("");
    }
}
