/**
	 * 
	 */

	package de.hdm.itprojekt.client.gui;

	import java.util.ArrayList;

	import com.google.gwt.core.shared.GWT;
	import com.google.gwt.dom.client.Style.Unit;
	import com.google.gwt.event.dom.client.ClickEvent;
	import com.google.gwt.event.dom.client.ClickHandler;
	import com.google.gwt.user.client.rpc.AsyncCallback;
	import com.google.gwt.user.client.ui.Button;
	import com.google.gwt.user.client.ui.DockLayoutPanel;
	import com.google.gwt.user.client.ui.Grid;
	import com.google.gwt.user.client.ui.HTML;
	import com.google.gwt.user.client.ui.HorizontalPanel;
	import com.google.gwt.user.client.ui.Label;
	import com.google.gwt.user.client.ui.RootLayoutPanel;
	import com.google.gwt.user.client.ui.TextBox;
	import com.google.gwt.user.client.ui.DockLayoutPanel;
	import com.google.gwt.user.client.ui.FlexTable;
	import com.google.gwt.user.client.ui.Panel;
	import com.google.gwt.user.client.ui.RootPanel;
	import com.google.gwt.user.client.ui.VerticalPanel;

	import de.hdm.itprojekt.client.ClientsideSettings;
	import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
	import de.hdm.itprojekt.shared.bo.Dozent;
	import de.hdm.itprojekt.shared.Verwaltungsklasse;
	import de.hdm.itprojekt.client.*;
	import de.hdm.itprojekt.client.gui.*;




/**
 * @author V. Hofmann
 *
 */
public class LehrveranstaltungForm extends Content {


		/**
		 * Aufbau der Seite, um die Lehrveranstaltung anzuzeigen, zu löschen und zu bearbeiten
		 */
		
		//final Label flexTable = new Label();
		//private VerticalPanel detailsPanel = new VerticalPanel();
		
		//final TextBox nachnameTextBox = new TextBox();
		//final TextBox vornameTextBox = new TextBox();
		final FlexTable tabelleLv = new FlexTable();
		final Button createLvButton = new Button ("Lehrveranstaltung anlegen");
		final Button changeLvButton = new Button("Lehrveranstaltung bearbeiten");
		final Button deleteLvButton = new Button("Lehrveranstaltung löschen");
		
		//final CreateLehrveranstaltung createLv = new CreateLehrveranstaltung();
		//final ChangeLehrveranstaltung changeLv = new ChangeLehrveranstaltung();
		//final DeleteLehrveranstaltung deleteLv = new DeleteLehrveranstaltung();
		
		// final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
		 
		
		//final Label valueLabel = new Label();
		


		
		public void onLoad() {
			
			showWidget();
		
			
		//int row = tabelleDozent.getRowCount();
			
			
			tabelleLv.setText(0, 0, "Bezeichnung");
			tabelleLv.setCellPadding(10);
			tabelleLv.setText(0, 1, "Semester");
			tabelleLv.setText(0, 2, "Umfang");
			tabelleLv.setText(0, 3, "Funktionen");
			tabelleLv.setWidget(1, 4, deleteLvButton);
			tabelleLv.setWidget(1, 5, changeLvButton);
			
			/**tabelleLv.setText(1, 0, "Thies");
			tabelleLv.setText(1, 1, "Peter");
			tabelleLv.setText(2, 0, "Rathke");
			tabelleLv.setText(2, 1, "Christian");
			//tabelleDozent.setWidget(1, 1, nachnameTextBox);
			//tabelleDozent.setWidget(1, 2, vornameTextBox);
			
			createLvButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
				this.add(createLv);
				}
			});
			
			changeLvButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					this.add(changeLv);
				}
			});*/
			
		}
			
		public void showWidget() {
			
			this.add(tabelleLv);
			this.add(createLvButton);
			this.add(changeLvButton);
			this.add(deleteLvButton);
			//this.add(changeLv);
			//this.add(createLv);
			//this.add(deleteLv);
		}
		
		
		/**public Lehrveranstaltung updateFlexTable (Lehrveranstaltung result) {
			for (int i = 0; i < getAllLehrveranstaltung.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benötigt
				tabelleLv.addItem(getAllLehrveranstaltung.get(i).getVorname());
				
			}
		}
	*/

}
