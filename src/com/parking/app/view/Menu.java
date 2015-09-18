package com.parking.app.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Menu extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private JMenu mnClientes;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
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
				    mnClientes.add(mntmBaja);
				}
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
