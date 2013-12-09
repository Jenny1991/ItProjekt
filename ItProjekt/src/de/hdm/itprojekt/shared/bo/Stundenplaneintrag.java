package de.hdm.itprojekt.shared.bo;

public class Stundenplaneintrag extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschluessel fuer die jeweiligen Referenzen
	 */
	private int dozentId = 0;
	private int lehrveranstaltungsId = 0;
	private int raumId = 0;
	private int zeitslotId = 0;
	private int semesterverbandId = 0;
	
	public Stundenplaneintrag(){
	}
	
	public int getDozentId() {
		return dozentId;
	}
	public void setDozentId(int dozentId) {
		this.dozentId = dozentId;
	}
	public int getLehrveranstaltungsId() {
		return lehrveranstaltungsId;
	}
	public void setLehrveranstaltungsId(int lehrveranstaltungsId) {
		this.lehrveranstaltungsId = lehrveranstaltungsId;
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
	public int getSemesterverbandId() {
		return semesterverbandId;
	}
	public void setSemesterverbandId(int semesterverbandId) {
		this.semesterverbandId = semesterverbandId;
	}


}
