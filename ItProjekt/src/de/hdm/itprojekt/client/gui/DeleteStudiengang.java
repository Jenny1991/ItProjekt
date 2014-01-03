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
import de.hdm.itprojekt.shared.bo.Studiengang;

/**
 * Hier wird ein bereits angelegter Studiengang gelöscht.
 * 
 * @author Thies, Espich
 * 
 */

public class DeleteStudiengang extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Studiengang löschen<h2>");
	
	Studiengang shownSg = null;
	private ArrayList<Studiengang> sg = new ArrayList<Studiengang> ();
	
	/**
	   * Unter der ï¿½berschrift trï¿½gt der User die neuen Daten des Studiengangs ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final TextBox tbbezeichnung = new TextBox ();
	  final Button speichern = new Button ("speichern");
	  final Button loeschen = new Button ("löschen");
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  
	  public void onLoad () {
		  
		  this.add(ueberschrift);
		  
		  this.add(lbbezeichnung);
		  this.add(tbbezeichnung);
		  this.add(loeschen);
		  
		  loeschen.addClickHandler(new ClickHandler(){
			  public void onClick(ClickEvent event) {			
					if (shownSg!=null){
						shownSg.setBezeichnung(tbbezeichnung.getText());
						deleteSelectedStudiengang();
					}
			  }

			  
		  public void deleteSelectedStudiengang(){	 
			 showWidget();
			  speichern.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  deleteStudiengang();
			  }
			  
			  private void deleteStudiengang () {
				  if (sg.isEmpty()){
					  verwaltungsSvc.deleteStudiengang(sg, new AsyncCallback<Void>(){
						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht gelöscht werden.");
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
  this.add(lbbezeichnung);
  this.add(tbbezeichnung);
  this.add(speichern);
}
}

