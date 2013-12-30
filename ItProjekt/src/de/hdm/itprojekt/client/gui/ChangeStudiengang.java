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
import de.hdm.itprojekt.shared.bo.Studiengang;

public class ChangeStudiengang extends Content {
	
	private VerticalPanel vPanel = new VerticalPanel ();
	private HorizontalPanel hPanel = new HorizontalPanel ();
	
	Studiengang shownSg = null;
	private ArrayList<Studiengang> sg = new ArrayList<Studiengang> ();

	  /**
	   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.	   */
	  
	protected String getHeadlineText() {
	    return "Studiengang bearbeiten";
	  }

	  /**
	   * Unter der Überschrift trägt der User die neuen Daten des Studiengangs ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final TextBox tbbezeichnung = new TextBox ();
	  final Button speichern = new Button ("speichern");
	  final Button bearbeiten = new Button ("bearbeiten");
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  
	  public void onLoad () {
		  
		  bearbeiten.addClickHandler(new ClickHandler(){
			  public void onClick(ClickEvent event) {			
					if (shownSg!=null){
						shownSg.setBezeichnung(tbbezeichnung.getText());
						verwaltungsSvc.getStudiengang(shownSg, new AsyncCallback<Studiengang>() {
								 @Override
								  public void onFailure (Throwable caught) {
								  }

								  @Override
								  public void onSuccess(Studiengang result) {
									  tbbezeichnung.setText(result.getBezeichnung());

									  emptyWidget();
									  changeSelectedStudiengang();											  
								  }
							  });
					  }
			  }

		  public void changeSelectedStudiengang(){
		  hPanel.add(lbbezeichnung);
		  hPanel.add(tbbezeichnung);
		  vPanel.add(hPanel);
		  vPanel.add(speichern);
		  
		  RootPanel.get("detailsPanel").add(vPanel); 
		    
		  speichern.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  updateStudiengang();
			  }
			  
			  public void updateStudiengang() {
				  boolean allFilled = true;
				  
				  if (tbbezeichnung.getText().isEmpty());
				  {	allFilled = false;
				  Window.alert ("Bitte füllen Sie alle Felder aus."); }
				  
				  if (allFilled == true) { 
					  final String bezeichnung = tbbezeichnung.getText().trim();
					  tbbezeichnung.setFocus(true);
					  
					  if (sg.contains(bezeichnung))
						  return;
					  
					  if (verwaltungsSvc == null) {
						  verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
					  }
				
					  AsyncCallback<Void> callback = new  AsyncCallback<Void> () {

						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht angelegt werden.");
						  }

						  @Override
						  public void onSuccess(Void result) {
							  tbbezeichnung.setText("");
							  Window.alert ("Erfolgreich gespeichert.");
						  } 	
						};
						verwaltungsSvc.changeStudiengang(sg.toArray(new String [0]), callback);
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
	  
	  
