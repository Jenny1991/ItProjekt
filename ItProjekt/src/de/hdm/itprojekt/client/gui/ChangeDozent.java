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
	 * Hier wird ein bereits angelegter Dozent bearbeitet.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class ChangeDozent extends ItProjekt {
		
		private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();
		
		  /**
		   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode @see ItProjekt#getHeadlineText() zu erstellen ist.
		   */
		
		  @Override
		  protected String getHeadlineText() {
		    return "Dozent bearbeiten";
		  }

		  /**
		   * Unter der Überschrift trägt der User die neuen Daten des  Dozenten ein. 
		   */
		  private final Label lbvorname = new Label ("Vorname"); 
		  private final Label lbnachname = new Label ("Nachname");
		  private final TextBox tbvorname = new TextBox ();
		  private final TextBox tbnachname = new TextBox ();
		  private final Button speichern = new Button ("speichern");
		  
		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {
			  LoadData();

				  hPanel.add(lbnachname);
				  hPanel.add(tbnachname);
				  hoPanel.add(lbvorname);
				  hoPanel.add(tbvorname);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
				  vPanel.add(speichern);
				  
				  RootPanel.get("detailsPanel").add(vPanel); 
				  
				  tbnachname.setFocus(true);
				  tbvorname.setFocus(true);	  
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  boolean allFilled = true;
						  if (tbnachname.getText().isEmpty());
						  if (tbvorname.getText().isEmpty());
						  {	allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
						  Dozent d = new Dozent();
						  d.setNachname(tbnachname.getText().trim());
						  d.setVorname(tbvorname.getText().trim());
						  Window.alert("Änderungen speichern?");
						  }
						  }
						  });
		  }
		  
		  /*
		   * Wir nutzen eine Nested Class.
		  */
		 
		  class CreateDozentCallback implements AsyncCallback<Dozent> {
			    private CreateDozent cdd = null;

			    public CreateDozentCallback(CreateDozent a) {
			      this.cdd = a;
			    }

			    @Override
			    public void onFailure(Throwable caught) {
			      Window.alert("Der Dozent konnte nicht angelegt werden.");
			    }

				@Override
				public void onSuccess(Dozent result) {
					tbnachname.setText("");
					tbvorname.setText("");
					Window.alert ("Erfolgreich gespeichert.");
				}
				}
		  	  
	}
