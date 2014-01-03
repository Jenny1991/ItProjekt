package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

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

	Dozent d;
	Dozent shownDozent = null;
	private ArrayList<Dozent> dozent = new ArrayList<Dozent> ();
	
	  /**
	   * Unter der ï¿½berschrift trï¿½gt der User die neuen Daten des  Dozenten ein. 
	   */
	  final Label lbvorname = new Label ("Vorname"); 
	  final Label lbnachname = new Label ("Nachname");
	  final TextBox tbvorname = new TextBox ();
	  final TextBox tbnachname = new TextBox ();
	  final Button loeschen = new Button ("löschen"); 
	  final Button speichern = new Button ("speichern");
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
		   
	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {
		  showWidget();
		  getSelectedData();

		  loeschen.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  deleteDozent();
				  } 
			  
				 private void deleteDozent () {
					 boolean allFilled = true;
				  
					  if (allFilled == true) {	
						  emptyWidget(); }
					  if (dozent.size() != 0) {
						  verwaltungsSvc.deleteDozent(d, new AsyncCallback<Void>() {
							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Dozent konnte nicht gelöscht werden.");
							  }

							  @Override
							  public void onSuccess(Void result) {
								  Window.alert ("Erfolgreich gelöscht.");
								  emptyWidget(); 	
							  } 	
							});
					  }
				  }
				  });	  
			  }

		  public void getSelectedData(){
			  verwaltungsSvc.getDozent(new AsyncCallback<Dozent>() {

				  @Override
				  public void onFailure (Throwable caught) {
					  Window.alert("Der Dozent konnte nicht gelöscht werden.");
				  }

				  @Override
				  public void onSuccess(Dozent result) {
					  if (result != null);
					  tbnachname.setText(result.getNachname().trim());
					  tbvorname.setText(result.getVorname().trim());
					}
		  		});
		  	}
		  
public void emptyWidget(){
this.emptyWidget();
}

public void showWidget() {
this.add(lbnachname);
this.add(tbnachname);
this.add(lbvorname);
this.add(tbvorname);
this.add(loeschen);
}
}
