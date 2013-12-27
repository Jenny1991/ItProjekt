package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.client.ItProjekt;


/**
 * Hier wird ein neuer Studiengang angelegt.
 * 
 * @author Thies, Espich
 * 
 */

public class CreateStudiengang extends ItProjekt {

	private VerticalPanel vPanel = new VerticalPanel ();
	private HorizontalPanel hPanel = new HorizontalPanel ();
	
	  /**
	   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
	   * Diese ist durch die Methode @see BasisKlasse#getHeadlineText() zu erstellen ist.
	   */
	  @Override
	  protected String getHeadlineText() {
	    return "Studiengang anlegen";
	  }

	  /**
	   * Unter der Überschrift trägt der User die Daten des neuen Dozenten ein. 
	   */
	  private final Label lbbezeichnung = new Label ("Bezeichnung"); 
	  	  private final TextBox tbbezeichnung = new TextBox ();
	  private final Button speichern = new Button ("speichern");
	  
	  /**
	  * Anordnen der Buttons und Labels auf den Panels
	  */
	  public void onLoad () {

			  hPanel.add(lbbezeichnung);
			  hPanel.add(tbbezeichnung);
			  vPanel.add(hPanel);
			  vPanel.add(speichern);
			  
			  RootPanel.get("detailsPanel").add(vPanel); 
			  
			  tbbezeichnung.setFocus(true);
			  
			  speichern.addClickHandler(new ClickHandler() {
				  public void onClick(ClickEvent event) {
					  boolean allFilled = true;
					  if (tbbezeichnung.getText().isEmpty());
					  {	allFilled = false;
					  Window.alert ("Bitte füllen Sie alle Felder aus."); }
					  
					  if (allFilled == true) { 
					  Studiengang st = new Studiengang ();
					  st.setNachname(tbbezeichnung.getText());
					  }
					  }
					  });
	  }

	  
	  /** 
	  * Wir nutzen eine Nested Class.
	  */
	 
	  class CreateStudiengangCallback implements AsyncCallback<Studiengang> {
		    private CreateStudiengang csd = null;

		    public CreateStudiengangCallback(CreateStudiengang a) {
		      this.csd = a;
		    }

		    @Override
		    public void onFailure(Throwable caught) {
			      Window.alert("Der Studiengang konnte nicht angelegt werden.");
		    }

			@Override
			public void onSuccess(Studiengang result) {
				tbbezeichnung.setText("");
				Window.alert ("Erfolgreich gespeichert."); 
			}
			}
	  	  
}
