package ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.objetoPloteable;

import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMapa;
import ar.edu.utn.frba.proyecto.citysoft.controller.componentesCitysoft.CityMarcador;

/**
 * Representa a un determinado objeto del modelo que tenga uno o varios aspectos
 * representables en un {@link CityMapa}. Ejemplos:
 * 
 * <li>El vehiculo (objeto del modelo) tiene una posicion en un mapa (aspecto
 * representable)
 * 
 * <li>Una solicitud de viaje (objeto del modelo) tiene un origen y un destino
 * (ambos aspectos representables)
 * 
 * <li>Un equipo de futbol (objeto del model) tiene jugadores repartidos dentro
 * de un campo de juego (12 aspectos representables).
 * 
 * <br>
 * Para dejar las cosas bien claras, si hablamos de un equipo de futbol,
 * entonces este objeto representa al equipo en si mismo, y no a sus jugadores.
 * Así, este objeto debe ofrecerme la posibilidad de mostrarme la posicion de
 * todos sus jugadores. Consecuentemente, este objto debe tener la capacidad de
 * hablar con el equipo para pedirle todos sus jugadores y luego preguntarle las
 * coordenadas a cada uno de ellos.
 */
public abstract class ObjetoPlotteable {

	public abstract Object getObjetoDelModelo();

	/**
	 * Crea y/o attachean los marcadores particulares de cada subclase al mapa
	 * recibido por parametro. Si los marcadores ya existian y pertenecian a
	 * otro mapa, entonces deberan desatacharse del mapa corriente y se
	 * atacharan al mapa nuevo.
	 * 
	 * Tengo marcadores <==> tengo mapa
	 */
	public abstract void mostrarMarcadores(CityMapa mapa);

	/**
	 * ELIMINA los marcadores del mapa. Debe hacer un detach y luego tirarlos a
	 * la basura porque el detach parece indicar que si no lo reattachas, la
	 * arquitectura disposea los recursos asociados en el cliente
	 * 
	 * Tengo marcadores <==> tengo mapa
	 */
	public abstract void quitarMarcadoresDelMapa();

	public abstract List<CityMarcador> getMarcadores();

	/**
	 * Toma todos sus marcadores y otros objetos ploteables, y los actualiza.
	 * Ej: si este objeto referencia a un viaje en curso, toma el marcador
	 * correspondiente al vehiculo y le actualiza las coordenadas
	 */
	public abstract void actualizarPlotting();

}
