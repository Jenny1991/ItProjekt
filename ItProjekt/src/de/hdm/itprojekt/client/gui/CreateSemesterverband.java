package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Semesterverband;
import de.hdm.itprojekt.client.ItProjekt;
import de.hdm.itprojekt.client.gui.SemesterverbandForm;


	/**
	 * Hier wird ein neuer Semesterverband angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateSemesterverband extends Content {
		
		private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();
		private HorizontalPanel horPanel = new HorizontalPanel ();
		private HorizontalPanel hrPanel = new HorizontalPanel ();
		private ArrayList<Semesterverband> sv = new ArrayList<Semesterverband> ();

		  /**
		   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.		   */
		  
		protected String getHeadlineText() {
		    return "Semesterverband anlegen";
		  }

		  /**
		   * Unter der Überschrift trägt der User die Daten des neuen Semesterverbands ein. 
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

				  hPanel.add(lbjahrgang);
				  hPanel.add(tbjahrgang);
				  hoPanel.add(lbstudiengang);
				  hoPanel.add(tbstudiengang);
				  horPanel.add(lbsemester);
				  horPanel.add(tbsemester);
				  hrPanel.add(lbanzahl);
				  hrPanel.add(tbanzahl);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
				  vPanel.add(horPanel);
				  vPanel.add(speichern);
				  
				  RootPanel.get("detailsPanel").add(vPanel); 

				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  addSemesterverband();
					  }
					  
					  public void addSemesterverband(){
						  boolean allFilled = true;
						  
						  if (tbjahrgang.getText().isEmpty());
						  if (tbanzahl.getText().isEmpty());
						  if (tbstudiengang.getText().isEmpty());
						  if (tbsemester.getText().isEmpty()); 
						  { allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
							  final String jahrgang = tbjahrgang.getText().trim();
							  final String studiengang = tbstudiengang.getText();
							  final int anzahl = tbanzahl.getVisibleLength();
							  final int semester = tbsemester.getVisibleLength();
							  tbjahrgang.setFocus(true);
							  tbstudiengang.setFocus(true);
							  tbanzahl.setFocus(true);
							  tbsemester.setFocus(true);
							  
							  if (sv.contains(jahrgang))
								  return;
							  if (sv.contains(studiengang))
								  return;
							  if (sv.contains(anzahl))
								  return;
							  if (sv.contains(semester))
								  return;
							  
							  if (verwaltungsSvc == null) {
								  verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
							  }
						
							  AsyncCallback<Semesterverband> callback = new  AsyncCallback<Semesterverband> () {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Der Semesterverband konnte nicht angelegt werden.");
								  }

								  @Override
								  public void onSuccess(Semesterverband result) {
									  
									  tbjahrgang.setText("");
									  tbstudiengang.setText("");
									  tbsemester.setVisibleLength(semester);
									  tbanzahl.setVisibleLength(anzahl);
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
								};
								verwaltungsSvc.addSemesterverband(sv.toArray(new String [0]), callback);
						  }
					  }
					  });
		  }
	}  
	