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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Dozent;
import de.hdm.itprojekt.shared.bo.Lehrveranstaltung;

/**
 * Hier wird eine bereits angelegte Lehrveranstaltung bearbeitet.
 * 
 * @author Thies, Espich
 * 
 */

public class ChangeLehrveranstaltung extends Content {
	
	
	private final HTML ueberschrift = new HTML ("<h2>Lehrveranstaltung bearbeiten<h2>");
	
	/*private VerticalPanel vPanel = new VerticalPanel ();
	private HorizontalPanel hPanel = new HorizontalPanel ();
	private HorizontalPanel hoPanel = new HorizontalPanel ();
	private HorizontalPanel horPanel = new HorizontalPanel ();*/
	
	Lehrveranstaltung shownLV = null;
	private ArrayList<Lehrveranstaltung> lv = new ArrayList<Lehrveranstaltung> ();
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.  */
	  
	/*protected String getHeadlineText() {
	    return "Lehrveranstaltung bearbeiten";
	  }*/

	  /**
	   * Unter der �berschrift tr�gt der User die neuen Daten der Lehrveranstaltung ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final Label lbsemester = new Label ("Semester");
	  final Label lbumfang = new Label ("Umfang");
	  final TextBox tbbezeichnung = new TextBox ();
	  final ListBox tbsemester = new ListBox(); {
	    tbsemester.addItem("1");
	    tbsemester.addItem("2");
	    tbsemester.addItem("3");
	    tbsemester.addItem("4");
	    tbsemester.addItem("5");
	    tbsemester.addItem("6");
	    tbsemester.addItem("7");
	    tbsemester.setVisibleItemCount(7);
	    this.add(tbsemester);
	    }
	  final ListBox tbumfang = new ListBox (); {
	  	tbumfang.addItem("1 SWS");
	  	tbumfang.addItem("2 SWS");
	  	tbumfang.addItem("3 SWS");
	  	tbumfang.addItem("4 SWS");
	  	tbumfang.setVisibleItemCount(4);
	  	this.add(tbumfang);
	    }
	  final Button speichern = new Button ("speichern");
	  final Button bearbeiten = new Button ("bearbeiten"); 
	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  
	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {
		  this.add(lbbezeichnung);
		  this.add(tbbezeichnung);
		  this.add(lbsemester);
		  this.add(tbsemester);
		  this.add(lbumfang);
		  this.add(tbumfang);
		  this.add(bearbeiten);
		  
		  //RootPanel.get("detailsPanel").add(vPanel); 
		  
		  bearbeiten.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if (shownLV != null){
					shownLV.setBezeichnung(tbbezeichnung.getText());
					shownLV.setSemester(tbsemester.getSelectedIndex());
					shownLV.setUmfang(tbumfang.getSelectedIndex());
					/*verwaltungsSvc.getLehrveranstaltung(shownLV, new AsyncCallback<Lehrveranstaltung>() {
						 @Override
						  public void onFailure (Throwable caught) {
						  }

						  @Override
						  public void onSuccess(Lehrveranstaltung result) {
							  tbbezeichnung.setText(result.getBezeichnung());
							  tbsemester.setSelectedIndex(result.getSemester());
							  tbumfang.setSelectedIndex(result.getUmfang());
							  
							  emptyWidget();
							  changeSelectedDozent();											  
						  }
					  });*/
			  }
	  }
	  
public void changeSelectedDozent(){	 
	
	showWidget();
	 /* hPanel.add(lbbezeichnung);
	  hPanel.add(tbbezeichnung);
	  hoPanel.add(lbsemester);
	  hoPanel.add(tbsemester);
	  horPanel.add(lbumfang);
	  horPanel.add(tbumfang);
	  vPanel.add(hPanel);
	  vPanel.add(hoPanel);
	  vPanel.add(horPanel);
	  vPanel.add(speichern);
	  
	  RootPanel.get("detailsPanel").add(vPanel);*/ 

 speichern.addClickHandler(new ClickHandler() {
	  public void onClick(ClickEvent event) {
		  updateLehrveranstaltung();
	  }
	  
	  private void updateLehrveranstaltung () {	
		  boolean allFilled = true;
		  
		  if (tbbezeichnung.getText().isEmpty());
		  {	allFilled = false;
		  Window.alert("Bitte füllen Sie alle Felder aus."); }
		 
		  if (allFilled == true) { 
			  final String bezeichnung = tbbezeichnung.getText().trim();
			  final int umfang = tbumfang.getSelectedIndex();
			  final int semester = tbsemester.getSelectedIndex();
			  tbbezeichnung.setFocus(true);
			  
			  if (lv.contains(bezeichnung))
				  return;
			  
			  if (verwaltungsSvc == null) {
				 // verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
			  }
		
			  AsyncCallback<Void> callback = new  AsyncCallback<Void> () {

				  @Override
				  public void onFailure (Throwable caught) {
					  Window.alert("Die Lehrveranstaltung konnte nicht bearbeitet werden.");
				  }

				  @Override
				  public void onSuccess(Void result) {
					  
					  tbbezeichnung.setText("");
					  tbsemester.setSelectedIndex(semester);
					  tbumfang.setSelectedIndex(umfang);
					  Window.alert ("Erfolgreich gespeichert.");
				  } 	
				};
				//verwaltungsSvc.changeLehrveranstaltung(lv.toArray(new String [0]), callback);
		  }
	  }
	  });
}
 
public void emptyWidget(){
	this.emptyWidget();
	}

		  });
	  }
	  
	  public void showWidget() {
		  this.add(lbbezeichnung);
		  this.add(tbbezeichnung);
		  this.add(lbsemester);
		  this.add(tbsemester);
		  this.add(lbumfang);
		  this.add(tbumfang);
		  this.add(speichern);
	  }
}
