package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Dozent;
import de.hdm.itprojekt.shared.bo.Raum;

public class ChangeRaum extends Content {
	
	private VerticalPanel vPanel = new VerticalPanel ();
	private HorizontalPanel hPanel = new HorizontalPanel ();
	private HorizontalPanel hoPanel = new HorizontalPanel ();
	
	Raum shownRaum = null;
	private ArrayList<Raum> raum = new ArrayList<Raum> ();
	
	  /**
	   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
	   * Diese ist durch die Methode #getHeadlineText() zu erstellen.		   */
	  
	protected String getHeadlineText() {
	    return "Raum bearbeiten";
	  }

	  /**
	   * Unter der Überschrift tragt der User die neuen Daten des  Raums ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final Label lbkapazität = new Label ("Kapazität");
	  final TextBox tbbezeichnung = new TextBox ();
	  final TextBox tbkapazität = new TextBox ();
	  final Button speichern = new Button ("speichern");
	  final Button bearbeiten = new Button ("bearbeiten");
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);

	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {

		  bearbeiten.addClickHandler(new ClickHandler(){
			  public void onClick(ClickEvent event) {			
					if (shownRaum !=null){
						shownRaum.setBezeichnung(tbbezeichnung.getText());
						shownRaum.setKapazitaet(tbkapazität.getVisibleLength());
						verwaltungsSvc.getRaum(shownRaum, new AsyncCallback<Raum>() {
								 @Override
								  public void onFailure (Throwable caught) {
								  }

								  @Override
								  public void onSuccess(Raum result) {
									  tbbezeichnung.setText(result.getBezeichnung());
									  tbkapazität.setVisibleLength(result.getKapazitaet());
									  
									  emptyWidget();
									  changeSelectedRaum();											  
								  }
							  });
					  }
			  }  
			  
		  public void changeSelectedRaum(){	 
			  hPanel.add(lbbezeichnung);
			  hPanel.add(tbbezeichnung);
			  hoPanel.add(lbkapazität);
			  hoPanel.add(tbkapazität);
			  vPanel.add(hPanel);
			  vPanel.add(hoPanel);
			  vPanel.add(speichern);
			  
			  RootPanel.get("detailsPanel").add(vPanel); 
				  
			  speichern.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  updateRaum();
					  }
				  
				  public void updateRaum(){
					  boolean allFilled = true;
					  
					  if (tbbezeichnung.getText().isEmpty());
					  if (tbkapazität.getText().isEmpty());
					  {	allFilled = false;
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }
					  
					  if (allFilled == true) { 
						  final String bezeichnung = tbbezeichnung.getText().trim();
						  final int kapazitaet = tbkapazität.getVisibleLength();
						  tbbezeichnung.setFocus(true);
						  tbkapazität.setFocus(true);	  
						  
						  if (raum.contains(bezeichnung))
							  return;
						  if (raum.contains(kapazitaet))
							  return;
						  
						  if (verwaltungsSvc == null) {
							  verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
						  }
					
						  AsyncCallback<Void> callback = new  AsyncCallback<Void> () {

							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Raum konnte nicht angelegt werden.");
							  }

							  @Override
							  public void onSuccess(Void result) {
								  
								  tbbezeichnung.setText("");
								  tbkapazität.setVisibleLength(kapazitaet);
								  Window.alert ("Erfolgreich gespeichert.");
							  } 	
							};
							verwaltungsSvc.changeRaum(raum.toArray(new String [0]), callback);
					  }
				  }
				  });
	  }
		  
public void emptyWidget(){
this.emptyWidget();
}

		  });
  }
}

