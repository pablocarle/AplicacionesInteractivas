package com.parking.app.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.db.PoolConnection;
import com.parking.app.model.Cliente;
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
	private JTextField telefonoField;
	private static AltaCliente instance;
	private Integer idCliente = null;
	
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
		lblNombreYApellido.setBounds(10, 11, 140, 14);
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
		lblTelefono.setBounds(10, 86, 140, 14);
		contentPanel.add(lblTelefono);
		
		telefonoField = new JTextField();
		telefonoField.setBounds(162, 83, 262, 20);
		contentPanel.add(telefonoField);
		telefonoField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    String nombre = nameField.getText();
					    String domicilio = domicilioField.getText();
					    String email = emailField.getText();
					    String tel = telefonoField.getText();
					    String errors = esFormularioValido(nombre, domicilio, email, tel);
						if (errors.isEmpty()) {
							try {
							    // Cuando hay un idCliente seteado este formulario
							    // sirve para modificar datos
							    if (idCliente == null) {
    								ClienteView cliente = SistemaCocheras
    								        .getSistemaCocheras().crearCliente(nombre, domicilio, email, tel);
    								if (cliente == null) {
    								    showMessageDialog(null, "Ya existe un cliente con dicho email.");
    								} else {
    								    showMessageDialog(null, "Creado cliente!: " + cliente);
    								    clearFields();
    	                                dispose();
    								}
							    } else {
							        // XXX Que pasa si se modifica el mail al de otro cliente existente?
							        ClienteView cliente = SistemaCocheras
							                .getSistemaCocheras().modificarCliente(idCliente, nombre, domicilio, email, tel);
							        showMessageDialog(null, "Cliente modificado!: " + cliente);
							        clearFields();
	                                dispose();
							    }
							} catch (Exception e1) {
								System.err.println(e1);
							}
						} else {
						    showMessageDialog(null, errors);
						}
					}


				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    clearFields();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		addWindowListener(new java.awt.event.WindowAdapter() {
	        public void windowClosing(WindowEvent winEvt) {
	            clearFields();
	        }
	    });
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private String esFormularioValido(String nombre, String domicilio, String email, String tel) {
	    String errorMessage = "";
		if (nombre.isEmpty() || email.isEmpty()) {
		    errorMessage = "Debe indicar nombre y email.";
		} else if (nombre.length() < 2 || nombre.matches(".*\\d+.*")) {
		    errorMessage = "El nombre ingresado es inválido.";
		} else if (!email.contains("@") || !email.contains(".")
		        || email.indexOf("@") > email.indexOf(".")
		        || email.endsWith(".")) {
		    errorMessage = "Email inválido.";
		}
		return errorMessage;
	}

	private void clearFields() {
	    idCliente = null;
        nameField.setText("");
        domicilioField.setText("");
        emailField.setText("");
        telefonoField.setText("");
	}
    public AltaCliente modificar(int id) {
        idCliente = id;
        ClienteView cliente = SistemaCocheras.getSistemaCocheras().obtenerCliente(id).obtenerVista();

        nameField.setText(cliente.getNombre());
        domicilioField.setText(cliente.getDomicilio());
        emailField.setText(cliente.getEmail());
        telefonoField.setText(cliente.getTelefono());
        return instance;
    }
}
