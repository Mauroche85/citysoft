package ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.entrega;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Cliente;
import ar.edu.utn.frba.proyecto.citysoft.modelo.ObjetoDeDominio;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;
import ar.edu.utn.frba.proyecto.citysoft.modelo.lotes.Lote;

public class LoteDeEntregaViajeReal implements Lote {

	@Override
	public List<ObjetoDeDominio> getLote() {
		List<ObjetoDeDominio> list = new ArrayList<ObjetoDeDominio>();
		list.add(buildViaje(1));
		return list;
	}

	public void cargar() {
		for (ObjetoDeDominio objetoDominio : getLote()) {
			Viaje v = (Viaje) objetoDominio;
			Central.getInstance().addViaje(v);
		}
	}

	// **************************************
	// ** Helpers
	// **************************************

	private Viaje buildViaje(int idViaje) {
		Viaje v = new Viaje(1);
		v.setCliente(findClienteTomassino());
		// A las 22:30hs
		v.setHoraRequerida(new GregorianCalendar(2009, 10, 13, 22, 30).getTime());
		// Origen
		v.setOrigenReferente("Carlitos");
		v.setOrigenCalle("Medrano");
		v.setOrigenAltura("950");
		v.setOrigenPisoDepto("");
		v.setOrigenLocalidad("Capital Federal");
		v.setOrigenProvincia("Capital Federal");
		v.setOrigenObservaciones("");
		// Destino
		v.setDestinoCalle("Mozart");
		v.setDestinoAltura("900");
		v.setDestinoPisoDepto("");
		v.setDestinoLocalidad("Capital Federal");
		v.setDestinoProvincia("Capital Federal");
		return v;
	}

	private Cliente findClienteTomassino() {
		return Central.getInstance().getCliente("ctomassino");
	}

}
