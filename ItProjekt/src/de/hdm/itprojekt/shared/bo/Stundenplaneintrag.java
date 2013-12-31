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
	
	public void setDozentId(int id){
		this.dozent.setId(id);
	}
	
	public int getDozentId(){
		return this.dozent.getId();
	}

	public Dozent getDozent() {
		return dozent;
	}

	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}

	
	public void setLehrveranstaltungId( int id){
		this.lehrveranstaltung.getId();
	}
	
	public int getLehrveranstaltungId(){
		return this.lehrveranstaltung.getId();
	}
	
	public Lehrveranstaltung getLehrveranstaltung() {
		return lehrveranstaltung;
	}

	public void setLehrveranstaltung(Lehrveranstaltung lehrveranstaltung) {
		this.lehrveranstaltung = lehrveranstaltung;
	}
	
	public void setRaumId(int id){
		this.raum.setId(id);
	}
	
	public int getRaumId(){
		return this.raum.getId();
	}

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}
	
	public void setZeitslotId(int id){
		this.zeitslot.getId();
	}
	
	public int getZeitslotId(){
		return this.zeitslot.getId();
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
