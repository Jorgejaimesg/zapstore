package com.zapstore.payment_method.infrastructure.payment_methodui;
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
import javax.swing.SwingUtilities;

import com.zapstore.payment_method.application.FindPaymentMethodByIdUseCase;
import com.zapstore.payment_method.application.UpdatePaymentMethodUseCase;
import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;
import com.zapstore.payment_method.infrastructure.PaymentMethodRepository;

import javax.swing.JOptionPane;

public class UpdatePaymentMethodUI extends JFrame implements ActionListener {
    private JLabel  labelcode, title, logoImg, labelName;
    private JTextField codepayment_method, Name;
    private JButton updateButton, findButton, backButton;
    private int gobackindicator, openPageIndicator;

    PaymentMethodService payment_methodService = new PaymentMethodRepository();
    FindPaymentMethodByIdUseCase findPaymentMethodByIdUseCase = new FindPaymentMethodByIdUseCase(payment_methodService);
    UpdatePaymentMethodUseCase updatePaymentMethodUseCase = new UpdatePaymentMethodUseCase(payment_methodService);

    public UpdatePaymentMethodUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update Payment Method");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/payment_method.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Payment Method");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 23));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codepayment_method = new JTextField();
        codepayment_method.setBounds(170, 130, 220, 30);
        codepayment_method.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codepayment_method.setForeground(new Color(255,242,45));
        codepayment_method.setBackground(new Color(10,20,28));
        add(codepayment_method);

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

    public void startUpdatePaymentMethod(int open, int goBack) {
        UpdatePaymentMethodUI updateUI = new UpdatePaymentMethodUI();
        updateUI.setBounds(0, 0, 520, 350);
        updateUI.setVisible(true);
        updateUI.setResizable(false);
        updateUI.setLocationRelativeTo(null);
        updateUI.openPageIndicator=open;
        updateUI.gobackindicator=goBack;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                int payment_methodCode = Integer.parseInt(codepayment_method.getText().trim().toUpperCase());
                if (payment_methodCode>0){
                    Optional<PaymentMethod> payment_method = findPaymentMethodByIdUseCase.findPaymentMethodById(payment_methodCode);
                    if (payment_method.isPresent()){
                        Name.setVisible(true);
                        Name.setText(payment_method.get().getName());
                    } else {
                        JOptionPane.showMessageDialog(this, "The PaymentMethod doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==updateButton){
            int payment_methodid = Integer.parseInt(codepayment_method.getText().trim());
            String payment_methodName = Name.getText().trim();
            PaymentMethod updatedPaymentMethod = new PaymentMethod();
            updatedPaymentMethod.setName(payment_methodName);
            updatedPaymentMethod.setId(payment_methodid);
            if (payment_methodName.length()>0){
                updatePaymentMethodUseCase.execute(updatedPaymentMethod);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Error to update", "Error", JOptionPane.ERROR_MESSAGE);

            }
            System.out.println(updatedPaymentMethod);
            Name.setText("");
            codepayment_method.setText("");
            Name.setVisible(false);
    }

        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
    
                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    PaymentMethodUI paymentMethodUI = new PaymentMethodUI();
                    paymentMethodUI.startPaymentMethod(openPageIndicator, gobackindicator);
                });
            });
        }
    }
    }
