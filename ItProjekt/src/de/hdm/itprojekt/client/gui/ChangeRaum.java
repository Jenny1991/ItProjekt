package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Raum;

public class ChangeRaum extends Content {
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
	  */
	private final HTML ueberschrift = new HTML ("<h2>Raum bearbeiten<h2>");

	  /**
	   * Unter der �berschrift tragt der User die neuen Daten des  Raums ein. 
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
		  
		  getSelectedData();

		  speichern.addClickHandler(new ClickHandler(){
			  public void onClick(ClickEvent event) {			
				  updateRaum();
				  }
				  
				  public void updateRaum(){
					  boolean allFilled = true;
					  
					  if (tbbezeichnung.getText().isEmpty());
					  if (tbkapazitaet.getText().isEmpty());
					  {	allFilled = false;
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }
					  
					  if (allFilled == true) { 
						  Raum r = new Raum();
						  r.setBezeichnung(tbbezeichnung.getText().trim());
						  r.setKapazitaet(tbkapazitaet.getVisibleLength());
						  tbbezeichnung.setFocus(true);
						  tbkapazitaet.setFocus(true);	  

						  verwaltungsSvc.changeRaum(r, new  AsyncCallback<Raum>() {

							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Raum konnte nicht angelegt werden.");
							  }

							  @Override
							  public void onSuccess(Raum result) {
								  
								  tbbezeichnung.setText("");
								  tbkapazitaet.setVisibleLength(result.getKapazitaet());
								  Window.alert ("Erfolgreich gespeichert.");
							  } 	
							});
					  }
				  }
			  });
		  }
		  
		  public void getSelectedData(){
		/**	  verwaltungsSvc.getRaum(new AsyncCallback<Raum>() {

				  @Override
				  public void onFailure (Throwable caught) {
				  }

				  @Override
				  public void onSuccess(Raum result) {
					  if (result != null);
					  tbbezeichnung.setText(result.getBezeichnung().trim());
					  tbkapazitaet.setVisibleLength(result.getKapazitaet());
					}
		  		}); */
		  	}
	}

