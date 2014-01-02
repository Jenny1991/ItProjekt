package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Stundenplan</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DozentMapper, LehrveranstaltungMapper, RaumMapper, 
 * SemesterverbandMapper, StundenplanMapper, StundenplaneintragMapper, ZeitslotMapper
 * @author Schmieder, Thies
 */
public class StudiengangMapper {

  /**
   * Die Klasse StudiengangMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see studiengangMapper()
   */
  private static StudiengangMapper studiengangMapper = null;

  /**
   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected StudiengangMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>StudiengangMapper.studiengangMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
   * Instanz von <code>StudiengangMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> StudiengangMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>StudiengangMapper</code>-Objekt.
   * @see studiengangMapper
   */
  public static StudiengangMapper studiengangMapper() {
    if (studiengangMapper == null) {
      studiengangMapper = new StudiengangMapper();
    }

    return studiengangMapper;
  }

  /**
   * Suchen eines Studienganges mit vorgegebener id. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Prim�rschl�sselattribut (->DB)
   * @return Studiengang-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Studiengang findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausf�llen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, bezeichnung FROM Studiengang "
    		  + "WHERE id=" + id);

      /*
       * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
       * werden. Pr�fe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Studiengang s = new Studiengang();
        s.setId(rs.getInt("id"));
        s.setBezeichnung(rs.getString("bezeichnung"));
                
        return s;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Studieng�nge.
   * 
   * @return Ein Vektor mit Studiengang-Objekten, die s�mtliche Studieng�nge 
   *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
   *         oder ggf. auch leerer Vetor zur�ckgeliefert.
   */
  public Vector<Studiengang> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Studiengang> result = new Vector<Studiengang>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, bezeichnung"
    	+ "FROM studiengang "
        + " ORDER BY id");

      // F�r jeden Eintrag im Suchergebnis wird nun ein Studiengang-Objekt erstellt.
      while (rs.next()) {
        Studiengang s = new Studiengang();
        s.setId(rs.getInt("id"));
        s.setBezeichnung(rs.getString("bezeichnung"));

        // Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        result.addElement(s);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zur�ckgeben
    return result;
  }

  
  /**
   * Einf�gen eines <code>Studiengang</code>-Objekts in die Datenbank. Dabei wird
   * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
   * berichtigt.
   * 
   * @param s das zu speichernde Objekt
   * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Studiengang insert(Studiengang s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zun�chst schauen wir nach, welches der momentan h�chste
       * Prim�rschl�sselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM studiengang ");

      // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * s erh�lt den bisher maximalen, nun um 1 inkrementierten
         * Prim�rschl�ssel.
         */
        s.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tats�chliche Einf�geoperation
        stmt.executeUpdate("INSERT INTO studiengang (id, bezeichnung FROM Studiengang) " + "VALUES ("
            + s.getId() + "," + s.getBezeichnung() );
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * R�ckgabe, des evtl. korrigierten Studienganges.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte �bergeben werden, w�re die Anpassung des Studiengang-Objekts auch
     * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
     * explizite R�ckgabe von s ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
     */
    return s;
  }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param s das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter �bergebene Objekt
   */
  public Studiengang update(Studiengang s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE studiengang " + "\" " + "WHERE id=" + s.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Studiengang s) zu wahren, geben wir s zur�ck
    return s;
  }

  /**
   * L�schen der Daten eines <code>Studiengang</code>-Objekts aus der Datenbank.
   * 
   * @param s das aus der DB zu l�schende "Objekt"
   */
  public void delete(Studiengang s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM studiengang " + "WHERE id=" + s.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

}
