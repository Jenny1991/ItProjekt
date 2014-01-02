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
import de.hdm.itprojekt.shared.bo.Raum;
import de.hdm.itprojekt.client.ItProjekt;
import de.hdm.itprojekt.client.gui.RaumForm;


	/**
	 * Hier wird ein neuer Raum angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateRaum extends Content {
		
		private final HTML ueberschrift = new HTML ("<h2>Neuen Raum anlegen<h2>");
		
		
		/*private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();*/
		private ArrayList<Raum> raum = new ArrayList<Raum> ();
		
		  /**
		   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.		   */
		  
		/*protected String getHeadlineText() {
		    return "Raum anlegen";
		  }*/

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
				  //vPanel.add(hPanel);
				  //vPanel.add(hoPanel);
				  this.add(speichern);
				  
				 // RootPanel.get("detailsPanel").add(vPanel); 
					  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  addRaum();
						  }
					  
					  public void addRaum(){
						  boolean allFilled = true;
						  
						  if (tbbezeichnung.getText().isEmpty());
						  if (tbkapazitaet.getText().isEmpty());
						  {	allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
							  final String bezeichnung = tbbezeichnung.getText().trim();
							  final int kapazitaet = tbkapazitaet.getVisibleLength();
							  tbbezeichnung.setFocus(true);
							  tbkapazitaet.setFocus(true);	  
							  
							  if (raum.contains(bezeichnung))
								  return;
							  if (raum.contains(kapazitaet))
								  return;
							  
							  if (verwaltungsSvc == null) {
								 // verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
							  }
						
							  AsyncCallback<Raum> callback = new  AsyncCallback<Raum> () {

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
								};
								//verwaltungsSvc.createRaum(raum.toArray(new String [0]), callback);
						  }
					  }
					  });
		  }
	}  