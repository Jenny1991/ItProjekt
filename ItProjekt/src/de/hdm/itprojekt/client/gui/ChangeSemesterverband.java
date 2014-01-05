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
import de.hdm.itprojekt.shared.bo.Dozent;
import de.hdm.itprojekt.shared.bo.Lehrveranstaltung;
import de.hdm.itprojekt.shared.bo.Semesterverband;

public class ChangeSemesterverband extends Content {

	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
	   */
	private final HTML ueberschrift = new HTML ("<h2>Semesterverband bearbeiten<h2>");

	  /**
	   * Unter der �berschrift tr�gt der User die neuen Daten des Semesterverbands ein. 
	   */
	  final Label lbjahrgang = new Label ("Jahrgang"); 
	  final Label lbstudiengang = new Label ("Studiengang");
	  final Label lbsemester = new Label ("Semster");
	  final Label lbanzahl = new Label ("Anzahl");
	  final TextBox tbjahrgang = new TextBox ();
	  final TextBox tbstudiengang = new TextBox();
	  final TextBox tbsemester = new TextBox ();
	  final TextBox tbanzahl = new TextBox ();
	  final Button speichern = new Button ("speichern");

	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);

	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {
		  
		  this.add(ueberschrift);
		  this.add(lbjahrgang);
		  this.add(tbjahrgang);
		  this.add(lbstudiengang);
		  this.add(tbstudiengang);
		  this.add(lbsemester);
		  this.add(tbsemester);
		  this.add(lbanzahl);
		  this.add(tbanzahl);
		  this.add(speichern);
		  
		  getSelectedData();
	
			  speichern.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  updateSemesterverband();
				  }
				  
				  public void updateSemesterverband(){
					  boolean allFilled = true;
					  
					  if (tbjahrgang.getText().isEmpty());
					  if (tbanzahl.getText().isEmpty());
					  if (tbstudiengang.getText().isEmpty());
					  if (tbsemester.getText().isEmpty()); 
					  { allFilled = false;
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }
					  
					  if (allFilled == true) { 
						  Semesterverband sv = new Semesterverband();
						  sv.setJahrgang(tbjahrgang.getText().trim());
						  sv.setBezeichnung(tbstudiengang.getText());
						  sv.setStudierendenAnzahl(tbanzahl.getVisibleLength());
						  sv.setSemester(tbsemester.getVisibleLength());
						  tbjahrgang.setFocus(true);
						  tbstudiengang.setFocus(true);
						  tbanzahl.setFocus(true);
						  tbsemester.setFocus(true);

						  verwaltungsSvc.changeSemsterverband(sv, new  AsyncCallback<Semesterverband>() {

							  @Override
							  public void onFailure (Throwable caught) {
								  Window.alert("Der Semesterverband konnte nicht bearbeitet werden.");
							  }

							  @Override
							  public void onSuccess(Semesterverband result) {								  
								  tbjahrgang.setText("");
								  tbstudiengang.setText("");
								  tbsemester.setVisibleLength(result.getSemester());
								  tbanzahl.setVisibleLength(result.getStudierendenAnzahl());
								  Window.alert ("Erfolgreich gespeichert.");
							  } 	
							});
					  }
				  }
			  });
		  }
		  
		  public void getSelectedData(){
			  verwaltungsSvc.getSemesterverband(new AsyncCallback<Semesterverband>() {

				  @Override
				  public void onFailure (Throwable caught) {
				  }

				  @Override
				  public void onSuccess(Semesterverband result) {
					  if (result != null);
					  tbjahrgang.setText(result.getJahrgang().trim());
					  tbstudiengang.setText(result.getBezeichnung.trim());
					  tbsemester.setVisibleLength(result.getSemester());
					  tbanzahl.setVisibleLength(result.getStudierendenAnzahl());
					}
		  		});
		  	}
	}

