package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
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
	

	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
	   */
	private final HTML ueberschrift = new HTML ("<h2>Studiengang bearbeiten<h2>");

	  /**
	   * Unter der �berschrift tr�gt der User die neuen Daten des Studiengangs ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final TextBox tbbezeichnung = new TextBox ();
	  final Button speichern = new Button ("speichern");

	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);	  		
	  Studiengang sg = null;
	  
	  public void onLoad () {
		  
		  this.add(ueberschrift);
		  showWidget();
		  getSelectedData();
		  
		  speichern.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  Studiengang sg = new Studiengang ();
				  sg.setBezeichnung(tbbezeichnung.getValue().trim());
				  updateStudiengang();
			  }
			  
			  private void updateStudiengang () {	
				  boolean allFilled = true;
			  
				  if (tbbezeichnung.getValue().isEmpty()); {
					  allFilled = false;
				  Window.alert ("Bitte füllen Sie alle Felder aus."); }
				  
				  if (allFilled == true) {
					  sg.setBezeichnung(tbbezeichnung.getValue().trim());
					  tbbezeichnung.setFocus(true);
				  
					  verwaltungsSvc.changeStudiengang(sg, new AsyncCallback<Studiengang> () {

						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht bearbeitet werden.");
						  }

						  @Override
						  public void onSuccess(Studiengang result) {
							  tbbezeichnung.setText("");
							  Window.alert ("Erfolgreich gespeichert.");
						  } 	
						});
				  }
			  }
			  });
	  }

		  public void getSelectedData(){
			  verwaltungsSvc.getStudiengang(new AsyncCallback<Studiengang>() {

				  @Override
				  public void onFailure (Throwable caught) {
				  }

				  @Override
				  public void onSuccess(Studiengang result) {
					  if (result != null);
					  tbbezeichnung.setText(result.getBezeichnung().trim());
					}
		  		});
		  	}
		  
public void emptyWidget(){
	this.emptyWidget();
	}

public void showWidget() {
  this.add(lbbezeichnung);
  this.add(tbbezeichnung);
  this.add(speichern);
  }
}
	  
	  
