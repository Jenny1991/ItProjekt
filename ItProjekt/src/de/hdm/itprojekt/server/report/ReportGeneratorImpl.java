package de.hdm.itprojekt.server.report;

import de.hdm.thies.bankProjekt.shared.report.StundenplanDozentReport;

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
     * Das Impressum soll wesentliche Informationen �ber die Bank enthalten.
     */
    Dozent dozent = this.verwaltung.getDozent();

    /*
     * Das Imressum soll mehrzeilig sein.
     */
    CompositeParagraph imprint = new CompositeParagraph();

    imprint.addSubParagraph(new SimpleParagraph(dozent.getVorname()));
    imprint.addSubParagraph(new SimpleParagraph(dozent.getNachname()));
    imprint.addSubParagraph(new SimpleParagraph(dozent.getEmail()));

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

    // Name und Vorname des Kunden aufnehmen
    header.addSubParagraph(new SimpleParagraph(d.getNachname() + ", "
        + d.getVorname()));

    // Hinzuf�gen der zusammengestellten Kopfdaten zu dem Report
    result.setHeaderData(header);

    /*
     * Ab hier erfolgt ein zeilenweises Hinzuf�gen von Konto-Informationen.
     */
    
    /*
     * Zun�chst legen wir eine Kopfzeile f�r die Konto-Tabelle an.
     */
    Row headline = new Row();

    /*
     * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
     * Spalte schreiben wir die jeweilige Kontonummer und in die zweite den
     * aktuellen Kontostand. In der Kopfzeile legen wir also entsprechende
     * �berschriften ab.
     */
    headline.addColumn(new Column("Kto.-Nr."));
    headline.addColumn(new Column("Kto.-Stand"));

    // Hinzuf�gen der Kopfzeile
    result.addRow(headline);

    /*
     * Nun werden s�mtliche Konten des Kunden ausgelesen und deren Kto.-Nr. und
     * Kontostand sukzessive in die Tabelle eingetragen.
     */
    Vector<Stundenplan> stundenplans = this.verwaltung.get();

    for (Stundenplan a : stundenplans) {
      // Eine leere Zeile anlegen.
      Row accountRow = new Row();

      // Erste Spalte: Kontonummer hinzuf�gen
      accountRow.addColumn(new Column(String.valueOf(a.getId())));

      // Zweite Spalte: Kontostand hinzuf�gen
      accountRow.addColumn(new Column(String.valueOf(this.verwaltung
          .getBalanceOf(a))));

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
