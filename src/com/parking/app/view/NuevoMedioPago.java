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

public class NuevoMedioPago extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombreMedioPago;
	private JTextField textFieldFTPOut;
	private JTextField textFieldFTPIn;
	private JTextField textFieldNombreArchivo;
	private JComboBox<BancoView> comboBoxBanco;

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
		setBounds(100, 100, 450, 232);
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
			lblBanco.setBounds(10, 42, 46, 14);
			contentPanel.add(lblBanco);
		}
		{
			comboBoxBanco = new JComboBox<BancoView>();
			comboBoxBanco.setBounds(211, 39, 213, 20);
			contentPanel.add(comboBoxBanco);
		}
		{
			JLabel lblNewLabel = new JLabel("FTP Salida");
			lblNewLabel.setBounds(10, 73, 94, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textFieldFTPOut = new JTextField();
			textFieldFTPOut.setBounds(211, 70, 213, 20);
			contentPanel.add(textFieldFTPOut);
			textFieldFTPOut.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("FTP Entrada");
			lblNewLabel_1.setBounds(10, 104, 94, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textFieldFTPIn = new JTextField();
			textFieldFTPIn.setBounds(211, 101, 213, 20);
			contentPanel.add(textFieldFTPIn);
			textFieldFTPIn.setColumns(10);
		}
		{
			JLabel lblNombreDeArchivo = new JLabel("Nombre de Archivo");
			lblNombreDeArchivo.setBounds(10, 135, 119, 14);
			contentPanel.add(lblNombreDeArchivo);
		}
		{
			textFieldNombreArchivo = new JTextField();
			textFieldNombreArchivo.setBounds(211, 132, 213, 20);
			contentPanel.add(textFieldNombreArchivo);
			textFieldNombreArchivo.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StringBuffer str = new StringBuffer();
						if (esValido(str)) {
							
						} else {
							showMessageDialog(null, str.toString());
						}
					}

					private boolean esValido(StringBuffer str) {
						// TODO Auto-generated method stub
						return false;
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
	    comboBoxBanco.removeAllItems();
        Vector<BancoView> bancos = SistemaCocheras.getSistemaCocheras().listarBancos();
        for (BancoView banco : bancos) {
            comboBoxBanco.addItem(banco);
        }
	}
}
