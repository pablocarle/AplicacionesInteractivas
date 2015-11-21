package com.parking.app.view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.parking.app.controller.SistemaCocheras;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class InicializacionCocheras extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldSimples;
	private JTextField textFieldEspeciales;
	private JLabel lblCantitadTotal = new JLabel("Cantitad Total:");

	public InicializacionCocheras() throws Exception {
		super();
		if (SistemaCocheras.getSistemaCocheras().estanCocherasInicializadas()) {
			throw new Exception("Las cocheras ya se encuentran inicializadas");
		}
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setTitle("Inicializacion de Cocheras");
		getContentPane().setLayout(null);
		
		JLabel lblCocherasSimples = new JLabel("Cocheras Simples");
		lblCocherasSimples.setBounds(10, 39, 153, 14);
		getContentPane().add(lblCocherasSimples);
		
		textFieldSimples = new JTextField();
		textFieldSimples.setBounds(188, 36, 186, 20);
		getContentPane().add(textFieldSimples);
		textFieldSimples.setColumns(10);
		
		JLabel lblCocherasEspeziales = new JLabel("Cocheras Espeziales");
		lblCocherasEspeziales.setBounds(10, 64, 153, 14);
		getContentPane().add(lblCocherasEspeziales);
		
		textFieldEspeciales = new JTextField();
		textFieldEspeciales.setBounds(188, 61, 186, 20);
		getContentPane().add(textFieldEspeciales);
		textFieldEspeciales.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(232, 92, 142, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isValidForm()) {
					try {
						SistemaCocheras.getSistemaCocheras().crearCocheras(Integer.parseInt(textFieldSimples.getText()), Integer.parseInt(textFieldEspeciales.getText()));
						showMessageDialog(null, "Cocheras inicializadas correctamente");
						dispose();
					} catch (Exception e1) {
						showMessageDialog(null, e1.getMessage());
					}
				} else {
					showMessageDialog(null, "");
				}
			}

			private boolean isValidForm() {
				try {
					Integer.parseInt(textFieldEspeciales.getText());
					Integer.parseInt(textFieldSimples.getText());
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		});
		btnOk.setBounds(86, 92, 134, 23);
		getContentPane().add(btnOk);
		
		lblCantitadTotal.setBounds(10, 11, 101, 14);
		getContentPane().add(lblCantitadTotal);
		
		JLabel labelTotal = new JLabel("");
		labelTotal.setBounds(188, 11, 186, 14);
		getContentPane().add(labelTotal);
		
		initGUI();
		initData();
	}
	
	private void initData() {
		lblCantitadTotal.setText(SistemaCocheras.getSistemaCocheras().getSistemaCocherasProperty("cantidadCocheras"));
	}

	private void initGUI() {
		try {
			setSize(400, 163);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
