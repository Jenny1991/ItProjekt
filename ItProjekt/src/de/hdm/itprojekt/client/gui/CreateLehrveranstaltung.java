package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Lehrveranstaltung;
import de.hdm.itprojekt.client.ItProjekt;
import de.hdm.itprojekt.client.gui.LehrveranstaltungForm;

	/**
	 * Hier wird eine neue Lehrveranstaltung angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateLehrveranstaltung extends Content {

		
		private final HTML ueberschrift = new HTML ("<h2>Neue Lehrveranstaltung anlegen<h2>");
		
		/*private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();
		private HorizontalPanel horPanel = new HorizontalPanel ();*/
		private ArrayList<Lehrveranstaltung> lv = new ArrayList<Lehrveranstaltung> ();
		
		  /**
		   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.
		   */
		
		  /*protected String getHeadlineText() {
		    return "Lehrveranstaltung anlegen";
		  }*/

		  /**
		   * Unter der �berschrift tr�gt der User die Daten der neuen Lehrveranstaltung ein. 
		   */
		  final Label lbbezeichnung = new Label ("Bezeichnung"); 
		  final Label lbsemester = new Label ("Semester");
		  final Label lbumfang = new Label ("Umfang");
		  final TextBox tbbezeichnung = new TextBox ();
		  /*final ListBox tbsemester = new ListBox();
		   	tbsemester.addItem("1");
		    tbsemester.addItem("2");
		    tbsemester.addItem("3");
		    tbsemester.addItem("4");
		    tbsemester.addItem("5");
		    tbsemester.addItem("6");
		    tbsemester.addItem("7");
		    tbsemester.setVisibleItemCount(7);
		   	this.add(tbsemester);
		   // RootPanel.get().add(tbsemester);*/
		   
		  
		  /*final ListBox tbumfang = new ListBox (); {
		  	tbumfang.addItem("1 SWS");
		  	tbumfang.addItem("2 SWS");
		  	tbumfang.addItem("3 SWS");
		  	tbumfang.addItem("4 SWS");
		  	tbumfang.setVisibleItemCount(4);
		  	this.add(tbumfang);
		  	}
		    //RootPanel.get().add(tbumfang);*/
		    
		  
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
				  final ListBox tbsemester = new ListBox();
				   	tbsemester.addItem("1");
				    tbsemester.addItem("2");
				    tbsemester.addItem("3");
				    tbsemester.addItem("4");
				    tbsemester.addItem("5");
				    tbsemester.addItem("6");
				    tbsemester.addItem("7");
				    tbsemester.setVisibleItemCount(7);
				  this.add(tbsemester);
				  this.add(lbumfang);
				  final ListBox tbumfang = new ListBox();
					  	tbumfang.addItem("1 SWS");
					  	tbumfang.addItem("2 SWS");
					  	tbumfang.addItem("3 SWS");
					  	tbumfang.addItem("4 SWS");
					  	tbumfang.setVisibleItemCount(4);
					  	this.add(tbumfang);
				  this.add(speichern);
				  
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  addLehrveranstaltung();
					  }
					  
					  public void addLehrveranstaltung(){
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
						
							  AsyncCallback<Lehrveranstaltung> callback = new  AsyncCallback<Lehrveranstaltung> () {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Die Lehrveranstaltung konnte nicht angelegt werden.");
								  }

								  @Override
								  public void onSuccess(Lehrveranstaltung result) {
									  
									  tbbezeichnung.setText("");
									  tbsemester.setSelectedIndex(semester);
									  tbumfang.setSelectedIndex(umfang);
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
								};
								//verwaltungsSvc.createLehrveranstaltung(lv.toArray(new String [0]), callback);
						  }
					  }
					  });
		  }
	}  


