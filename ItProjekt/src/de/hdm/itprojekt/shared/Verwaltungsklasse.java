package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.*;

import com.google.gwt.user.client.rpc.RemoteService;


public interface Verwaltungsklasse extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	public void setDozent(Dozent d) throws IllegalArgumentException;
	
	public Dozent getDozent() throws IllegalArgumentException;
	
	public Studiengang createStudiengang(String bezeichnung)
			throws IllegalArgumentException;
	
	public Stundenplaneintrag createStundenplaneintrag(Dozent d, Lehrveranstaltung l, Raum r, 
			Zeitslot z, Semesterverband sv)
					throws IllegalArgumentException;
	
	Dozent createDozent(String vorname, String nachname);
	
	public Zeitslot createZeitslot(String wochentag, double anfangszeit, double endzeit)
			throws IllegalArgumentException;
	
	public Lehrveranstaltung createLehrveranstaltung(String bezeichnung, int semester, int umfang)
			throws IllegalArgumentException;
	
	public Raum createRaum(String bezeichnung, int kapazitaet)
			throws IllegalArgumentException;
	
	public Semesterverband createSemesterverband(Studiengang bezeichnung, int semester, int studierendenAnzahl, String jahrgang)
			throws IllegalArgumentException;
	
	public void deleteZeitslot(Zeitslot z)
			throws IllegalArgumentException;
	
	public void deleteStudiengang(Studiengang sg)
			throws IllegalArgumentException;
	
	public void deleteStundenplaneintrag(Stundenplaneintrag s)
			throws IllegalArgumentException;
	
	public void deleteDozent(Dozent d) 
			throws IllegalArgumentException;
	
	public void deleteLehrveranstaltung(Lehrveranstaltung l) 
			throws IllegalArgumentException;
	
	public void deleteRaum(Raum r) 
			throws IllegalArgumentException;
	
	public void deleteSemesterverband(Semesterverband sv) 
			throws IllegalArgumentException;
	
	public Studiengang changeStudiengang(Studiengang s)
			throws IllegalArgumentException;
	
	public Zeitslot changeZeitslot(Zeitslot z)
			throws IllegalArgumentException;
	
	public Stundenplaneintrag changeStundenplaneintrag(Stundenplaneintrag s)
			throws IllegalArgumentException;
	
	public Dozent changeDozent(Dozent d)
			throws IllegalArgumentException;
	
	public Lehrveranstaltung changeLehrveranstaltung(Lehrveranstaltung l)
			throws IllegalArgumentException;
	
	public Raum changeRaum(Raum r)
			throws IllegalArgumentException;
	
	public Semesterverband changeSemsterverband(Semesterverband sv)
			throws IllegalArgumentException;
}

