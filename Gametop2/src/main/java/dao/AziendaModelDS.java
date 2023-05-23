package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.AziendaBean;

public class AziendaModelDS implements GenericModel<AziendaBean> {
	private static final String TABLE_NAME = "AziendaProduzione"; // Tabella del DB dove andiamo ad operare

	private static DataSource ds;

	static {
		try {
			/* Contesto iniziale JNDI */
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			/* Lookup del DataSource */
			ds = (DataSource) envCtx.lookup("jdbc/dblogin");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public AziendaModelDS() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
@Override
public synchronized void doSave(AziendaBean azienda) throws SQLException {

    Connection connection = null;
    PreparedStatement ps = null;

    // prepara la query per effettuare insert dell'azienda nel DB
    String insertSQL = "INSERT INTO " + AziendaModelDS.TABLE_NAME + " (idAzienda, nomeAzienda) "
            + " VALUES(?,?)";
    try {
        connection = ds.getConnection(); // connessione al DB tramite il ConnectionPool
        ps = connection.prepareStatement(insertSQL); // Passiamo la query SQL
        ps.setInt(1, azienda.getId());
        ps.setString(2, azienda.getName());

        ps.executeUpdate(); // esegue la Query di aggiornamento

        connection.commit();

    } finally {

        try {
            if (ps != null)
                ps.close();
        } finally {
            connection.close(); // Rilascio la connessione dal pool
        }
    }
}

@Override
public synchronized AziendaBean doRetrieveByKey(String id) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;

    int codice = Integer.parseInt(id);
    AziendaBean azienda = new AziendaBean(); // Azienda di appoggio

    String selectSQL = "SELECT * FROM " + AziendaModelDS.TABLE_NAME + " WHERE idAzienda = ?";

    try {
        connection = ds.getConnection();
        ps = connection.prepareStatement(selectSQL); // Passiamo la query SQL

        ps.setInt(1, codice);

        try {
            ResultSet rs = ps.executeQuery(); // ottiene il risultato della query

            // scorriamo il risultato della query
            while (rs.next()) {

                // prendiamo ogni campo dell'AziendaBean che restituisce la query
                azienda.setId(rs.getInt("idAzienda"));
                azienda.setName(rs.getString("nomeAzienda"));

            }
        } catch (SQLException e) {
            return null;
        }

    } finally {
        try {
            if (ps != null)
                ps.close();
        } finally {
            connection.close(); // Rilascio la connessione dal pool
        }
    }
    return azienda; // restituisce l'azienda con gli attributi settati
}


@Override
public synchronized Collection<AziendaBean> doRetrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    Collection<AziendaBean> aziende = new LinkedList<AziendaBean>(); // Collezione di AziendaBean

    String selectSQL = "SELECT * FROM " + AziendaModelDS.TABLE_NAME;

    try {
        connection = ds.getConnection();

        ps = connection.prepareStatement(selectSQL); // Passiamo la query SQL

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            AziendaBean azienda = new AziendaBean();

            azienda.setId(rs.getInt("idAzienda"));
            azienda.setName(rs.getString("nomeAzienda"));

            aziende.add(azienda);
        }

    } finally {
        try {
            if (ps != null)
                ps.close();
        } finally {
            connection.close(); // Rilascio la connessione dal pool
        }
    }
    return aziende;
}

@Override
public synchronized boolean doDelete(String id) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;

    int result = 0; // Flag di controllo
    int codice = Integer.parseInt(id);

    String deleteSQL = "DELETE FROM " + AziendaModelDS.TABLE_NAME + " WHERE idAzienda = ?";

    try {
        connection = ds.getConnection();
        ps = connection.prepareStatement(deleteSQL); // Passiamo la query SQL
        ps.setInt(1, codice);

        result = ps.executeUpdate();
    } finally {

        try {
            if (ps != null)
                ps.close();
        } finally {
            connection.close(); // Rilascio la connessione dal pool
        }
    }

    return (result != 0); // vedere errori SQL
}

@Override
public synchronized void doUpdate(AziendaBean azienda) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;

    String updateSQL = "UPDATE " + AziendaModelDS.TABLE_NAME + " SET nomeAzienda = ?";
    System.out.println(updateSQL);
    try {

        connection = ds.getConnection();
        ps = connection.prepareStatement(updateSQL); // Passiamo la query SQL
        ps.setString(1, azienda.getName());

        ps.executeUpdate();
        System.out.print(ps);

    } finally {
        try {
            if (ps != null)
                ps.close();
        } finally {
            connection.close(); // Rilascio la connessione al Database
        }
    }
}
}

