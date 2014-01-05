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
import de.hdm.itprojekt.shared.bo.Dozent;

	/**
	 * Hier wird ein bereits angelegter Dozent bearbeitet.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class ChangeDozent extends Content {

		  /**
		   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   */
		private final HTML ueberschrift = new HTML ("<h2>Dozent bearbeiten<h2>");

		  /**
		   * Unter der �berschrift tr�gt der User die neuen Daten des  Dozenten ein. 
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
				 
				 getSelectedData();
			
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  updateDozent();
					  }
					  
					  private void updateDozent () {	
						  boolean allFilled = true;
					  
						  if (tbnachname.getText().isEmpty());
						  if (tbvorname.getText().isEmpty()); {	
							  allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) {
							  Dozent d = new Dozent ();
							  d.setNachname(tbnachname.getText().trim());
							  d.setVorname(tbvorname.getText().trim());
							  tbnachname.setFocus(true);
							  tbvorname.setFocus(true);
							  						  
							  verwaltungsSvc.changeDozent(d, new  AsyncCallback<Dozent> () {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Der Dozent konnte nicht bearbeitet werden.");
								  }

								  @Override
								  public void onSuccess(Dozent result) {
									  tbnachname.setText("");
									  tbvorname.setText("");
									  Window.alert ("Erfolgreich gespeichert.");
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
