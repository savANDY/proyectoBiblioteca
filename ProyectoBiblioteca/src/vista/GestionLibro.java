package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controlador.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionLibro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControladorLibro controladorLibro;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionLibro dialog = new GestionLibro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionLibro() {
		setBounds(100, 100, 500, 443);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GestionLibro.class.getResource("/imagenes/gestion_libros.jpg")));
		label.setBounds(295, 12, 177, 139);
		contentPanel.add(label);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID libro", "Titulo", "Autor", "Paginas"
			}
		));
		table.setBounds(10, 12, 275, 348);
		contentPanel.add(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnInsertarLibro = new JButton("Insertar Libro");
			btnInsertarLibro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					controladorLibro = new ControladorLibro();
					controladorLibro.abrirNuevoLibro();
					
				}
			});
			buttonPane.add(btnInsertarLibro);
			
			JButton btnBorrarLibro = new JButton("Borrar Libro");
			btnBorrarLibro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					controladorLibro = new ControladorLibro();
					controladorLibro.abrirBorrarLibro();
					
				}
			});
			buttonPane.add(btnBorrarLibro);
			
			JButton btnModificarLibro = new JButton("Modificar Libro");
			btnModificarLibro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					controladorLibro = new ControladorLibro();
					controladorLibro.abrirModificarLibro();
					
				}
			});
			buttonPane.add(btnModificarLibro);
			
			JButton btnConsultarLibro = new JButton("Consultar Libro");
			btnConsultarLibro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					controladorLibro = new ControladorLibro();
					controladorLibro.abrirConsultarLibro();
					
				}
			});
			buttonPane.add(btnConsultarLibro);
		}
	}
}
