package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.thies.bankProjekt.shared.report.*;

/**
 * Das asynchrone Gegenst�ck des Interface {@link ReportGenerator}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. F�r weitere Informationen siehe das
 * synchrone Interface {@link ReportGenerator}.
 * 
 * @author thies
 */
public interface ReportGeneratorAsync {


  void init(AsyncCallback<Void> callback);


void createStundenplanDozentReport(Dozent d,
		AsyncCallback<StundenplanDozentReport> callback);

void createRaumbelungsReport(Raum r, AsyncCallback<RaumbelgungsReport> callback);

void createStundenplanSemesterverbandReport(Semesterverband sv,
		AsyncCallback<StundenplanSemesterverbandReport> callback);

void setRaum(Raum r, AsyncCallback<Void> callback);


void setDozent(Dozent d, AsyncCallback<Void> callback);


void setSemesterverband(Semesterverband sv, AsyncCallback<Void> callback);

}
