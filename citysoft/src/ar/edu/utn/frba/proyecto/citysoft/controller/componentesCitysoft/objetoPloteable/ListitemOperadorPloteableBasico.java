package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityVentanaConMapa;

public class ListitemOperadorPloteableBasico extends Listitem implements
		ConstantesGeneralesDeVentanas {

	private static final long serialVersionUID = -2262250085426515641L;

	// **************************************
	// ** Atributos
	// **************************************

	protected CityVentanaConMapa ventana;
	protected ObjetoPlotteable objetoPloteable;

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
		hbox.setParent(cell);
	}

	protected void buildLabel(Component parent) {
		Label label = new Label();
		label.setValue(getObjetoPloteable().getObjetoDelModelo().toString());
		label.setWidth("130px");
		label.setParent(parent);
	}

}
