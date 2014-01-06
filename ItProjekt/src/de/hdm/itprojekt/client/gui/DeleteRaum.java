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
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Raum;

public class DeleteRaum extends Content {
	
	private final HTML ueberschrift = new HTML ("<h2>Raum löschen<h2>");

	Raum shownRaum = null;
	private ArrayList<Raum> raum = new ArrayList<Raum> ();

		final Label lbbezeichnung = new Label ("Bezeichnung"); 
		  final Label lbkapazitaet = new Label ("KapazitÃ¤t");
		  final TextBox tbbezeichnung = new TextBox ();
		  final TextBox tbkapazitaet = new TextBox ();
		  final Button speichern = new Button ("speichern");
		  final Button loeschen = new Button ("löschen");
		  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);

		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {
			  this.add(ueberschrift);
			  
			  this.add(lbbezeichnung);
			  this.add(tbbezeichnung);
			  this.add(lbkapazitaet);
			  this.add(tbkapazitaet);
			  this.add(loeschen);

			  loeschen.addClickHandler(new ClickHandler(){
				  public void onClick(ClickEvent event) {			
						if (shownRaum !=null){
							shownRaum.setBezeichnung(tbbezeichnung.getText());
							shownRaum.setKapazitaet(tbkapazitaet.getVisibleLength());
								  deleteSelectedRaum();											  
									  }
								 	}
			  					});
		  				}
		  
			  public void deleteSelectedRaum(){	 
				  showWidget();	  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  deleteRaum();
						  }
					  
					  public void deleteRaum(){
						  if (raum.isEmpty()){
					/**		  verwaltungsSvc.deleteRaum(raum, new AsyncCallback<Void>(){
								  @Override
								  public void onFailure (Throwable caught) {
									  Window.alert("Der Raum konnte nicht gelöscht werden.");
								  }

								  @Override
								  public void onSuccess(Void result) {
									  Window.alert ("Erfolgreich gelöscht.");
									  emptyWidget(); 
								  }
							  }); */
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
		  this.add(lbkapazitaet);
		  this.add(tbkapazitaet);
		  this.add(speichern);
	}
	}

