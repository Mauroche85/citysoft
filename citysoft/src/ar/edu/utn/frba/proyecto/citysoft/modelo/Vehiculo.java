package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @version 1.0
 * @param <T>
 * @created 23-Jul-2009 10:54:52 p.m.
 */
public class Vehiculo implements ObjetoDeDominio, Comparable<Vehiculo> {

	private int idVehiculo;
	// Datos del titular
	private String nombreUsuario;
	private String nombre;
	private String apellido;
	private int dni;
	// Informacion del veh�culo
	private String patente;
	private String poliza;
	private String idTracker;
	private String marca;
	private String modelo;
	private String detalle;
	private Conductor conductor;
	// Estado del veh�culo
	private Collection<Viaje> viajes = new ArrayList<Viaje>();
	private SortedSet<Track> tracks = new TreeSet<Track>();
	/** El viaje en curso YA ESTA contenido en la coleccion de viajes */
	private Viaje viajeEnCurso;
	private Boolean activado;

	// **************************************
	// ** Constructor
	// **************************************

	public Vehiculo() {
		this.setIdVehiculo(Central.getInstance().getGeneradorDeIds().getProximoIdVehiculo());
	}

	// **************************************
	// ** Accessors
	// **************************************

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	// **************************************
	// ** Accessors - DATOS DEL TITULAR
	// **************************************

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombre) {
		this.nombreUsuario = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	// **************************************
	// ** Accessors - INFORMACION DEL VEHICULO
	// **************************************

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public String getIdTracker() {
		return idTracker;
	}

	public void setIdTracker(String idTracker) {
		this.idTracker = idTracker;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	// **************************************
	// ** Accessors - ESTADO DEL VEHICULO
	// **************************************

	/**
	 * El viaje en curso YA ESTA contenido en la coleccion de viajes
	 */
	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public void addViaje(Viaje viaje) {
		viaje.setVehiculo(this);
		this.viajes.add(viaje);
	}

	public SortedSet<Track> getTracks() {
		return tracks;
	}

	public void addTrack(Track track) {
		this.tracks.add(track);
	}

	public Viaje getViajeEnCurso() {
		return viajeEnCurso;
	}

	/**
	 * <li>Para asignar un vehiculo a un viaje, no se debe llamar a este metodo,
	 * sino recurrir a {@link Viaje#asignar(Vehiculo)}.
	 * 
	 * <li>Para liberar un vehiculo por viaje finalizado, no se debe llamar a
	 * este metodo, sino recurrir a {@link Viaje#finalizar()}
	 * 
	 * <li>Para liberar un vehiculo por viaje cancelado, no se debe llamar a
	 * este metodo, sino recurrir a {@link Viaje#cancelar()}
	 */
	public void setViajeEnCurso(Viaje viajeEnCurso) {
		validarVehiculoActivo();
		validarVehiculoLibre();
		this.viajeEnCurso = viajeEnCurso;
		this.viajes.add(viajeEnCurso);
	}

	/**
	 * <li>Para liberar un vehiculo por viaje finalizado, no se debe llamar a
	 * este metodo, sino recurrir a {@link Viaje#finalizar()}
	 * 
	 * <li>Para liberar un vehiculo por viaje cancelado, no se debe llamar a
	 * este metodo, sino recurrir a {@link Viaje#cancelar()}
	 */
	public void limpiarViajeEnCurso() {
		validarVehiculoActivo();
		validarVehiculoEnCurso();
		this.viajeEnCurso = null;
	}

	public Boolean getActivado() {
		return activado;
	}

	public void setActivado(Boolean activado) {
		this.activado = activado;
	}

	// **************************************
	// ** Execution
	// **************************************

	public double getLat() {
		return getUltimoTrack().getCoordenadas().getLatitud();
	}

	public double getLng() {
		return getUltimoTrack().getCoordenadas().getLongitud();
	}

	public Track getUltimoTrack() {
		try {
			return this.getTracks().last();
		} catch (NoSuchElementException e) {
			throw new RuntimeException("No se encontr� la �ltima posici�n del veh�culo", e);
		}
	}

	public void nuevoTrack(double lat, double lng) {
		Track t = new Track();
		// TODO ver que onda con el ID de track
		t.setIdTrack(-1);
		t.setInstante(new Date());
		t.setVehiculo(this);
		t.setCoordenadas(new Coordenadas(lat, lng));
		this.tracks.add(t);
	}

	public boolean estoyLibre() {
		// validarVehiculoActivo(); esto esta mal por que hace recursividad
		return this.getViajeEnCurso() == null;
	}

	/*
	 * @Override public int compareTo(Vehiculo theOtherVehiculo) { return
	 * this.patente.compareTo(theOtherVehiculo.patente); }
	 */

	@Override
	public int compareTo(Vehiculo theOther) {
		int thisId = this.idVehiculo;
		int theOtherId = theOther.idVehiculo;
		// Esto nos lo choriceamos de Integer!!!
		return (thisId < theOtherId ? -1 : (thisId == theOtherId ? 0 : 1));
	}

	// **************************************
	// ** Helpers
	// **************************************

	public void validarVehiculoActivo() {
		if (!this.activado) {
			throw new RuntimeException(
					"No se le puede asignar viaje a un veh�culo que no est� en actividad");
		}
	}

	public void validarVehiculoLibre() {
		if (!estoyLibre()) {
			throw new RuntimeException("No se puede asignar viaje a un veh�culo asignado u ocupado");
		}
	}

	public void validarVehiculoEnCurso() {
		if (estoyLibre()) {
			throw new RuntimeException("No se puede asignar viaje a un veh�culo asignado u ocupado");
		}
	}

}
