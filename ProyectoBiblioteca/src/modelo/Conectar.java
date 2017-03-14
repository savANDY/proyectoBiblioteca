package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conectar {

	protected Connection cn; // Esta vacio
	
	public Conectar() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Driver MYSQL para JAVA
			
			String url = "jdbc:mysql://localhost/biblioteca"; // Nombre de la BD
			
			cn = (Connection) DriverManager.getConnection(url,"root","");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR DE CONEXION");
		}
	}
	
	
}
