package filmDatabaseApp;

public enum Genre {
	NOGENRE("N/A"), DRAMA("DRAMA"), COMEDY("COMEDY"), ACTION("ACTION"), HORROR("HORROR");


	private String gValues;

	Genre() {
		this.gValues = "N/A";
	}

	public String getGValues() {
		return gValues;
	}

	public void setGValues(String gValues) {
		this.gValues = gValues;
	}

	private Genre(String gValues) {
		this.gValues = gValues;
	}
	
	
}
