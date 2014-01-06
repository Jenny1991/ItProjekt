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
import de.hdm.itprojekt.shared.bo.Zeitslot;


	/**
	 * Hier wird ein neuer Zeitslot angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateZeitslot extends Content {
		  
		/**
		   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   */
		private final HTML ueberschrift = new HTML ("<h2>Neuen Zeitslot anlegen<h2>");

		  /**
		   * Unter der �berschrift tr�gt der User die Daten des neuen Zeitslots ein. 
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
						  addZeitslot();
					  }
					  
					  public void addZeitslot(){
						  boolean allFilled = true;
						  
						  if (tbwochentag.getText().isEmpty());
						  if (tbanfangszeit.getText().isEmpty());
						  if (tbendzeit.getText().isEmpty());
						  {	allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
							  final String wochentag = tbwochentag.getText().trim();
							  final double anfangszeit = tbanfangszeit.getVisibleLength();
							  final double endzeit = tbendzeit.getVisibleLength();
							  tbwochentag.setFocus(true);
							  tbanfangszeit.setFocus(true);
							  tbendzeit.setFocus(true);
	
							  verwaltungsSvc.createZeitslot(wochentag, anfangszeit, endzeit, new AsyncCallback<Zeitslot>() {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Das Zeitslot konnte nicht angelegt werden.");
								  }

								  @Override
								  public void onSuccess(Zeitslot result) {
									  
									  tbwochentag.setText("");
							//		  tbanfangszeit.setVisibleLength(anfangszeit);
								//	  tbendzeit.setVisibleLength(endzeit);
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
								});
						  }
					  }
					  });
		  }
	} 