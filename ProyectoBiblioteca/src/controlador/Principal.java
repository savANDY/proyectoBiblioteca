package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import modelo.Libro;
import modelo.ModeloLibro;
import modelo.ModeloPrestamo;
import modelo.ModeloSocio;
import modelo.Prestamo;
import modelo.Socio;

public class Principal {

	final static int SOCIOS = 1;
	final static int LIBROS = 2;
	final static int PRESTAMOS = 3;

	final static int NUEVO = 1;
	final static int BORRAR = 2;
	final static int MODIFICAR = 3;
	final static int CONSULTAR = 4;

	final static int TODOS_SOCIOS = 1;
	final static int SOCIOS_POR_DNI = 2;
	final static int SOCIOS_POR_POBLACION = 3;

	final static int TODOS_LIBROS = 1;
	final static int LIBROS_POR_ID = 2;
	final static int LIBROS_POR_AUTOR = 3;

	final static int TODOS_PRESTAMOS = 1;
	final static int PRESTAMOS_DEL_SOCIO = 2;
	final static int PRESTAMOS_DEL_LIBRO = 3;
	final static int LIBROS_SIN_DEVOLVER = 4;
	final static int PRESTAMOS_POR_NOMBRE_APELLIDO = 5;
	final static int VOLVER_PRESTAMOS = 6;

	final static int SALIR = 4;
	final static int VOLVER = 5;

	static Date fecha_date;
	static SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
	static ArrayList<Socio> socios;
	static ArrayList<Prestamo> prestamos;
	static ModeloLibro model_libro;
	static ModeloSocio model_socio = new ModeloSocio();
	
	static String comprobarDevuelto(Boolean devuelto){
		String resultado = "No";
		if (devuelto) {
			resultado = "Si";
		}
		
		return resultado;
	}

	static void gestionSocios() {
		Socio socio = new Socio();
		ModeloSocio model_socio = new ModeloSocio();
		ResultSet rs;
		int opcion;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("\n\n\t\tMENU SOCIO\n");
			System.out.println("\t\t1.Insertar Socio.");
			System.out.println("\t\t2.Borrar Socio.");
			System.out.println("\t\t3.Modificar Socio.");
			System.out.println("\t\t4.Consultar Socios.");
			System.out.println("\t\t5.Volver.");

			System.out.print("\n\t\tINTRODUCE UNA OPCION: ");
			opcion = Integer.parseInt(scan.nextLine());

			switch (opcion) {
			case NUEVO:

				System.out.println("\t\tDATOS SOCIO");

				System.out.print("\tID: ");
				socio.setId(Integer.parseInt(scan.nextLine()));

				System.out.print("\tNombre: ");
				socio.setNombre(scan.nextLine());

				System.out.print("\tApellido: ");
				socio.setApellido(scan.nextLine());

				System.out.print("\tDireccion: ");
				socio.setDireccion(scan.nextLine());

				System.out.print("\tPoblacion: ");
				socio.setPoblacion(scan.nextLine());

				System.out.print("\tProvincia: ");
				socio.setProvincia(scan.nextLine());

				System.out.print("\tDNI: ");
				socio.setDni(scan.nextLine());

				try {
					model_socio.insertar(socio);
				} catch (SQLException e) {
					System.out.println("ERROR AL INSERTAR SOCIO");
					e.printStackTrace();
				}

				break;
			case BORRAR:

				System.out.println("\t\tBORRAR SOCIO POR ID");
				System.out.print("\tIntroduce el id: ");
				int num_aux = Integer.parseInt(scan.nextLine());

				try {
					model_socio.borrar(num_aux);
				} catch (Exception e) {
					System.out.println("ERROR AL BORRAR SOCIO");
					e.printStackTrace();

				}

				break;
			case MODIFICAR:

				System.out.println("\t\tDATOS ALUMNO");

				System.out.print("\tId: ");
				socio.setId(Integer.parseInt(scan.nextLine()));

				System.out.print("\tNueva Direccion: ");
				socio.setDireccion(scan.nextLine());

				try {
					model_socio.modificar_direc(socio);
				} catch (Exception e) {
					System.out.println("Error al modificar direccion");
					e.printStackTrace();
				}

				break;

			case CONSULTAR:

				do {
					System.out.println("\n\tMenu Consultas Socios");
					System.out.println("\t1. TODOS LOS SOCIOS");
					System.out.println("\t2. POR DNI");
					System.out.println("\t3. POR POBLACION");
					System.out.println("\t4. VOLVER");

					System.out.print("\n\t\tINTRODUCE UNA OPCION: ");
					opcion = Integer.parseInt(scan.nextLine());

					switch (opcion) {

					case TODOS_SOCIOS:

						socios = new ArrayList<Socio>();

						try {
							socios = model_socio.seleccionarTodos();
							System.out.println("\t\tSOCIOS:");

							for (Socio socio_aux : socios) {
								System.out.println("\n\t\t\t Id: " + socio_aux.getId());
								System.out.println("\t\t\t Nombre: " + socio_aux.getNombre());
								System.out.println("\t\t\t Apellidos: " + socio_aux.getApellido());
								System.out.println("\t\t\t Direccion: " + socio_aux.getDireccion());
								System.out.println("\t\t\t Poblacion: " + socio_aux.getPoblacion());
								System.out.println("\t\t\t Provincia: " + socio_aux.getProvincia());
								System.out.println("\t\t\t DNI: " + socio_aux.getDni());

							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					case SOCIOS_POR_DNI:

						socios = new ArrayList<Socio>();

						try {

							System.out.print("\tIntroduce el DNI del socio a consultar: ");
							String dni = scan.nextLine();

							socios = model_socio.seleccionarPorDni(dni);
							System.out.println("\t\tSOCIOS:");

							for (Socio socio_aux : socios) {
								System.out.println("\n\t\t\t Id: " + socio_aux.getId());
								System.out.println("\t\t\t Nombre: " + socio_aux.getNombre());
								System.out.println("\t\t\t Apellidos: " + socio_aux.getApellido());
								System.out.println("\t\t\t Direccion: " + socio_aux.getDireccion());
								System.out.println("\t\t\t Poblacion: " + socio_aux.getPoblacion());
								System.out.println("\t\t\t Provincia: " + socio_aux.getProvincia());
								System.out.println("\t\t\t DNI: " + socio_aux.getDni());

							}

						} catch (Exception e) {
							System.out.println("\t\tERROR AL SELECCIONAR SOCIO");
							e.getMessage();
							// e.printStackTrace();
						}

						break;
					case SOCIOS_POR_POBLACION:

						socios = new ArrayList<Socio>();

						try {

							System.out.print("\tIntroduce la Poblacion: ");
							String poblacion = scan.nextLine();

							socios = model_socio.seleccionarPorPoblacion(poblacion);
							System.out.println("\t\tSOCIOS:");

							for (Socio socio_aux : socios) {
								System.out.println("\n\t\t\t Id: " + socio_aux.getId());
								System.out.println("\t\t\t Nombre: " + socio_aux.getNombre());
								System.out.println("\t\t\t Apellidos: " + socio_aux.getApellido());
								System.out.println("\t\t\t Direccion: " + socio_aux.getDireccion());
								System.out.println("\t\t\t Poblacion: " + socio_aux.getPoblacion());
								System.out.println("\t\t\t Provincia: " + socio_aux.getProvincia());
								System.out.println("\t\t\t DNI: " + socio_aux.getDni());

							}

						} catch (Exception e) {
							System.out.println("\t\tERROR AL SELECCIONAR SOCIO");
							e.getMessage();
							// e.printStackTrace();
						}

						break;
					case VOLVER:
						System.out.println("\tVolviendo...");
						break;

					default:
						System.out.println("\tAlgo ha ido muy mal...");
					}

				} while (opcion != VOLVER); // VOLVER = 4

			case VOLVER:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Algo ha ido mal...");

			}
		} while (opcion != 4);
	}

	static void gestionLibros() {

		Libro libro = new Libro();
		ModeloLibro model_libro = new ModeloLibro();
		ResultSet rs;
		int opcion;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("\n\n\t\tMENU LIBROS\n");
			System.out.println("\t\t1.Insertar Libro.");
			System.out.println("\t\t2.Borrar Libro.");
			System.out.println("\t\t3.Modificar Libro.");
			System.out.println("\t\t4.Consultas Libro.");
			System.out.println("\t\t5.Volver.");

			System.out.print("\n\t\tINTRODUCE UNA OPCION: ");
			opcion = Integer.parseInt(scan.nextLine());

			switch (opcion) {
			case NUEVO:

				System.out.println("\t\tDATOS libro");

				System.out.print("\tID: ");
				libro.setId(Integer.parseInt(scan.nextLine()));

				System.out.print("\tTitulo: ");
				libro.setTitulo(scan.nextLine());

				System.out.print("\tAutor: ");
				libro.setAutor(scan.nextLine());

				System.out.print("\tNumero de paginas: ");
				libro.setNum_pag(Integer.parseInt(scan.nextLine()));

				try {
					model_libro.insertar(libro);
				} catch (SQLException e) {
					System.out.println("ERROR AL INSERTAR LIBRO");
					e.printStackTrace();
				}

				break;
			case BORRAR:

				System.out.println("\t\tBORRAR LIBRO POR ID");
				System.out.print("\tIntroduce el id: ");
				int num_aux = Integer.parseInt(scan.nextLine());

				try {
					model_libro.borrar(num_aux);
				} catch (Exception e) {
					System.out.println("ERROR AL BORRAR LIBRO");
					e.printStackTrace();

				}

				break;
			case MODIFICAR:

				System.out.println("\n\t\tDATOS LIBRO");

				System.out.print("\tId: ");
				libro.setId(Integer.parseInt(scan.nextLine()));

				System.out.print("\tNuevas Páginas: ");
				libro.setNum_pag(Integer.parseInt(scan.nextLine()));

				try {
					model_libro.modificar_direc(libro);
				} catch (Exception e) {
					System.out.println("Error al modificar paginas");
					e.printStackTrace();
				}

				break;

			case CONSULTAR:

				do {
					System.out.println("\n\tMenu Consultas Libros");
					System.out.println("\t1. TODOS LOS LIBROS");
					System.out.println("\t2. POR ID");
					System.out.println("\t3. POR AUTOR");
					System.out.println("\t4. VOLVER");

					System.out.print("\n\t\tINTRODUCE UNA OPCION: ");
					opcion = Integer.parseInt(scan.nextLine());

					switch (opcion) {

					case TODOS_LIBROS:
						break;
					case LIBROS_POR_ID:
						break;
					case LIBROS_POR_AUTOR:
						break;

					case VOLVER:
						System.out.println("\tVolviendo...");
						break;

					default:
						System.out.println("\tAlgo ha ido muy mal...");
					}

				} while (opcion != VOLVER); // VOLVER = 4
				break;

			case VOLVER:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Algo ha ido mal...");

			}
		} while (opcion != 4);
	}

	static void gestionPrestamos() {

		Prestamo prestamo = new Prestamo();
		ModeloPrestamo model_prestamo = new ModeloPrestamo();
		ResultSet rs;
		int opcion;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("\n\n\t\tMENU PRESTAMOS\n");
			System.out.println("\t\t1.Insertar prestamo.");
			System.out.println("\t\t2.Borrar prestamo.");
			System.out.println("\t\t3.Modificar prestamo.");
			System.out.println("\t\t4.Consultar Prestamos.");
			System.out.println("\t\t5.Volver.");

			System.out.print("\n\t\tINTRODUCE UNA OPCION: ");
			opcion = Integer.parseInt(scan.nextLine());

			switch (opcion) {
			case NUEVO:

				System.out.println("\t\tDATOS prestamo");

				System.out.print("\tID Libro: ");
				prestamo.setId_libro(Integer.parseInt(scan.nextLine()));

				System.out.print("\tID Socio: ");
				prestamo.setId_socio(Integer.parseInt(scan.nextLine()));

				System.out.print("\tFecha: (yyyy/MM/dd)");

				String fecha_string = scan.nextLine();
				SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

				// pasar de string a date utilizando el formato

				try {

					fecha_date = formato.parse(fecha_string);
					prestamo.setFecha(fecha_date);

				} catch (ParseException e1) {
					System.out.print("\tFORMATO DE FECHA ERRONEO (yyyy/MM/dd) ");
					e1.printStackTrace();
				}

				System.out.print("\tDevuelto: ");
				prestamo.setDevuelto(Boolean.parseBoolean(scan.nextLine()));

				try {
					model_prestamo.insertar(prestamo);
				} catch (SQLException e) {
					System.out.println("ERROR AL INSERTAR prestamo");
					e.printStackTrace();
				}

				break;
			case BORRAR:

				System.out.println("\t\tBORRAR prestamo POR ID");

				System.out.print("\tIntroduce el id del libro: ");
				prestamo.setId_libro(Integer.parseInt(scan.nextLine()));

				System.out.print("\tIntroduce el id del socio: ");
				prestamo.setId_socio(Integer.parseInt(scan.nextLine()));

				System.out.print("\tIntroduce la fecha: ");
				fecha_string = scan.nextLine();

				// pasar de string a date utilizando el formato

				formato = new SimpleDateFormat("yyyy/MM/dd");

				try {

					fecha_date = formato.parse(fecha_string);
					prestamo.setFecha(fecha_date);

				} catch (ParseException e1) {
					System.out.print("\tFORMATO DE FECHA ERRONEO (yyyy/MM/dd) ");
					e1.printStackTrace();
				}

				try {
					model_prestamo.borrar(prestamo);
				} catch (Exception e) {
					System.out.println("ERROR AL BORRAR prestamo");
					e.printStackTrace();

				}

				break;
			case MODIFICAR:

				System.out.println("\n\t\tDATOS prestamo");

				System.out.print("\tId libro: ");
				prestamo.setId_libro(Integer.parseInt(scan.nextLine()));

				System.out.print("\tId socio: ");
				prestamo.setId_socio(Integer.parseInt(scan.nextLine()));

				System.out.print("\tIntroduce la fecha (yyyy/MM/dd): ");
				fecha_string = scan.nextLine();

				// pasar de string a date utilizando el formato

				formato = new SimpleDateFormat("yyyy/MM/dd");

				try {

					fecha_date = formato.parse(fecha_string);
					prestamo.setFecha(fecha_date);

				} catch (ParseException e1) {
					System.out.print("\tFORMATO DE FECHA ERRONEO (yyyy/MM/dd) ");
					e1.printStackTrace();
				}

				System.out.print("\tNuevo Devuelto: ");
				prestamo.setDevuelto(Boolean.parseBoolean(scan.nextLine()));

				try {
					model_prestamo.modificar(prestamo);
				} catch (Exception e) {
					System.out.println("Error al modificar 'devuelto'");
					e.printStackTrace();
				}

				break;

			case CONSULTAR:
				do {
					System.out.println("\n\tMenu Consultas Libros");
					System.out.println("\t1. TODOS LOS PRESTAMOS");
					System.out.println("\t2. PRESTAMOS DE SOCIO");
					System.out.println("\t3. PRESTAMOS DE LIBRO");
					System.out.println("\t4. LIBROS SIN DEVOLVER HASTA FECHA");
					System.out.println("\t5. PRESTAMOS POR NOMBRE Y APELLIDO");
					System.out.println("\t6. VOLVER");

					System.out.print("\n\t\tINTRODUCE UNA OPCION: ");
					opcion = Integer.parseInt(scan.nextLine());

					switch (opcion) {

					case TODOS_PRESTAMOS:

						prestamos = new ArrayList<Prestamo>();

						try {
							prestamos = model_prestamo.seleccionarTodos();
							System.out.println("\t\tPRESTAMOS:");

							for (Prestamo pres : prestamos) {
								ModeloLibro model_libro = new ModeloLibro();
								ModeloSocio model_socio = new ModeloSocio();
								System.out.println("\n\t\t\t Libro: " + model_libro.seleccionarId(pres.getId_libro()) + "(id: " + pres.getId_libro() + ")");
								
								System.out.println("\t\t\t Socio: " + model_socio.seleccionarId(pres.getId_socio()) + "(id: " + pres.getId_socio() + ")");
								System.out.println("\t\t\t Fecha: " + pres.getFecha());
								System.out.println("\t\t\t Devuelto: " + comprobarDevuelto(pres.getDevuelto()));

							}

						} catch (Exception e) {
							System.out.println("\t\tError al mostrar Prestamos");
							e.printStackTrace();
						}

						break;
					case PRESTAMOS_DEL_SOCIO: // (DNI)

						prestamos = new ArrayList<Prestamo>();

						try {

							System.out.print("\tIntroduce el DNI del Socio a consultar: ");
							int dni = Integer.parseInt(scan.nextLine());

							prestamos = model_prestamo.seleccionarPrestamoSocio(dni);
							System.out.println("\t\tPRESTAMOS:");

							for (Prestamo pres : prestamos) {
								ModeloLibro model_libro = new ModeloLibro();
								ModeloSocio model_socio = new ModeloSocio();
								System.out.println("\n\t\t\t Libro: " + model_libro.seleccionarId(pres.getId_libro()));
								
								System.out.println("\t\t\t Socio: " + model_socio.seleccionarId(pres.getId_socio()));
								System.out.println("\t\t\t Fecha: " + pres.getFecha());
								System.out.println("\t\t\t Devuelto: " + pres.getDevuelto());

							}

						} catch (Exception e) {
							System.out.println("\t\tError al mostrar Prestamos");
							e.printStackTrace();
						}

						break;
					case PRESTAMOS_DEL_LIBRO: // (ID)

						prestamos = new ArrayList<Prestamo>();

						try {

							System.out.print("\tIntroduce el Titulo del Libro a consultar: ");
							String titulo_libro = scan.nextLine();

							prestamos = model_prestamo.seleccionarPrestamoLibro(titulo_libro);
							System.out.println("\t\tPRESTAMOS:");

							for (Prestamo pres : prestamos) {
								ModeloLibro model_libro = new ModeloLibro();
								ModeloSocio model_socio = new ModeloSocio();
								System.out.println("\n\t\t\t Libro: " + model_libro.seleccionarId(pres.getId_libro()));
								
								System.out.println("\t\t\t Socio: " + model_socio.seleccionarId(pres.getId_socio()));
								System.out.println("\t\t\t Fecha: " + pres.getFecha());
								System.out.println("\t\t\t Devuelto: " + comprobarDevuelto(pres.getDevuelto()));

							}

						} catch (Exception e) {
							System.out.println("\t\tError al mostrar Prestamos");
							e.printStackTrace();
						}

						break;
					case LIBROS_SIN_DEVOLVER: // (Libros sin devolver a fecha
												// <--- pedir) fecha metida <
												// fecha AND devuelto = no
						
						prestamos = new ArrayList<Prestamo>();

						try {

//							System.out.print("\tIntroduce el Titulo del Libro a consultar: ");
//							String titulo_libro = scan.nextLine();
							
							System.out.print("\tIntroduce la fecha: ");
							
							fecha_string = scan.nextLine();
							formato = new SimpleDateFormat("yyyy/MM/dd");

							// pasar de string a date utilizando el formato

							try {

								fecha_date = formato.parse(fecha_string);
								prestamo.setFecha(fecha_date);

							} catch (ParseException e1) {
								System.out.print("\tFORMATO DE FECHA ERRONEO (yyyy/MM/dd) ");
								e1.printStackTrace();
							}

							prestamos = model_prestamo.seleccionarSinDevolver(fecha_date);
							System.out.println("\t\tPRESTAMOS:");

							for (Prestamo pres : prestamos) {
								ModeloLibro model_libro = new ModeloLibro();
								ModeloSocio model_socio = new ModeloSocio();
								System.out.println("\n\t\t\t Libro: " + model_libro.seleccionarId(pres.getId_libro()) + "(id: " + pres.getId_libro() + ")");
								
								System.out.println("\t\t\t Socio: " + model_socio.seleccionarId(pres.getId_socio()) + "(id: " + pres.getId_socio() + ")");
								
								System.out.println("\t\t\t Fecha: " + pres.getFecha());
								System.out.println("\t\t\t Devuelto: " + comprobarDevuelto(pres.getDevuelto()));

							}

						} catch (Exception e) {
							System.out.println("\t\tError al mostrar Prestamos");
							e.getMessage();
							e.printStackTrace();
						}
						
						
						break;
						
					case PRESTAMOS_POR_NOMBRE_APELLIDO:
						
						ModeloSocio model_socio = new ModeloSocio();
						
						prestamos = new ArrayList<Prestamo>();

						try {

//							System.out.print("\tIntroduce el Titulo del Libro a consultar: ");
//							String titulo_libro = scan.nextLine();
							
							System.out.print("\tIntroduce El nombre: ");
							
							String nombre = scan.nextLine();
							
														
							System.out.print("\tIntroduce el apellido: ");
							
							String apellido = scan.nextLine();
							
							int id_socio = model_socio.seleccionarNombrePorId(nombre, apellido);
										
							prestamos = model_prestamo.seleccionarPorId(id_socio);
							System.out.println("\t\tPRESTAMOS:");

							for (Prestamo pres : prestamos) {
								ModeloLibro model_libro = new ModeloLibro();
								model_socio = new ModeloSocio();
								System.out.println("\n\t\t\t Libro: " + model_libro.seleccionarId(pres.getId_libro()) + "(id: " + pres.getId_libro() + ")");
								
								System.out.println("\t\t\t Socio: " + model_socio.seleccionarId(pres.getId_socio()) + "(id: " + pres.getId_socio() + ")");
								
								System.out.println("\t\t\t Fecha: " + pres.getFecha());
								System.out.println("\t\t\t Devuelto: " + comprobarDevuelto(pres.getDevuelto()));

							}

						} catch (Exception e) {
							System.out.println("\t\tError al mostrar Prestamos");
							e.getMessage();
							e.printStackTrace();
						}
						
						
						break;
						
						

					case VOLVER_PRESTAMOS:
						System.out.println("\tVolviendo...");
						break;

					default:
						System.out.println("\tAlgo ha ido muy mal...");
					}

				} while (opcion != VOLVER); // VOLVER = 4
				break;

			case VOLVER:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Algo ha ido mal...");

			}

		} while (opcion != 4);

	}

	public static void main(String[] args) {

		ResultSet rs;
		int opcion;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("\n\n\t\tMENU BIBLIOTECA\n");
			System.out.println("\t\t1.Gestion Socios.");
			System.out.println("\t\t2.Gestion Libros.");
			System.out.println("\t\t3.Gestion Prestamos.");
			System.out.println("\t\t4.Salir.");

			System.out.print("\n\t\tINTRODUCE UNA OPCION: ");
			opcion = Integer.parseInt(scan.nextLine());

			switch (opcion) {

			case SOCIOS:
				gestionSocios();
				break;

			case LIBROS:
				gestionLibros();
				break;

			case PRESTAMOS:
				gestionPrestamos();
				break;

			case SALIR:
				System.out.println("Saliendo...");
				break;

			default:
				System.out.println("Algo ha ido mal...");
			}

		} while (opcion != 4);

	}

}
