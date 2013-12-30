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

	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}

	public Lehrveranstaltung getLehrveranstaltung() {
		return lehrveranstaltung;
	}

	public void setLehrveranstaltung(Lehrveranstaltung lehrveranstaltung) {
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
	
	public String toString(){
		return this.zeitslot.getAnfangszeit() + " - " + this.zeitslot.getEndzeit() + "\n" +
				this.lehrveranstaltung.getBezeichnung() + "\n" +
				this.raum.getBezeichnung();
	}
	
	

}
