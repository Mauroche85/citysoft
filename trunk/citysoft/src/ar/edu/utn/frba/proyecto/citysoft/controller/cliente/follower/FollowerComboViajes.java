package ar.edu.utn.frba.proyecto.citysoft.controller.cliente.follower;

import java.util.Collection;
import java.util.TreeSet;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.zkoss.zul.Combobox;

import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Viaje;
import ar.edu.utn.frba.proyecto.citysoft.user.UserContext;

public class FollowerComboViajes extends Combobox {

	public Collection<Viaje> getMisViajes() {
		final TreeSet<Viaje> c = new TreeSet<Viaje>();
		Collection<Viaje> viajes = Central.getInstance().getViajes();
		CollectionUtils.forAllDo(viajes, new Closure() {
			@Override
			public void execute(Object arg0) {
				Viaje v = (Viaje) arg0;
				// Si el viaje le pertenece al usuario conectado
				if ((v.estaAsignado() || v.estaTransportando())
						&& v.getCliente().equals(UserContext.getUserContext().getCliente()))
					c.add(v);
			}
		});
		return c;
	}

}
