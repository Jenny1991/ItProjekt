package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.Date;

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
import de.hdm.itprojekt.shared.bo.Zeitslot;
import de.hdm.itprojekt.client.ItProjekt;
import de.hdm.itprojekt.client.gui.ZeitslotForm;;


	/**
	 * Hier wird ein neuer Zeitslot angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateZeitslot extends ItProjekt {
		
		private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();
		private HorizontalPanel horPanel = new HorizontalPanel ();
		private ArrayList<Zeitslot> zeitslot = new ArrayList<Zeitslot> ();
		
		  /**
		   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode @see BasisKlasse#getHeadlineText() zu erstellen ist.
		   */
		  @Override
		  protected String getHeadlineText() {
		    return "Zeitslot anlegen";
		  }

		  /**
		   * Unter der Überschrift trägt der User die Daten des neuen Zeitslots ein. 
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

				  hPanel.add(lbwochentag);
				  hPanel.add(tbwochentag);
				  hoPanel.add(lbanfangszeit);
				  hoPanel.add(tbanfangszeit);
				  horPanel.add(lbendzeit);
				  horPanel.add(tbendzeit);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
				  vPanel.add(horPanel);
				  vPanel.add(speichern);
				  
				  RootPanel.get("detailsPanel").add(vPanel); 
				  
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
							  
							  if (zeitslot.contains(wochentag))
								  return;
							  if (zeitslot.contains(anfangszeit))
								  return;
							  if (zeitslot.contains(endzeit))
								  return;
							  
							  if (verwaltungsSvc == null) {
								  verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
							  }
						
							  AsyncCallback<Zeitslot> callback = new  AsyncCallback<Zeitslot> () {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Das Zeitslot konnte nicht angelegt werden.");
								  }

								  @Override
								  public void onSuccess(Zeitslot result) {
									  
									  tbwochentag.setText("");
									  tbanfangszeit.setVisibleLength(anfangszeit);
									  tbendzeit.setVisibleLength(endzeit);
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
								};
								verwaltungsSvc.addZeitslot(zeitslot.toArray(new String [0]), callback);
						  }
					  }
					  });
		  }
	} 