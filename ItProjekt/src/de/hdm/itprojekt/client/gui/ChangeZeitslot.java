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
import de.hdm.itprojekt.shared.bo.Lehrveranstaltung;
import de.hdm.itprojekt.shared.bo.Zeitslot;

public class ChangeZeitslot extends Content {
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
	   */
	private final HTML ueberschrift = new HTML ("<h2>Zeitslot bearbeiten<h2>");

	  /**
	   * Unter der �berschrift tr�gt der User die neuen Daten des Zeitslots ein. 
	   */
	  final Label lbwochentag = new Label ("Wochentag"); 
	  final Label lbanfangszeit = new Label ("Anfangszeit");
	  final Label lbendzeit = new Label ("Endzeit");
	  final TextBox tbwochentag = new TextBox ();
	  final TextBox tbanfangszeit = new TextBox ();
	  final TextBox tbendzeit = new TextBox ();
	  final Button speichern = new Button ("speichern");

	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);

	  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {
			  
			  this.add(ueberschrift);
			  
			  this.add(lbwochentag);
			  this.add(tbwochentag);
			  this.add(lbanfangszeit);
			  this.add(tbanfangszeit);
			  this.add(lbendzeit);
			  this.add(tbendzeit);
			  this.add(speichern);
			  
			  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  updateZeitslot();
					  }
					  
					  public void updateZeitslot(){
						  boolean allFilled = true;
						  
						  if (tbwochentag.getText().isEmpty());
						  if (tbanfangszeit.getText().isEmpty());
						  if (tbendzeit.getText().isEmpty());
						  {	allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
							  Zeitslot z = new Zeitslot();
							  z.setWochentag(tbwochentag.getText().trim());
							  z.setAnfangszeit(tbanfangszeit.getVisibleLength());
							  z.setEndzeit(tbendzeit.getVisibleLength());
							  tbwochentag.setFocus(true);
							  tbanfangszeit.setFocus(true);
							  tbendzeit.setFocus(true);
						
							  verwaltungsSvc.changeZeitslot(z, new  AsyncCallback<Zeitslot>() {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Das Zeitslot konnte nicht bearbeitet werden.");
								  }

								  @Override
								  public void onSuccess(Zeitslot result) {
									  tbwochentag.setText("");
									  tbanfangszeit.setVisibleLength(result.getAnfangszeit());
									  tbendzeit.setVisibleLength(result.getEndzeit());
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
								});
						  }
					  }
				  });
			  }
			  
			  public void getSelectedData(){
				  verwaltungsSvc.getZeitslot(new AsyncCallback<Zeitslot>() {

					  @Override
					  public void onFailure (Throwable caught) {
					  }

					  @Override
					  public void onSuccess(Zeitslot result) {
						  if (result != null);
						  tbwochentag.setText(result.getWochentag().trim());
						  tbanfangszeit.setVisibleLength(result.getAnfangszeit());
						  tbendzeit.setVisibleLength(result.getEndzeit());
						}
			  		});
			  	}
		}