package de.hdm.itprojekt.server.report;

import de.hdm.thies.bankProjekt.shared.bo.Customer;
import de.hdm.thies.bankProjekt.shared.report.Column;
import de.hdm.thies.bankProjekt.shared.report.StundenplanDozentReport;
import de.hdm.thies.bankProjekt.shared.report.SimpleReport;

import java.util.Date;
import java.util.Vector;







import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.client.*;
import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.server.*;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.thies.bankProjekt.shared.report.*;
/**
 * @author hofmann & thies & holz
 */
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet
    implements ReportGenerator {

  /**
   * Ein ReportGenerator ben�tigt Zugriff auf die BankAdministration, da diese die
   * essentiellen Methoden f�r die Koexistenz von Datenobjekten (vgl.
   * bo-Package) bietet.
   */
  private Verwaltungsklasse verwaltung = null;

  /**
   * <p>
   * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
   * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
   * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
   * Konstruktors ist durch die Client-seitige Instantiierung durch
   * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
   * m�glich.
   * </p>
   * <p>
   * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
   * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
   * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
   * </p>
   */
  public ReportGeneratorImpl() throws IllegalArgumentException {
  }

  /**
   * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor.
   * 
   * @see #ReportGeneratorImpl()
   */
  public void init() throws IllegalArgumentException {
    /*
     * Ein ReportGeneratorImpl-Objekt instantiiert f�r seinen Eigenbedarf eine
     * VerwaltungklasseImpl-Instanz.
     */
    VerwaltungsklasseImpl a = new VerwaltungsklasseImpl();
    a.init();
    this.verwaltung = a;
  }

  /**
   * Auslesen der zugeh�rigen BankAdministration (interner Gebrauch).
   * 
   * @return das BankVerwaltungsobjekt
   */
  protected Verwaltungsklasse getVerwaltungsklasse() {
    return this.verwaltung;
  }

  /**
   * Setzen des zugeh�rigen Dozenten-Objekts.
   */
  public void setDozent(Dozent d) {
    this.verwaltung.setDozent(d);
  }

  /**
   * Hinzuf�gen des Report-Impressums. Diese Methode ist aus den
   * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden
   * diese T�tigkeiten redundant auszuf�hren h�tte. Stattdessen rufen die
   * <code>create...</code>-Methoden diese Methode auf.
   * 
   * @param r der um das Impressum zu erweiternde Report.
   */
  protected void addImprint(Report r) {
    /*
     * Das Imressum soll mehrzeilig sein.
     */
    CompositeParagraph imprint = new CompositeParagraph();

    imprint.addSubParagraph(new SimpleParagraph("Hochschule der Medien"));
    imprint.addSubParagraph(new SimpleParagraph("Stuttgart"));

    // Das eigentliche Hinzuf�gen des Impressums zum Report.
    r.setImprint(imprint);

  }

  /**
   * Erstellen von <code>StundenplanDozentReport</code>-Objekten.
   * 
   * @param c das Kundenobjekt bzgl. dessen der Report erstellt werden soll.
   * @return der fertige Report
   */
  public StundenplanDozentReport createStundenplanDozentReport(Dozent d) 
		  throws IllegalArgumentException {

    if (this.getVerwaltungsklasse() == null)
      return null;

    /*
     * Zun�chst legen wir uns einen leeren Report an.
     */
    StundenplanDozentReport result = new StundenplanDozentReport();

    // Jeder Report hat einen Titel (Bezeichnung / �berschrift).
    result.setTitle("Stundenplan des Dozenten");

    // Imressum hinzuf�gen
    this.addImprint(result);

    /*
     * Datum der Erstellung hinzuf�gen. new Date() erzeugt autom. einen
     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
     */
    result.setCreated(new Date());

    /*
     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
     * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
     * die Verwendung von CompositeParagraph.
     */
    CompositeParagraph header = new CompositeParagraph();

    // Name und Vorname des Dozenten aufnehmen
    header.addSubParagraph(new SimpleParagraph(d.getVorname() + ", "
        + d.getNachname()));

    // Hinzuf�gen der zusammengestellten Kopfdaten zu dem Report
    result.setHeaderData(header);

    /*
     * Ab hier erfolgt ein zeilenweises Hinzuf�gen von Stundenplaneintrag-Informationen.
     */
    
    /*
     * Zun�chst legen wir eine Kopfzeile f�r die Stundenplaneintrag-Tabelle an.
     */
    Row headline = new Row();

    /*
     * Erzeugen einer StundenplanTabelle mit 6 Spalten für jeden Wochentag.
     */
    headline.addColumn(new Column("Montag"));
    headline.addColumn(new Column("Dienstag"));
    headline.addColumn(new Column("Mittwoch"));
    headline.addColumn(new Column("Donnerstag"));
    headline.addColumn(new Column("Freitag"));

    // Hinzuf�gen der Kopfzeile
    result.addRow(headline);

    /*
     * Nun werden s�mtliche Stundenplaneintraege des Dozenten ausgelesen und in die Tabelle eingetragen.
     */
    Vector<Stundenplaneintrag> stundenplaneintraege = this.verwaltung.getAllStundenplaneintragOf(d);
    for (Stundenplaneintrag a : stundenplaneintraege) {
      
    if (a.getZeitslot().getAnfangszeit() == 8.15){
    	  Vector<Stundenplaneintrag> b = new Vector<Stundenplaneintrag>();
    	  b.add(a);
    }
      
      
    	
    Row accountRow = new Row();

    if (a.getZeitslot().getWochentag().equals("Montag") && a.getZeitslot().getAnfangszeit() == 8.15){ 
    	accountRow.addColumn(new Column(a.toString()));
    }
    if (a.getZeitslot().getWochentag().equals("Dienstag") && a.getZeitslot().getAnfangszeit() == 8.15){ 
		accountRow.addColumn(new Column(a.toString()));
	}
    if (a.getZeitslot().getWochentag().equals("Mittwoch") && a.getZeitslot().getAnfangszeit() == 8.15){ 
    	accountRow.addColumn(new Column(a.toString()));
    }
      
      
      
      
      // Erste Spalte: Kontonummer hinzufügen
      accountRow.addColumn(new Column(String.valueOf(a.getId())));
      
      
      
      
      //a.getZeitslotId()  (alles)
      //a.getLehrveranstaltungId() 
      //a.getRaumId()
      
      //Erstellen von 5 Spalten und hinzufügen des jeweiligen 
      //Stundenplaneintrag durch selektierung in die
      //jeweilige Spalte und folgend üBerprüfung der Reihenfolge.

      

      // und schlie�lich die Zeile dem Report hinzuf�gen.
      result.addRow(accountRow);
    }

    /*
     * Zum Schluss m�ssen wir noch den fertigen Report zur�ckgeben.
     */
    return result;
  }

  /**
   * Erstellen von <code>AllAccountsOfAllCustomersReport</code>-Objekten.
   * 
   * @return der fertige Report
   */
  public AllAccountsOfAllCustomersReport createAllAccountsOfAllCustomersReport()
      throws IllegalArgumentException {

    if (this.getBankVerwaltung() == null)
      return null;

    /*
     * Zun�chst legen wir uns einen leeren Report an.
     */
    AllAccountsOfAllCustomersReport result = new AllAccountsOfAllCustomersReport();

    // Jeder Report hat einen Titel (Bezeichnung / �berschrift).
    result.setTitle("Alle Konten aller Kunden");

    // Imressum hinzuf�gen
    this.addImprint(result);

    /*
     * Datum der Erstellung hinzuf�gen. new Date() erzeugt autom. einen
     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
     */
    result.setCreated(new Date());

    /*
     * Da AllAccountsOfAllCustomersReport-Objekte aus einer Sammlung von
     * AllAccountsOfCustomerReport-Objekten besteht, ben�tigen wir keine
     * Kopfdaten f�r diesen Report-Typ. Wir geben einfach keine Kopfdaten an...
     */

    /*
     * Nun m�ssen s�mtliche Kunden-Objekte ausgelesen werden. Anschlie�end wir
     * f�r jedes Kundenobjekt c ein Aufruf von
     * createAllAccountsOfCustomerReport(c) durchgef�hrt und somit jeweils ein
     * AllAccountsOfCustomerReport-Objekt erzeugt. Diese Objekte werden
     * sukzessive der result-Variable hinzugef�gt. Sie ist vom Typ
     * AllAccountsOfAllCustomersReport, welches eine Subklasse von
     * CompositeReport ist.
     */
    Vector<Customer> allCustomers = this.verwaltung.getAllCustomers();

    for (Customer c : allCustomers) {
      /*
       * Anlegen des jew. Teil-Reports und Hinzuf�gen zum Gesamt-Report.
       */
      result.addSubReport(this.createAllAccountsOfCustomerReport(c));
    }

    /*
     * Zu guter Letzt m�ssen wir noch den fertigen Report zur�ckgeben.
     */
    return result;
  }

}
