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

public class CreateDozent2 extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Neuen Dozenten anlegen<h2>");
	
	/** Brauchen wir nicht
	 * private VerticalPanel vPanel = new VerticalPanel ();
	private HorizontalPanel hPanel = new HorizontalPanel ();
	private HorizontalPanel hoPanel = new HorizontalPanel ();*/
	private ArrayList<Dozent> dozent = new ArrayList<Dozent> ();
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.	   */
	
	  /*protected String getHeadlineText() {
	    return "Dozent anlegen";
	  }*/

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
		  
		  /** Panels werden durch this. ersetzt
		   * hPanel.add(lbnachname);
			  hPanel.add(tbnachname);
			  hoPanel.add(lbvorname);
			  hoPanel.add(tbvorname);
			  vPanel.add(hPanel);
			  vPanel.add(hoPanel);
			  vPanel.add(speichern);
			  
			  RootPanel.get("detailsPanel").add(vPanel); 
			  */
		  this.add(lbnachname);
		  this.add(tbnachname);
		  this.add(lbvorname);
		  this.add(tbvorname);
		 // vPanel.add(hPanel);
		 // vPanel.add(hoPanel);
		  this.add(speichern);
		  
			  
				  speichern.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  addDozent();
				  }
				  
				  private void addDozent () {	
					  boolean allFilled = true;
				  
					  if (tbnachname.getText().isEmpty());
					  if (tbvorname.getText().isEmpty()); {	
						  allFilled = false;
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }
					  
					  if (allFilled == true) {
						  final String nachname = tbnachname.getText().trim();
						  final String vorname = tbvorname.getText().trim();
						  tbnachname.setFocus(true);
						  tbvorname.setFocus(true);
						  
						  if (dozent.contains(vorname))
							  return;
						  if (dozent.contains(nachname))
							  return;
						  
						  if (verwaltungsSvc == null) {
							 // verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
						  }
					
						  AsyncCallback<Dozent> callback = new  AsyncCallback<Dozent> () {

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
							};
							//verwaltungsSvc.createDozent(dozent.toArray(new String [0]), callback);
					  }
				  }
				  });
	  }
}  
			  	
		

