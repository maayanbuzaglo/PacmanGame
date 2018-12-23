package File_format;

/*
 * This class represents an object of csv file.
 * @author maayan
 * @author nahama
 */
public class Row_Locate {
	
	String MAC;
	String SSID;
	String AuthMode;	
	String FirstSeen;	
	double CurrentLatitude;	
	double CurrentLongitude;	
	double AltitudeMeters;
	
	/*
	 * Constructor that makes a csv file.
	 */
	public Row_Locate(String mAC, String sSID, String authMode, String firstSeen,
			          double currentLatitude, double currentLongitude, double altitudeMeters) {
		this.MAC = mAC;
		this.SSID = sSID;
		this.AuthMode = authMode;
		this.FirstSeen = firstSeen;
		this.CurrentLatitude = currentLatitude;
		this.CurrentLongitude = currentLongitude;
		this.AltitudeMeters = altitudeMeters;
	}
	
	/*
	 * This function prints the csv file.
	 */
	public String toString() {
		return "[MAC = " + MAC + 
				", SSID = " + SSID + 
				", AuthMode = " + AuthMode + 
				", FirstSeen = " + FirstSeen +  
				", CurrentLatitude = " + CurrentLatitude + 
				", CurrentLongitude = " + CurrentLongitude + ".]";
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public void setAuthMode(String authMode) {
		AuthMode = authMode;
	}

	public String getFirstSeen() {
		return FirstSeen;
	}

	public void setFirstSeen(String firstSeen) {
		FirstSeen = firstSeen;
	}

	public double getCurrentLatitude() {
		return CurrentLatitude;
	}

	public void setCurrentLatitude(double currentLatitude) {
		CurrentLatitude = currentLatitude;
	}

	public double getCurrentLongitude() {
		return CurrentLongitude;
	}

	public void setCurrentLongitude(double currentLongitude) {
		CurrentLongitude = currentLongitude;
	}

	public double getAltitudeMeters() {
		return AltitudeMeters;
	}

	public void setAltitudeMeters(double altitudeMeters) {
		AltitudeMeters = altitudeMeters;
	}

}