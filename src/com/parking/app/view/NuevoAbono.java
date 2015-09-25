package com.parking.app.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NuevoAbono extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nombre;
	private JTextField dias;
	private JTextField descuento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoAbono dialog = new NuevoAbono();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoAbono() {
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Nuevo Abono");
		setBounds(100, 100, 450, 169);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 11, 108, 14);
			contentPanel.add(lblNombre);
		}
		{
			nombre = new JTextField();
			nombre.setBounds(208, 8, 226, 20);
			contentPanel.add(nombre);
			nombre.setColumns(10);
		}
		{
			JLabel lblDias = new JLabel("Dias");
			lblDias.setBounds(10, 42, 46, 14);
			contentPanel.add(lblDias);
		}
		{
			dias = new JTextField();
			dias.setBounds(208, 39, 226, 20);
			contentPanel.add(dias);
			dias.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Porcentaje Descuento");
			lblNewLabel.setBounds(10, 73, 150, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			descuento = new JTextField();
			descuento.setBounds(208, 70, 226, 20);
			contentPanel.add(descuento);
			descuento.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
