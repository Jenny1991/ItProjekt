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
import de.hdm.itprojekt.shared.bo.Dozent;

/**
 * Hier wird ein bereits angelegter Dozent gelöscht.
 * 
 * @author Thies, Espich
 * 
 */

public class DeleteDozent extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Dozent löschen<h2>");

	  /**
	   * Unter der ï¿½berschrift trï¿½gt der User die neuen Daten des  Dozenten ein. 
	   */
	  final Label lbvorname = new Label ("Vorname"); 
	  final Label lbnachname = new Label ("Nachname");
	  final TextBox tbvorname = new TextBox ();
	  final TextBox tbnachname = new TextBox ();
	  final Button loeschen = new Button ("löschen"); 
	  
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	 Dozent shownDozent = null;
		   
	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {
		  
		  this.add(ueberschrift);
		  this.add(lbnachname);
		  this.add(tbnachname);
		  this.add(lbvorname);
		  this.add(tbvorname);
		  this.add(loeschen);

		  getSelectedData();

		  loeschen.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
				 
					 if (shownDozent != null) {
						  verwaltungsSvc.deleteDozent(shownDozent, new AsyncCallback<Void>() {
							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Dozent konnte nicht gelöscht werden." +
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
			  verwaltungsSvc.getDozent(new AsyncCallback<Dozent>() {

				  @Override
				  public void onFailure (Throwable caught) {
				  }

				  @Override
				  public void onSuccess(Dozent result) {
					  if (result != null);
					  tbnachname.setText(result.getNachname().trim());
					  tbvorname.setText(result.getVorname().trim());
					}
		  		});
		  	}
}
