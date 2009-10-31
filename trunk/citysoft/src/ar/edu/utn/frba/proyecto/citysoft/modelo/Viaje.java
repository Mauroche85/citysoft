package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <li>Viaje EN CURSO: es un viaje TRANSPORTANDO o ASIGNADO
 * 
 * <li>Viaje TRANSPORTANDO: cuando la personita esta arriba del vehiculo
 * 
 * <li>Viaje ASIGNADO: cuando hay una solicitud de viaje y le asignaron un
 * vehiculo pero el vehiculo todavia no llego a recoger al cliente
 * 
 * <li>Viaje PENDIENTE: cuando hay una solicitud de viaje y todavia no le
 * asignaron un vehiculo
 * 
 * 
 * @version 1.0
 * @created 23-Jul-2009 10:54:53 p.m.
 */
public class Viaje implements ObjetoDeDominio, Comparable<Viaje> {

	public static final String ESTADO_PENDIENTE = "PENDIENTE";
	public static final String ESTADO_ASIGNADO = "ASIGNADO";
	public static final String ESTADO_TRANSPORTANDO = "TRANSPORTANDO";
	public static final String ESTADO_CANCELADO = "CANCELADO";
	public static final String ESTADO_COMPLETADO = "COMPLETADO";

	// **************************************
	// ** Attributes
	// **************************************

	private int idViaje;
	private Vehiculo vehiculo;
	private Cliente cliente;
	private Date horaRequerida;
	private Date horaAsignado;
	private Date horaComienzo;
	private Date horaFin;
	private String origenReferente;
	private String origenCalle;
	private String origenAltura;
	private String origenPisoDepto;
	private String origenLocalidad;
	private String origenProvincia;
	private String destinoCalle;
	private String destinoAltura;
	private String destinoPisoDepto;
	private String destinoLocalidad;
	private String destinoProvincia;
	private String origenObservaciones;
	private String estado;
	private double origenLatitud;
	private double origenLongitud;
	private double destinoLatitud;
	private double destinoLongitud;

	// **************************************
	// ** Constructor(s)
	// **************************************

	public Viaje() {
		this.idViaje = Central.getInstance().getGeneradorDeIds().getProximoIdViaje();
		this.estado = ESTADO_PENDIENTE;
	}

	// **************************************
	// ** Accessors
	// **************************************

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo v) {
		this.vehiculo = v;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getHoraRequerida() {
		return horaRequerida;
	}

	public void setHoraRequerida(Date horaRequerida) {
		this.horaRequerida = horaRequerida;
	}

	public void setHoraAsignado(Date horaAsignado) {
		this.horaAsignado = horaAsignado;
	}

	public Date getHoraAsignado() {
		return horaAsignado;
	}

	public void setHoraComienzo(Date horaComienzo) {
		this.horaComienzo = horaComienzo;
	}

	public Date getHoraComienzo() {
		return horaComienzo;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public String getOrigenReferente() {
		return origenReferente;
	}

	public void setOrigenReferente(String origenReferente) {
		this.origenReferente = origenReferente;
	}

	public String getOrigenCalle() {
		return origenCalle;
	}

	public void setOrigenCalle(String origenCalle) {
		this.origenCalle = origenCalle;
	}

	public String getOrigenAltura() {
		return origenAltura;
	}

	public void setOrigenAltura(String origenAltura) {
		this.origenAltura = origenAltura;
	}

	public String getOrigenPisoDepto() {
		return origenPisoDepto;
	}

	public void setOrigenPisoDepto(String origenPisoDepto) {
		this.origenPisoDepto = origenPisoDepto;
	}

	public String getOrigenLocalidad() {
		return origenLocalidad;
	}

	public void setOrigenLocalidad(String origenLocalidad) {
		this.origenLocalidad = origenLocalidad;
	}

	public String getOrigenProvincia() {
		return origenProvincia;
	}

	public void setOrigenProvincia(String origenProvincia) {
		this.origenProvincia = origenProvincia;
	}

	public String getDestinoCalle() {
		return destinoCalle;
	}

	public void setDestinoCalle(String destinoCalle) {
		this.destinoCalle = destinoCalle;
	}

	public String getDestinoAltura() {
		return destinoAltura;
	}

	public void setDestinoAltura(String destinoAltura) {
		this.destinoAltura = destinoAltura;
	}

	public String getDestinoPisoDepto() {
		return destinoPisoDepto;
	}

	public void setDestinoPisoDepto(String destinoPisoDepto) {
		this.destinoPisoDepto = destinoPisoDepto;
	}

	public String getDestinoLocalidad() {
		return destinoLocalidad;
	}

	public void setDestinoLocalidad(String destinoLocalidad) {
		this.destinoLocalidad = destinoLocalidad;
	}

	public String getDestinoProvincia() {
		return destinoProvincia;
	}

	public void setDestinoProvincia(String destinoProvincia) {
		this.destinoProvincia = destinoProvincia;
	}

	public String getOrigenObservaciones() {
		return origenObservaciones;
	}

	public void setOrigenObservaciones(String origenObservaciones) {
		this.origenObservaciones = origenObservaciones;
	}

	public void setOrigenLatitud(double origenLatitud) {
		this.origenLatitud = origenLatitud;
	}

	public double getOrigenLatitud() {
		return origenLatitud;
	}

	public void setOrigenLongitud(double origenLongitud) {
		this.origenLongitud = origenLongitud;
	}

	public double getOrigenLongitud() {
		return origenLongitud;
	}

	public void setDestinoLatitud(double destinoLatitud) {
		this.destinoLatitud = destinoLatitud;
	}

	public double getDestinoLatitud() {
		return destinoLatitud;
	}

	public void setDestinoLongitud(double destinoLongitud) {
		this.destinoLongitud = destinoLongitud;
	}

	public double getDestinoLongitud() {
		return destinoLongitud;
	}

	// **************************************
	// ** Preguntas
	// **************************************

	public boolean estaPendiente() {
		return this.estado.equals(ESTADO_PENDIENTE);
	}

	public boolean estaAsignado() {
		return this.estado.equals(ESTADO_ASIGNADO);
	}

	public boolean estaTransportando() {
		return this.estado.equals(ESTADO_TRANSPORTANDO);
	}

	public boolean fueCancelado() {
		return this.estado.equals(ESTADO_CANCELADO);
	}

	public boolean fueCompletado() {
		return this.estado.equals(ESTADO_COMPLETADO);
	}

	// **************************************
	// ** Ejecucion
	// **************************************

	public void asignar(Vehiculo t) {
		validarViajePendiente();
		this.vehiculo = t;
		t.setViajeEnCurso(this);
		this.setHoraAsignado(new Date());
		this.estado = ESTADO_ASIGNADO;
	}
	
	public void liberar(Vehiculo t) {
		validarViajeAsignado();
		this.vehiculo = t;
		t.limpiarViajeEnCurso();
		this.setHoraAsignado(null);
		this.estado = ESTADO_PENDIENTE;
	}

	public void comenzar() {
		validarViajeAsignado();
		this.setHoraComienzo(new Date());
		this.estado = ESTADO_TRANSPORTANDO;
	}

	public void finalizar() {
		validarViajeTransportando();
		this.setHoraFin(new Date());
		this.getVehiculo().limpiarViajeEnCurso();
		this.estado = ESTADO_COMPLETADO;
	}

	public void cancelar() {
		validarViajePendiente();
		this.getVehiculo().setViajeEnCurso(null);
		this.estado = ESTADO_CANCELADO;
	}

	/**
	 * @return lista de {@link Track} ordenada cronologicamente, con la posicion
	 *         del taxi desde que el taxi comienza a transportar el pasajero
	 *         hasta que finaliza el viajes
	 */
	public SortedSet<Track> getTracksDelViaje() {
		SortedSet<Track> tracks = new TreeSet<Track>();
		Vehiculo v = getVehiculo();
		Track fromElement = new Track();
		fromElement.setInstante(this.getHoraComienzo());
		Track toElement = new Track();
		toElement.setInstante(this.getHoraFin());
		v.getTracks().subSet(fromElement, toElement);
		return tracks;
	}

	// **************************************
	// ** Helpers
	// **************************************

	public void validarViajePendiente() {
		if (!this.estado.equals(ESTADO_PENDIENTE)) {
			throw new RuntimeException("El viaje deberia estar pendiente (" + this.estado + ")");
		}
	}

	public void validarViajeAsignado() {
		if (!this.estado.equals(ESTADO_ASIGNADO)) {
			throw new RuntimeException("El viaje deber�a estar asignado (" + this.estado + ")");
		}
	}

	public void validarViajeTransportando() {
		if (!this.estado.equals(ESTADO_TRANSPORTANDO)) {
			throw new RuntimeException("El viaje deber�a estar transportando (" + this.estado + ")");
		}
	}
	
	public void validarViajeEnCurso() {
		if (!this.estado.equals(ESTADO_TRANSPORTANDO) || !this.estado.equals(ESTADO_ASIGNADO)) {
			throw new RuntimeException("El viaje deber�a estar en curso (" + this.estado + ")");
		}
	}

	// **************************************
	// ** Interfaces
	// **************************************

	@Override
	public int compareTo(Viaje theOther) {
		int thisId = this.idViaje;
		int theOtherId = theOther.idViaje;
		// Esto nos lo choriceamos de Integer!!!
		return (thisId < theOtherId ? -1 : (thisId == theOtherId ? 0 : 1));
	}

	@Override
	public String toString() {
		return "Viaje " + this.getIdViaje() + " - " + this.getVehiculo().getPatente();
	}

}