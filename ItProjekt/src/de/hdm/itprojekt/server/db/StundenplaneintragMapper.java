package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Stundenplaneintrag</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
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
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see stundenplaneintragMapper()
   */
  private static StundenplaneintragMapper stundenplaneintragMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected StundenplaneintragMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>StundenplaneintragMapper.stundenplaneintragMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
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
   * wird genau ein Objekt zurückgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Stundenplaneintrag-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Stundenplaneintrag findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, dozentId, raumId, zeitslotId, "
    		  + "semesterverbandId, lehrveranstaltungId FROM Stundenplaneintrag "
    		  + "WHERE id=" + id);

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Stundenplaneintrag s = new Stundenplaneintrag();
        s.setId(rs.getInt("id"));
        s.setDozentId(rs.getInt("dozentId"));
        s.setRaumId(rs.getInt("raumId"));
        s.setZeitslotId(rs.getInt("zeitslotId"));
        s.setSemesterverbandId(rs.getInt("semesterverbandId"));
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
   * Auslesen aller Stundenplaneinträge nach einem bestimmten Dozenten, sortiert nach der Anfangszeit.
   * 
   * @return Ein Vektor mit Stundenplaneintrag-Objekten, die sämtliche Stundenplaneinträge
   *         repräsentieren, die dem übergebenen Dozenten zugeordnet sind und nach der Anfangszeit 
   *         im Zeitslot sortiert sind. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Stundenplaneintrag> findByDozentOrderByAnfangszeit(int dozentid) {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Stundenplaneintrag> result = new Vector<Stundenplaneintrag>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT stundenplaneintrag.id, stundenplaneintrag.dozentId, stundenplaneintrag.raumId, stundenplaneintrag.zeitslotId, "
    	+ "stundenplaneintrag.semesterverbandId, stundenplaneintrag.lehrveranstaltungId, "
    	+ "FROM stundenplaneintrag "
    	+ "INNER JOIN ( "
    	+ "stundenplaneintragzeitslot "
    	+ "INNER JOIN "
    	+ "zeitslot "
    	+ "ON "
    	+ "zeitslot.id = stundenplaneintragzeitslot.zeitslotid) "
    	+ "ON "
    	+ "stundenplaneintrag.zeitslotid = stundenplaneintragzeitslot.zeitslotid "
    	+ "WHERE "
    	+ "stundenplaneintrag.dozentid =  " + dozentid
    	+ " ORDER BY zeitslot.anfangszeit");

      // Für jeden Eintrag im Suchergebnis wird nun ein Stundenplaneintrag-Objekt erstellt.
      while (rs.next()) {
        Stundenplaneintrag s = new Stundenplaneintrag();
        s.setId(rs.getInt("id"));
        s.setDozentId(rs.getInt("dozentId"));
        s.setRaumId(rs.getInt("raumId"));
        s.setZeitslotId(rs.getInt("zeitslotId"));
        s.setSemesterverbandId(rs.getInt("semesterverbandId"));
        s.setLehrveranstaltungId(rs.getInt("lehrveranstaltungId"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(s);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }
  
  /**
   * Auslesen aller Stundenplaneinträge nach einem bestimmten Dozenten, sortiert nach der Anfangszeit.
   * 
   * @return Ein Vektor mit Stundenplaneintrag-Objekten, die sämtliche Stundenplaneinträge
   *         repräsentieren, die dem übergebenen Dozenten zugeordnet sind und nach der Anfangszeit 
   *         im Zeitslot sortiert sind. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Stundenplaneintrag> findByRaumOrderByAnfangszeit(int raumid) {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Stundenplaneintrag> result = new Vector<Stundenplaneintrag>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT stundenplaneintrag.id, stundenplaneintrag.dozentId, stundenplaneintrag.raumId, stundenplaneintrag.zeitslotId, "
    	+ "stundenplaneintrag.semesterverbandId, stundenplaneintrag.lehrveranstaltungId, "
    	+ "FROM stundenplaneintrag "
    	+ "INNER JOIN "
    	+ "zeitslot "
    	+ "ON "
    	+ "zeitslot.id = stundenplaneintragzeitslot.zeitslotid) "
    	+ "WHERE "
    	+ "stundenplaneintrag.raumid = " + raumid
    	+ " ORDER BY zeitslot.anfangszeit");

      // Für jeden Eintrag im Suchergebnis wird nun ein Stundenplaneintrag-Objekt erstellt.
      while (rs.next()) {
        Stundenplaneintrag s = new Stundenplaneintrag();
        s.setId(rs.getInt("id"));
        s.setDozentId(rs.getInt("dozentId"));
        s.setRaumId(rs.getInt("raumId"));
        s.setZeitslotId(rs.getInt("zeitslotId"));
        s.setSemesterverbandId(rs.getInt("semesterverbandId"));
        s.setLehrveranstaltungId(rs.getInt("lehrveranstaltungId"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(s);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  
  /**
   * Auslesen aller Stundenplaneinträge.
   * 
   * @return Ein Vektor mit Stundenplaneintrag-Objekten, die sämtliche Stundenplaneinträge
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Stundenplaneintrag> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Stundenplaneintrag> result = new Vector<Stundenplaneintrag>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, dozentId, raumId, zeitslotId, "
    	+ "semesterverbandId, lehrveranstaltungId "
    	+ "FROM stundenplaneintrag "
        + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Stundenplaneintrag-Objekt erstellt.
      while (rs.next()) {
        Stundenplaneintrag s = new Stundenplaneintrag();
        s.setId(rs.getInt("id"));
        s.setDozentId(rs.getInt("dozentId"));
        s.setRaumId(rs.getInt("raumId"));
        s.setZeitslotId(rs.getInt("zeitslotId"));
        s.setSemesterverbandId(rs.getInt("semesterverbandId"));
        s.setLehrveranstaltungId(rs.getInt("lehrveranstaltungId"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(s);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }
  
  
  /**
   * Einfügen eines <code>Stundenplaneintrag</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param s das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Stundenplaneintrag insert(Stundenplaneintrag s) {
    Connection con = DBConnection.connection();
    int stundenplaneintragzeitslotid;
    int stundenplaneintragsemesterverbandid;
    int stundenplaneintragstundenplanid;

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM stundenplaneintrag ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * s erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        s.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Einfügeoperation für die Tabelle stundenplaneintrag
        stmt.executeUpdate("INSERT INTO stundenplaneintrag (id, dozentid, raumid, zeitslotid, "
    		  + "semesterverbandid, lehrveranstaltungid) " + "VALUES ("
            + s.getId() + "," + s.getDozentId() + "," + s.getRaumId() + s.getZeitslotId()  
            + s.getSemesterverbandId() + s.getLehrveranstaltungId() );
        
     // Einfügeoperation für die Zwischentabelle stundenplaneintragsemesterverband
        rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
                + "FROM stundenplaneintragsemesterverband ");
        
        stundenplaneintragsemesterverbandid = rs.getInt("maxid") + 1;
        
        stmt.executeUpdate("INSERT INTO stundenplaneintragsemesterverband "
        	  + "(id, stundenplaneintragid, semesterverbandid) "
       		  + "VALUES ("
              + stundenplaneintragsemesterverbandid + "," + s.getId() + "," + s.getSemesterverbandId() );
        
     // Einfügeoperation für die Zwischentabelle stundenplaneintragzeitslotid
        rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
                + "FROM stundenplaneintragzeitslot ");
        
        stundenplaneintragzeitslotid = rs.getInt("maxid") + 1;
        
        stmt.executeUpdate("INSERT INTO stundenplaneintragzeitslot "
        	  + "(id, stundenplaneintragid, zeitslotid) "
       		  + "VALUES (" + stundenplaneintragsemesterverbandid + "," + s.getId() + "," + s.getZeitslotId() );
        
     // Einfügeoperation für die Zwischentabelle stundenplaneintragstundenplan
        rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
                + "FROM stundenplaneintragstundenplan ");
        
        stundenplaneintragstundenplanid = rs.getInt("maxid") + 1;
        
        stmt.executeUpdate("INSERT INTO stundenplaneintragstundenplan "
        	  + "(id, stundenplaneintragid, zeitslotid) "
       		  + "VALUES (" + stundenplaneintragstundenplanid + "," + s.getId() + "," + s.getStundenplanId() );
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Stundenplaneintrages.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des Stundenplaneintrag-Objekts auch
     * ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar. Die
     * explizite Rückgabe von s ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
     */
    return s;
  }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param s das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Stundenplaneintrag update(Stundenplaneintrag s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE stundenplaneintrag SET " + "lehrveranstaltungid=\""
              + s.getLehrveranstaltungId() + "\", " + "raumid=\""
              + s.getRaumId() + "\", " + "dozentid=\"" + s.getDozentId()
              + "\" " + "WHERE id=" + s.getId());
      
      stmt.executeUpdate("UPDATE stundenplaneintragsemesterverband SET " + "semesterverbandid=\""
              + s.getSemesterverbandId() + "\", " + "raumid=\""
              + s.getRaumId() + "\""
              + "WHERE id=" + s.getId());
      
      stmt.executeUpdate("UPDATE stundenplaneintrag SET " + "lehrveranstaltungid=\""
              + s.getLehrveranstaltungId() + "\", " + "raumid=\""
              + s.getRaumId() + "\", " + "dozentid=\"" + s.getDozentId()
              + "\" " + "WHERE id=" + s.getId());
      
      stmt.executeUpdate("UPDATE stundenplaneintrag SET " + "lehrveranstaltungid=\""
              + s.getLehrveranstaltungId() + "\", " + "raumid=\""
              + s.getRaumId() + "\", " + "dozentid=\"" + s.getDozentId()
              + "\" " + "WHERE id=" + s.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Stundenplaneintrag s) zu wahren, geben wir s zurück
    return s;
  }

  /**
   * Löschen der Daten eines <code>Stundenplaneintrag</code>-Objekts aus der Datenbank.
   * 
   * @param s das aus der DB zu löschende "Objekt"
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
