package com.parking.app.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.Cliente;
import com.parking.app.model.ClienteView;

import static javax.swing.JOptionPane.showMessageDialog;

public class EliminarCliente extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static EliminarCliente instance; 

    public static EliminarCliente getInstance() {
        if (instance == null) {
            instance = new EliminarCliente();
        }
        
        return instance;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            getInstance().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public EliminarCliente() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
            final JList<ClienteView> list = new JList<ClienteView>();
            contentPanel.add(list);
            list.setListData(SistemaCocheras.getSistemaCocheras().listarClientes());
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Eliminar");
                okButton.setActionCommand("Eliminar");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if (!list.isSelectionEmpty()) {
                                SistemaCocheras.getSistemaCocheras().bajaCliente(list.getSelectedValue().getIdCliente());
    
                                list.removeAll();
                                list.setListData(SistemaCocheras.getSistemaCocheras().listarClientes());
                                showMessageDialog(null, "El cliente ha sido dado de baja");
                            }
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                });

                {
                    JButton cancelButton = new JButton("Cancelar");
                    cancelButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    cancelButton.setActionCommand("Cancel");
                    buttonPane.add(cancelButton);
                }
        }
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }


}
