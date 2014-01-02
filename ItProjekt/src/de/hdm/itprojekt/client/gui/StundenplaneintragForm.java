
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
	
	
public class StundenplaneintragForm extends Content {




			/**
			 * Aufbau der Seite, um den Raum anzuzeigen, zu löschen und zu bearbeiten
			 */
			
			//final Label flexTable = new Label();
			//private VerticalPanel detailsPanel = new VerticalPanel();
			
			//final TextBox nachnameTextBox = new TextBox();
			//final TextBox vornameTextBox = new TextBox();
			final FlexTable tabelleSpe = new FlexTable();
			final Button createSpeButton = new Button ("Stundenplaneintrag anlegen");
			final Button changeSpeButton = new Button("Stundenplaneintrag bearbeiten");
			final Button deleteSpeButton = new Button("Stundenplaneintrag löschen");
			
			//final CreateStundenplaneintrag createSpe = new CreateStundenplaneintrag();
			//final ChangeStundenplaneintrag changeSpe = new ChangeStundenplaneintrag();
			//final DeleteStundenplaneintrag deleteSpe = new DeleteStundenplaneintrag();
			
			// final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
			 
			
			//final Label valueLabel = new Label();
			


			
			public void onLoad() {
				
				showWidget();
			
				
			//int row = tabelleDozent.getRowCount();
				
				
				tabelleSpe.setText(0, 0, "Bezeichnung");
				tabelleSpe.setCellPadding(10);
				tabelleSpe.setText(0, 1, "Kapazität");
				tabelleSpe.setText(0, 3, "Funktionen");
				tabelleSpe.setWidget(1, 4, deleteSpeButton);
				tabelleSpe.setWidget(1, 5, changeSpeButton);

				
				/**createSpeButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
					this.add(createSpe);
					}
				});
				
				changeSpeButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						this.add(changeSpe);
					}
				});*/
				
			}
				
			public void showWidget() {
				
				this.add(tabelleSpe);
				this.add(createSpeButton);
				this.add(changeSpeButton);
				this.add(deleteSpeButton);
				//this.add(changeSpe);
				//this.add(createSpe);
				//this.add(deleteSpe);
			}
			
			
			/**public Stundenplaneintrag updateFlexTable (Stundenplaneintrag result) {
				for (int i = 0; i < getAllStundenplaneintrag.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benötigt
					tabelleSpe.addItem(getAllStundenplaneintrag.get(i).getVorname());
					
				}
			}
		*/

	}




