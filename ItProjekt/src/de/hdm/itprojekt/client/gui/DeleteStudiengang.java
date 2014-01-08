package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Studiengang;
import de.hdm.itprojekt.shared.bo.Stundenplaneintrag;
import de.hdm.itprojekt.client.gui.SelectionModel;
import de.hdm.itprojekt.client.gui.StudiengangDetails;
/**
 * Hier wird ein bereits angelegter Studiengang gelöscht.
 * 
 * @author Thies, Espich
 * 
 */

public class DeleteStudiengang extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Studiengang löschen<h2>");
	
	/**
	   * Unter der ï¿½berschrift trï¿½gt der User die neuen Daten des Studiengangs ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final TextBox tbbezeichnung = new TextBox ();
	  final Button loeschen = new Button ("löschen");
	  SelectionModel<StudiengangDetails> selectionModel;
	  private List<StudiengangDetails> studiengangDetails;

	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	  // Studiengang sg = new Studiengang();

	  
	  public void onLoad () {
		  
		  this.add(ueberschrift);		  
		  this.add(lbbezeichnung);
		  this.add(tbbezeichnung);
		  this.add(loeschen);
		  		  
		  loeschen.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  deleteSelectedStudiengang();
			  } 
		  
			 private void deleteSelectedStudiengang () {
				 boolean isSelected = true;
				 
				 List<StudiengangDetails> selctedStudiengang = selectionModel.getSelectedItems();
				 ArrayList<String> stg = new ArrayList<String>();

				 if (selctedStudiengang instanceof Stundenplaneintrag) {
					 isSelected = false;
					 Window.alert("Der gewählte Studiengang kann nicht gelöscht werden, " +
					 		"da er noch in ein oder mehreren Stundenplaneinträgen eingetragen ist" );
				 }

				 else if(isSelected == true) {
				 for (int i=0; i<selctedStudiengang.size(); i++){
					 stg.add(selctedStudiengang.get(i).getId());
				 }
				 verwaltungsSvc.deleteStudiengang(stg, new AysncCallback<ArrayList<StudiengangDetails>>(){
					  public void onFailure (Throwable caught) {
						//  Window.alert("Der Studiengang konnte nicht gelöscht werden." +
						 // 		"Er ist in ein oder mehreren Stundenplaneinträgen eingetragen");
					  }
					  public void onSuccess(ArrayList<StudiengangDetails> result) {
						  studiengangDetails = result;
						  Window.alert ("Erfolgreich gelöscht.");
					  }
				 });
			 }
			 }
		  });
	  }
}	 
				 
				 
				 /** ich brauche von Lui und Domi eine Methode die ich aufrufen kann, um zu sehen, ob der Studiengang noch in 
				  * Stundenplaneinträgen eingetragen ist
				 				 
				 
				 if (sg != null) {
					  verwaltungsSvc.deleteStudiengang(sg, new AsyncCallback<Void>() {
						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht gelöscht werden." +
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
		  verwaltungsSvc.getStudiengang(new AsyncCallback<Studiengang>() {

			  @Override
			  public void onFailure (Throwable caught) {
			  }

			  @Override
			  public void onSuccess(Studiengang result) {
				  if (result != null);
				  tbbezeichnung.setText(result.getBezeichnung().trim());
				}
	  		});
	  	} */

