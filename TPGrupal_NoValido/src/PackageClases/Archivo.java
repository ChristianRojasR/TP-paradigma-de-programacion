package PackageClases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Archivo {
	private String nombre;

	public Archivo(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public List<Usuario> leerArchivoUsuario(){
		
		Scanner scanner = null;
		List<Usuario> usuarios = null;
		
		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");
			scanner = new Scanner(file);
			
			scanner.useLocale(Locale.ENGLISH);
			
			//Aca comienza la lectura del archivo
			//Inicializo el Set como TreeSet para optimizar la busqueda
			usuarios = new LinkedList<Usuario>();
			int cantUsuarios = scanner.nextInt();
			for (int i = 0; i < cantUsuarios; i++) {
				usuarios.add(new Usuario(scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextDouble()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return usuarios;
	}
	
	public Set<Atraccion> leerArchivoAtraccion(){
		
		Scanner scanner = null;
		Set<Atraccion> atracciones = null;
		
		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");
			scanner = new Scanner(file);
			
			scanner.useLocale(Locale.ENGLISH);
			
			//Aca comienza la lectura del archivo
			//Inicializo el Set como TreeSet para optimizar la busqueda
			atracciones = new TreeSet<Atraccion>();
			int cantAtracciones = scanner.nextInt();
			for (int i = 0; i < cantAtracciones; i++) {
				atracciones.add(new Atraccion(scanner.next(), scanner.next(), scanner.nextInt(),
						scanner.nextDouble(), scanner.nextInt()));
			}
		} catch (Exception e) {    
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		return atracciones;
	}

	public Set<Paquete> leerArchivoPaquete(Set<Atraccion> atracciones){
		
		Scanner scanner = null;
		Set<Paquete> paquetes = null;
		
		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");
			scanner = new Scanner(file);
			
			scanner.useLocale(Locale.ENGLISH);
			
			//Aca comienza la lectura del archivo
			//Inicializo el Set como TreeSet para optimizar la busqueda
			paquetes = new TreeSet<Paquete>();
			int cantPaquetes = scanner.nextInt();
			for (int i = 0; i < cantPaquetes; i++) {
				paquetes.add(new Paquete(scanner.next(), scanner.next(),
						guardarAtraccionesDelPaquete(scanner.nextInt(),scanner, atracciones),
						instanciarObjetoPromocion(scanner.next(), scanner, atracciones)));
			}
		} catch (Exception e) {    
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		return paquetes;
	}
	
	private Set<Atraccion> guardarAtraccionesDelPaquete(int cantAtracciones,Scanner scanner, Set<Atraccion> atracciones){
		Set<Atraccion> atraccionesPaquete = new TreeSet<Atraccion>();
		
		for (int i = 0; i < cantAtracciones; i++) {
			String nombreAtraccion = scanner.next();
			
			for (Atraccion atraccion : atracciones) {
				if(atraccion.nombre.equals(nombreAtraccion)) {
					atraccionesPaquete.add(atraccion);
					break;
				}
			}
		}
		return atraccionesPaquete;
	}
	
	private Promocion instanciarObjetoPromocion(String tipoDePromocion, Scanner scanner,Set<Atraccion> atracciones) {
		if(tipoDePromocion.equals("Porcentual"))
			return new Porcentual(scanner.nextDouble());
		if(tipoDePromocion.equals("Absoluta"))
			return new Absoluta(scanner.nextInt());
		// Logica para AxB guardar la atraccion gratis
		String nombreAtraccion = scanner.next();
		Promocion gratis = null;
		for (Atraccion atraccion : atracciones) {
			if(atraccion.nombre.equals(nombreAtraccion))
				gratis = new AxB(atraccion);
		}
		return gratis;
	}
	
	public void guardarArchivo(List<Producto> productos, Usuario usuario) {
		FileWriter file = null;
		PrintWriter printerWriter = null;

		try {
			file = new FileWriter("casos de prueba/out/" + this.nombre + ".out");
			printerWriter = new PrintWriter(file);

			printerWriter.println(usuario);
			for (Producto producto : productos) {
				printerWriter.println(producto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
