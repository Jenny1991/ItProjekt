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

import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Semesterverband;

/**
 * Hier wird ein bereits angelegter Semesterverband gelöscht.
 * 
 * @author Thies, Espich
 * 
 */

public class DeleteSemesterverband extends Content {

	private final HTML ueberschrift = new HTML ("<h2>Semesterverband löschen<h2>");
	
	Semesterverband shownSv = null;
	Semesterverband sv = new Semesterverband();
	
	/**
	   * Unter der ï¿½berschrift trï¿½gt der User die neuen Daten des Semesterverbands ein. 
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
	  final Button loeschen = new Button ("löschen");
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
		  this.add(loeschen);

		  loeschen.addClickHandler(new ClickHandler(){
			  public void onClick(ClickEvent event) {			
					if (shownSv!=null){
						shownSv.setJahrgang(tbjahrgang.getText());
				//		shownSv.setBezeichnung(tbstudiengang.getText());
						shownSv.setSemester(tbsemester.getVisibleLength());
						shownSv.setStudierendenAnzahl(tbanzahl.getVisibleLength());
						deleteSelectedSemesterverband();
					  }
			  }

			  
		  public void deleteSelectedSemesterverband(){	 
			 showWidget();
			  speichern.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  deleteSemesterverband();
			  }
			  
			  private void deleteSemesterverband () {
				  if (sv != null){
				  verwaltungsSvc.deleteSemesterverband(sv, new AsyncCallback<Void>(){
						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Semesterverband konnte nicht gelöscht werden.");
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
	  this.add(lbjahrgang);
	  this.add(tbjahrgang);
	  this.add(lbstudiengang);
	  this.add(tbstudiengang);
	  this.add(lbsemester);
	  this.add(tbsemester);
	  this.add(lbanzahl);
	  this.add(tbanzahl);
	  this.add(speichern);
}
}

