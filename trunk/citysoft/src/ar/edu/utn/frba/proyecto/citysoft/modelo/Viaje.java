package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.Date;

/**
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
	private int horaEstimadaInicio;
	private int horaRealInicio;
	private int horaArriboDestino;
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

	public int getHoraEstimadaInicio() {
		return horaEstimadaInicio;
	}

	public void setHoraEstimadaInicio(int horaEstimadaInicio) {
		this.horaEstimadaInicio = horaEstimadaInicio;
	}

	public int getHoraRealInicio() {
		return horaRealInicio;
	}

	public void setHoraRealInicio(int horaRealInicio) {
		this.horaRealInicio = horaRealInicio;
	}

	public int getHoraArriboDestino() {
		return horaArriboDestino;
	}

	public void setHoraArriboDestino(int horaArriboDestino) {
		this.horaArriboDestino = horaArriboDestino;
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
		this.estado = ESTADO_ASIGNADO;
	}

	public void cancelar() {
		this.estado = ESTADO_CANCELADO;
	}

	// **************************************
	// ** Helpers
	// **************************************

	public void validarViajePendiente() {
		if (!this.estado.equals(ESTADO_PENDIENTE)) {
			throw new RuntimeException("No se asignar un viaje que no está pendiente (" + this.estado
					+ ")");
		}
	}

	/**
	 * Valida que el viaje este ASIGNADO o TRANSPORTANDO
	 */
	public void validarViajeEnCurso() {
		if (!this.estado.equals(ESTADO_ASIGNADO) && !this.estado.equals(ESTADO_TRANSPORTANDO)) {
			throw new RuntimeException("El viaje debería estar en curso (" + this.estado + ")");
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