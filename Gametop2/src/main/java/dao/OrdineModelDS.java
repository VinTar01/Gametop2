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

import model.OrdineBean;

public class OrdineModelDS implements GenericModel<OrdineBean> {
	private static final String TABLE_NAME = "Ordine"; //Tabella del DB dove andiamo ad operare
	
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
	
    
	  
    public OrdineModelDS() {
        super();
        // TODO Auto-generated constructor stub
    }

@Override   
public synchronized void doSave(OrdineBean ordine) throws SQLException{
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		//prepara la query per effettuare insert dell'utente nel DB
		String insertSQL = "INSERT INTO " + OrdineModelDS.TABLE_NAME + " (idOrdine,totale) "
				+ " VALUES(?,?,?,?,?)";
		try {
			connection = ds.getConnection(); //connessione al DB tramite il ConnectionPool
			ps = connection.prepareStatement(insertSQL); //Passiamo la query SQL
			ps.setInt(1,ordine.getId());
			ps.setDouble(2,ordine.getTotale());  //DIOOOOOOO NUMERO
			
			
			
			
			ps.executeUpdate(); //esegue la Query di aggiornamento
			
			connection.commit();
				
		}finally {
		
			try {
				if(ps != null)
					ps.close();
			}finally {
				connection.close(); //Rilascio la connessione dal pool
			}
		}
	}

@Override
public synchronized OrdineBean doRetrieveByKey(String id) throws SQLException{
	Connection connection = null;
	PreparedStatement ps = null;
	
	int codice = Integer.parseInt(id);
	OrdineBean ordine = new OrdineBean(); //Utente di appoggio
	
	String selectSQL = "SELECT * FROM " + OrdineModelDS.TABLE_NAME + " WHERE idOrdine = ?";  
	
	try {
		connection = ds.getConnection(); 
		ps = connection.prepareStatement(selectSQL); //Passiamo la query SQL
		
		ps.setInt(1, codice);
		
		try {
		ResultSet rs = ps.executeQuery();  //ottiene il risultato della query
		
		
		//scorriamo il risultato della query
		while(rs.next()) {
			
			//prendiamo ogni campo dell'UserBean che restituisce la query
			ordine.setId(rs.getInt("idOrdine"));
			ordine.setTotale(rs.getDouble("totale"));
			
		
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
	return ordine;  //restituisce l'utente con gli attributi settati
}


@Override
public synchronized Collection<OrdineBean> doRetrieveAll() throws SQLException
{
	
	Connection connection = null;
	PreparedStatement ps = null;
	Collection<OrdineBean> ordini = new LinkedList<OrdineBean>(); //Collezione di OrdineBean
	
	String selectSQL = "SELECT * FROM " + OrdineModelDS.TABLE_NAME ;
	
	try {
		connection = ds.getConnection(); 

		ps = connection.prepareStatement(selectSQL); //Passiamo la query SQL
		
		
		ResultSet rs = ps.executeQuery();
		
	
		
		while(rs.next()) {
			OrdineBean ordine = new OrdineBean();
			
			ordine.setId(rs.getInt("idOrdine"));
			ordine.setTotale(rs.getDouble("totale"));
			
		
			ordini.add(ordine);
			
		}
		
	}finally {
		try {
			if(ps != null)
				ps.close();
		}finally {
			connection.close(); //Rilascio la connessione dal pool
		}
	}
	return ordini;
}



@Override
public synchronized boolean doDelete(String id) throws SQLException{
	Connection connection = null;
	PreparedStatement ps = null;
	
	int result = 0; //Flag di controllo 
	int codice = Integer.parseInt(id);
	
	String deleteSQL = "DELETE FROM " + OrdineModelDS.TABLE_NAME + " WHERE idOrdine = ?";
	
	try {
		connection = ds.getConnection(); 
		ps = connection.prepareStatement(deleteSQL); //Passiamo la query SQL
		ps.setInt(1, codice);
		
		result = ps.executeUpdate();
	}finally {
		
		try {
			if(ps != null)
				ps.close();
		}finally {
			connection.close();; //Rilascio la connessione dal pool
		}
	}
	
	return (result != 0);  //vedere errori SQL
}


@Override
public synchronized void doUpdate(OrdineBean prodotto) throws SQLException{
	Connection connection = null;
 	PreparedStatement ps = null;
	
	
	String updateSQL = "UPDATE "+ OrdineModelDS.TABLE_NAME +"SET totale= ?"; 
	System.out.println(updateSQL);
	try {
		
		connection = ds.getConnection(); 
		ps = connection.prepareStatement(updateSQL); //Passiamo la query SQL
		ps.setDouble(1, prodotto.getTotale());

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