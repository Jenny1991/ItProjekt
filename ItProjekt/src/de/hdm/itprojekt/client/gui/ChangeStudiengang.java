package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.Studiengang;

public class ChangeStudiengang extends Content implements StudiengangInhalt {
	
	  /**
	   * Jede Klasse enth�t eine �berschrift, die definiert, was der User machen kann.
	   */
	private final HTML ueberschrift = new HTML ("<h2>Studiengang bearbeiten<h2>");

	  /**
	   * Unter der �berschrift tr�gt der User die neuen Daten des Studiengangs ein. 
	   */
	  final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  final TextBox tbbezeichnung = new TextBox ();
	  final Button speichern = new Button ("speichern");

	  final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);	  		
	  
	  public void onLoad () {
		  
		  this.add(ueberschrift);
		  this.add(lbbezeichnung);
		  this.add(tbbezeichnung);
		  this.add(speichern);

		//  getSelectedData();
		  
		  speichern.addClickHandler(new ClickHandler() {
			  public void onClick(ClickEvent event) {
				  updateStudiengang();
			  }
			  
			  private void updateStudiengang () {	
			  
				  if (!tbbezeichnung.getValue().isEmpty()) {
				  Window.alert ("Bitte füllen Sie alle Felder aus."); }
				  
				  else {
					  Studiengang sg = new Studiengang ();
					  sg.setBezeichnung(tbbezeichnung.getValue().trim());
					  tbbezeichnung.setFocus(true);
				  
					  verwaltungsSvc.changeStudiengang(sg, new AsyncCallback<Studiengang> () {

						  @Override
						  public void onFailure (Throwable caught) {
							  Window.alert("Der Studiengang konnte nicht bearbeitet werden.");
						  }

						  @Override
						  public void onSuccess(Studiengang result) {
							  tbbezeichnung.setValue("");
							  Window.alert ("Erfolgreich gespeichert.");
						  } 	
						});
				  }
			  }
			  });
	  }

		  public void displayStudiengang(Object model) {
			  Studiengang sg = (Studiengang) model; 
			  tbbezeichnung.setText(sg.getBezeichnung());
		  }

		  public void getSelectedData() {
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
		  	}

		  @Override
		public HandlerRegistration addSelectionChangeHandler(Handler handler) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isSelected(Studiengang object) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setSelected(Studiengang object, boolean selected) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getKey(Studiengang item) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getBezeichnung() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String setBezeichnung() {
			return null;
		} 
}
	  
	  
