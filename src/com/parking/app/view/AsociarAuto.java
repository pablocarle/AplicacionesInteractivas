package com.parking.app.view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.ClienteView;

public class AsociarAuto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldPatente;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JComboBox comboBoxClientes = new JComboBox();
	private JCheckBox chckbxEsGrande = new JCheckBox("Es grande?");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AsociarAuto dialog = new AsociarAuto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AsociarAuto() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setTitle("Asociar Auto");
		setBounds(100, 100, 450, 252);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCliente = new JLabel("Cliente");
			lblCliente.setBounds(10, 11, 46, 14);
			contentPanel.add(lblCliente);
		}
		{
			comboBoxClientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldMarca.setText("");
					textFieldModelo.setText("");
					textFieldPatente.setText("");
				}
			});
			comboBoxClientes.setBounds(220, 8, 204, 20);
			contentPanel.add(comboBoxClientes);
		}
		{
			JLabel lblPatente = new JLabel("Patente");
			lblPatente.setBounds(10, 64, 46, 14);
			contentPanel.add(lblPatente);
		}
		{
			textFieldPatente = new JTextField();
			textFieldPatente.setBounds(220, 61, 204, 20);
			contentPanel.add(textFieldPatente);
			textFieldPatente.setColumns(10);
		}
		{
			JLabel lblMarca = new JLabel("Marca");
			lblMarca.setBounds(10, 95, 46, 14);
			contentPanel.add(lblMarca);
		}
		{
			textFieldMarca = new JTextField();
			textFieldMarca.setBounds(220, 92, 204, 20);
			contentPanel.add(textFieldMarca);
			textFieldMarca.setColumns(10);
		}
		{
			JLabel lblModelo = new JLabel("Modelo");
			lblModelo.setBounds(10, 126, 46, 14);
			contentPanel.add(lblModelo);
		}
		{
			textFieldModelo = new JTextField();
			textFieldModelo.setBounds(220, 123, 204, 20);
			contentPanel.add(textFieldModelo);
			textFieldModelo.setColumns(10);
		}
		
		chckbxEsGrande.setBounds(10, 151, 97, 23);
		contentPanel.add(chckbxEsGrande);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (esValido()) {
							ClienteView cliente = (ClienteView) comboBoxClientes.getSelectedItem();
							try {
								@SuppressWarnings("unused")
								ClienteView clienteModificado = SistemaCocheras.getSistemaCocheras().asociarAuto(cliente.getIdCliente(), textFieldPatente.getText(), textFieldModelo.getText(), textFieldMarca.getText(), chckbxEsGrande.isSelected());
								showMessageDialog(null, "Auto asociado correctamente");
								limpiarTodo();
							} catch (Exception e1) {
								showMessageDialog(null, e1.getMessage());
							}
						} else {
							showMessageDialog(null, "Formulario invalido"); 
						}
					}

					private void limpiarTodo() {
						textFieldMarca.setText("");
						textFieldModelo.setText("");
						textFieldPatente.setText("");
					}

					private boolean esValido() {
						return comboBoxClientes.getSelectedIndex() >= 0 && textFieldMarca.getText().length() > 0 && textFieldModelo.getText().length() > 0 && textFieldPatente.getText().length() > 0;
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
		comboBoxClientes.removeAllItems();
		Vector<ClienteView> clientes = SistemaCocheras.getSistemaCocheras().listarClientes();
		for (ClienteView cliente : clientes) {
			comboBoxClientes.addItem(cliente);
		}
	}
}
