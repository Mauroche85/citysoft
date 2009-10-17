package com.blogspot.unserializableone;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

public class GCoder {
	private static final String URL = "http://maps.google.com/maps/geo?output=json";
	private static final String DEFAULT_KEY = "ABQIAAAAxPbpriJFATP1kV4Jfg7FrhTHx8S3jtCwO1hw0XE3N9WOac4cqRQ8_vMKB22No44yFau5GRY7TcCHZA";

	public static GAddress geocode(String address, String key) throws Exception {
		URL url = new URL(URL + "&q=" + URLEncoder.encode(address, "UTF-8") + "&key=" + key);
		URLConnection conn = url.openConnection();
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
		IOUtils.copy(conn.getInputStream(), output);
		output.close();
		GAddress gaddr = new GAddress();
		JSONObject json = JSONObject.fromObject(output.toString());
		JSONObject placemark = (JSONObject) query(json, "Placemark[0]");
		final String commonId = "AddressDetails.Country.AdministrativeArea";
		gaddr.setFullAddress(query(placemark, "address").toString());
		gaddr.setZipCode(query(placemark,
				commonId + ".SubAdministrativeArea.Locality.PostalCode.PostalCodeNumber").toString());
		gaddr.setAddress(query(placemark,
				commonId + ".SubAdministrativeArea.Locality.Thoroughfare.ThoroughfareName")
				.toString());
		gaddr.setCity(query(placemark, commonId + ".SubAdministrativeArea.SubAdministrativeAreaName")
				.toString());
		gaddr.setState(query(placemark, commonId + ".AdministrativeAreaName").toString());
		gaddr.setLat(Double.parseDouble(query(placemark, "Point.coordinates[1]").toString()));
		gaddr.setLng(Double.parseDouble(query(placemark, "Point.coordinates[0]").toString()));
		return gaddr;
	}

	public static GAddress geocode(String address) {
		try {
			return geocode(address, DEFAULT_KEY);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} /* allow query for json nested objects, ie. Placemark[0].address */

	private static Object query(JSONObject jo, String query) {
		try {
			String[] keys = query.split("\\.");
			Object r = queryHelper(jo, keys[0]);
			for (int i = 1; i < keys.length; i++) {
				r = queryHelper(JSONObject.fromObject(r), keys[i]);
			}
			return r;
		} catch (JSONException e) {
			return "";
		}
	} /* help in query array objects: Placemark[0] */

	private static Object queryHelper(JSONObject jo, String query) {
		int openIndex = query.indexOf('[');
		int endIndex = query.indexOf(']');
		if (openIndex > 0) {
			String key = query.substring(0, openIndex);
			int index = Integer.parseInt(query.substring(openIndex + 1, endIndex));
			return jo.getJSONArray(key).get(index);
		}
		return jo.get(query);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(GCoder.geocode("28 275, Mercedes, Buenos Aires, Argentina"));
		System.out.println(GCoder.geocode("94103"));
		System.out
				.println(GCoder.geocode("peña 2700 capital federal cdad. autonoma de buenos aires"));
		System.out.println(GCoder
				.geocode("2700 peña, capital federal, ciudad autonoma de buenos aires, argentina"));
	}
}
