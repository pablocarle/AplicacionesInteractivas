package com.parking.app.view;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showConfirmDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.ContratoView;

public class ListaContratosDialog extends JDialog {
	
	private JList<ContratoView> listContratos;
	
	public ListaContratosDialog() {
		setModal(true);
		setResizable(false);
		setTitle("Contratos");
		setBounds(100, 100, 786, 289);
		getContentPane().setLayout(null);
		
		listContratos = new JList<ContratoView>();
		listContratos.setBounds(10, 11, 759, 210);
		getContentPane().add(listContratos);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listContratos.getSelectedIndex();
				if (index >= 0) {
					int ret = showConfirmDialog(null, "Confirma eliminación?", "Eliminar contrato", JOptionPane.YES_NO_OPTION);
					if (ret == JOptionPane.YES_OPTION) {
						try {
							SistemaCocheras.getSistemaCocheras().eliminarContrato(listContratos.getSelectedValue().getIdContrato());
							loadData();
						} catch (Exception e1) {
							showMessageDialog(null, e1.getMessage());
						}
					}
				} else {
					showMessageDialog(null, "Debe seleccionar un contrato");
				}
			}
		});
		btnEliminar.setBounds(581, 232, 89, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(680, 232, 89, 23);
		getContentPane().add(btnCerrar);
		loadData();
	}

	private void loadData() {
		listContratos.removeAll();
		listContratos.setListData(SistemaCocheras.getSistemaCocheras().obtenerContratosActivos().toArray(new ContratoView[0]));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
