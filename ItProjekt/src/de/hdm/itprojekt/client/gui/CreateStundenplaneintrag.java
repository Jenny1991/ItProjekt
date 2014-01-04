package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
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

		/**
		 * Jede Klasse enthï¿½t eine ï¿½berschrift, die definiert, was der User machen kann.
		 */
		private final HTML ueberschrift = new HTML ("<h2>Neuen Stundenplaneintrag anlegen<h2>");

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

				  this.add(ueberschrift);
			  	  this.add(lbdozent);
				  this.add(tbdozent);
				  this.add(lbzeitslot);
				  this.add(tbzeitslot);
				  this.add(lbraum);
				  this.add(tbraum);
				  this.add(lbstudiengang);
				  this.add(tbstudiengang);
				  this.add(lbsemesterverband);
				  this.add(tbsemesterverband);
				  this.add(lblehrveranstaltung);
				  this.add(tblehrveranstaltung);
				  this.add(speichern);
				  				  
				  verwaltungsSvc.getAllDozenten(new AsyncCallback<Vector<Dozent>>(){
					  @Override
					  public void onFailure (Throwable caught) {
					  }
					  @Override
					  public void onSuccess(Vector<Dozent> result){
						  int i = 0; 
						  while (!result.isEmpty()) {
							  tbdozent.addItem(result.get(i).getNachname() + "/" + result.get(i).getVorname());
							  i++;
						  }
					  }
				  });
				  
				  
				  
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  addStundenplaneintrag();
					  }
					  
					  public void addStundenplaneintrag(){					  
						  final String [] d;
						  tbdozent.getItemText(tbdozent.getSelectedIndex());
						  final String [] r;
						  r= tbraum.getItemText(tbraum.getSelectedIndex()).split("");
						  final String [] l;
						  l = tblehrveranstaltung.getItemText(tblehrveranstaltung.getSelectedIndex()).split("");
						  final String [] sv;
						  sv = tbsemesterverband.getItemText(tbsemesterverband.getSelectedIndex()).split("");
						  final String [] s;
						  s = tbstudiengang.getItemText(tbstudiengang.getSelectedIndex()).split("");
						  final String [] z;
						  z = tbzeitslot.getItemText(tbzeitslot.getSelectedIndex()).split("");
						  
						  Stundenplaneintrag stdpe = new Stundenplaneintrag();
						  
						  verwaltungsSvc.createStundenplaneintrag(d, l, r, z, sv, new AsyncCallback<Stundenplaneintrag>() {
								 @Override
								  public void onFailure (Throwable caught) {
								  }

								  @Override
								  public void onSuccess(Stundenplaneintrag result) {
									  Window.alert("Erfolgreich gespeichert");
								  }
							  });
					  		}
						 });
				  
		  }
}


