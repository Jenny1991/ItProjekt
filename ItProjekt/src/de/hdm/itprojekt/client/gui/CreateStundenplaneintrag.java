package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.client.ItProjekt;


	/**
	 * Hier wird ein neuer Stundenplaneintrag angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

public class CreateStundenplaneintrag extends Content {
		
		private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();
		private HorizontalPanel horPanel = new HorizontalPanel ();
		private HorizontalPanel hrPanel = new HorizontalPanel ();
		private HorizontalPanel horiPanel = new HorizontalPanel ();
		private HorizontalPanel hriPanel = new HorizontalPanel ();
		
		  /**
		   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.		   */
		
		protected String getHeadlineText() {
		    return "Stundenplaneintrag anlegen";
		  }

		  /**
		   * Unter der Überschrift trägt der User die Daten des neuen Stundenplaneintrags ein. 
		   */
		  final Label lbdozent = new Label ("Dozent"); 
		  final Label lbzeitslot = new Label ("Zeitslot");
		  final Label lbraum = new Label ("Raum");
		  final Label lbstudiengang = new Label ("Studiengang");
		  final Label lbsemesterverband = new Label ("Semesterverband");
		  final Label lblehrveranstaltung = new Label ("Lehrveranstaltung");
		  final ListBox tbdozent = new ListBox (false);
		  final ListBox tbzeitslot = new ListBox (false);
		  final ListBox tbraum = new ListBox (false);
		  final ListBox tbstudiengang = new ListBox(false); 
		  final ListBox tbsemesterverband = new ListBox (false); 
		  final ListBox tblehrveranstaltung = new ListBox (false);
		  final Button speichern = new Button ("speichern");
		  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);

		  
		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {

				  hPanel.add(lbdozent);
				  hPanel.add(tbdozent);
				  hoPanel.add(lbzeitslot);
				  hoPanel.add(tbzeitslot);
				  horPanel.add(lbraum);
				  horPanel.add(tbraum);
				  hrPanel.add(lbstudiengang);
				  hrPanel.add(tbstudiengang);
				  horiPanel.add(lbsemesterverband);
				  horiPanel.add(tbsemesterverband);
				  hriPanel.add(lblehrveranstaltung);
				  hriPanel.add(tblehrveranstaltung);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
				  vPanel.add(horPanel);
				  vPanel.add(hrPanel);
				  vPanel.add(horiPanel);
				  vPanel.add(hriPanel);
				  vPanel.add(speichern);
				  
				  RootPanel.get("detailsPanel").add(vPanel); 
				  
				  verwaltungsSvc.getAllDozent(new AsyncCallback<ArrayList<Dozent>>()){
					  
				  }
				  
				  
				  
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  addStundenplaneintrag();
					  }
					  
					  public void addStundenplaneintrag(){					  
						  final String [] dozent;
						  dozent = tbdozent.getItemText(tbdozent.getSelectedIndex()).split("");
						  final String vorname = dozent [1];
						  final String nachname = dozent [2];
						  final String [] raum;
						  raum = tbraum.getItemText(tbraum.getSelectedIndex()).split("");
						  final String [] lehrveranstaltung;
						  lehrveranstaltung = tblehrveranstaltung.getItemText(tblehrveranstaltung.getSelectedIndex()).split("");
						  final String [] semesterverband;
						  semesterverband = tbsemesterverband.getItemText(tbsemesterverband.getSelectedIndex()).split("");
						  final String [] studiengang;
						  studiengang = tbstudiengang.getItemText(tbstudiengang.getSelectedIndex()).split("");
						  final String [] zeitslot;
						  zeitslot = tbzeitslot.getItemText(tbzeitslot.getSelectedIndex()).split("");
						  
						  Stundenplaneintrag stdpe = new Stundenplaneintrag();
						  
						  verwaltungsSvc.getStundenplaneintrag(stdpe, new AsyncCallback<Stundenplaneintrag>() {
								 @Override
								  public void onFailure (Throwable caught) {
								  }

								  @Override
								  public void onSuccess(Stundenplaneintrag result) {
									  stdpe.setDozent(dozent[1] + " " + dozent[2]);
									  stdpe.setLehrveranstaltungs(lehrveranstaltung[1]);
									  stdpe.setRaum(raum[1]);
									  stdpe.setSemesterverband(semesterverband[1]);
									  stdpe.setStudiengang(studiengang[1]);
									  stdpe.setZeitslot(zeitslot[1]);
									  Window.alert("Erfolgreich gespeichert");
								  }
							  });
					  		}
						 });
				  
		  }
}


