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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Dozent;
import de.hdm.itprojekt.client.ClientsideSettings;
import de.hdm.itprojekt.client.ItProjekt;

	/**
	 * Hier wird ein bereits angelegter Dozent bearbeitet.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class ChangeDozent extends Content {
		
		private final HTML ueberschrift = new HTML ("<h2>Dozent bearbeiten<h2>");
		
		/*private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();*/

		Dozent d;
		Dozent shownDozent = null;
		private ArrayList<Dozent> dozent = new ArrayList<Dozent> ();

		  /**
		   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode #getHeadlineText() zu erstellen.
		   */
		
		 /* protected String getHeadlineText() {
		    return "Dozent bearbeiten";
		  }*/

		  /**
		   * Unter der �berschrift tr�gt der User die neuen Daten des  Dozenten ein. 
		   */
		  final Label lbvorname = new Label ("Vorname"); 
		  final Label lbnachname = new Label ("Nachname");
		  final TextBox tbvorname = new TextBox ();
		  final TextBox tbnachname = new TextBox ();
		  //final Button bearbeiten = new Button ("bearbeiten"); 
		  final Button speichern = new Button ("speichern");
		  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
			   
		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {

			  this.add(ueberschrift);
			  
			/*	  hPanel.add(lbnachname);
				  hPanel.add(tbnachname);
				  hoPanel.add(lbvorname);
				  hoPanel.add(tbvorname);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
			!!	  an dieser Stelle muss die FlexTable hin, da der Bearbeiten-Button dort ist!!
				  
				  vPanel.add(bearbeiten);*/
			  this.add(lbnachname);
			  this.add(tbnachname);
			  this.add(lbvorname);
			  this.add(tbvorname);
			  this.add(speichern);
			  //vPanel.add(hPanel);
			  //vPanel.add(hoPanel);
			  //this.add(bearbeiten);
				  
				  //RootPanel.get("detailsPanel").add(vPanel); 

				 /* bearbeiten.addClickHandler(new ClickHandler(){
					  public void onClick(ClickEvent event) {			
							if (shownDozent!=null){
								shownDozent.setVorname(tbvorname.getText());
								shownDozent.setNachname(tbnachname.getText());
								verwaltungsSvc.getDozent(shownDozent, new AsyncCallback<Dozent>() {
										 @Override
										  public void onFailure (Throwable caught) {
										  }

										  @Override
										  public void onSuccess(Dozent result) {
											  tbvorname.setText(result.getVorname());
											  tbnachname.setText(result.getNachname());
											  
											  emptyWidget();
											  changeSelectedDozent();											  
										  }
									  });
							  }
					  }*/
					  
				//  public void changeSelectedDozent(){	 
					// showWidget();
					  /*hPanel.add(lbnachname);
					 hPanel.add(tbnachname);
					 hoPanel.add(lbvorname);
					 hoPanel.add(tbvorname);
					 vPanel.add(hPanel);
					 vPanel.add(hoPanel);
					 vPanel.add(speichern);
					 
					 RootPanel.get("detailsPanel").add(vPanel); */


				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  Dozent d = new Dozent ();
						  d.setNachname(tbnachname.getText().trim());
						  d.setVorname(tbvorname.getText().trim());
						  updateDozent();
					  }
					  
					  private void updateDozent () {	
						  boolean allFilled = true;
					  
						  if (tbnachname.getText().isEmpty());
						  if (tbvorname.getText().isEmpty()); {	
							  allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) {
							  d.setNachname(tbnachname.getText().trim());
							  d.setVorname(tbvorname.getText().trim());
							  tbnachname.setFocus(true);
							  tbvorname.setFocus(true);
							  
							/*  if (dozent.contains(vorname))
								  return;
							  if (dozent.contains(nachname))
								  return;
							  
							  if (verwaltungsSvc == null) {
								  //verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
							  }
						
							  AsyncCallback<Void> callback = new  AsyncCallback<Void> () { */
							  
							  verwaltungsSvc.changeDozent(d, new  AsyncCallback<Dozent> () {

								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Der Dozent konnte nicht bearbeitet werden.");
								  }

								  @Override
								  public void onSuccess(Dozent result) {
									  tbnachname.setText("");
									  tbvorname.setText("");
									  Window.alert ("Erfolgreich gespeichert.");
								  } 	
								});
								//verwaltungsSvc.changeDozent(dozent.toArray(new String [0]), callback);
						  }
					  }
					  });	  
				  }
				  
public void emptyWidget(){
	this.emptyWidget();
	}

				  //});
		  
		//}
		  
	public void showWidget() {
		this.add(lbnachname);
		 this.add(tbnachname);
		 this.add(lbvorname);
		 this.add(tbvorname);
		 this.add(speichern);
	}
}
