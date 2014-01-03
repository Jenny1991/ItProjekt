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
import de.hdm.itprojekt.shared.bo.Zeitslot;

/**
 * Hier wird ein bereits angelegter Dozent gelöscht.
 * 
 * @author Thies, Espich
 * 
 */

public class DeleteZeitslot  extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Zeitslot löschen<h2>");
	
	Zeitslot shownZeitslot = null;
	private ArrayList<Zeitslot> zeitslot = new ArrayList<Zeitslot> ();
	
	final Label lbwochentag = new Label ("Wochentag"); 
	  final Label lbanfangszeit = new Label ("Anfangszeit");
	  final Label lbendzeit = new Label ("Endzeit");
	  final TextBox tbwochentag = new TextBox ();
	  final TextBox tbanfangszeit = new TextBox ();
	  final TextBox tbendzeit = new TextBox ();
	  final Button speichern = new Button ("speichern");
	  final Button loeschen = new Button ("löschen");
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
			  this.add(loeschen);
			  
			  
			  loeschen.addClickHandler(new ClickHandler(){
				  public void onClick(ClickEvent event) {			
						if (shownZeitslot!=null){
							shownZeitslot.setWochentag(tbwochentag.getText());
							shownZeitslot.setAnfangszeit(tbanfangszeit.getVisibleLength());
							shownZeitslot.setEndzeit(tbendzeit.getVisibleLength());
							deleteSelectedZeitslot();
						  }
				  }

				  
			  public void deleteSelectedZeitslot(){	 
				 showWidget();
				  speichern.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  deleteZeitslot();
				  }
				  
				  private void deleteZeitslot () {
					  if (zeitslot.isEmpty()){
						  verwaltungsSvc.deleteZeitslot(zeitslot, new AsyncCallback<Void>(){
							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Das Zeitslot konnte nicht gelöscht werden.");
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
			  });
	  }
	  
public void emptyWidget(){
this.emptyWidget();
}

public void showWidget() {
	this.add(lbwochentag);
	this.add(tbwochentag);
	this.add(lbanfangszeit);
	this.add(tbanfangszeit);
	this.add(lbendzeit);
	this.add(tbendzeit);
	this.add(speichern);
}
}
							
							
							
