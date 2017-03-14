package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloLibro extends Conectar {

	public ModeloLibro() {
		super();
	}

	// Operaciones (insertar | borrar | modificar | seleccionar)
	public void insertar(Libro libro) throws SQLException {

		// escribir la instruccion INSERT
		try {
			PreparedStatement pst = cn.prepareStatement("INSERT INTO libros VALUES (?,?,?,?)");
			pst.setInt(1, libro.getId());
			pst.setString(2, libro.getTitulo());
			pst.setString(3, libro.getAutor());
			pst.setInt(4, libro.getNum_pag());

			pst.execute(); // EJECUTA

			System.out.println("LIBRO INSERTADO CON EXITO");

			if (pst.getUpdateCount() == 0) {
				System.out.println("NO HA INSERTADO NADA");

			}
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void borrar(int id) throws Exception {

		try {
			PreparedStatement pst = cn.prepareStatement("DELETE FROM libros WHERE id = ?");
			pst.setInt(1, id);

			pst.execute();

			if (pst.getUpdateCount() == 0) {
				System.out.println("Socio no existe...");
			} else {
				System.out.println("LIBRO BORRADO CON EXITO");
			}

		} catch (SQLException ex) {
			throw ex;
		}

	}

	public void modificar_direc(Libro libro) throws Exception {
		try {
			PreparedStatement pst = cn.prepareStatement("UPDATE libros SET num_pag=? WHERE id=?");

			pst.setInt(1, libro.getNum_pag());// primera ??
			pst.setInt(2, libro.getId());// segunda ??

			// System.out.println(pst); // muesttra lo que esta enviando a la
			// base de datos para hacer el metodo.

			pst.execute();
			if (pst.getUpdateCount() == 0) {
				System.out.println("El libro No Existe");
			} else {
				System.out.println("Modificando libro");
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public ResultSet seleccionar() throws SQLException {
		Statement st = cn.createStatement();
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM libros");
			return rs;
		} catch (SQLException e) {
			throw e;
		}

	}
	
	public String seleccionarId(int id) throws SQLException {
		Statement st = cn.createStatement();
		try {
			
			
			PreparedStatement pst = cn.prepareStatement("SELECT titulo FROM libros WHERE id = ?");
//			ResultSet rs = st.executeQuery("SELECT nombre FROM socios WHERE id = ?");
			pst.setInt(1,id);
		//	System.out.println(pst);
			pst.execute();
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			String nombre = rs.getString(1);

			
			return nombre;
					
		} catch (SQLException e) {
			throw e;
		}

	}

}
