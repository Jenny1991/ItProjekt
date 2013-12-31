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
	
	
public class ZeitslotForm extends Content {




			/**
			 * Aufbau der Seite, um den Raum anzuzeigen, zu löschen und zu bearbeiten
			 */
			
			//final Label flexTable = new Label();
			//private VerticalPanel detailsPanel = new VerticalPanel();
			
			//final TextBox nachnameTextBox = new TextBox();
			//final TextBox vornameTextBox = new TextBox();
			final FlexTable tabelleZs = new FlexTable();
			final Button createZsButton = new Button ("Zeitslot anlegen");
			final Button changeZsButton = new Button("Zeitslot bearbeiten");
			final Button deleteZsButton = new Button("Zeitslot löschen");
			
			//final CreateZeitslot createZs = new CreateZeitslot();
			//final ChangeZeitslot changeZs = new ChangeZeitslot();
			//final DeleteZeitslot deleteZs = new DeleteZeitslot();
			
			// final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
			 
			
			//final Label valueLabel = new Label();
			


			
			public void onLoad() {
				
				showWidget();
			
				
			//int row = tabelleDozent.getRowCount();
				
				
				tabelleZs.setText(0, 0, "Montag");
				tabelleZs.setCellPadding(10);
				tabelleZs.setText(0, 1, "Dienstag");
				tabelleZs.setText(0, 2, "Mittwoch");
				tabelleZs.setText(0, 3, "Donnerstag");
				tabelleZs.setText(0, 1, "Freitag");
				tabelleZs.setWidget(1, 4, deleteZsButton);
				tabelleZs.setWidget(1, 5, changeZsButton);

				
				/**createZsButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
					this.add(createZs);
					}
				});
				
				changeZsButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						this.add(changeZs);
					}
				});*/
				
			}
				
			public void showWidget() {
				
				this.add(tabelleZs);
				this.add(createZsButton);
				this.add(changeZsButton);
				this.add(deleteZsButton);
				//this.add(changeZs);
				//this.add(createZs);
				//this.add(deleteZs);
			}
			
			
			/**public Zeitslot updateFlexTable (Zeitslot result) {
				for (int i = 0; i < getAllZeitslot.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benötigt
					tabelleZs.addItem(getAllZeitslot.get(i).getVorname());
					
				}
			}
		*/

	}




