package com.parking.app.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.ClienteView;
import static javax.swing.JOptionPane.showMessageDialog;

public class AltaCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField domicilioField;
	private JTextField emailField;
	private JTextField textField;
	private static AltaCliente instance;
	
	public static AltaCliente getInstance() {
	    if (instance == null) {
	        instance = new AltaCliente();
	    }
	    
	    return instance;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    getInstance().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaCliente() {
		setTitle("Alta de Cliente");
		setBounds(100, 100, 450, 198);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y Apellido");
		lblNombreYApellido.setBounds(10, 11, 103, 14);
		contentPanel.add(lblNombreYApellido);
		
		nameField = new JTextField();
		nameField.setBounds(162, 8, 262, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 36, 59, 14);
		contentPanel.add(lblDomicilio);
		
		domicilioField = new JTextField();
		domicilioField.setBounds(162, 33, 262, 20);
		contentPanel.add(domicilioField);
		domicilioField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 61, 46, 14);
		contentPanel.add(lblEmail);
		
		emailField = new JTextField();
		emailField.setBounds(162, 58, 262, 20);
		contentPanel.add(emailField);
		emailField.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 86, 46, 14);
		contentPanel.add(lblTelefono);
		
		textField = new JTextField();
		textField.setBounds(162, 83, 262, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (esFormularioValido()) {
							try {
								ClienteView cliente = SistemaCocheras.getSistemaCocheras().crearCliente(nameField.getText(), domicilioField.getText(), emailField.getText(), null);
								showMessageDialog(null, "Creado cliente!: " + cliente);
								dispose();
							} catch (Exception e1) {
								System.err.println(e1);
							}
						}
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
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private boolean esFormularioValido() {
		// TODO Auto-generated method stub
		return true;
	}
}
