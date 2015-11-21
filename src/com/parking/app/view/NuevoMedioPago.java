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

public class NuevoMedioPago extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombreMedioPago;
	private JComboBox<BancoView> comboBoxBanco;
	private JTextField textFieldDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoMedioPago dialog = new NuevoMedioPago();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoMedioPago() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setTitle("Nuevo Medio de Pago");
		setBounds(100, 100, 450, 170);
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
			JLabel lblBanco = new JLabel("Banco");
			lblBanco.setBounds(10, 66, 46, 14);
			contentPanel.add(lblBanco);
		}
		{
			comboBoxBanco = new JComboBox<BancoView>();
			comboBoxBanco.setBounds(211, 63, 213, 20);
			contentPanel.add(comboBoxBanco);
		}
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(10, 40, 135, 14);
		contentPanel.add(lblDescripcin);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(211, 37, 213, 20);
		contentPanel.add(textFieldDescripcion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 97, 450, 39);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    String nombre = textFieldNombreMedioPago.getText();
                        String descripcion = textFieldDescripcion.getText();
                        BancoView bancoView = (BancoView) comboBoxBanco.getSelectedItem();
                        MedioPagoView medioPagoView = null;
						String errores = esValido(nombre, descripcion);
						if (errores.isEmpty()) {
							try {
							    Integer idBanco = (bancoView == null) ? null : bancoView.getIdBanco();
                                medioPagoView = SistemaCocheras.getSistemaCocheras().crearMedioPago(nombre, idBanco, descripcion);
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
							if (medioPagoView == null) {
                                showMessageDialog(null, "Error: Ya existe un medio de pago con el mismo nombre.");
                            } else {
                                showMessageDialog(null, "El medio de pago " + medioPagoView.getNombre() + " fue creado exitosamente!");
                                dispose();
                            }
						} else {
							showMessageDialog(null, errores);
						}
					}

					private String esValido(String nombre, String descripcion) {
                        String error = "";
                        if (nombre.length() < 2) {
                            error = "El nombre debe ser de por lo menos dos caractéres";
                        } else if (descripcion.length() < 2){
                            error = "La descripción debe ser de por lo menos dos caractéres";
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

	public void initData() {
	    comboBoxBanco.removeAllItems();
        Vector<BancoView> bancos = SistemaCocheras.getSistemaCocheras().listarBancos();
        for (BancoView banco : bancos) {
            comboBoxBanco.addItem(banco);
        }
        comboBoxBanco.setSelectedIndex(-1);
	}
}
