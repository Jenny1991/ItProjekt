package de.hdm.itprojekt.shared;

import de.hdm.itProjekt.shared.report.*;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.report.StundenplanDozentReport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author thies
 */
@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

  /**
   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
   * RPC zus�tzlich zum No Argument Constructor der implementierenden Klasse
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
   * Report-Typ stellt s�mtliche R�ume und ihre Belegungen dar.
   * 
   */
  public RaumbelegungsReport createRaumbelungsReport(
      Raum r) throws IllegalArgumentException;

  /**
   * Erstellen eines <code>StundenplanDozentReport</code>-Reports.
   * Dieser Report-Typ stellt den Stundenplan eines Dozenten dar.
   * 
   * @return das fertige Reportobjekt
   * @throws IllegalArgumentException
   * @see AllAccountsOfAllCustomersReport
   */
  public StundenplanDozentReport createStundenplanDozentReport(Dozent d)
      throws IllegalArgumentException;
  
  /**
   * Erstellen eines <code>StundenplanDozentReport</code>-Reports.
   * Dieser Report-Typ stellt den Stundenplan eine dar.
   * 
   * @return das fertige Reportobjekt
   * @throws IllegalArgumentException
   * @see AllAccountsOfAllCustomersReport
   */
  
  public StundenplanSemesterverbandReport createStundenplanSemesterverbandReport(Semesterverband sv)
	      throws IllegalArgumentException;
}