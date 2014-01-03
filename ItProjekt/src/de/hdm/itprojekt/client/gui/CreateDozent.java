package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Dozent;
import de.hdm.itprojekt.client.ItProjekt;
import de.hdm.itprojekt.client.gui.DozentForm;

/**
 * Hier wird ein neuer Dozent angelegt.
 * 
 * @author Thies, Espich
 * 
 */

public class CreateDozent extends Content {
	
	
	
	/**
    * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann. 
    */
	private final HTML ueberschrift = new HTML ("<h2>Neuen Dozenten anlegen<h2>");
	
	Dozent d;
	// private ArrayList<Dozent> dozent = new ArrayList<Dozent> ();

	  /**
	   * Unter der �berschrift tr�gt der User die Daten des neuen Dozenten ein. 
	   */
	  final Label lbvorname = new Label ("Vorname"); 
	  final Label lbnachname = new Label ("Nachname");
	  final TextBox tbvorname = new TextBox ();
	  final TextBox tbnachname = new TextBox ();
	  final Button speichern = new Button ("speichern"); 
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);

	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {

		  this.add(ueberschrift);
		  this.add(lbnachname);
		  this.add(tbnachname);
		  this.add(lbvorname);
		  this.add(tbvorname);
		  this.add(speichern);
		  
			  
				  speichern.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  addDozent();
				  }
				  
				  private void addDozent () {
					  Dozent d = new Dozent();
					  boolean allFilled = true;
				  
					  if (tbnachname.getText().isEmpty());
					  if (tbvorname.getText().isEmpty()); {	
						  allFilled = false;
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }
					  
					  if (allFilled == true) {	
						  d.setNachname(tbnachname.getText().trim());
						  d.setVorname(tbvorname.getText().trim());
						  String vorname = tbvorname.getText().trim();
						  String nachname = tbnachname.getText().trim();
						  tbnachname.setFocus(true);
						  tbvorname.setFocus(true);
						  
						 /* if (dozent.contains(vorname))
							  return;
						  if (dozent.contains(nachname))
							  return;
						  
						  if (verwaltungsSvc == null) {
							 // verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
						  } */
					
						 verwaltungsSvc.createDozent(vorname, nachname, new AsyncCallback<Dozent>() {

							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Dozent konnte nicht angelegt werden.");
							  }

							  @Override
							  public void onSuccess(Dozent result) {
								  tbnachname.setText("");
								  tbvorname.setText("");
								  Window.alert ("Erfolgreich gespeichert.");
							  } 	
							});
							//verwaltungsSvc.createDozent(dozent.toArray(new String [0]), callback);
					  }
				  }
				  });
	  }
}  
			  	
		


