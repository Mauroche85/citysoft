package ar.edu.utn.frba.proyecto.citysoft.modelo;

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

}