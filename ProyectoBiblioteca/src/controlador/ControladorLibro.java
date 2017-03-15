package controlador;

import javax.swing.JOptionPane;

import modelo.Libro;
import modelo.ModeloLibro;
import vista.BorrarLibro;
import vista.ConsultarLibro;
import vista.GestionLibro;
import vista.ModificarLibro;
import vista.NuevoLibro;

public class ControladorLibro {

	private GestionLibro gestionLibro;
	private NuevoLibro nuevoLibro;
	private BorrarLibro borrarLibro;
	private ModificarLibro modificarLibro;
	private ConsultarLibro consultarLibro;
	private ModeloLibro modeloLibro;

	public void insertarLibro(String titulo, String autor, int num_pag) {

		Libro libro = new Libro();

		libro.setTitulo(titulo);
		libro.setAutor(autor);
		libro.setNum_pag(num_pag);

		modeloLibro = new ModeloLibro();

		try {
			modeloLibro.insertar(libro);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al insertar nuevo libro");
		}
	}

	
	public void abrirNuevoLibro() {

		nuevoLibro = new NuevoLibro();
		this.nuevoLibro.setVisible(true);

	}
	
	public void abrirModificarLibro() {

		modificarLibro = new ModificarLibro();
		this.modificarLibro.setVisible(true);

	}
	
	public void abrirGestionLibro() {

		gestionLibro = new GestionLibro();
		this.gestionLibro.setVisible(true);

	}

	public void abrirBorrarLibro() {

		borrarLibro = new BorrarLibro();
		this.borrarLibro.setVisible(true);

	}

	public void abrirConsultarLibro() {

		consultarLibro = new ConsultarLibro();
		this.consultarLibro.setVisible(true);

	}

}
