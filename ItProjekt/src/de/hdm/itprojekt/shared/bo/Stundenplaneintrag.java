package de.hdm.itprojekt.shared.bo;

public class Stundenplaneintrag extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	
	/**
	 * jeweilige Businessobjekte der Stundenplaneintraege
	 */
	private int dozentId;
	private int lehrveranstaltungId;
	private int raumId;
	private int zeitslotId;
	private int semesterverbandID;
	
	public Stundenplaneintrag(){
	}
	
	public 

	public int getDozentId() {
		return dozentId;
	}

	public void setDozentId(int dozentId) {
		this.dozentId = dozentId;
	}

	public int getLehrveranstaltungId() {
		return lehrveranstaltungId;
	}

	public void setLehrveranstaltungId(int lehrveranstaltungId) {
		this.lehrveranstaltungId = lehrveranstaltungId;
	}

	public int getRaumId() {
		return raumId;
	}

	public void setRaumId(int raumId) {
		this.raumId = raumId;
	}

	public int getZeitslotId() {
		return zeitslotId;
	}

	public void setZeitslotId(int zeitslotId) {
		this.zeitslotId = zeitslotId;
	}

	public int getSemesterverbandID() {
		return semesterverbandID;
	}

	public void setSemesterverbandID(int semesterverbandID) {
		this.semesterverbandID = semesterverbandID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
