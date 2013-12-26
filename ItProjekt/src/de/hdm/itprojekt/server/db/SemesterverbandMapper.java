package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Semesterverband</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DozentMapper, LehrveranstaltungMapper, RaumMapper, 
 * StundenplaneintragMapper, StundenplanMapper, ZeitslotMapper
 * @author Schmieder, Thies
 */
public class SemesterverbandMapper {

  /**
   * Die Klasse SemesterverbandMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see semesterverbandMapper()
   */
  private static SemesterverbandMapper semesterverbandMapper = null;

  /**
   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected SemesterverbandMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>SemesterverbandMapper.semesterverbandMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
   * Instanz von <code>SemesterverbandMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> SemesterverbandMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>SemesterverbandMapper</code>-Objekt.
   * @see semesterverbandMapper
   */
  public static SemesterverbandMapper semesterverbandMapper() {
    if (semesterverbandMapper == null) {
      semesterverbandMapper = new SemesterverbandMapper();
    }

    return semesterverbandMapper;
  }

  /**
   * Suchen eines Semesterverbandes mit vorgegebener id. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Prim�rschl�sselattribut (->DB)
   * @return Semesterverband-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Semesterverband findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausf�llen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, kapazitaet, bezeichnung FROM Semesterverband "
          + "WHERE id=" + id);

      /*
       * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
       * werden. Pr�fe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Semesterverband s = new Semesterverband();
        r.setId(rs.getInt("id"));
        r.setBezeichnung(rs.getString("bezeichnung"));
        r.setKapazitaet(rs.getInt("kapazitaet"));
        
        return r;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller R�ume.
   * 
   * @return Ein Vektor mit Semesterverband-Objekten, die s�mtliche R�ume
   *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
   *         oder ggf. auch leerer Vetor zur�ckgeliefert.
   */
  public Vector<Semesterverband> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Semesterverband> result = new Vector<Semesterverband>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, bezeichnung, kapazitaet FROM semesterverband "
          + " ORDER BY id");

      // F�r jeden Eintrag im Suchergebnis wird nun ein Semesterverband-Objekt erstellt.
      while (rs.next()) {
        Semesterverband s = new Semesterverband();
        r.setId(rs.getInt("id"));
        r.setBezeichnung(rs.getString("bezeichnung"));
        r.setKapazitaet(rs.getInt("kapazitaet"));
        

        // Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        result.addElement(r);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zur�ckgeben
    return result;
  }

  
  /**
   * Einf�gen eines <code>Semesterverband</code>-Objekts in die Datenbank. Dabei wird
   * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
   * berichtigt.
   * 
   * @param r das zu speichernde Objekt
   * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Semesterverband insert(Semesterverband s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zun�chst schauen wir nach, welches der momentan h�chste
       * Prim�rschl�sselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM semesterverband ");

      // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * r erh�lt den bisher maximalen, nun um 1 inkrementierten
         * Prim�rschl�ssel.
         */
        r.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tats�chliche Einf�geoperation
        stmt.executeUpdate("INSERT INTO semesterverband (id, bezeichnung, kapazitaet) " + "VALUES ("
            + r.getId() + "," + r.getBezeichnung() + "," + r.getKapazitaet() );
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * R�ckgabe, des evtl. korrigierten Semesterverbandes.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte �bergeben werden, w�re die Anpassung des Semesterverband-Objekts auch
     * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
     * explizite R�ckgabe von r ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
     */
    return r;
  }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param r das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter �bergebene Objekt
   */
  public Semesterverband update(Semesterverband s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE semesterverband " + "\" " + "WHERE id=" + r.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Semesterverband s) zu wahren, geben wir r zur�ck
    return r;
  }

  /**
   * L�schen der Daten eines <code>Semesterverband</code>-Objekts aus der Datenbank.
   * 
   * @param r das aus der DB zu l�schende "Objekt"
   */
  public void delete(Semesterverband s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM semesterverband " + "WHERE id=" + r.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

}
