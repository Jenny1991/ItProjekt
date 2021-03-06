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

import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Studiengang;

public class ChangeStudiengang extends Content {
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
	   */
	private final HTML ueberschrift = new HTML ("<h2>Studiengang bearbeiten<h2>");

	  /**
	   * Unter der �berschrift tr�gt der User die neuen Daten des Studiengangs ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final TextBox tbbezeichnung = new TextBox ();
	  final Button speichern = new Button ("speichern");

	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);	  		
	  
	  public void onLoad () {
		  
		  this.add(ueberschrift);
		  this.add(lbbezeichnung);
		  this.add(tbbezeichnung);
		  this.add(speichern);

		  getSelectedData();
		  
		  speichern.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
	
				  boolean allFilled = true;
			  
				  if (tbbezeichnung.getValue().isEmpty()) {
					  allFilled = false;
				  Window.alert ("Bitte füllen Sie alle Felder aus."); }
				  
				  if (allFilled == true) {
					  Studiengang s = new Studiengang ();
					  s.setBezeichnung(tbbezeichnung.getValue().trim());
					  tbbezeichnung.setFocus(true);
				  
					  verwaltungsSvc.changeStudiengang(s, new AsyncCallback<Studiengang> () {

						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht bearbeitet werden.");
						  }

						  @Override
						  public void onSuccess(Studiengang result) {
							  tbbezeichnung.setText("");
							  Window.alert ("Erfolgreich gespeichert.");
						  } 	
						});
				  }
			  }
			  });
	  }

		  public void getSelectedData(){
			  verwaltungsSvc.getStudiengang(new AsyncCallback<Studiengang>() {

				  @Override
				  public void onFailure (Throwable caught) {
				  }

				  @Override
				  public void onSuccess(Studiengang result) {
					  if (result != null);
					  tbbezeichnung.setText(result.getBezeichnung().trim());
					}
		  		});
		  	}
}
	  
	  
