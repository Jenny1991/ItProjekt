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

		Lehrveranstaltung shownLV = null;
		private ArrayList<Lehrveranstaltung> lv = new ArrayList<Lehrveranstaltung> ();
		
		/**
		   * Unter der ï¿½berschrift trï¿½gt der User die neuen Daten der Lehrveranstaltung ein. 
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
		  final Button loeschen = new Button ("löschen"); 
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
			  this.add(loeschen);
			  
			  loeschen.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					if (shownLV != null){
						shownLV.setBezeichnung(tbbezeichnung.getText());
						shownLV.setSemester(tbsemester.getSelectedIndex());
						shownLV.setUmfang(tbumfang.getSelectedIndex());
						deleteSelectedLehrveranstaltung();											  
							  }
							}
						  });
				  }
		  		  
	public void deleteSelectedLehrveranstaltung(){	 
		showWidget();
		speichern.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			  deleteLehrveranstaltung();
		  }
		  
		  private void deleteLehrveranstaltung () {
			  if (lv.isEmpty()){
				  verwaltungsSvc.deleteLehrveranstaltung(lv, new AsyncCallback<Void>(){
					  @Override
					  public void onFailure (Throwable caught) {
						  Window.alert("Die Lehrveranstaltung konnte nicht gelöscht werden.");
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

public void emptyWidget(){
this.emptyWidget();
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