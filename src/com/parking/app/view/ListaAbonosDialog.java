package com.parking.app.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.AbonoView;

public class ListaAbonosDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<AbonoView> abonoList;
	private boolean borrado;

	public ListaAbonosDialog(boolean borrado) {
		super();
		setTitle("Abonos");
		this.borrado = borrado;
		setResizable(false);
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 450, 311);
		
		abonoList = new JList<AbonoView>();
		abonoList.setBounds(12, 42, 422, 185);
		getContentPane().add(abonoList);
		
		JLabel lblAbonos = new JLabel("Abonos");
		lblAbonos.setBounds(12, 15, 70, 15);
		getContentPane().add(lblAbonos);
		
		JButton btnEliminarAbono = new JButton("Eliminar");
		btnEliminarAbono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (abonoList.getSelectedValue() != null) {
					int value = JOptionPane.showConfirmDialog(null, "Confirma eliminar el abono " + abonoList.getSelectedValue() + "?", "Eliminar Abono", JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						try {
							SistemaCocheras.getSistemaCocheras().eliminarAbono(abonoList.getSelectedValue().getIdAbono());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un abono");
				}
			}
		});
		btnEliminarAbono.setBounds(188, 239, 117, 25);
		getContentPane().add(btnEliminarAbono);
		if (!this.borrado) {
			btnEliminarAbono.setVisible(false);
		}
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(317, 239, 117, 25);
		getContentPane().add(btnCancelar);
		initData();
	}

	private void initData() {
		abonoList.setListData(SistemaCocheras.getSistemaCocheras().listarAbonos());
	}
}
