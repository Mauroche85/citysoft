package ar.edu.utn.frba.proyecto.citysoft.modelo;

import java.util.Date;

import org.zkoss.zul.api.Textbox;
import org.zkoss.zul.api.Timebox;

/**
 * @version 1.0
 * @created 23-Jul-2009 10:54:53 p.m.
 */
public class Viaje implements ObjetoDeDominio {

	private int idViaje;
	private Taxi taxi;
	private Cliente cliente;
	private int horaEstimadaInicio;
	private int horaRealInicio;
	private int horaArriboDestino;
	//agregados para almacenar viajes pedidos por UI (GD)
	private Date horaPedido; 
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
	private double origenLatitud;
	private double origenLongitud;
	private double destinoLatitud;
	private double destinoLongitud;
	
	
		
	// **************************************
	// ** Accessors
	// **************************************

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public Date getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(Date horaPedido) {
		this.horaPedido = horaPedido;
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


}