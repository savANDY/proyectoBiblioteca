package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorLibro;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NuevoLibro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField titulo;
	private JTextField autor;
	private JTextField num_pag;
	private JTextPane txtpnTitulo;
	private JTextPane txtpnAutor;
	private ControladorLibro controladorLibro;
	private JTextPane txtpnPginas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoLibro dialog = new NuevoLibro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoLibro() {
		setTitle("A\u00F1adir nuevo libro");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.setBounds(202, 63, 113, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		titulo = new JTextField();
		titulo.setColumns(10);
		titulo.setBounds(202, 94, 113, 20);
		contentPanel.add(titulo);
		
		autor = new JTextField();
		autor.setColumns(10);
		autor.setBounds(202, 125, 113, 20);
		contentPanel.add(autor);
		
		num_pag = new JTextField();
		num_pag.setColumns(10);
		num_pag.setBounds(202, 156, 113, 20);
		contentPanel.add(num_pag);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setEditable(false);
		txtpnId.setText("ID");
		txtpnId.setOpaque(false);
		txtpnId.setBounds(325, 63, 23, 20);
		contentPanel.add(txtpnId);
		
		txtpnTitulo = new JTextPane();
		txtpnTitulo.setText("Titulo");
		txtpnTitulo.setOpaque(false);
		txtpnTitulo.setEditable(false);
		txtpnTitulo.setBounds(325, 94, 75, 20);
		contentPanel.add(txtpnTitulo);
		
		txtpnAutor = new JTextPane();
		txtpnAutor.setText("Autor");
		txtpnAutor.setOpaque(false);
		txtpnAutor.setEditable(false);
		txtpnAutor.setBounds(325, 125, 75, 20);
		contentPanel.add(txtpnAutor);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controladorLibro = new ControladorLibro();
				controladorLibro.insertarLibro(titulo.getText(), autor.getText(), Integer.parseInt(num_pag.getText()));
				
			}
		});
		btnOk.setBounds(212, 187, 89, 23);
		contentPanel.add(btnOk);
		
		txtpnPginas = new JTextPane();
		txtpnPginas.setText("P\u00E1ginas");
		txtpnPginas.setOpaque(false);
		txtpnPginas.setEditable(false);
		txtpnPginas.setBounds(325, 156, 75, 20);
		contentPanel.add(txtpnPginas);
	}
}
