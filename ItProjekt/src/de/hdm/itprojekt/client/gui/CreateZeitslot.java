package de.hdm.itprojekt.client.gui;

import java.util.Date;

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
	 * Hier wird ein neuer Zeitslot angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateZeitslot extends ItProjekt {
		
		private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();
		private HorizontalPanel horPanel = new HorizontalPanel ();
		
		
		  /**
		   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode @see BasisKlasse#getHeadlineText() zu erstellen ist.
		   */
		  @Override
		  protected String getHeadlineText() {
		    return "Zeitslot anlegen";
		  }

		  /**
		   * Unter der Überschrift trägt der User die Daten des neuen Zeitslots ein. 
		   */
		  private final Label lbwochentag = new Label ("Wochentag"); 
		  private final Label lbanfangszeit = new Label ("Anfangszeit");
		  private final Label lbendzeit = new Label ("Endzeit");
		  private final TextBox tbwochentag = new TextBox ();
		  private final TextBox tbanfangszeit = new TextBox ();
		  private final TextBox tbendzeit = new TextBox ();
		  private final Button speichern = new Button ("speichern");
		  
		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {

				  hPanel.add(lbwochentag);
				  hPanel.add(tbwochentag);
				  hoPanel.add(lbanfangszeit);
				  hoPanel.add(tbanfangszeit);
				  horPanel.add(lbendzeit);
				  horPanel.add(tbendzeit);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
				  vPanel.add(horPanel);
				  vPanel.add(speichern);
				  
				  RootPanel.get("detailsPanel").add(vPanel); 
				  
				  tbwochentag.setFocus(true);
				  tbanfangszeit.setFocus(true);	 
				  tbendzeit.setFocus(true);
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  boolean allFilled = true;
						  if (tbwochentag.getText().isEmpty());
						  if (tbanfangszeit.getText().isEmpty());
						  if (tbendzeit.getText().isEmpty());
						  {	allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
						  Zeitslot z = new Zeitslot();
						  z.setWochentag(tbwochentag.);
						  z.setAnfangszeit(tbanfangszeit);
						  z.setEndzeit(tbendzeit);						  
						  }
						  }
						  });
		  }
		  
		  
		  /** 
		  * Wir nutzen eine Nested Class.
		  */
		 
		  class CreateZeitslotCallback implements AsyncCallback<Zeitslot> {
			    private CreateZeitslot czd = null;

			    public CreateZeitslotCallback(CreateZeitslot a) {
			      this.czd = a;
			    }

			    @Override
			    public void onFailure(Throwable caught) {
				      Window.alert("Das Zeitslot konnte nicht angelegt werden.");
			    }

				@Override
				public void onSuccess(Zeitslot result) {
					tbwochentag.setText("");
					tbanfangszeit.setText("");
					tbendzeit.setText("");
					Window.alert ("Erfolgreich gespeichert."); 
				}
				}
		  	  
	}

