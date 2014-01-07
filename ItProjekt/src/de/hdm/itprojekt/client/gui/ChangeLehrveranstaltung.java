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

/**
 * Hier wird eine bereits angelegte Lehrveranstaltung bearbeitet.
 * 
 * @author Thies, Espich
 * 
 */

public class ChangeLehrveranstaltung extends Content {
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
	   */
	private final HTML ueberschrift = new HTML ("<h2>Lehrveranstaltung bearbeiten<h2>");

	  /**
	   * Unter der �berschrift tr�gt der User die neuen Daten der Lehrveranstaltung ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final Label lbsemester = new Label ("Semester");
	  final Label lbumfang = new Label ("Umfang");
	  final TextBox tbbezeichnung = new TextBox ();
	  final TextBox tbsemester = new TextBox(); 
	  final TextBox tbumfang = new TextBox ();
	  final Button speichern = new Button ("speichern");
	  
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  
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
		  this.add(speichern);
		  
		  getSelectedData();
		  
		  speichern.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				updateLehrveranstaltung();
			  }
				
			  private void updateLehrveranstaltung () {	
				  boolean allFilled = true;
				  
				  if (tbbezeichnung.getText().isEmpty());
				  if (tbumfang.getText().isEmpty());
				  if (tbsemester.getText().isEmpty());
				  {	allFilled = false;
				  Window.alert("Bitte füllen Sie alle Felder aus."); }
				 
				  if (allFilled == true) {
					  Lehrveranstaltung lv = new Lehrveranstaltung();
					  lv.setBezeichnung(tbbezeichnung.getText().trim());
					  lv.setSemester(tbsemester.getVisibleLength());
					  lv.setUmfang(tbumfang.getVisibleLength());
					  tbbezeichnung.setFocus(true);
					  tbsemester.setFocus(true);
					  tbumfang.setFocus(true);

					  verwaltungsSvc.changeLehrveranstaltung(lv, new AsyncCallback<Lehrveranstaltung>(){

						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Die Lehrveranstaltung konnte nicht bearbeitet werden.");
						  }

						  @Override
						  public void onSuccess(Lehrveranstaltung result) {			  
							  tbbezeichnung.setText("");
							  tbsemester.setVisibleLength(result.getSemester());
							  tbumfang.setVisibleLength(result.getUmfang());
							  Window.alert ("Erfolgreich gespeichert.");
						  } 	
						});
				  }
			  }
		  });
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
