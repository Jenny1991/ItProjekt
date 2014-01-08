package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Studiengang;


/**
 * Hier wird ein neuer Studiengang angelegt.
 * 
 * @author Thies, Espich
 * 
 */

public class CreateStudiengang extends Content {
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   */
	private final HTML ueberschrift = new HTML ("<h2>Neuen Studiengang anlegen<h2>");

	  /**
	   * Unter der �berschrift tr�gt der User die Daten des neuen Studiengangs ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final TextBox tbbezeichnung = new TextBox ();
	  final Button speichern = new Button ("speichern");
	  
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  
	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {

		  this.add(ueberschrift);
		  this.add(lbbezeichnung);
		  this.add(tbbezeichnung);
		  this.add(speichern);
		  	
		  speichern.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  addStudiengang();
			  }
			  
			  public void addStudiengang() {
		  
				  	if (!tbbezeichnung.getValue().isEmpty()) {
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }

				  	else {
				  		final String bezeichnung = tbbezeichnung.getValue().trim();
				  		tbbezeichnung.setFocus(true);
				  
					  verwaltungsSvc.createStudiengang(bezeichnung, new AsyncCallback<Studiengang>() {

						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht angelegt werden.");
						  }

						  @Override
						  public void onSuccess(Studiengang result) {
							  tbbezeichnung.setValue("");
							  Window.alert ("Erfolgreich gespeichert.");
						  } 	
						});
				  }
			  }
		  });
	  }
}