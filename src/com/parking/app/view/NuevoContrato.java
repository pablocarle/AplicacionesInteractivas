package com.parking.app.view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.AbonoView;
import com.parking.app.model.AutoView;
import com.parking.app.model.ChequeView;
import com.parking.app.model.ClienteView;
import com.parking.app.model.ContratoView;
import com.parking.app.model.MedioPagoView;

public class NuevoContrato extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<ClienteView> comboBoxClientes = new JComboBox<ClienteView>();
	private JComboBox<AutoView> comboBoxAuto = new JComboBox<AutoView>();
	private JLabel lblMedioDePago = new JLabel("Medio de Pago");
	private JComboBox<MedioPagoView> comboBoxMediosPago = new JComboBox<MedioPagoView>();
	private JComboBox<AbonoView> comboBoxAbonos = new JComboBox<AbonoView>();
	
	private List<ChequeView> cheques = new ArrayList<ChequeView>();
	private JDialog chequesDialog = new AsignarChequesDialog(cheques);
	private final JButton btnCheques = new JButton("Cheques");
	private JTextField txtHoras;
	private JLabel lblHoras;
	{
		btnCheques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chequesDialog.setVisible(true);
			}
		});
		btnCheques.setVisible(false);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoContrato dialog = new NuevoContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoContrato() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setTitle("Nuevo Contrato");
		setBounds(100, 100, 450, 236);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCliente = new JLabel("Cliente");
			lblCliente.setBounds(10, 11, 129, 14);
			contentPanel.add(lblCliente);
		}
		
		comboBoxClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView cliente = (ClienteView) comboBoxClientes.getSelectedItem();
				List<AutoView> autos = cliente.getAutos();
				comboBoxAuto.removeAllItems();
				for (AutoView auto : autos) {
					comboBoxAuto.addItem(auto);
				}
				comboBoxAbonos.setSelectedIndex(-1);
				comboBoxMediosPago.setSelectedIndex(-1);
			}
		});
		comboBoxClientes.setBounds(192, 8, 232, 20);
		contentPanel.add(comboBoxClientes);
		
		JLabel lblAuto = new JLabel("Auto");
		lblAuto.setBounds(10, 42, 46, 14);
		contentPanel.add(lblAuto);
		
		comboBoxAuto.setBounds(192, 39, 232, 20);
		contentPanel.add(comboBoxAuto);
		
		JLabel lblAbono = new JLabel("Abono");
		lblAbono.setBounds(10, 73, 46, 14);
		contentPanel.add(lblAbono);
		comboBoxAbonos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxAbonos.getSelectedItem() != null) {
					AbonoView abono = (AbonoView) comboBoxAbonos.getSelectedItem();
					if (abono.getIdAbono() == SistemaCocheras.ABONO_SINABONO) {
						txtHoras.setVisible(true);
						lblHoras.setVisible(true);
						comboBoxMediosPago.removeAllItems();
						MedioPagoView efectivo = new MedioPagoView();
						efectivo.setIdMedioPago(SistemaCocheras.MEDIOPAGO_EFECTIVO);
						efectivo.setNombre("Efectivo");
						efectivo.setDescripcion("Efectivo");
						efectivo.setBanco(null);
						comboBoxMediosPago.addItem(efectivo);
					} else {
						txtHoras.setVisible(false);
						lblHoras.setVisible(false);
						comboBoxMediosPago.removeAllItems();
						Vector<MedioPagoView> mediosPago = SistemaCocheras.getSistemaCocheras().listarMediosPago();
						for (MedioPagoView medioPago : mediosPago) {
							comboBoxMediosPago.addItem(medioPago);
						}
					}
				}
			}
		});
		
		comboBoxAbonos.setBounds(192, 70, 232, 20);
		contentPanel.add(comboBoxAbonos);
		
		lblMedioDePago.setBounds(10, 105, 146, 14);
		contentPanel.add(lblMedioDePago);
		comboBoxMediosPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedioPagoView mpv = (MedioPagoView) comboBoxMediosPago.getSelectedItem();
				if (mpv != null) {
					if (SistemaCocheras.MEDIOPAGO_CHEQUE == mpv.getIdMedioPago()) {
						btnCheques.setVisible(true);
					} else {
						btnCheques.setVisible(false);
					}
				}
			}
		});
		
		comboBoxMediosPago.setBounds(192, 102, 232, 20);
		contentPanel.add(comboBoxMediosPago);
		
		lblHoras = new JLabel("Horas");
		lblHoras.setBounds(10, 130, 46, 14);
		contentPanel.add(lblHoras);
		lblHoras.setVisible(false);
		
		txtHoras = new JTextField();
		txtHoras.setBounds(190, 127, 234, 20);
		txtHoras.setVisible(false);
		contentPanel.add(txtHoras);
		txtHoras.setColumns(10);
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
							ClienteView cliente = (ClienteView) comboBoxClientes.getSelectedItem();
							AbonoView abono = (AbonoView) comboBoxAbonos.getSelectedItem();
							MedioPagoView medioPago = (MedioPagoView) comboBoxMediosPago.getSelectedItem();
							AutoView auto = (AutoView) comboBoxAuto.getSelectedItem();
							
							if (cliente != null && abono != null && medioPago != null && auto != null) {
								if (medioPago.getIdMedioPago() == SistemaCocheras.MEDIOPAGO_CHEQUE && cheques.isEmpty()) {
									showMessageDialog(null, "No se han cargado cheques");
								} else {
									try {
										ContratoView contrato = null;
										if (SistemaCocheras.ABONO_SINABONO == abono.getIdAbono() && txtHoras != null && txtHoras.getText().length() > 0) {
											contrato = SistemaCocheras.getSistemaCocheras().crearContratoHora(cliente.getIdCliente(), auto.getPatente(), medioPago.getIdMedioPago(), abono.getIdAbono(), Integer.parseInt(txtHoras.getText()));
											showMessageDialog(null, "Contrato creado correctamente con id " + contrato.getIdContrato());
										} else if (SistemaCocheras.ABONO_SINABONO == abono.getIdAbono() && (txtHoras == null || txtHoras.getText().length() == 0)) {
											showMessageDialog(null, "Debe especificar horas");
										} else {
											if (SistemaCocheras.MEDIOPAGO_CHEQUE == medioPago.getIdMedioPago()) {
												contrato = SistemaCocheras.getSistemaCocheras().crearContratoAbonoCheque(cliente.getIdCliente(), auto.getPatente(), medioPago.getIdMedioPago(), abono.getIdAbono(), cheques);
											} else if (SistemaCocheras.MEDIOPAGO_EFECTIVO == medioPago.getIdMedioPago()) {
												contrato = SistemaCocheras.getSistemaCocheras().crearContratoAbonoEfectivo(cliente.getIdCliente(), auto.getPatente(), medioPago.getIdMedioPago(), abono.getIdAbono());
											} else {
												contrato = SistemaCocheras.getSistemaCocheras().crearContratoAbonoDebito(cliente.getIdCliente(), auto.getPatente(), medioPago.getIdMedioPago(), abono.getIdAbono());
											}
											showMessageDialog(null, "Contrato creado correctamente con id " + contrato.getIdContrato());
										}
									} catch (Exception e1) {
										showMessageDialog(null, e1.getMessage());
									}
								}
							} else {
								showMessageDialog(null, "Faltan valores requeridos");
							}
							
						} else {
							showMessageDialog(null, str.toString());
						}
					}

					private boolean esValido(StringBuffer str) {
//						TODO Hay forma que el contrato tenga datos invalidos en el formulario (solo cliente)?
						str.append("Es invalido porque....");
						return true;
					}
				});
				
				buttonPane.add(btnCheques);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						chequesDialog.dispose();
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
		comboBoxAbonos.removeAllItems();
		Vector<AbonoView> abonos = SistemaCocheras.getSistemaCocheras().listarAbonos();
		for (AbonoView abono : abonos) {
			comboBoxAbonos.addItem(abono);
		}
		comboBoxMediosPago.removeAllItems();
		Vector<MedioPagoView> mediosPago = SistemaCocheras.getSistemaCocheras().listarMediosPago();
		for (MedioPagoView medioPago : mediosPago) {
			comboBoxMediosPago.addItem(medioPago);
		}
	}
}
