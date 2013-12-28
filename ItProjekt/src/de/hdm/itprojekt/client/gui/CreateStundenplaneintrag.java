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

import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.client.ItProjekt;


	/**
	 * Hier wird ein neuer Stundenplaneintrag angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

public class CreateStundenplaneintrag extends ItProjekt {
		
		private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();
		private HorizontalPanel horPanel = new HorizontalPanel ();
		private HorizontalPanel hrPanel = new HorizontalPanel ();
		private HorizontalPanel horiPanel = new HorizontalPanel ();
		private HorizontalPanel hriPanel = new HorizontalPanel ();
		
		  /**
		   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode @see BasisKlasse#getHeadlineText() zu erstellen ist.
		   */
		  @Override
		  protected String getHeadlineText() {
		    return "Stundenplaneintrag anlegen";
		  }

		  /**
		   * Unter der Überschrift trägt der User die Daten des neuen Stundenplaneintrags ein. 
		   */
		  private final Label lbdozent = new Label ("Dozent"); 
		  private final Label lbzeitslot = new Label ("Zeitslot");
		  private final Label lbraum = new Label ("Raum");
		  private final Label lbstudiengang = new Label ("Studiengang");
		  private final Label lbsemesterverband = new Label ("Semesterverband");
		  private final Label lblehrveranstaltung = new Label ("Lehrveranstaltung");
		  private final ListBox tbdozent = new ListBox ();{
			  
		  }
		  private final ListBox tbzeitslot = new ListBox (); {
			  
		  }
		  private final ListBox tbraum = new ListBox (); {
			  
		  }
		  private final ListBox tbstudiengang = new ListBox(); {
				tbstudiengang.addItem("Wirtschaftsinformatik und digitale Medien");
				tbstudiengang.addItem("Online-Medien Management");
				tbstudiengang.addItem("Informationsdesign");
				tbstudiengang.addItem("Bibliotheks- und Informationsmanagement");
				tbstudiengang.setVisibleItemCount(4);
			    RootPanel.get().add(tbstudiengang);
		  		}
		  private final ListBox tbsemesterverband = new ListBox (); {
			  
		  }
		  private final ListBox tblehrveranstaltung = new ListBox ();{
			  
		  }
		  private final Button speichern = new Button ("speichern");
		  
		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {

				  hPanel.add(lbdozent);
				  hPanel.add(tbdozent);
				  hoPanel.add(lbzeitslot);
				  hoPanel.add(tbzeitslot);
				  horPanel.add(lbraum);
				  horPanel.add(tbraum);
				  hrPanel.add(lbstudiengang);
				  hrPanel.add(tbstudiengang);
				  horiPanel.add(lbsemesterverband);
				  horiPanel.add(tbsemesterverband);
				  hriPanel.add(lblehrveranstaltung);
				  hriPanel.add(tblehrveranstaltung);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
				  vPanel.add(horPanel);
				  vPanel.add(hrPanel);
				  vPanel.add(horiPanel);
				  vPanel.add(hriPanel);
				  vPanel.add(speichern);
				  
				  RootPanel.get("detailsPanel").add(vPanel); 
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  Window.alert("Erfolgreich gespeichert");
					  		}
						 });
				  
		  }

		  
		  /** 
		  * Wir nutzen eine Nested Class.
		  */
		 
		  class CreateSemesterverbandCallback implements AsyncCallback<Semesterverband> {
			    private CreateSemesterverband csvd = null;

			    public CreateSemesterverbandCallback(CreateSemesterverband a) {
			      this.csvd = a;
			    }

			    @Override
			    public void onFailure(Throwable caught) {
				      Window.alert("Der Semesterverband konnte nicht angelegt werden.");
			    }

				@Override
				public void onSuccess(Semesterverband result) {
					Window.alert ("Erfolgreich gespeichert."); 
				}
				}
		  	  
	}

