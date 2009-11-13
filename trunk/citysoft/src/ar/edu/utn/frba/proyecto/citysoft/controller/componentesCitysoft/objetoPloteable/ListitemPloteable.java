package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityVentanaConMapa;

public class ListitemPloteable extends Listitem implements ConstantesGeneralesDeVentanas {

	private static final long serialVersionUID = 1939563104121561569L;

	// **************************************
	// ** Atributos
	// **************************************

	private CityVentanaConMapa ventana;
	private ObjetoPlotteable objetoPloteable;

	// **************************************
	// ** Accessors
	// **************************************

	public CityVentanaConMapa getVentana() {
		return ventana;
	}

	public void setVentana(CityVentanaConMapa ventana) {
		this.ventana = ventana;
	}

	public ObjetoPlotteable getObjetoPloteable() {
		return objetoPloteable;
	}

	public void setObjetoPloteable(ObjetoPlotteable objetoPloteable) {
		this.objetoPloteable = objetoPloteable;
		buildCelda();
	}

	// **************************************
	// ** Execution
	// **************************************

	public void mostrarOcultarObjetoPloteable(boolean mostrar) {
		if (mostrar)
			this.objetoPloteable.mostrarEnMapa(getVentana().elemMapa());
		else
			this.objetoPloteable.quitarDelMapa();
	}

	// **************************************
	// ** Builders
	// **************************************

	private void buildCelda() {
		Listcell cell = new Listcell();
		cell.setParent(this);
		// Otros componentes...
		buildLabel(cell);
		buildCheckboxMostrarPlot(cell);
	}

	private void buildLabel(Listcell cell) {
		Label label = new Label();
		label.setValue(getObjetoPloteable().getObjetoDelModelo().toString());
		label.setParent(cell);
	}

	private void buildCheckboxMostrarPlot(Listcell cell) {
		final Checkbox check = new Checkbox();
		final ListitemPloteable listItem = this;
		check.addEventListener(Events.ON_CHECK, new EventListener() {
			private ListitemPloteable item = listItem;

			@Override
			public void onEvent(Event event) throws Exception {
				item.mostrarOcultarObjetoPloteable(check.isChecked());
			}
		});
		check.setParent(cell);
	}

}
