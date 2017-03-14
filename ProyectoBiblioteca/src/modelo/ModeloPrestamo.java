package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeloPrestamo extends Conectar {

	public ModeloPrestamo() {
		super();
	}

	// Operaciones (insertar | borrar | modificar | seleccionar)
	public void insertar(Prestamo prestamo) throws SQLException {

		// escribir la instruccion INSERT
		try {
			PreparedStatement pst = cn.prepareStatement("INSERT INTO prestamos VALUES (?,?,?,?)");
			pst.setInt(1, prestamo.getId_libro());
			pst.setInt(2, prestamo.getId_socio());
			pst.setDate(3, new java.sql.Date(prestamo.getFecha().getTime()));
			pst.setBoolean(4, prestamo.getDevuelto());

			System.out.println(pst);

			pst.execute(); // EJECUTA

			System.out.println("PRESTAMO INSERTADO CON EXITO");

			if (pst.getUpdateCount() == 0) {
				System.out.println("NO HA INSERTADO NADA");

			}
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void borrar(Prestamo prestamo) throws Exception {

		try {
			PreparedStatement pst = cn
					.prepareStatement("DELETE FROM prestamos WHERE id_libro = ? AND id_socio = ? AND fecha = ?");
			pst.setInt(1, prestamo.getId_libro());
			pst.setInt(2, prestamo.getId_socio());
			pst.setDate(3, new java.sql.Date(prestamo.getFecha().getTime()));

			System.out.println(pst);

			pst.execute();

			if (pst.getUpdateCount() == 0) {
				System.out.println("Prestamo no existe...");
			} else {
				System.out.println("PRESTAMO BORRADO CON EXITO");
			}

		} catch (SQLException ex) {
			throw ex;
		}

	}

	public void modificar(Prestamo prestamo) throws Exception {
		try {
			PreparedStatement pst = cn.prepareStatement(
					"UPDATE prestamos SET devuelto = ? WHERE id_libro = ? AND id_socio=? AND fecha = ?");

			pst.setBoolean(1, prestamo.getDevuelto());
			pst.setInt(2, prestamo.getId_libro());
			pst.setInt(3, prestamo.getId_socio());
			pst.setDate(4, new java.sql.Date(prestamo.getFecha().getTime()));

			pst.execute();
			if (pst.getUpdateCount() == 0) {
				System.out.println("El Prestamo No Existe");
			} else {
				System.out.println("Modificando prestamo");
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public ResultSet seleccionar() throws SQLException {
		Statement st = cn.createStatement();
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos");
			return rs;
		} catch (SQLException e) {
			throw e;
		}

	}

	public ArrayList<Prestamo> seleccionarTodos() throws SQLException {

		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM prestamos");

		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

		while (rs.next()) {

			Prestamo prestamo = new Prestamo();

			prestamo.setId_libro(rs.getInt(1));
			prestamo.setId_socio(rs.getInt(2));
			prestamo.setFecha(rs.getDate(3));
			prestamo.setDevuelto(rs.getBoolean(4));
			prestamos.add(prestamo);
		}

		return prestamos;
	}

	public ArrayList<Prestamo> seleccionarPrestamoSocio(int dni) throws SQLException {

		PreparedStatement pst = cn
				.prepareStatement("SELECT * FROM prestamos where id_socio = (SELECT id FROM socios WHERE dni = ?)");
		pst.setInt(1, dni);

		ResultSet rs = pst.executeQuery();

		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

		while (rs.next()) {

			Prestamo prestamo = new Prestamo();

			prestamo.setId_libro(rs.getInt(1));
			prestamo.setId_socio(rs.getInt(2));
			prestamo.setFecha(rs.getDate(3));
			prestamo.setDevuelto(rs.getBoolean(4));

			System.out.println(pst);

			prestamos.add(prestamo);

		}

		return prestamos;
	}

	public ArrayList<Prestamo> seleccionarPrestamoLibro(String titulo) throws SQLException {

		// PreparedStatement pst = cn.prepareStatement("SELECT * FROM prestamos
		// WHERE id_libro = (SELECT id FROM libros WHERE titulo = ?)");
		PreparedStatement pst = cn
				.prepareStatement("SELECT P.* FROM prestamos P, WHERE P.id_libro = S.ID AND L.TITULO");
		pst.setString(1, titulo);

		ResultSet rs = pst.executeQuery();

		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

		while (rs.next()) {

			Prestamo prestamo = new Prestamo();

			prestamo.setId_libro(rs.getInt(1));
			prestamo.setId_socio(rs.getInt(2));
			prestamo.setFecha(rs.getDate(3));
			prestamo.setDevuelto(rs.getBoolean(4));

			System.out.println(pst);

			prestamos.add(prestamo);

		}

		return prestamos;
	}

	public ArrayList<Prestamo> seleccionarSinDevolver(java.util.Date fecha) {

		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

		PreparedStatement pst;
		try {
			pst = cn.prepareStatement("SELECT * FROM prestamos WHERE fecha < ? AND devuelto = 0");

			Date fecha_sql = new Date(fecha.getTime());
			pst.setDate(1, fecha_sql);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Prestamo prestamo = new Prestamo();

				prestamo.setId_libro(rs.getInt(1));
				prestamo.setId_socio(rs.getInt(2));
				prestamo.setFecha(rs.getDate(3));
				prestamo.setDevuelto(rs.getBoolean(4));
				prestamos.add(prestamo);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
	}

	public ArrayList<Prestamo> seleccionarPorId(int id_socio) {

		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

		PreparedStatement pst;
		try {
			pst = cn.prepareStatement("SELECT * FROM prestamos WHERE id_socio = ?");
			pst.setInt(1, id_socio);
			
			//System.out.println(pst);
			
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Prestamo prestamo = new Prestamo();

				prestamo.setId_libro(rs.getInt(1));
				prestamo.setId_socio(rs.getInt(2));
				prestamo.setFecha(rs.getDate(3));
				prestamo.setDevuelto(rs.getBoolean(4));
				prestamos.add(prestamo);

			}
			
			if (pst.getUpdateCount() == 0) {
				System.out.println("No hay ningun prestamo con ese nombre...");
			}

		} catch (SQLException e) {
			System.out.println("Error al seleccionar prestamos por nombre");
			e.printStackTrace();
		}
		return prestamos;
	}

}