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
import de.hdm.itprojekt.shared.bo.Raum;


	/**
	 * Hier wird ein neuer Raum angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateRaum extends Content {
		
		  /**
		   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   */
		private final HTML ueberschrift = new HTML ("<h2>Neuen Raum anlegen<h2>");

		  /**
		   * Unter der �berschrift tragt der User die Daten des neuen Raums ein. 
		   */
		  final Label lbbezeichnung = new Label ("Bezeichnung"); 
		  final Label lbkapazitaet = new Label ("Kapazität");
		  final TextBox tbbezeichnung = new TextBox ();
		  final TextBox tbkapazitaet = new TextBox ();
		  final Button speichern = new Button ("speichern");
		  
		  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);

		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {
			  
			  this.add(ueberschrift);
			  
				  this.add(lbbezeichnung);
				  this.add(tbbezeichnung);
				  this.add(lbkapazitaet);
				  this.add(tbkapazitaet);
				  this.add(speichern);
					  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  addRaum();
						  }
					  
					  public void addRaum(){
						  boolean allFilled = true;
						  
						  if (tbbezeichnung.getText().isEmpty() || tbkapazitaet.getText().isEmpty())
						  {	allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
							  final String bezeichnung = tbbezeichnung.getText().trim();
							  final int kapazitaet = tbkapazitaet.getVisibleLength();
							  tbbezeichnung.setFocus(true);
							  tbkapazitaet.setFocus(true);	  

							  verwaltungsSvc.createRaum(bezeichnung, kapazitaet, new AsyncCallback<Raum>() {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Der Raum konnte nicht angelegt werden.");
								  }

								  @Override
								  public void onSuccess(Raum result) {
									  tbbezeichnung.setText("");
									  tbkapazitaet.setVisibleLength(kapazitaet);
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
								});
						  }
					  }
					  });
		  }
	}  