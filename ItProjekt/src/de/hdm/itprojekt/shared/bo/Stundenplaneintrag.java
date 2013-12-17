package de.hdm.itprojekt.shared.bo;

public class Stundenplaneintrag extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	
	/**
	 * jeweilige Businessobjekte der Stundenplaneintraege
	 */
	private Dozent dozent;
	private Lehrveranstaltung lehrveranstaltung;
	private Raum raum;
	private Zeitslot zeitslot;
	private Semesterverband semesterverband;
	
	public Stundenplaneintrag(){
	}
	
	public Dozent getDozent() {
		return dozent;
	}
	public void setDozentId(Dozent dozent) {
		this.dozent = dozent;
	}
	public Lehrveranstaltung getLehrveranstaltungs() {
		return lehrveranstaltung;
	}
	public void setLehrveranstaltungs(Lehrveranstaltung lehrveranstaltung) {
		this.lehrveranstaltung = lehrveranstaltung;
	}
	public Raum getRaum() {
		return raum;
	}
	public void setRaum(Raum raum) {
		this.raum = raum;
	}
	public Zeitslot getZeitslot() {
		return zeitslot;
	}
	public void setZeitslot(Zeitslot zeitslot) {
		this.zeitslot = zeitslot;
	}
	public Semesterverband getSemesterverband() {
		return semesterverband;
	}
	public void setSemesterverband(Semesterverband semesterverband) {
		this.semesterverband = semesterverband;
	}


}
