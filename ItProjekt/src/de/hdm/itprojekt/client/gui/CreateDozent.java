package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

public class CreateDozent extends ItProjekt {
	
	private VerticalPanel vPanel = new VerticalPanel ();
	private HorizontalPanel hPanel = new HorizontalPanel ();
	private HorizontalPanel hoPanel = new HorizontalPanel ();
	
	  /**
	   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
	   * Diese ist durch die Methode @see ItProjekt#getHeadlineText() zu erstellen ist.
	   */
	
	  @Override
	  protected String getHeadlineText() {
	    return "Dozent anlegen";
	  }

	  /**
	   * Unter der Überschrift trägt der User die Daten des neuen Dozenten ein. 
	   */
	  final Label lbvorname = new Label ("Vorname"); 
	  final Label lbnachname = new Label ("Nachname");
	  final TextBox tbvorname = new TextBox ();
	  final TextBox tbnachname = new TextBox ();
	  final Button speichern = new Button ("speichern");
	  Dozent d;	  
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);

	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {

			  hPanel.add(lbnachname);
			  hPanel.add(tbnachname);
			  hoPanel.add(lbvorname);
			  hoPanel.add(tbvorname);
			  vPanel.add(hPanel);
			  vPanel.add(hoPanel);
			  vPanel.add(speichern);
			  
			  RootPanel.get("detailsPanel").add(vPanel); 
			  
				  speichern.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  boolean allFilled = true;
					  if (tbnachname.getText().isEmpty());
					  if (tbvorname.getText().isEmpty());
					  {	allFilled = false;
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }
					  
					  if (allFilled == true) { 
					  d = new Dozent();
					  d.setNachname(tbnachname.getText().trim());
					  d.setVorname(tbvorname.getText().trim());
					  tbnachname.setFocus(true);
					  tbvorname.setFocus(true);
					  
					  /*Kapier ich nicht ganz --> Nachschauen:Callabck und AsyncCallback
					   * Lui und Domi müssen noch addDozent im Async anlegen
					   */
					  verwaltungsSvc.addDozent (d, new AsyncCallback<Dozent>() {
					  
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
					});
				  }
			  	}
			  });
	  }
	  
	  
	  /*
	   * Wir nutzen eine Nested Class zum Aufruf des Service, um den neuen Dozenten zu erhalten
	  */
	  
	  private void refreshDozentForm () {
		 
		  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	
		  AsyncCallback<Dozent> callback = new  AsyncCallback<Dozent> () {

			  @Override
			  public void onFailure(Throwable caught) {
			  }

			  @Override
			  public void onSuccess(Dozent result) {
				  updateFlexTable(result);
				  /*Methode muss von Verena gemacht werden --> aktualisiert die Tabellen der FlexTable*/
			  }		
			};
			verwaltungsSvc.createDozent(d.getVorname(), d.getNachname(), callback);
	  }
	  
}
		


