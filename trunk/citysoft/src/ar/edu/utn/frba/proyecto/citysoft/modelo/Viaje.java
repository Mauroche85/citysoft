package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.Date;
import java.util.SortedSet;

import com.blogspot.unserializableone.GAddress;
import com.blogspot.unserializableone.GCoder;

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
public class Viaje implements ObjetoDeDominio {

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

	public Viaje(int idViaje) {
		if (Central.getInstance().getViaje(idViaje) != null) {
			throw new RuntimeException("Ya existe un viaje con el id " + idViaje);
		}
		this.idViaje = idViaje;
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
		if (this.origenLatitud == 0)
			reconocerCoordenadas();
		return origenLatitud;
	}

	public void setOrigenLongitud(double origenLongitud) {
		this.origenLongitud = origenLongitud;
	}

	public double getOrigenLongitud() {
		if (this.origenLongitud == 0)
			reconocerCoordenadas();
		return origenLongitud;
	}

	public void setDestinoLatitud(double destinoLatitud) {
		this.destinoLatitud = destinoLatitud;
	}

	public double getDestinoLatitud() {
		if (this.destinoLatitud == 0)
			reconocerCoordenadas();
		return destinoLatitud;
	}

	public void setDestinoLongitud(double destinoLongitud) {
		this.destinoLongitud = destinoLongitud;
	}

	public double getDestinoLongitud() {
		if (this.destinoLongitud == 0)
			reconocerCoordenadas();
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
		asignar(t, new Date());
	}

	public void asignar(Vehiculo t, Date horaAsignacion) {
		validarViajePendiente();
		this.vehiculo = t;
		t.setViajeEnCurso(this);
		this.setHoraAsignado(horaAsignacion);
		this.estado = ESTADO_ASIGNADO;
	}

	/**
	 * Esta accion retrodece el estado del taxi. Es como si le hubiesen asignado
	 * un chofer y luego cambiado por otro, o desasignado chofer para luego
	 * cancelar el viaje.
	 */
	public void liberar() {
		validarViajeAsignado();
		this.getVehiculo().limpiarViajeEnCurso();
		this.setVehiculo(null);
		this.setHoraAsignado(null);
		this.estado = ESTADO_PENDIENTE;
	}

	public void comenzar() {
		comenzar(new Date());
	}

	public void comenzar(Date horaComienzo) {
		validarViajeAsignado();
		this.setHoraComienzo(horaComienzo);
		this.estado = ESTADO_TRANSPORTANDO;
	}

	public void finalizar() {
		finalizar(new Date());
	}

	public void finalizar(Date horaFinalizacion) {
		validarViajeTransportando();
		this.setHoraFin(horaFinalizacion);
		this.getVehiculo().limpiarViajeEnCurso();
		this.estado = ESTADO_COMPLETADO;
	}

	public void cancelar() {
		validarViajePendiente();
		this.estado = ESTADO_CANCELADO;
	}

	/**
	 * @return lista de {@link Track} ordenada cronologicamente, con la posicion
	 *         del taxi desde que el taxi comienza a transportar el pasajero
	 *         hasta que finaliza el viajes
	 */
	public SortedSet<Track> getTracksDelViaje() {
		Vehiculo v = getVehiculo();
		// Desde (intervalo cerrado)
		Track fromElement = new Track(this.getHoraComienzo());
		// Hasta (intervalo abierto ==> sumarle un cachitin)
		Date hasta = new Date(this.getHoraFin().getTime() + 1);
		Track toElement = new Track(hasta);
		// Tomamos el subconjunto y lo devolvemos!!!
		return v.getTracks().subSet(fromElement, toElement);
	}

	public void reconocerCoordenadas() {
		String strAddress;
		GAddress googleAddress = new GAddress();
		// **************************************
		// ** Busco cordenadas origen
		// **************************************
		strAddress = this.origenCalle + " " + this.origenAltura + ", " + this.origenProvincia
				+ ", Argentina";
		googleAddress = GCoder.geocode(strAddress);
		this.origenLatitud = googleAddress.getLat();
		this.origenLongitud = googleAddress.getLng();
		// **************************************
		// ** Busco cordenadas destino
		// **************************************
		strAddress = this.destinoCalle + " " + this.destinoAltura + ", " + this.destinoProvincia
				+ ", Argentina";
		googleAddress = GCoder.geocode(strAddress);
		this.destinoLatitud = googleAddress.getLat();
		this.destinoLongitud = googleAddress.getLng();
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
			throw new RuntimeException("El viaje debería estar asignado (" + this.estado + ")");
		}
	}

	public void validarViajeTransportando() {
		if (!this.estado.equals(ESTADO_TRANSPORTANDO)) {
			throw new RuntimeException("El viaje debería estar transportando (" + this.estado + ")");
		}
	}

	public void validarViajeEnCurso() {
		if (!this.estado.equals(ESTADO_TRANSPORTANDO) && !this.estado.equals(ESTADO_ASIGNADO)) {
			throw new RuntimeException("El viaje debería estar en curso (" + this.estado + ")");
		}
	}

	// **************************************
	// ** Miscelanea
	// **************************************

	public String getDescripcionOrigen() {
		return getOrigenCalle() + " " + getOrigenAltura() + ", " + getOrigenProvincia()
				+ ", Argentina";
	}

	public String getDescripcionDesitno() {
		return getDestinoCalle() + " " + getDestinoAltura() + ", " + getDestinoProvincia()
				+ ", Argentina";
	}

	// **************************************
	// ** Interfaces
	// **************************************

	@Override
	public int compareTo(ObjetoDeDominio theOther) {
		int thisId = this.idViaje;
		int theOtherId = ((Viaje) theOther).idViaje;
		// Esto nos lo choriceamos de Integer!!!
		return (thisId < theOtherId ? -1 : (thisId == theOtherId ? 0 : 1));
	}

	@Override
	public String toString() {
		return (getVehiculo() == null) ? "" + getIdViaje() : getVehiculo().getPatente() + " - "
				+ getIdViaje();
	}

}