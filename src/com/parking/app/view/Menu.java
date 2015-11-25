package com.parking.app.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


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
public class Menu extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem1;
	private JMenuItem mniInicializar;
	private JMenu mnCocheras;
	private JMenu jMenu1;
	private JMenu mnClientes;
	private JMenu mnAbonos;
	private JMenu mnMediosDePago;
	private JMenuItem mntmNuevoMedioDe;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Menu inst = new Menu();
			}
		});
	}
	
	public Menu() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setTitle("Sistema de Administracion de Cocheras");
			JFrame.setDefaultLookAndFeelDecorated(true);
			this.setMinimumSize(new java.awt.Dimension(800, 600));
			this.setResizable(false);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
				    mnClientes = new JMenu("Clientes");
				    jMenuBar1.add(mnClientes);
				    
				    JMenuItem mntmAlta = new JMenuItem("Alta");
				    mntmAlta.addActionListener(new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
				            AltaCliente.getInstance().setVisible(true);
				        
				        }
				    });
				    mnClientes.add(mntmAlta);
				    
				    JMenuItem mntmModificar = new JMenuItem("Modificar");
				    mntmModificar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	ModificarCliente.getInstance().refresh();
                            ModificarCliente.getInstance().setVisible(true);
                        }
                    });
				    mnClientes.add(mntmModificar);
				    
				    JMenuItem mntmBaja = new JMenuItem("Baja");
				    mntmBaja.addActionListener(new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
				            EliminarCliente.getInstance().setVisible(true);
				        }
				    });
				    
				    JMenuItem mntmAsociarAuto = new JMenuItem("Asociar Auto");
				    mntmAsociarAuto.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent e) {
				    		JDialog dialog = new AsociarAuto();
				    		dialog.setVisible(true);
				    	}
				    });
				    mnClientes.add(mntmAsociarAuto);
				    
				    JMenuItem mntmAdministrarAutos = new JMenuItem("Administrar Autos");
				    mntmAdministrarAutos.addActionListener(new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
				            AdministrarAutos.getInstance().setVisible(true);
				        }
				    });
				    mnClientes.add(mntmAdministrarAutos);
				    mnClientes.add(mntmBaja);
				}
				{
					mnCocheras = new JMenu();
					jMenuBar1.add(mnCocheras);
					mnCocheras.setText("Cocheras");
					{
						mniInicializar = new JMenuItem();
						mnCocheras.add(mniInicializar);
						mniInicializar.setText("Inicializar");
						mniInicializar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								JDialog dialog;
								try {
									dialog = new InicializacionCocheras();
									dialog.setVisible(true);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							}
						});
					}
				}
				
				JMenu mnContratos = new JMenu("Contratos");
				jMenuBar1.add(mnContratos);
				
				JMenuItem mntmNuevoContrato = new JMenuItem("Nuevo Contrato");
				mntmNuevoContrato.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JDialog dialog = new NuevoContrato();
						dialog.setVisible(true);
					}
				});
				mnContratos.add(mntmNuevoContrato);
				
				JMenuItem mntmBajaContrato = new JMenuItem("Baja Contrato");
				mnContratos.add(mntmBajaContrato);
				
				mnAbonos = new JMenu("Abonos");
				jMenuBar1.add(mnAbonos);
				
				JMenuItem mntmNuevoAbono = new JMenuItem("Nuevo Abono");
				mntmNuevoAbono.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JDialog nuevoAbonoDialog = new NuevoAbono();
					    nuevoAbonoDialog.setVisible(true);
					}
				});
				mnAbonos.add(mntmNuevoAbono);
				
				JMenuItem mntmEliminarAbono = new JMenuItem("Eliminar Abono");
				mntmEliminarAbono.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JDialog  listarAbonosDialog = new ListaAbonosDialog(true);
						listarAbonosDialog.setVisible(true);
					}
				});
				mnAbonos.add(mntmEliminarAbono);
				
				JMenuItem mntmListarAbonos = new JMenuItem("Listar Abonos");
				mntmListarAbonos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JDialog listarAbonosDialog = new ListaAbonosDialog(false);
						listarAbonosDialog.setVisible(true);
					}
				});
				mnAbonos.add(mntmListarAbonos);
				
				mnMediosDePago = new JMenu("Medios de Pago");
				jMenuBar1.add(mnMediosDePago);
				
				mntmNuevoMedioDe = new JMenuItem("Nuevo Medio de Pago");
				mntmNuevoMedioDe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    JDialog dialog = new NuevoMedioPago();
                        dialog.setVisible(true);
                        //dialog.initData();
					}
				});
				mnMediosDePago.add(mntmNuevoMedioDe);
				
				JMenuItem mntmEliminarMedioDe = new JMenuItem("Eliminar Medio de Pago");
				mntmEliminarMedioDe.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        JDialog dialog = new EliminarMedioPago();
                        dialog.setVisible(true);
				    }
				});
				mnMediosDePago.add(mntmEliminarMedioDe);
				
				mnNewMenu = new JMenu("Bancos");
				jMenuBar1.add(mnNewMenu);
				
				mntmNewMenuItem_1 = new JMenuItem("Procesar pagos");
				mntmNewMenuItem_1.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    }
				});
				mnNewMenu.add(mntmNewMenuItem_1);

				mntmNewMenuItem = new JMenuItem("Nuevo Banco");
				mntmNewMenuItem.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        JDialog dialog = new NuevoBanco();
                        dialog.setVisible(true);
				    }
				});
				mnNewMenu.add(mntmNewMenuItem);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("Salir del Sistema");
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Salir");
						jMenuItem1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) 
							{
								System.exit(0);
							}
						});
					}
				}
			}
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			pack();
			setLocationRelativeTo(null);
			setSize(800, 600);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
