package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Radio;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;

public class ListitemOperadorPloteable extends ListitemOperadorPloteableBasico implements
		ConstantesGeneralesDeVentanas {

	private static final long serialVersionUID = -6169094911886451076L;

	// **************************************
	// ** Execution
	// **************************************

	public void mostrarOcultarObjetoPloteable(boolean mostrar) {
		if (mostrar)
			this.objetoPloteable.mostrarEnMapa(getVentana().elemMapa());
		else
			this.objetoPloteable.quitarDelMapa();
	}

	public void centrarObjetoPloteable() {
		this.objetoPloteable.centrar();
	}

	// **************************************
	// ** Builders
	// **************************************

	protected void buildCelda() {
		Listcell cell = new Listcell();
		cell.setParent(this);
		buildBox(cell);
	}

	protected void buildBox(Listcell cell) {
		Hbox hbox = new Hbox();
		hbox.setWidths("130px,,");
		hbox.setAlign("center");
		buildLabel(hbox);
		buildCheckboxMostrarPlot(hbox);
		buildRadioCentrar(hbox);
		hbox.setParent(cell);
	}

	protected void buildLabel(Component parent) {
		Label label = new Label();
		label.setValue(getObjetoPloteable().getObjetoDelModelo().toString());
		label.setWidth("130px");
		label.setParent(parent);
	}

	protected void buildCheckboxMostrarPlot(Component parent) {
		final Checkbox check = new Checkbox();
		final ListitemOperadorPloteable listItem = this;
		check.addEventListener(Events.ON_CHECK, new EventListener() {
			private ListitemOperadorPloteable item = listItem;

			@Override
			public void onEvent(Event event) throws Exception {
				item.mostrarOcultarObjetoPloteable(check.isChecked());
			}
		});
		check.setChecked(this.objetoPloteable.estoyPloteado());
		check.setParent(parent);
	}

	protected void buildRadioCentrar(Component parent) {
		if (this.objetoPloteable.soyCentrable()) {
			final Radio radio = new Radio();
			final ListitemOperadorPloteable listItem = this;
			radio.addEventListener(Events.ON_CHECK, new EventListener() {
				private ListitemOperadorPloteable item = listItem;

				@Override
				public void onEvent(Event event) throws Exception {
					item.centrarObjetoPloteable();
				}
			});
			radio.setChecked(this.objetoPloteable.estoyPloteado()
					&& this.objetoPloteable.soyCentrable() && this.objetoPloteable.soyCentro());
			radio.setParent(parent);
		}
	}

}
