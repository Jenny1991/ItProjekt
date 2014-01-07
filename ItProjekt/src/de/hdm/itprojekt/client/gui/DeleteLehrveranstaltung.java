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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Lehrveranstaltung;

/**
 * Hier wird eine bereits angelegte Lehrveranstaltung gelöscht.
 * 
 * @author Thies, Espich
 * 
 */

public class DeleteLehrveranstaltung extends Content {
	
		private final HTML ueberschrift = new HTML ("<h2>Lehrveranstaltung löschen<h2>");
		
		/**
		   * Unter der ï¿½berschrift trï¿½gt der User die neuen Daten der Lehrveranstaltung ein. 
		   */
		  final Label lbbezeichnung = new Label ("Bezeichnung"); 
		  final Label lbsemester = new Label ("Semester");
		  final Label lbumfang = new Label ("Umfang");
		  final TextBox tbbezeichnung = new TextBox ();
		  final TextBox tbsemester = new TextBox();
		  final TextBox tbumfang = new TextBox ();
		  final Button loeschen = new Button ("löschen"); 
		  
		  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
		  Lehrveranstaltung lv = new Lehrveranstaltung();
		  
		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {
			  
			  this.add(ueberschrift);
			  this.add(lbbezeichnung);
			  this.add(tbbezeichnung);
			  this.add(lbsemester);
			  this.add(tbsemester);
			  this.add(lbumfang);
			  this.add(tbumfang);
			  this.add(loeschen);
			  
			  loeschen.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  deleteDozent();
				  } 
			  
				 private void deleteDozent () {
					 /** ich brauche von Lui und Domi eine Methode die ich aufrufen kann, um zu sehen, ob die Lehrveranstaltung
					  * noch in Stundenplaneinträgen eingetragen ist
					  */				 
					 
					 if (lv != null) {
						  verwaltungsSvc.deleteLehrveranstaltung(lv, new AsyncCallback<Void>(){
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Dozent konnte nicht gelöscht werden." +
								  		"Er ist in ein oder mehreren Stundenplaneinträgen eingetragen");
							  }

							  @Override
							  public void onSuccess(Void result) {
								  Window.alert ("Erfolgreich gelöscht.");
							  } 	
							});
					  }
				  }
				  });
		  		this.clear();
			  }

public void getSelectedData(){
/**	  verwaltungsSvc.getLehrveranstaltung(new AsyncCallback<Lehrveranstaltung>() {

		  @Override
		  public void onFailure (Throwable caught) {
		  }

		  @Override
		  public void onSuccess(Lehrveranstaltung result) {
			  if (result != null);
			  tbbezeichnung.setText(result.getBezeichnung().trim());
			  tbsemester.setVisibleLength(result.getSemester());
			  tbumfang.setVisibleLength(result.getUmfang());
			}
		});*/
	}
}