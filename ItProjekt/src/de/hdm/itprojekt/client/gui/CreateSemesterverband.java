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
import de.hdm.itprojekt.shared.bo.Semesterverband;


	/**
	 * Hier wird ein neuer Semesterverband angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateSemesterverband extends Content {
		
		  /**
		   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   */
		private final HTML ueberschrift = new HTML ("<h2>Neuen Semesterverband anlegen<h2>");

		  /**
		   * Unter der �berschrift tr�gt der User die Daten des neuen Semesterverbands ein. 
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
			  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {

						  boolean allFilled = true;
						  
						  if (tbjahrgang.getText().isEmpty() || tbanzahl.getText().isEmpty()
								  || tbstudiengang.getText().isEmpty() || tbsemester.getText().isEmpty()) 
						  { allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
							  final String jahrgang = tbjahrgang.getText().trim();
					//		  final Studiengang bezeichnung = tbstudiengang.getText().trim();
							  final int studierendenAnzahl = tbanzahl.getVisibleLength();
							  final int semester = tbsemester.getVisibleLength();
			
					/**		  verwaltungsSvc.createSemesterverband(bezeichnung, semester, studierendenAnzahl, jahrgang, new AsyncCallback<Semesterverband>() {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Der Semesterverband konnte nicht angelegt werden.");
								  }

								  @Override
								  public void onSuccess(Semesterverband result) {
									  
									  tbjahrgang.setText("");
									  tbstudiengang.setText("");
									  tbsemester.setVisibleLength(semester);
									  tbanzahl.setVisibleLength(studierendenAnzahl);
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
								}); */
						  }
					  }
					  });
		  }
	}  
	