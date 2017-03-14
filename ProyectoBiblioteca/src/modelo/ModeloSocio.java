package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeloSocio extends Conectar {

	public ModeloSocio() {
		super();
	}

	// Operaciones (insertar | borrar | modificar | seleccionar)
	public void insertar(Socio socio) throws SQLException {

		// escribir la instruccion INSERT
		try {
			PreparedStatement pst = cn.prepareStatement("INSERT INTO SOCIOS VALUES (?,?,?,?,?,?,?)");
			pst.setInt(1, socio.getId());
			pst.setString(2, socio.getNombre());
			pst.setString(3, socio.getApellido());
			pst.setString(4, socio.getDireccion());
			pst.setString(5, socio.getPoblacion());
			pst.setString(6, socio.getProvincia());
			pst.setString(7, socio.getDni());

			pst.execute(); // EJECUTA

			System.out.println("SOCIO INSERTADO CON EXITO");

			if (pst.getUpdateCount() == 0) {
				System.out.println("NO HA INSERTADO NADA");

			}
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void borrar(int id) throws Exception {

		try {
			PreparedStatement pst = cn.prepareStatement("DELETE FROM socios WHERE id = ?");
			pst.setInt(1, id);

			pst.execute();

			if (pst.getUpdateCount() == 0) {
				System.out.println("Socio no existe...");
			} else {
				System.out.println("SOCIO BORRADO CON EXITO");
			}

		} catch (SQLException ex) {
			throw ex;
		}

	}

	public void modificar_direc(Socio socio) throws Exception {
		try {
			PreparedStatement pst = cn.prepareStatement("UPDATE socios SET direccion=? WHERE id=?");

			pst.setString(1, socio.getDireccion());// primera ??
			pst.setInt(2, socio.getId());// segunda ??

			// System.out.println(pst); // muesttra lo que esta enviando a la
			// base de datos para hacer el metodo.

			pst.execute();
			if (pst.getUpdateCount() == 0) {
				System.out.println("El Socio No Existe");
			} else {
				System.out.println("Modificando Socio");
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public ResultSet seleccionar() throws SQLException {
		Statement st = cn.createStatement();
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM alumno");
			return rs;
		} catch (SQLException e) {
			throw e;
		}

	}

	public ArrayList<Socio> seleccionarTodos() throws Exception {

		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM socios");

		ArrayList<Socio> socios = new ArrayList<Socio>();

		while (rs.next()) {

			Socio socio = new Socio();

			socio.setId(rs.getInt(1));
			socio.setNombre(rs.getString(2));
			socio.setApellido(rs.getString(3));
			socio.setDireccion(rs.getString(4));
			socio.setPoblacion(rs.getString(5));
			socio.setProvincia(rs.getString(6));
			socio.setDni(rs.getString(7));
			socios.add(socio);

		}

		return socios;

	}

	public ArrayList<Socio> seleccionarPorDni(String dni) throws Exception {

		PreparedStatement pst = cn.prepareStatement("SELECT * FROM socios WHERE dni = ?");
		pst.setString(1, dni);

		// System.out.println(pst);

		ResultSet rs = pst.executeQuery();

		ArrayList<Socio> socios = new ArrayList<Socio>();

		// Solo hay 1 registro
		rs.next();
		Socio socio = new Socio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7));

		socios.add(socio);

		return socios;
	}

	public ArrayList<Socio> seleccionarPorPoblacion(String poblacion) throws Exception {

		PreparedStatement pst = cn.prepareStatement("SELECT * FROM socios WHERE poblacion = ?");
		pst.setString(1, poblacion);

		// System.out.println(pst);

		ResultSet rs = pst.executeQuery();

		ArrayList<Socio> socios = new ArrayList<Socio>();

		// Solo hay 1 registro
		rs.next();

		while (rs.next()) {


			Socio socio = new Socio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7));

			socios.add(socio);
		}

		return socios;
	}
	
	public String seleccionarId(int id) throws SQLException {
		Statement st = cn.createStatement();
		try {
			
			
			PreparedStatement pst = cn.prepareStatement("SELECT nombre, apellido FROM socios WHERE id = ?");
//			ResultSet rs = st.executeQuery("SELECT nombre FROM socios WHERE id = ?");
			pst.setInt(1,id);
			
		//	System.out.println(pst);
			pst.execute();
			ResultSet rs = pst.executeQuery();
			
			String nombre_completo = "ERROR AL BUSCAR NOMBRE";
			
		
			while (rs.next()){
			nombre_completo = rs.getString(1) + " " + rs.getString(2);
			}
			
			return nombre_completo;
					
		} catch (SQLException e) {
			throw e;
		}

	}
	
	public int seleccionarNombrePorId(String nombre, String apellido) throws SQLException {
		Statement st = cn.createStatement();
		try {
			
			
			PreparedStatement pst = cn.prepareStatement("SELECT id FROM socios WHERE nombre like ? AND apellido like ?");

			pst.setString(1,nombre);
			pst.setString(2, apellido);
			
			//System.out.println(pst);
			
			pst.execute();
			ResultSet rs = pst.executeQuery();
			
			int id_socio = 0;
			
		
			while (rs.next()){
			id_socio = rs.getInt(1);
			}
			
			return id_socio;
					
		} catch (SQLException e) {
			throw e;
		}

	}


}
