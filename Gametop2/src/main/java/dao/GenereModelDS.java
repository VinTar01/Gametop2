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

import model.GenereBean;

public class GenereModelDS implements GenericModel<GenereBean> {
	private static final String TABLE_NAME = "Genere"; // Tabella del DB dove andiamo ad operare

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

	public GenereModelDS() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void doSave(GenereBean genere) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;

		// prepara la query per effettuare insert del genere nel DB
		String insertSQL = "INSERT INTO " + GenereModelDS.TABLE_NAME + " (idGenere, nomeGenere) "
				+ " VALUES(?,?)";
		try {
			connection = ds.getConnection(); // connessione al DB tramite il ConnectionPool
			ps = connection.prepareStatement(insertSQL); // Passiamo la query SQL
			ps.setInt(1, genere.getId());
			ps.setString(2, genere.getTitle());

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
	public synchronized GenereBean doRetrieveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int codice = Integer.parseInt(id);
		GenereBean genere = new GenereBean(); // Genere di appoggio

		String selectSQL = "SELECT * FROM " + GenereModelDS.TABLE_NAME + " WHERE idGenere = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(selectSQL); // Passiamo la query SQL

			ps.setInt(1, codice);

			try {
				ResultSet rs = ps.executeQuery(); // ottiene il risultato della query

				// scorriamo il risultato della query
				while (rs.next()) {

					// prendiamo ogni campo del GenereBean che restituisce la query
					genere.setId(rs.getInt("idGenere"));
					genere.setTitle(rs.getString("nomeGenere"));

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
		return genere; // restituisce il genere con gli attributi settati
	}

	@Override
	public synchronized Collection<GenereBean> doRetrieveAll() throws SQLException {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    Collection<GenereBean> generi = new LinkedList<GenereBean>(); // Collezione di GenereBean

	    String selectSQL = "SELECT * FROM " + GenereModelDS.TABLE_NAME;

	    try {
	        connection = ds.getConnection();

	        ps = connection.prepareStatement(selectSQL); // Passiamo la query SQL

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            GenereBean genere = new GenereBean();

	            genere.setId(rs.getInt("idGenere"));
	            genere.setTitle(rs.getString("nomeGenere"));

	            generi.add(genere);
	        }

	    } finally {
	        try {
	            if (ps != null)
	                ps.close();
	        } finally {
	            connection.close(); // Rilascio la connessione dal pool
	        }
	    }
	    return generi;
	}

	@Override
	public synchronized boolean doDelete(String id) throws SQLException {
	    Connection connection = null;
	    PreparedStatement ps = null;

	    int result = 0; // Flag di controllo
	    int codice = Integer.parseInt(id);

	    String deleteSQL = "DELETE FROM " + GenereModelDS.TABLE_NAME + " WHERE idGenere = ?";

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
	public synchronized void doUpdate(GenereBean genere) throws SQLException {
	    Connection connection = null;
	    PreparedStatement ps = null;

	    String updateSQL = "UPDATE " + GenereModelDS.TABLE_NAME + " SET nomeGenere = ? ";
	    System.out.println(updateSQL);
	    try {

	        connection = ds.getConnection();
	        ps = connection.prepareStatement(updateSQL); // Passiamo la query SQL
	        ps.setString(1, genere.getTitle());

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


