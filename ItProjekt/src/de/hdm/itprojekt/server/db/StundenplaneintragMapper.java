package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Stundenplaneintrag</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DozentMapper, LehrveranstaltungMapper, RaumMapper, 
 * SemesterverbandMapper, StundenplanMapper, ZeitslotMapper
 * @author Schmieder, Thies
 */
public class StundenplaneintragMapper {

  /**
   * Die Klasse StundenplaneintragMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see stundenplaneintragMapper()
   */
  private static StundenplaneintragMapper stundenplaneintragMapper = null;

  /**
   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected StundenplaneintragMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>StundenplaneintragMapper.stundenplaneintragMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
   * Instanz von <code>StundenplaneintragMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> StundenplaneintragMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>StundenplaneintragMapper</code>-Objekt.
   * @see stundenplaneintragMapper
   */
  public static StundenplaneintragMapper stundenplaneintragMapper() {
    if (stundenplaneintragMapper == null) {
      stundenplaneintragMapper = new StundenplaneintragMapper();
    }

    return stundenplaneintragMapper;
  }

  /**
   * Suchen eines Stundenplaneintrages mit vorgegebener id. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Prim�rschl�sselattribut (->DB)
   * @return Stundenplaneintrag-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Stundenplaneintrag findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausf�llen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, dozentId, raumId, zeitslotId, studiengangId, "
    		  + "semesterverbandId, stundenplanId, lehrverantsaltungId FROM Stundenplaneintrag "
    		  + "WHERE id=" + id);

      /*
       * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
       * werden. Pr�fe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Stundenplaneintrag s = new Stundenplaneintrag();
        s.setId(rs.getInt("id"));
        s.setDozentId(rs.getInt("dozentId"));
        s.setRaumId(rs.getInt("raumId"));
        s.setZeitslotId(rs.getInt("zeitslotId"));
        s.setStudiengangId(rs.getInt("studiengangId"));
        s.setSemesterverbandId(rs.getInt("semesterverbandId"));
        s.setStundenplanId(rs.getInt("stundenplanId"));
        s.setLehrveranstaltungId(rs.getInt("lehrveranstaltungId"));
        
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
   * Auslesen aller Stundenplaneintr�ge.
   * 
   * @return Ein Vektor mit Stundenplaneintrag-Objekten, die s�mtliche Stundenplaneintr�ge
   *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
   *         oder ggf. auch leerer Vetor zur�ckgeliefert.
   */
  public Vector<Stundenplaneintrag> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Stundenplaneintrag> result = new Vector<Stundenplaneintrag>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, dozentId, raumId, zeitslotId, studiengangId, "
    	+ "semesterverbandId, stundenplanId, lehrveranstaltungId "
    	+ "FROM stundenplaneintrag "
        + " ORDER BY id");

      // F�r jeden Eintrag im Suchergebnis wird nun ein Stundenplaneintrag-Objekt erstellt.
      while (rs.next()) {
        Stundenplaneintrag s = new Stundenplaneintrag();
        s.setId(rs.getInt("id"));
        s.setDozentId(rs.getInt("dozentId"));
        s.setRaumId(rs.getInt("raumId"));
        s.setZeitslotId(rs.getInt("zeitslotId"));
        s.setStudiengangId(rs.getInt("studiengangId"));
        s.setSemesterverbandId(rs.getInt("semesterverbandId"));
        s.setStundenplanId(rs.getInt("stundenplanId"));
        s.setLehrveranstaltungId(rs.getInt("lehrveranstaltungId"));

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
   * Einf�gen eines <code>Stundenplaneintrag</code>-Objekts in die Datenbank. Dabei wird
   * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
   * berichtigt.
   * 
   * @param s das zu speichernde Objekt
   * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Stundenplaneintrag insert(Stundenplaneintrag s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zun�chst schauen wir nach, welches der momentan h�chste
       * Prim�rschl�sselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM stundenplaneintrag ");

      // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * s erh�lt den bisher maximalen, nun um 1 inkrementierten
         * Prim�rschl�ssel.
         */
        s.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tats�chliche Einf�geoperation
        stmt.executeUpdate("INSERT INTO stundenplaneintrag (id, dozentId, raumId, zeitslotId, studiengangId, "
    		  + "semesterverbandId, stundenplanId, lehrveranstaltungId FROM Stundenplaneintrag) " + "VALUES ("
            + s.getId() + "," + s.getDozentId() + "," + s.getRaumId() + s.getzeitslotId() + s.getstudiengangId() 
            + s.getsemesterverbandId() + s.getstundenplanId() + s.getlehrveranstaltungId() );
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * R�ckgabe, des evtl. korrigierten Stundenplaneintrages.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte �bergeben werden, w�re die Anpassung des Stundenplaneintrag-Objekts auch
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
  public Stundenplaneintrag update(Stundenplaneintrag s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE stundenplaneintrag " + "\" " + "WHERE id=" + s.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Stundenplaneintrag s) zu wahren, geben wir s zur�ck
    return s;
  }

  /**
   * L�schen der Daten eines <code>Stundenplaneintrag</code>-Objekts aus der Datenbank.
   * 
   * @param s das aus der DB zu l�schende "Objekt"
   */
  public void delete(Stundenplaneintrag s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM stundenplaneintrag " + "WHERE id=" + s.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

}
