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
	 * Hier wird ein neuer Semesterverband angelegt.
	 * 
	 * @author Thies, Espich
	 * 
	 */

	public class CreateSemesterverband extends ItProjekt {
		
		private VerticalPanel vPanel = new VerticalPanel ();
		private HorizontalPanel hPanel = new HorizontalPanel ();
		private HorizontalPanel hoPanel = new HorizontalPanel ();
		private HorizontalPanel horPanel = new HorizontalPanel ();
		private HorizontalPanel hrPanel = new HorizontalPanel ();
		
		  /**
		   * Jede Klasse enthät eine Überschrift, die definiert, was der User machen kann.
		   * Diese ist durch die Methode @see BasisKlasse#getHeadlineText() zu erstellen ist.
		   */
		  @Override
		  protected String getHeadlineText() {
		    return "Semesterverband anlegen";
		  }

		  /**
		   * Unter der Überschrift trägt der User die Daten des neuen Semesterverbands ein. 
		   */
		  private final Label lbjahrgang = new Label ("Jahrgang"); 
		  private final Label lbstudiengang = new Label ("Studiengang");
		  private final Label lbsemester = new Label ("Semster");
		  private final Label lbanzahl = new Label ("Anzahl");
		  private final TextBox tbjahrgang = new TextBox ();
		  private final ListBox tbstudiengang = new ListBox(); {
			tbstudiengang.addItem(getStudiengang); /*muss noch angelegt werden in Semesterverband shared.bo*/
			tbstudiengang.addItem("Wirtschaftsinformatik und digitale Medien");
			tbstudiengang.addItem("Online-Medien Management");
			tbstudiengang.addItem("Informationsdesign");
			tbstudiengang.addItem("Bibliotheks- und Informationsmanagement");
			tbstudiengang.setVisibleItemCount(4);
		    RootPanel.get().add(tbstudiengang);
		    }
		  private final ListBox tbsemester = new ListBox (); {
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
		  private final TextBox tbanzahl = new TextBox ();
		  private final Button speichern = new Button ("speichern");
		  
		  /**
		  * Anordnen der Buttons und Labels auf den Panels
		  */
		  public void onLoad () {

				  hPanel.add(lbjahrgang);
				  hPanel.add(tbjahrgang);
				  hoPanel.add(lbstudiengang);
				  hoPanel.add(tbstudiengang);
				  horPanel.add(lbsemester);
				  horPanel.add(tbsemester);
				  hrPanel.add(lbanzahl);
				  hrPanel.add(tbanzahl);
				  vPanel.add(hPanel);
				  vPanel.add(hoPanel);
				  vPanel.add(horPanel);
				  vPanel.add(speichern);
				  
				  RootPanel.get("detailsPanel").add(vPanel); 
				  
				  tbjahrgang.setFocus(true);
				  tbanzahl.setFocus(true);
				  
				  speichern.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						  boolean allFilled = true;
						  if (tbjahrgang.getText().isEmpty());
						  if (tbanzahl.getText().isEmpty());
						  {	allFilled = false;
						  Window.alert ("Bitte füllen Sie alle Felder aus."); }
						  
						  if (allFilled == true) { 
						  Semesterverband sv = new Semesterverband();
						  sv.setJahrgang(tbjahrgang.getText());
						  sv.setStudierendenAnzahl(tbanzahl.getTabIndex());
						  
						  }
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
					tbjahrgang.setText("");
					tbanzahl.setText("");
					Window.alert ("Erfolgreich gespeichert."); 
				}
				}
		  	  
	}

