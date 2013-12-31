/**

 * 
 */
package de.hdm.itprojekt.client.gui;


/**
 * @author V. Hofmann
 *
 */


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



public class RaumForm extends Content {


		/**
		 * Aufbau der Seite, um den Raum anzuzeigen, zu löschen und zu bearbeiten
		 */
		
		//final Label flexTable = new Label();
		//private VerticalPanel detailsPanel = new VerticalPanel();
		
		//final TextBox nachnameTextBox = new TextBox();
		//final TextBox vornameTextBox = new TextBox();
		final FlexTable tabelleRaum = new FlexTable();
		final Button createRaumButton = new Button ("Raum anlegen");
		final Button changeRaumButton = new Button("Raum bearbeiten");
		final Button deleteRaumButton = new Button("Raum löschen");
		
		//final CreateRaum createRaum = new CreateRaum();
		//final ChangeRaum changeRaum = new ChangeRaum();
		//final DeleteRaum deleteRaum = new DeleteRaum();
		
		// final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
		 
		
		//final Label valueLabel = new Label();
		


		
		public void onLoad() {
			
			showWidget();
		
			
		//int row = tabelleDozent.getRowCount();
			
			
			tabelleRaum.setText(0, 0, "Bezeichnung");
			tabelleRaum.setCellPadding(10);
			tabelleRaum.setText(0, 1, "Kapazität");
			tabelleRaum.setText(0, 3, "Funktionen");
			tabelleRaum.setWidget(1, 4, deleteRaumButton);
			tabelleRaum.setWidget(1, 5, changeRaumButton);
			
			/**tabelleRaum.setText(1, 0, "Thies");
			tabelleRaum.setText(1, 1, "Peter");
			tabelleRaum.setText(2, 0, "Rathke");
			tabelleRaum.setText(2, 1, "Christian");
			
			createRaumButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
				this.add(createRaum);
				}
			});
			
			changeRaumButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					this.add(changeRaum);
				}
			});*/
			
		}
			
		public void showWidget() {
			
			this.add(tabelleRaum);
			this.add(createRaumButton);
			this.add(changeRaumButton);
			this.add(deleteRaumButton);
			//this.add(changeRaum);
			//this.add(createRaum);
			//this.add(deleteRaum);
		}
		
		
		/**public Raum updateFlexTable (Raum result) {
			for (int i = 0; i < getAllRaum.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benötigt
				tabelleRaum.addItem(getAllRaum.get(i).getVorname());
				
			}
		}
	*/

}



