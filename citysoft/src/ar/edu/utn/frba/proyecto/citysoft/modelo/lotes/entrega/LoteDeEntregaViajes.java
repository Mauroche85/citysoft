package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.Lote;
import ar.edu.utn.frba.proyecto.citysoft.nmeaInterface.Simulaciones;
import ar.edu.utn.frba.proyecto.citysoft.utils.CoordenadasUtils;

public class LoteDeEntregaViajes implements Lote, Simulaciones {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> list = new ArrayList<ObjetoDeDominio>();
		list.add(buildViajeFinalizado());
		list.add(buildViajePendiente());
		return list;
	}

	public void cargar() {
		for (ObjetoDeDominio objetoDominio : getLote()) {
			Viaje v = (Viaje) objetoDominio;
			Central.getInstance().addViaje(v);
			if (v.estaAsignado() || v.estaTransportando() || v.fueCancelado() || v.fueCompletado())
				Central.getInstance().addVehiculo(v.getVehiculo());
		}
	}

	// **************************************
	// ** VIAJE FINALIZADO
	// **************************************

	private Viaje buildViajeFinalizado() {
		Viaje v = new Viaje(1);
		v.setCliente(findClienteTomassino());
		v.setOrigenReferente("Carlitos");
		v.setOrigenObservaciones("");
		deMedranoACampus(v);
		// Fue a las 22:30hs
		v.setHoraRequerida(new GregorianCalendar(2009, 10, 1, 22, 30).getTime());

		// SIMULAMOS COMIENZO DE VIAJE
		Date horaAsignacion = new GregorianCalendar(2009, 10, 1, 22, 0).getTime();
		Date horaComienzo = new GregorianCalendar(2009, 10, 1, 22, 32).getTime();
		Date horaFinalizacion = new GregorianCalendar(2009, 10, 1, 22, 56).getTime();
		v.asignar(findVehiculoQuaiers(), horaAsignacion);
		v.comenzar(horaComienzo);
		v.finalizar(horaFinalizacion);
		CoordenadasUtils.generarTracksParaViaje(v, SIM__MEDRANO_CAMPUS_FILE, horaComienzo);

		return v;
	}

	// **************************************
	// ** VIAJE PENDIENTE
	// **************************************

	private Viaje buildViajePendiente() {
		Viaje v = new Viaje(2);
		v.setCliente(findClienteTomassino());
		v.setOrigenReferente("Carlitos");
		v.setOrigenObservaciones("");
		deMedranoACampus(v);
		// A las 22:30hs
		v.setHoraRequerida(new GregorianCalendar(2009, 10, 13, 22, 30).getTime());
		return v;
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private Cliente findClienteTomassino() {
		return Central.getInstance().getCliente("ctomassino");
	}

	private Vehiculo findVehiculoQuaiers() {
		return Central.getInstance().getVehiculoPorTrackerId("TAXI01");
	}

	private void deMedranoACampus(Viaje v) {
		// Origen
		v.setOrigenCalle("Medrano");
		v.setOrigenAltura("950");
		v.setOrigenPisoDepto("");
		v.setOrigenLocalidad("Capital Federal");
		v.setOrigenProvincia("Capital Federal");
		// Destino
		v.setDestinoCalle("Mozart");
		v.setDestinoAltura("2300");
		v.setDestinoPisoDepto("");
		v.setDestinoLocalidad("Capital Federal");
		v.setDestinoProvincia("Capital Federal");
	}

	// **************************************
	// ** Object
	// **************************************

	public String toString() {
		return "Lote de viajes para entrega final";
	}

}
