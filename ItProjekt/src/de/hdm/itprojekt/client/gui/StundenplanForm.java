
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


	
public class StundenplanForm extends Content {




			/**
			 * Aufbau der Seite, um den Raum anzuzeigen, zu löschen und zu bearbeiten
			 */
			
			//final Label flexTable = new Label();
			//private VerticalPanel detailsPanel = new VerticalPanel();
			
			//final TextBox nachnameTextBox = new TextBox();
			//final TextBox vornameTextBox = new TextBox();
			final FlexTable tabelleSp = new FlexTable();
			
			//final CreateStundenplan createSp = new CreateStundenplan();
			//final ChangeStundenplan changeSp = new ChangeStundenplan();
			//final DeleteStundenplan deleteSp = new DeleteStundenplan();
			
			// final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
			 
			
			//final Label valueLabel = new Label();
			


			
			public void onLoad() {
				
				showWidget();
			
				
			//int row = tabelleDozent.getRowCount();
				
				
				tabelleSp.setText(0, 0, "Bezeichnung");
				tabelleSp.setCellPadding(10);
				tabelleSp.setText(0, 1, "Kapazität");
			//	tabelleSp.setText(0, 3, "Funktionen");
			//	tabelleSp.setWidget(1, 4, deleteSpButton);
			//	tabelleSp.setWidget(1, 5, changeSpButton);
				

				
				/**
				 * createSpButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
					this.add(createSp);
					}
				});
				
				changeSpButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						this.add(changeSp);
					}
				});*/
				
			}
				
			public void showWidget() {
				
				this.add(tabelleSp);
				//this.add(changeSp);
				//this.add(createSp);
				//this.add(deleteSp);
			}
			
			
			/**public Stundenplan updateFlexTable (Stundenplan result) {
				for (int i = 0; i < getAllStundenplan.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benötigt
					tabelleSp.addItem(getAllStundenplan.get(i).getVorname());
					
				}
			}
		*/

	}



