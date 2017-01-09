package it.polito.dp2.geoip;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * JAXB-annotated value class corresponding to the GeoIP structure returned by the service
 */
@XmlRootElement(name="GeoIP",namespace="http://www.webservicex.net/")
public class GeoIP {
	private int ReturnCode;
	private String IP;
	private String ReturnCodeDetails;
	private String CountryName;
	private String CountryCode;

	public GeoIP() {
	}

	@XmlElement(name="ReturnCode", namespace="http://www.webservicex.net/")
	public int getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(int returnCode) {
		ReturnCode = returnCode;
	}

	@XmlElement(name="IP", namespace="http://www.webservicex.net/")
	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	@XmlElement(name="ReturnCodeDetails", namespace="http://www.webservicex.net/")
	public String getReturnCodeDetails() {
		return ReturnCodeDetails;
	}

	public void setReturnCodeDetails(String returnCodeDetails) {
		ReturnCodeDetails = returnCodeDetails;
	}

	@XmlElement(name="CountryName", namespace="http://www.webservicex.net/")
	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	@XmlElement(name="CountryCode", namespace="http://www.webservicex.net/")
	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

}
