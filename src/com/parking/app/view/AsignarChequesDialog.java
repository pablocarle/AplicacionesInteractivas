package com.parking.app.view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import com.parking.app.model.ChequeView;

public class AsignarChequesDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ChequeView> cheques;
	private JTextField txtNumero;
	private JTextField txtEntidad;
	private JTextField txtFecha;
	private JTextField txtMonto;
	
	private JList<ChequeView> chequeList;

	public AsignarChequesDialog(List<ChequeView> cheques) {
		super();
		setBounds(100, 100, 534, 366);
		setResizable(false);
		setModal(true);
		cheques.clear();
		this.cheques = cheques;
		getContentPane().setLayout(null);
		
		chequeList = new JList<>();
		chequeList.setBounds(12, 12, 504, 131);
		getContentPane().add(chequeList);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esFormValido()) {
					ChequeView cv = new ChequeView();
					cv.setEntidad(txtEntidad.getText());
					SimpleDateFormat sdf = new SimpleDateFormat();
					Date fecha;
					try {
						fecha = sdf.parse(txtFecha.getText());
						cv.setFecha(fecha);
						cv.setMonto(new BigDecimal(Double.parseDouble(txtMonto.getText())));
						cv.setNumero(txtNumero.getText());
						
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnAgregar.setBounds(12, 295, 117, 25);
		getContentPane().add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = chequeList.getSelectedIndex();
				if (index >= 0) {
					chequeList.remove(index);
				} else {
					showMessageDialog(null, "Debe seleccionar un cheque");
				}
			}
		});
		btnEliminar.setBounds(141, 295, 117, 25);
		getContentPane().add(btnEliminar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAceptar.setBounds(270, 295, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(399, 295, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(12, 155, 70, 15);
		getContentPane().add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(167, 155, 349, 19);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblEntidad = new JLabel("Entidad");
		lblEntidad.setBounds(12, 182, 70, 15);
		getContentPane().add(lblEntidad);
		
		txtEntidad = new JTextField();
		txtEntidad.setBounds(167, 180, 349, 19);
		getContentPane().add(txtEntidad);
		txtEntidad.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(12, 209, 70, 15);
		getContentPane().add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(167, 207, 349, 19);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(12, 236, 70, 15);
		getContentPane().add(lblMonto);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(167, 232, 349, 19);
		getContentPane().add(txtMonto);
		txtMonto.setColumns(10);
		loadCheques();
	}

	private void loadCheques() {
		chequeList.setListData(cheques.toArray(new ChequeView[0]));
	}
	
	private boolean esFormValido() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.parse(txtFecha.getText());
			Double.parseDouble(txtMonto.getText());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
