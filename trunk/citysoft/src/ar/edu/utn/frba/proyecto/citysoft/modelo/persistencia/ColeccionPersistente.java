package ar.edu.utn.frba.proyecto.citysoft.modelo.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import ar.edu.utn.frba.proyecto.citysoft.config.ContextoAplicacion;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ColeccionPersistente<E> implements Collection<E> {

	private static final long serialVersionUID = 9138157570196958793L;

	// **************************************
	// ** Consutrctor
	// **************************************

	/**
	 * Ya debe tener abierta la conexion con la base
	 */
	public ColeccionPersistente(Class<E> claseParaPersistir) {
		ObjectSet<E> query = (ObjectSet<E>) getDb().query(claseParaPersistir);
		while (query.hasNext()) {
			this.l.add(query.next());
		}
	}

	// **************************************
	// ** Underlying collection
	// **************************************

	private List<E> l = new ArrayList<E>();

	private ObjectContainer db;

	// **************************************
	// ** Interface
	// **************************************

	@Override
	public boolean add(E e) {
		guardarEnDb(e);
		return this.l.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			guardarEnDb(it.next());
		}
		return this.l.addAll(c);
	}

	@Override
	public void clear() {
		for (Iterator<E> it = this.l.iterator(); it.hasNext();) {
			borrarDeDb(it.next());
		}
		this.l.clear();
	}

	@Override
	public boolean contains(Object o) {
		return l.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.l.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return this.l.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return l.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		borrarDeDb((E) o);
		return this.l.remove(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> c) {
		for (Iterator<?> it = c.iterator(); it.hasNext();) {
			borrarDeDb((E) it.next());
		}
		return this.l.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// es complicado este metodo...
		//
		// return this.l.retainAll(c);
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return this.l.size();
	}

	@Override
	public Object[] toArray() {
		return this.l.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.l.toArray(a);
	}

	// **************************************
	// ** HELPERS
	// **************************************

	private ObjectContainer getDb() {
		if (this.db == null) {
			this.db = ContextoAplicacion.getInstance().obtenerDb();
		}
		return this.db;
	}

	private void guardarEnDb(E e) {
		getDb().store(e);
		getDb().commit();
	}

	private void borrarDeDb(E e) {
		getDb().delete(e);
		getDb().commit();
	}

}
