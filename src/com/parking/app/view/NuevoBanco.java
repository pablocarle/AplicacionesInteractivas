package com.parking.app.view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.BancoView;
import com.parking.app.model.MedioPagoView;

public class NuevoBanco extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldNombreMedioPago;
    private JTextField textFieldFTPOut;
    private JTextField textFieldFTPIn;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            NuevoBanco dialog = new NuevoBanco();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public NuevoBanco() {
        setResizable(false);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setModal(true);
        setTitle("Nuevo Medio de Pago");
        setBounds(100, 100, 450, 180);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JLabel lblNombre = new JLabel("Nombre");
            lblNombre.setBounds(10, 11, 135, 14);
            contentPanel.add(lblNombre);
        }
        {
            textFieldNombreMedioPago = new JTextField();
            textFieldNombreMedioPago.setBounds(211, 8, 213, 20);
            contentPanel.add(textFieldNombreMedioPago);
            textFieldNombreMedioPago.setColumns(10);
        }
        {
            JLabel lblNewLabel = new JLabel("FTP Salida");
            lblNewLabel.setBounds(10, 45, 94, 14);
            contentPanel.add(lblNewLabel);
        }
        {
            textFieldFTPOut = new JTextField();
            textFieldFTPOut.setBounds(211, 42, 213, 20);
            contentPanel.add(textFieldFTPOut);
            textFieldFTPOut.setColumns(10);
        }
        {
            JLabel lblNewLabel_1 = new JLabel("FTP Entrada");
            lblNewLabel_1.setBounds(10, 76, 94, 14);
            contentPanel.add(lblNewLabel_1);
        }
        {
            textFieldFTPIn = new JTextField();
            textFieldFTPIn.setBounds(211, 73, 213, 20);
            contentPanel.add(textFieldFTPIn);
            textFieldFTPIn.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 116, 450, 39);
            contentPanel.add(buttonPane);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nombre = textFieldNombreMedioPago.getText();
                        String ftpOut = textFieldFTPOut.getText();
                        String ftpIn = textFieldFTPIn.getText();
                        BancoView bancoView = null;
                        String errores = esValido(nombre, ftpOut, ftpIn);
                        if (errores.isEmpty()) {
                            try {
                                bancoView = SistemaCocheras.getSistemaCocheras().crearBanco(nombre, ftpOut, ftpIn);
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (bancoView == null) {
                                showMessageDialog(null, "Error: Ya existe un banco con el mismo nombre.");
                            } else {
                                showMessageDialog(null, "El banco " + bancoView.getNombre() + " fue creado exitosamente!");
                                dispose();
                            }
                        } else {
                            showMessageDialog(null, errores);
                        }
                    }

                    private String esValido(String nombre, String ftpOut, String ftpIn) {
                        String error = "";
                        if (nombre.length() < 2) {
                            error = "El nombre debe ser de por lo menos dos caractéres";
                        } else if (ftpOut.length() < 2){
                            error = "El ftp de salida debe ser de por lo menos dos caractéres";
                        } else if (ftpIn.length() < 2){
                            error = "El ftp de entrada debe ser de por lo menos dos caractéres";
                        }
                        return error;
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        initData();
    }

    private void initData() {
       
    }
}
