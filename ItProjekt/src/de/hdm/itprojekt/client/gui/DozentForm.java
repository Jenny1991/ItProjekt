/**

 * 
 */
package de.hdm.itprojekt.client.gui;

//import java.util.ArrayList;


import com.google.gwt.core.shared.GWT;
//import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.DockLayoutPanel;
//import com.google.gwt.user.client.ui.Grid;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.Panel;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.VerticalPanel;


//import de.hdm.itprojekt.client.ClientsideSettings;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.Verwaltungsklasse;
//import de.hdm.itprojekt.client.*;
//import de.hdm.itprojekt.client.gui.*;


/**
 * @author Hofmann
 *
 */
public class DozentForm extends Content {

	/**
	 * Aufbau der Seite, um den Dozent anzuzeigen, zu löschen und zu bearbeiten
	 */
		
	private final HTML ueberschrift = new HTML ("<h2>Übersicht der Dozenten<h2>");
	
	final TextBox nachnameTextBox = new TextBox();
	final TextBox vornameTextBox = new TextBox();
	final FlexTable tabelleDozent = new FlexTable();
	final Button createDozentButton = new Button ("Dozent anlegen");
	final Button changeDozentButton = new Button("Dozent bearbeiten");
	final Button deleteDozentButton = new Button("Dozent löschen");
	
	final CreateDozent createD = new CreateDozent();
	final ChangeDozent changeD = new ChangeDozent();
	final DeleteDozent deleteD = new DeleteDozent();
	
	final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	 
	
	//final Label valueLabel = new Label();
	
	/*VerwaltungsklasseAsync verwaltungsklasse = ClientsideSettings
			.getVerwaltungsklasse();
	Dozent shownDozent = null;
	*/

	
	public void onLoad() {
		
		this.add(ueberschrift);
		showWidget();
		
		
		//final VerticalPanel detailsPanel = new VerticalPanel();
		//this.add(tabelleDozent);
		//TextBox nachnameTextBox = new TextBox();
		//TextBox vornameTextBox = new TextBox();
		//Label valueLabel = new Label();

		//int row = tabelleDozent.getRowCount();
		
		
		tabelleDozent.setText(0, 0, "Nachname");
		tabelleDozent.setCellPadding(10);
		tabelleDozent.setText(0, 1, "Vorname");
		tabelleDozent.setText(0, 3, "Funktionen");
		tabelleDozent.setWidget(1, 3, deleteDozentButton);
		tabelleDozent.setWidget(1, 4, changeDozentButton);
		tabelleDozent.setText(1, 0, "Thies");
		tabelleDozent.setText(1, 1, "Peter");
		tabelleDozent.setText(2, 0, "Rathke");
		tabelleDozent.setText(2, 1, "Christian");
		//tabelleDozent.setWidget(1, 1, nachnameTextBox);
		//tabelleDozent.setWidget(1, 2, vornameTextBox);
		
		createDozentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			showCreate();
			}
		});
		
		changeDozentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showChange();
			}
		});
		
		deleteDozentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showDelete();
			}
		});
		
	}
		
	
	public void showWidget() {
		
		this.add(tabelleDozent);
		this.add(createDozentButton);
		this.add(changeDozentButton);
		this.add(deleteDozentButton);

	}
	
	public void showCreate() {
		this.clear();
		this.add(createD);
	}
	
	public void showChange() {
		this.clear();
		this.add(changeD);
	}

	public void showDelete() {
		this.clear();
		this.add(deleteD);
	}
	
	
	/**public Dozent updateFlexTable (Dozent result) {
		for (int i = 0; i < getAllDozent.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benötigt
			tabelleDozent.addItem(getAllDozent.get(i).getVorname());
			
		}
	}
*/
}




	
	/**
		nachnameTextBox.setText(shownDozent.getNachname());
		vornameTextBox.setText(shownDozent.getVorname());
	}
	
	void clearFields () {
		nachnameTextBox.setText("");
		vornameTextBox.setText("");
	}
	
	void setSelected (Dozent d) {
		if (d != null) {
			shownDozent = d;
			setFields();
		} else {
			clearFields();
		}
	}
	
	
	
	
	class deleteDozentCallback implements AsyncCallback<Dozent> {
		
		Dozent dozent = null;
		
		deleteDozentCallback(Dozent d) {
			dozent = d;
		}

		@Override
		public void onFailure(Throwable caught) {

		}

		@Override
		public void onSuccess(Dozent result) {
			if (dozent != null) {
				setSelected(null);
			;
			}
		}
	}


	
	class CreateDozentCallback implements AsyncCallback<Dozent> {
		@Override
		public void onFailure(Throwable caught) {
			// this.showcase.append("Fehler bei der Abfrage " +
			// caught.getMessage());
		}

		public void onSuccess(Dozent dozent) {
			if (dozent != null) {
				ClientsideSettings.getLogger().info(
						"Der Dozent " + dozent.getVorname() + " "
								+ dozent.getNachname() + " wurde angelegt.");
				tabelleDozent.addCell(1);
			}
		}
	}
	
	
	void changeSelectedDozent() {
		if (this.shownDozent!=null){
			shownDozent.setVorname(vornameTextBox.getText());
			shownDozent.setNachname(nachnameTextBox.getText());
			verwaltungsklasse.changeDozent(shownDozent, new AsyncCallback<Dozent>() {
				@Override
				public void onFailure(Throwable caught){					
				}
				@Override
				public void onSuccess(Dozent result){		
					//Dozent.
				}
			});
		}
	}
	
	
}
*/
