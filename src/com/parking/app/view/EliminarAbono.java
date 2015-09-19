package com.parking.app.view;

import static javax.swing.JOptionPane.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dialog.ModalityType;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import com.parking.app.controller.SistemaCocheras;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarAbono extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarAbono dialog = new EliminarAbono();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarAbono() {
		setTitle("Eliminar Abono");
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(100, 100, 450, 146);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAbono = new JLabel("Abono");
			lblAbono.setBounds(10, 11, 46, 14);
			contentPanel.add(lblAbono);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(221, 8, 213, 20);
			contentPanel.add(comboBox);
		}
		{
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int option = showConfirmDialog(null, "Confirma?");
					if (option == 1) {
//						SistemaCocheras.getSistemaCocheras().eliminarAbono()
					} else {
						
					}
				}
			});
			btnEliminar.setBounds(345, 51, 89, 23);
			contentPanel.add(btnEliminar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
