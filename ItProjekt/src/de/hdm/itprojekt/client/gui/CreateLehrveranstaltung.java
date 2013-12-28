package de.hdm.itprojekt.client.gui;

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
import de.hdm.itprojekt.shared.bo.Lehrveranstaltung;
import de.hdm.itprojekt.client.ItProjekt;

	/**
	 * Hier wird eine neue Lehrveranstaltung angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateLehrveranstaltung extends ItProjekt {
		
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
		    return "Lehrveranstaltung anlegen";
		  }

		  /**
		   * Unter der Überschrift trägt der User die Daten der neuen Lehrveranstaltung ein. 
		   */
		  private final Label lbbezeichnung = new Label ("Bezeichnung"); 
		  private final Label lbsemester = new Label ("Semester");
		  private final Label lbumfang = new Label ("Umfang");
		  private final TextBox tbbezeichnung = new TextBox ();
		  private final ListBox tbsemester = new ListBox(); {
		    tbsemester.addItem("1");
		    tbsemester.addItem("2");
		    tbsemester.addItem("3");
		    tbsemester.addItem("4");
		    tbsemester.addItem("5");
		    tbsemester.addItem("6");
		    tbsemester.addItem("7");
		    tbsemester.setVisibleItemCount(7);
		    RootPanel.get().add(tbsemester);
		    }
		  private final ListBox tbumfang = new ListBox (); {
		  	tbumfang.addItem("1 SWS");
		  	tbumfang.addItem("2 SWS");
		  	tbumfang.addItem("3 SWS");
		  	tbumfang.addItem("4 SWS");
		  	tbumfang.setVisibleItemCount(4);
		    RootPanel.get().add(tbumfang);
		    }
		  private final Button speichern = new Button ("speichern");
		  
		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {

				  hPanel.add(lbbezeichnung);
				  hPanel.add(tbbezeichnung);
				  hoPanel.add(lbsemester);
				  hoPanel.add(tbsemester);
				  horPanel.add(lbumfang);
				  horPanel.add(tbumfang);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
				  vPanel.add(horPanel);
				  vPanel.add(speichern);
				  
				  RootPanel.get("detailsPanel").add(vPanel); 
				  
				  tbbezeichnung.setFocus(true);
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  boolean allFilled = true;
						  if (tbbezeichnung.getText().isEmpty());
						  {	allFilled = false;
						  Window.alert("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
						  Lehrveranstaltung lv = new Lehrveranstaltung();
						  lv.setBezeichnung(tbbezeichnung.getText());
						  }
						  }
						  });
		  }
		  
		  /** 
		  * Wir nutzen eine Nested Class.
		  */
		 
		  class CreateLehrveranstaltungCallback implements AsyncCallback<Lehrveranstaltung> {
			    private CreateLehrveranstaltung clvd = null;

			    public CreateLehrveranstaltungCallback(CreateLehrveranstaltung a) {
			      this.clvd = a;
			    }

			    @Override
			    public void onFailure(Throwable caught) {
				      Window.alert("Die Lehrveranstaltung  konnte nicht angelegt werden.");
				      }

				@Override
				public void onSuccess(Lehrveranstaltung result) {
					tbbezeichnung.setText("");
					Window.alert ("Erfolgreich gespeichert."); 
				}
				}
		  	  
	}


