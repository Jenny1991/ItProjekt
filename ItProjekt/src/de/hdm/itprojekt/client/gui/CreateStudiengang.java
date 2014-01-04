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
import de.hdm.itprojekt.shared.bo.Studiengang;
import de.hdm.itprojekt.client.ItProjekt;
import de.hdm.itprojekt.client.gui.StudiengangForm;


/**
 * Hier wird ein neuer Studiengang angelegt.
 * 
 * @author Thies, Espich
 * 
 */

public class CreateStudiengang extends Content {
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.	   
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
					  boolean allFilled = true;
					  
					  if (tbbezeichnung.getText().isEmpty());
					  {	allFilled = false;
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }
					  
					  if (allFilled == true) { 
						  final String bezeichnung = tbbezeichnung.getValue().trim();
						  tbbezeichnung.setFocus(true);
					
						  verwaltungsSvc.createStudiengang(bezeichnung, new AsyncCallback<Studiengang>() {

							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Studiengang konnte nicht angelegt werden.");
							  }

							  @Override
							  public void onSuccess(Studiengang result) {
								  tbbezeichnung.setValue(bezeichnung);
								  Window.alert ("Erfolgreich gespeichert.");
							  } 	
							});
					  }
				  }
				  });
	  }
}  
