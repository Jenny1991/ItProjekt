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

/**
 * Hier wird ein bereits angelegter Studiengang gelöscht.
 * 
 * @author Thies, Espich
 * 
 */

public class DeleteStudiengang extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Studiengang löschen<h2>");
	
	/**
	   * Unter der ï¿½berschrift trï¿½gt der User die neuen Daten des Studiengangs ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final TextBox tbbezeichnung = new TextBox ();
	  final Button loeschen = new Button ("löschen");

	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  Studiengang sg = new Studiengang();
	  
	  public void onLoad () {
		  
		  this.add(ueberschrift);		  
		  this.add(lbbezeichnung);
		  this.add(tbbezeichnung);
		  this.add(loeschen);
		  
		  getSelectedData();
		  
		  loeschen.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  deleteStudiengang();
			  } 
		  
			 private void deleteStudiengang () {
				 /** ich brauche von Lui und Domi eine Methode die ich aufrufen kann, um zu sehen, ob der Studiengang noch in 
				  * Stundenplaneinträgen eingetragen ist
				  */				 
				 
				 if (sg != null) {
					  verwaltungsSvc.deleteStudiengang(sg, new AsyncCallback<Void>() {
						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht gelöscht werden." +
							  		"Er ist in ein oder mehreren Stundenplaneinträgen eingetragen");
						  }

						  @Override
						  public void onSuccess(Void result) {
							  Window.alert ("Erfolgreich gelöscht.");
						  } 	
						});
				  }
			  }
			  });
	  		this.clear();
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

