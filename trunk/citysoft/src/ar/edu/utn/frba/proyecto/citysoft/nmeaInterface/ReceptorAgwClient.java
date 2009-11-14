package ar.edu.utn.frba.proyecto.citysoft.nmeaInterface;

import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import agwClient.Controller;
import agwClient.Functions;
import agwClient.Packet;
import agwClient.PacketTransport;
import agwClient.PacketUser;
import ar.edu.utn.frba.proyecto.citysoft.config.ArchivoDeConfiguracion;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Central;
import ar.edu.utn.frba.proyecto.citysoft.modelo.Vehiculo;

public class ReceptorAgwClient implements Runnable, PacketUser {

	// **************************************
	// ** Attributes
	// **************************************

	String hostname;
	int port;

	PacketTransport remote; // NIO support
	Controller controller;
	int state = Packet.CLOSED;
	private static int agw_monitor_status = 0;
	boolean running = false;

	// **************************************
	// ** Execution
	// **************************************

	public static void main(String[] args) {
		new ReceptorAgwClient().run();
	}

	/**
	 * Empieza a leer actualizaciones de la información de localización
	 */
	@Override
	public void run() {
		controller = new Controller(); // runs the NIO select
		controller.start(true); // start NIO select as a separate thread
		remote = new PacketTransport(controller);
		hostname = ArchivoDeConfiguracion.getInstance().getReceptorHostname();
		port = ArchivoDeConfiguracion.getInstance().getReceptorPuerto();
		if (remote.connect(this, hostname, port))
			setSockState(Packet.OPENING);
		else
			remote = null;

		monitorAGW();

		// while (true) {
		// String nmea = leer(reader);
		// hacer(nmea);
		// }
	}

	// private String leer(BufferedReader reader) {
	// String nmea = "";
	// try {
	// // TODO leer byte por byte
	// nmea = reader.readLine();
	// } catch (IOException e) {
	// Logger.getLogger(this.getClass()).error("No se puede leer sentencia NMEA",
	// e);
	// throw new RuntimeException(e);
	// }
	// return nmea;
	// }

	private void hacer(String trackerId, String nmea) {
		Logger.getLogger(this.getClass()).debug("Sentencia NMEA recibida: " + nmea);
		try {
			NmeaGeopos pos = NmeaGeoposParser.parse(nmea);
			Vehiculo v = Central.getInstance().getVehiculoPorTrackerId(trackerId);
			v.nuevoTrack(pos.lat, pos.lng);
			// Siempre que modificamos algo del vehiculo tenemos que
			// re-guardarlo para actualizra la DB
			Central.getInstance().addVehiculo(v);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("*** ERROR: No se pudo interpretar sentencia NMEA: " + nmea);
		}
	}

	// **************************************
	// ** [AGW CLIENT] Comando TURN ON MONITOR
	// **************************************

	void monitorAGW() {
		toLog("send monitor request");
		Packet pkt = new Packet(this, Packet.SEND, null, null); // get an empty
		// send packet
		pkt.setDataKind((int) 'm'); // set the type
		pkt.setPort(0);
		pkt.setCallTo(null);
		pkt.setCallFrom(null);
		try {
			remote.send(pkt);
			if (agw_monitor_status == 0) {
				agw_monitor_status = 1;
				// agwMonitorItem.setText("Off AGWPE Monitor");
			} else {
				// agwMonitorItem.setText("On AGWPE Monitor");
				agw_monitor_status = 0;
			}
		} catch (Exception e) {
			toLog(e.toString());
			toLog("Did you connect?");
		}
	}

	private void receive(Packet pkt) {
		toLog("received packet data length=" + pkt.getDataLength());

		ByteBuffer hbuffer = pkt.getHeader();
		Functions.println("header position=" + hbuffer.position() + " limit=" + hbuffer.limit()
				+ " remaining=" + hbuffer.remaining());

		ByteBuffer buffer = pkt.getData();
		Functions.println("received position=" + buffer.position() + " limit=" + buffer.limit()
				+ " remaining=" + buffer.remaining());

		toLog("Header", true);
		int hlen = hbuffer.limit();
		byte[] head = new byte[hlen];
		hbuffer.get(head, 0, hlen);
		toLogHex(head, 0, hlen);
		System.out.println("[AGW INTERFACE] " + head);

		toLog("Data", true);
		int dlen = buffer.limit();
		byte[] dat = new byte[dlen];
		buffer.get(dat, 0, dlen);
		toLogHex(dat, 0, dlen);
		System.out.println("[AGW INTERFACE] " + dat);

		String packetBody = Functions.atochar(dat, 0, dlen);
		// Tomamos el vehiculo destino
		String positionSource = packetBody.substring(packetBody.indexOf(":Fm ") + 4, packetBody
				.indexOf(" To "));
		System.out.println("Fuente: " + positionSource);
		// Tomamos la sentencia NMEA
		String nmeaSentence = packetBody.substring(packetBody.indexOf("$GP"), dlen - 3);
		System.out.println("NMEA SENTENCE: " + nmeaSentence);
		hacer(positionSource, nmeaSentence);
	}

	// **************************************
	// ** [AGW CLINET] Y ESTO?!?!?!
	// **************************************

	// called when the run method in Packet is executed in the AWT event
	// dispatch thread
	// looks a bit like an action event
	public void runPacket(Packet pkt) {
		int type = pkt.getType();

		switch (type) {
		case Packet.STATE:
			int s = ((Integer) pkt.getArg()).intValue();
			setSockState(s);
			break;

		case Packet.RECEIVE:
			receive(pkt);
			break;

		case Packet.FLOW:
			boolean b = ((Boolean) pkt.getArg()).booleanValue();
			toLog("flow=" + b);
			break;
		default:
			toLog("unexpected packet type=" + type);
			break;
		}
	}

	// uses invokeLater to put this packet on the system event queue so Swing
	// will run it
	public void postPacket(Packet pkt) {
		// the packet implements Runnable, so we can use invokeLater
		SwingUtilities.invokeLater(pkt);
	}

	private void setSockState(int s) {
		if (state != s) {
			state = s;
			switch (state) {
			case Packet.OPENED:
				// startItem.setText("Disconnect");
				setStatus("Connected to " + hostname);
				break;
			case Packet.CLOSED:
				// startItem.setText("Connect");
				// agwMonitorItem.setText("On AGWPE Monitor");
				agw_monitor_status = 0;
				setStatus("Disconnected");
				remote = null;
				if (!running)
					System.exit(0);
				break;
			case Packet.OPENING:
				setStatus("Connecting to " + hostname);
				// startItem.setText("Abort");
				break;

			case Packet.CLOSING:
				setStatus("Disconnecting from " + hostname);
				// startItem.setText("Abort");
				break;
			}
		}
	}

	void setStatus(String st) {
		System.out.println("[AGW INTERFACE] " + st);
	}

	// methods to put text in logging window, toLog(text,true) if it came from
	// user
	void toLog(String txt) {
		toLog(txt, false);
	}

	void toLog(String txt, boolean user) {
		System.out.println("[AGW INTERFACE] " + (user ? "> " : "< ") + txt);
	}

	/**
	 * print the hexadecimal and character representation of a byte array
	 * 
	 * @param ba
	 *            the byte array
	 * @param ofs
	 *            the offset to start at
	 * @param len
	 *            the length to print
	 */
	public void toLogHex(byte[] ba, int ofs, int len) {
		int dlen;
		String dstr;

		int originalOffset = ofs;
		int originalLen = len;
		while (len > 0) {
			if (len < 16) {
				dlen = len;
				char[] c48 = new char[48];
				Arrays.fill(c48, ' ');
				String fill48 = String.valueOf(c48);
				dstr = fill48.substring(0, 3 * (16 - dlen));
			} else {
				dlen = 16;
				dstr = "";
			}
			dstr = Functions.tohex4(ofs) + ": " + Functions.atohex(ba, ofs, dlen) + dstr + ": "
					+ Functions.atochar(ba, ofs, dlen);
			toLog(dstr, true);
			ofs += dlen;
			len -= dlen;
		}
		System.out.println("[AGW INTERFACE] conv: "
				+ Functions.atochar(ba, originalOffset, originalLen));
	}

}
