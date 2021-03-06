/**


 * 
 */
package de.hdm.itprojekt.client.gui;

//import java.util.ArrayList;


//import java.util.ArrayList;


import java.util.Arrays;

import java.util.List;
import java.util.Vector;












import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
//import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.DockLayoutPanel;
//import com.google.gwt.user.client.ui.Grid;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.RootLayoutPanel;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.Panel;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.VerticalPanel;



import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;







import com.google.gwt.view.client.SingleSelectionModel;


import com.google.gwt.view.client.TreeViewModel.DefaultNodeInfo;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;


//import de.hdm.itprojekt.client.ClientsideSettings;
//import de.hdm.itprojekt.client.ClientsideSettings;
import de.hdm.itprojekt.shared.VerwaltungsklasseAsync;
import de.hdm.itprojekt.shared.Verwaltungsklasse;
import de.hdm.itprojekt.shared.bo.BusinessObjekt;
import de.hdm.itprojekt.shared.bo.Dozent;
//import de.hdm.itprojekt.client.*;
//import de.hdm.itprojekt.client.gui.*;



/**
 * @author V. Hofmann
 *
 */
public class DozentForm extends Content {


	/*public static class ContactInfo implements Comparable<ContactInfo> {

		public static final ProvidesKey<ContactInfo> KEY_PROVIDER = new ProvidesKey<ContactInfo>() {
			@Override
			public Object getKey(ContactInfo item) {
				return item == null ? null : item.getId();
			}
		};
		
		private String nachname;
		private String vorname;
		private int id;
		
	
		@Override
		public int compareTo(ContactInfo o) {
			return (o == null || o.vorname == null) ? -1 : -o.vorname.compareTo(vorname);
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof ContactInfo) {
				return id == ((ContactInfo) o).id;
			}
			return false;
		}
		
		public String getVorname() {
			return vorname;
		}
		
		public String getNachname() {
			return nachname;
		}
		
		public int getId() {
			return this.id;
		}
		
		@Override
		public int hashCode() {
			return id;
		}
		
		public void setVorname(String vorname) {
			this.vorname = vorname;
		}
		
		public void setNachname(String nachname) {
			this.nachname = nachname;
		}	
	}
	
	static interface DatabaseConstants extends Constants {
		String [] contactDatabaseCategories();
	}
	
	
	private static DozentForm instance;
	public static DozentForm get() {
		if (instance == null) {
			instance = new DozentForm();
		}
		return instance;
	}
	
	
	private ListDataProvider<ContactInfo> dataProvider = new ListDataProvider<ContactInfo>();
	public void addContact(ContactInfo contact) {
		List<ContactInfo> contacts = dataProvider.getList();
		contacts.remove(contact);
		contacts.add(contact);
		}
	

	public void addDataDisplay(HasData<ContactInfo> display) {
		dataProvider.addDataDisplay(display);
	}
	
	public void generateContacts(int count) {
		List<ContactInfo> contacts = dataProvider.getList();
		for (int i = 0; i < count; i++) {
			contacts.add(createContactInfo());
		}
 	}
	
	public ListDataProvider<ContactInfo> getDataProvider() {
		return dataProvider;
	}
	
		
	/*public void refreshDisplays() {
		dataProvider.refresh();
	}
		
	@SuppressWarnings("deprecation")
	private ContactInfo createContactInfo() {
		ContactInfo contact = new ContactInfo();
		contact.setNachname(null);
		return contact;
	}*/
	
	
	
	
	
	

	
	
	
	
	/*private static final List<String> DOZENTS = Arrays.asList("");
	
	public void onLoad() {
		TextCell textCell = new TextCell();
		
		final CellList<String> cellList = new CellList<String>(textCell);
		
		cellList.setRowCount(DOZENTS.size(), true);
		
		cellList.setVisibleRange(1, 3);
		
		AsyncDataProvider<String> dataProvider = new AsyncDataProvider<String>() {

			@Override
			protected void onRangeChanged(HasData<String> display) {
				final Range range = display.getVisibleRange();
				
				
			}
			
		}
	}*/
	
	
	
	
	
	
	
	
	 //Aufbau der Seite, um den Dozent anzuzeigen, zu löschen und zu bearbeiten
	
		
	private final HTML ueberschrift = new HTML ("<h2>Übersicht der Dozenten<h2>");

	final FlexTable tabelleDozent = new FlexTable();
	final Button createDozentButton = new Button ("Dozent anlegen");
	final Button changeDozentButton = new Button("Dozent bearbeiten");
	final Button deleteDozentButton = new Button("Dozent löschen");
	
	final CreateDozent createD = new CreateDozent();
	final ChangeDozent changeD = new ChangeDozent();
	final DeleteDozent deleteD = new DeleteDozent();
	
	final VerwaltungsklasseAsync verwaltungsSvc = GWT.create(Verwaltungsklasse.class);
	
	private ListDataProvider<Dozent> dozentDataProvider;
	
	private ProvidesKey<BusinessObjekt> boKeyProvider = new ProvidesKey<BusinessObjekt>() {
		public Integer getKey(BusinessObjekt bo) {
			if (bo == null) {
				return null;
			}
			if (bo instanceof Dozent) {
				return new Integer(bo.getId());
			} else {
				return new Integer(-bo.getId());
			}
		}
	};
	
	private SingleSelectionModel<BusinessObjekt> selectionModel = new SingleSelectionModel<BusinessObjekt>(
			boKeyProvider);
	
	
	/*Dozent getSelectedCustomer() {
		return selectedDozent;
	}

	void setSelectedDozent(Dozent d) {
		selectedDozent = d;
		tabelleDozent.setSelected(d);
	}*/
	
	void addDozent(Dozent d) {
		dozentDataProvider.getList().add(d);
	}
	
	
	
	public <T> NodeInfo<?> getNodeInfo(T value) {

		if (value instanceof String) {
			// Erzeugen eines ListDataproviders für Customerdaten
			dozentDataProvider = new ListDataProvider<Dozent>();
			verwaltungsSvc
					.getAllDozenten(new AsyncCallback<Vector<Dozent>>() {
						public void onFailure(Throwable t) {
						}

						public void onSuccess(Vector<Dozent> dozenten) {
							for (Dozent d : dozenten) {
								dozentDataProvider.getList().add(d);
							}
						}

						
					});

			/*// Return a node info that pairs the data with a cell.
			return new DefaultNodeInfo<Dozent>(dozentDataProvider,
					new DozentCell, selectionModel, null);*/
		}
		return null;

		/*if (value instanceof Dozent) {
			// Erzeugen eines ListDataproviders für Account-Daten
			final ListDataProvider<Account> accountsProvider = new ListDataProvider<Account>();
			accountDataProviders.put((Customer) value, accountsProvider);

			bankVerwaltung.getAccountsOf((Customer) value,
					new AsyncCallback<Vector<Account>>() {
						public void onFailure(Throwable t) {
						}

						public void onSuccess(Vector<Account> accounts) {
							for (Account a : accounts) {
								accountsProvider.getList().add(a);
							}
						}
					});

			// Return a node info that pairs the data with a cell.
			return new DefaultNodeInfo<Account>(accountsProvider,
					new AccountCell(), selectionModel, null);
		}*/
		//return null;
	}
	 
	

	/**
	 * Folgende Methode definiert die Widgets beim Laden der Seite
	 */
	public void onLoad() {
		
		this.add(ueberschrift);
		
		tabelleDozent.setText(0, 0, "Nachname");
		tabelleDozent.setCellPadding(10);
		tabelleDozent.setText(0, 1, "Vorname");
		tabelleDozent.setText(0, 3, "Funktionen");
		//tabelleDozent.setWidget(1, 3, deleteDozentButton);
		//tabelleDozent.setWidget(1, 4, changeDozentButton);
		
		
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
		
		
		showWidget();
		
		getData();
		
		
		
	}
		
	
	/**
	 * Daten werden in die Tabelle geladen
	 */
	
	public void getData() {
		verwaltungsSvc.getAllDozenten(new AsyncCallback<Vector<Dozent>>() {
			

					@Override
					public void onSuccess(Vector<Dozent> result) {
						if (result != null) {

							/*tabelleDozent.setText(0, 0, "Nachname");
							//tabelleDozent.setCellPadding(10);
							tabelleDozent.setText(0, 1, "Vorname");
							tabelleDozent.setText(0, 3, "Funktionen");
							tabelleDozent.setWidget(1, 3, deleteDozentButton);
							tabelleDozent.setWidget(1, 4, changeDozentButton);
						
							/**
							 * Dozent wird in die Tabelle geladen
							 */
							
							int firstRow = 1;
							for (int i = 0; i < result.size(); i++) {
								tabelleDozent.setText(firstRow, 0, String.valueOf(result.get(i).getNachname()));
								tabelleDozent.setText(firstRow, 1, String.valueOf(result.get(i).getVorname()));
								tabelleDozent.setWidget(firstRow, 2, changeDozentButton);
								tabelleDozent.setWidget(firstRow, 3, deleteDozentButton);
								firstRow++;
							}	
							
							/**
							 * Definition der Buttons anlegen, löschen und bearbeiten
							 */
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
							
							//showWidget();
							
					} else {
						Window.alert("Keine Dozenten in der Datenbank vorhanden");
					}
					
				}
					
					@Override
					public void onFailure(Throwable caught) {
						
					}
			});
		
	}
	
	
	/**
	 * Widgets werden angezeigt
	 */
	public void showWidget() {
		
		this.add(tabelleDozent);
		this.add(createDozentButton);
		this.add(changeDozentButton);
		this.add(deleteDozentButton);

	}
	
	/**
	 * Zugriff auf die Klasse CreateDozent zum Erstellen eines Dozenten
	 */
	public void showCreate() {
		this.clear();
		this.add(createD);
	}
	
	
	/**
	 * Zugriff auf die Klasse ChangeDozent zum Bearbeiten eines Dozenten
	 */
	public void showChange() {
		this.clear();
		this.add(changeD);
	}

	/**
	 * Zugriff auf die Klasse DeleteDozent zum Löschen eiens Dozenten
	 */
	public void showDelete() {
		this.clear();
		this.add(deleteD);
	}
	
	/*public Dozent updateFlexTable (Dozent result) {
		for (int i = 0; i < getAllDozenten.size(); i++) { //getAllDozent wird noch als Methode oder Klasse benötigt
			tabelleDozent.addItem(getAllDozenten.get(i).getVorname());
			
		}
	}*/

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
	
	*/



