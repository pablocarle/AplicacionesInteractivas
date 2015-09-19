package com.parking.app.view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.parking.app.controller.SistemaCocheras;
import com.parking.app.model.ClienteView;

public class ModificarCliente extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private static ModificarCliente instance; 
    
    private final JList list = new JList();

    public static ModificarCliente getInstance() {
        if (instance == null) {
            instance = new ModificarCliente();
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
    public ModificarCliente() {
    	setModal(true);
    	setModalityType(ModalityType.APPLICATION_MODAL);
    	setResizable(false);
    	setTitle("Modificar Cliente");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
            contentPanel.add(list);
            list.setListData(SistemaCocheras.getSistemaCocheras().listarClientes());
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            
            JButton okButton = new JButton("Modificar");
            okButton.setActionCommand("Modificar");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
            okButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        if (!list.isSelectionEmpty()) {
                            JDialog dialog = AltaCliente.getInstance().modificar(((ClienteView)list.getSelectedValue()).getIdCliente());
                            dialog.setVisible(true);
                            dialog.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    list.removeAll();
                                    list.setListData(SistemaCocheras.getSistemaCocheras().listarClientes());
                                }
                            });
                        }
                    } catch (Exception e1) {
                    	showMessageDialog(null, e1.getMessage());
                    }
                }
            });

            JButton cancelButton = new JButton("Cancelar");
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

	public void refresh() {
		list.removeAll();
		list.setListData(SistemaCocheras.getSistemaCocheras().listarClientes());
	}
}
