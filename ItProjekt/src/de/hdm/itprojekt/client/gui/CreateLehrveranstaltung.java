package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Lehrveranstaltung;

	/**
	 * Hier wird eine neue Lehrveranstaltung angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateLehrveranstaltung extends Content {

		  /**
		   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   */
		private final HTML ueberschrift = new HTML ("<h2>Neue Lehrveranstaltung anlegen<h2>");

		   /**
		   * Unter der �berschrift tr�gt der User die Daten der neuen Lehrveranstaltung ein. 
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
			/*	  final ListBox tbsemester = new ListBox();
				  	tbsemester.addItem("1");
				    tbsemester.addItem("2");
				    tbsemester.addItem("3");
				    tbsemester.addItem("4");
				    tbsemester.addItem("5");
				    tbsemester.addItem("6");
				    tbsemester.addItem("7");
				    tbsemester.setVisibleItemCount(7);
				  this.add(tbsemester); */
				  this.add(lbumfang);
				  this.add(tbumfang);
			/*	  final ListBox tbumfang = new ListBox();
					  	tbumfang.addItem("1 SWS");
					  	tbumfang.addItem("2 SWS");
					  	tbumfang.addItem("3 SWS");
					  	tbumfang.addItem("4 SWS");
					  	tbumfang.setVisibleItemCount(4);
					  	this.add(tbumfang); */
				  this.add(speichern);
				  
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  addLehrveranstaltung();
					  }
					  
					  public void addLehrveranstaltung(){
						  boolean allFilled = true;
						  
						  if (tbbezeichnung.getValue().isEmpty());
						  {	allFilled = false;
						  Window.alert("Bitte füllen Sie alle Felder aus."); }
						 
						  if (allFilled == true) { 
							  final String bezeichnung = tbbezeichnung.getValue().trim();
							  final int umfang = tbumfang.getVisibleLength();
							  final int semester = tbsemester.getVisibleLength();
							  tbbezeichnung.setFocus(true);
							  tbumfang.setFocus(true);
							  tbsemester.setFocus(true);
						
							  verwaltungsSvc.createLehrveranstaltung(bezeichnung, semester, umfang, new AsyncCallback <Lehrveranstaltung>() {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Die Lehrveranstaltung konnte nicht angelegt werden.");
								  }

								  @Override
								  public void onSuccess(Lehrveranstaltung result) {
									  tbbezeichnung.setValue("");
									  tbsemester.setVisibleLength(semester);
									  tbumfang.setVisibleLength(umfang);
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
						  });
					  }
					  }
				  });
		  }
		  }  


