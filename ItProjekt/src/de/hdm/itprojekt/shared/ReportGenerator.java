package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.thies.bankProjekt.shared.report.AllAccountsOfAllCustomersReport;
import de.hdm.thies.bankProjekt.shared.report.AllAccountsOfCustomerReport;
import de.hdm.thies.bankProjekt.shared.report.RaumbelgungsReport;
import de.hdm.thies.bankProjekt.shared.report.StundenplanDozentReport;
import de.hdm.thies.bankProjekt.shared.report.StundenplanSemesterverbandReport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Erstellung von
 * Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar {@link BankAdministration} und {@lBankAdministrationImplImpl}. Zu
 * technischen Erläuterung etwa bzgl. GWT RPC bzw. {@link RemoteServiceServlet}
 * siehe {@link BankAdministration} undBankAdministrationImpltungImpl}.
 * </p>
 * <p>
 * Ein ReportGenerator bietet die Möglichkeit, eine Menge von Berichten
 * (Reports) zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
 * Systems zweckspezifisch darstellen.
 * </p>
 * <p>
 * Die Klasse bietet eine Reihe von <code>create...</code>-Methoden, mit deren
 * Hilfe die Reports erstellt werden können. Jede dieser Methoden besitzt eine
 * dem Anwendungsfall entsprechende Parameterliste. Diese Parameter benötigt der
 * der Generator, um den Report erstellen zu können.
 * </p>
 * <p> 
 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
 * Weise erweitert werden. Hierzu können zusätzliche <code>create...</code>
 * -Methoden implementiert werden. Die bestehenden Methoden bleiben davon
 * unbeeinflusst, so dass bestehende Programmlogik nicht verändert werden muss.
 * </p>
 * 
 * @author thies
 */
@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

  /**
   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
   *BankAdministrationImpltungImpl} notwendig. Bitte diese Methode direkt nach der
   * Instantiierung aufrufen.
   * 
   * @throws IllegalArgumentException
   */
  public void init() throws IllegalArgumentException;

  /**
   * Setzen des zugeordneten Raums.
   * 
   * @para Raum-Objekt
   * @throws IllegalArgumentException
   */
  public void setRaum(Raum r) throws IllegalArgumentException;
  public void setDozent(Dozent d) throws IllegalArgumentException;
  public void setSemesterverband(Semesterverband sv) throws IllegalArgumentException;

  /**
   * Erstellen eines <code>RaumbelegungsReport</code>-Reports. Dieser
   * Report-Typ stellt sämtliche Räume und ihre Belegungen dar.
   * 
   */
  public abstract RaumbelgungsReport createRaumbelungsReport(
      Raum r) throws IllegalArgumentException;

  /**
   * Erstellen eines <code>StundenplanDozentReport</code>-Reports.
   * Dieser Report-Typ stellt den Stundenplan eines Dozenten dar.
   * 
   * @return das fertige Reportobjekt
   * @throws IllegalArgumentException
   * @see AllAccountsOfAllCustomersReport
   */
  public abstract StundenplanDozentReport createStundenplanDozentReport(Dozent d)
      throws IllegalArgumentException;
  
  /**
   * Erstellen eines <code>StundenplanDozentReport</code>-Reports.
   * Dieser Report-Typ stellt den Stundenplan eine dar.
   * 
   * @return das fertige Reportobjekt
   * @throws IllegalArgumentException
   * @see AllAccountsOfAllCustomersReport
   */
  
  public abstract StundenplanSemesterverbandReport createStundenplanSemesterverbandReport(Semesterverband sv)
	      throws IllegalArgumentException;
}