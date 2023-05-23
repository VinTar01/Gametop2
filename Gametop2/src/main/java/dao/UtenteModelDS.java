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

import model.UtenteBean;

public class UtenteModelDS implements GenericModel<UtenteBean> {
	private static final String TABLE_NAME = "Utente"; //Tabella del DB dove andiamo ad operare
	
	private static DataSource ds;

	static {
		try {
			/*Contesto iniziale JNDI*/
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			/*Lookup del DataSource*/
			ds = (DataSource) envCtx.lookup("jdbc/dblogin");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
    
	  
    public UtenteModelDS() {
        super();
        // TODO Auto-generated constructor stub
    }

@Override   
public synchronized void doSave(UtenteBean utente) throws SQLException{
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		//prepara la query per effettuare insert dell'utente nel DB
		String insertSQL = "INSERT INTO " + UtenteModelDS.TABLE_NAME + " (email,password,nome,cognome,admin) "
				+ " VALUES(?,?,?,?,?)";
		try {
			connection = ds.getConnection(); //connessione al DB 
			ps = connection.prepareStatement(insertSQL); //Passiamo la query SQL
			ps.setString(1,utente.getEmail());
			ps.setString(2,utente.getPassword());
			ps.setString(3,utente.getFirstname());
			ps.setString(4,utente.getLastname());
			ps.setBoolean(5,utente.isAdmin());
			
			
			ps.executeUpdate(); //esegue la Query di aggiornamento
			
			connection.commit();
				
		}finally {
		
			try {
				if(ps != null)
					ps.close();
			}finally {
				connection.close();; //Rilascio la connessione
			}
		}
	}

@Override
public synchronized UtenteBean doRetrieveByKey(String email) throws SQLException{
	Connection connection = null;
	PreparedStatement ps = null;
	
	UtenteBean utente = new UtenteBean(); //Utente di appoggio
	
	String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME + " WHERE email = ?";  
	
	try {
		connection = ds.getConnection();
		ps = connection.prepareStatement(selectSQL); //Passiamo la query SQL
		
		ps.setString(1, email);
		
		try {
		ResultSet rs = ps.executeQuery();  //ottiene il risultato della query
		
		
		//scorriamo il risultato della query
		while(rs.next()) {
			
			//prendiamo ogni campo dell'UserBean che restituisce la query
			utente.setEmail(rs.getString("email"));
			utente.setPassword(rs.getString("password"));
			utente.setFirstname(rs.getString("nome"));
			utente.setLastname(rs.getString("cognome"));
			utente.setAdmin(rs.getBoolean("admin"));
			}
		}catch(SQLException e){
			return null;
		}
		
	}finally {
		try {
			if(ps != null)
				ps.close();
		}finally {
			connection.close(); //Rilascio la connessione dal pool
		}
	}
	return utente;  //restituisce l'utente con gli attributi settati
}


@Override
public synchronized Collection<UtenteBean> doRetrieveAll() throws SQLException
{
	
	Connection connection = null;
	PreparedStatement ps = null;
	Collection<UtenteBean> utenti = new LinkedList<UtenteBean>(); //Collezione di UserBean
	
	String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME ;
	
	try {
		connection = ds.getConnection(); 

		ps = connection.prepareStatement(selectSQL); //Passiamo la query SQL
		
		
		ResultSet rs = ps.executeQuery();
		
	
		
		while(rs.next()) {
			UtenteBean utente = new UtenteBean();
			
			utente.setEmail(rs.getString("email"));
			utente.setPassword(rs.getString("password"));
			utente.setFirstname(rs.getString("nome"));
			utente.setLastname(rs.getString("cognome"));
			utente.setAdmin(rs.getBoolean("admin"));
			
		
			utenti.add(utente);
			
		}
		
	}finally {
		try {
			if(ps != null)
				ps.close();
		}finally {
			connection.close();; //Rilascio la connessione dal pool
		}
	}
	return utenti;
}



@Override
public synchronized boolean doDelete(String email) throws SQLException{
	Connection connection = null;
	PreparedStatement ps = null;
	
	int result = 0; //Flag di controllo 
	String deleteSQL = "DELETE FROM " + UtenteModelDS.TABLE_NAME + " WHERE email = ?";
	
	try {
		connection = ds.getConnection(); 
		ps = connection.prepareStatement(deleteSQL); //Passiamo la query SQL
		ps.setString(1, email);
		
		result = ps.executeUpdate();
	}finally {
		
		try {
			if(ps != null)
				ps.close();
		}finally {
			connection.close(); //Rilascio la connessione dal pool
		}
	}
	
	return (result != 0);  //vedere errori SQL
}


@Override
public synchronized void doUpdate(UtenteBean utente) throws SQLException{
	Connection connection = null;
 	PreparedStatement ps = null;
	
	
	String updateSQL = "UPDATE "+ UtenteModelDS.TABLE_NAME +"SET password= ?, SET nome= ?, SET cognome= ?, SET admin= ? WHERE email=? "; 
	System.out.println(updateSQL);
	try {
		
		connection = ds.getConnection(); 
		ps = connection.prepareStatement(updateSQL); //Passiamo la query SQL
		ps.setString(1, utente.getPassword());
		ps.setString(2, utente.getFirstname());
		ps.setString(3, utente.getLastname());
		ps.setBoolean(4, utente.isAdmin());
		ps.setString(5, utente.getEmail());
		ps.executeUpdate();
		System.out.print(ps);
	
	}finally {
		try {
			if(ps!=null)
				ps.close();
		}finally {
			connection.close();//Rilascio la connessione al Database
		}
	}
}




}