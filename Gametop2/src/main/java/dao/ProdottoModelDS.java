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

import model.ProdottoBean;

public class ProdottoModelDS implements GenericModel<ProdottoBean> {
	private static final String TABLE_NAME = "Prodotto"; //Tabella del DB dove andiamo ad operare
	
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
	
    
	  
    public ProdottoModelDS() {
        super();
        // TODO Auto-generated constructor stub
    }

@Override   
public synchronized void doSave(ProdottoBean prodotto) throws SQLException{
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		//prepara la query per effettuare insert dell'utente nel DB
		String insertSQL = "INSERT INTO " + ProdottoModelDS.TABLE_NAME + " (idProdotto,prezzo,nome) "
				+ " VALUES(?,?,?,?,?)";
		try {
			connection = ds.getConnection(); //connessione al DB tramite il ConnectionPool
			ps = connection.prepareStatement(insertSQL); //Passiamo la query SQL
			ps.setInt(1,prodotto.getId());
			ps.setDouble(2,prodotto.getPrezzo());
			ps.setString(3,prodotto.getTitolo());
			////AGGIUNGERE PATH FOTO
			
			
			
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
public synchronized ProdottoBean doRetrieveByKey(String id) throws SQLException{
	Connection connection = null;
	PreparedStatement ps = null;
	
	int codice = Integer.parseInt(id);
	ProdottoBean prodotto = new ProdottoBean(); //Utente di appoggio
	
	String selectSQL = "SELECT * FROM " + ProdottoModelDS.TABLE_NAME + " WHERE idProdotto = ?";  
	
	try {
		connection = ds.getConnection(); 
		ps = connection.prepareStatement(selectSQL); //Passiamo la query SQL
		
		ps.setInt(1, codice);
		
		try {
		ResultSet rs = ps.executeQuery();  //ottiene il risultato della query
		
		
		//scorriamo il risultato della query
		while(rs.next()) {
			
			//prendiamo ogni campo dell'UserBean che restituisce la query
			prodotto.setId(rs.getInt("idProdotto"));
			prodotto.setPrezzo(rs.getDouble("prezzo"));
			prodotto.setTitolo(rs.getString("nomeProdotto"));
			//AGGIUNGERE PATH FOTO 
		
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
	return prodotto;  //restituisce l'utente con gli attributi settati
}


@Override
public synchronized Collection<ProdottoBean> doRetrieveAll() throws SQLException
{
	
	Connection connection = null;
	PreparedStatement ps = null;
	Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>(); //Collezione di UserBean
	
	String selectSQL = "SELECT * FROM " + ProdottoModelDS.TABLE_NAME ;
	
	try {
		connection = ds.getConnection(); 

		ps = connection.prepareStatement(selectSQL); //Passiamo la query SQL
		
		
		ResultSet rs = ps.executeQuery();
		
	
		
		while(rs.next()) {
			ProdottoBean prodotto = new ProdottoBean();
			
			prodotto.setId(rs.getInt("idProdotto"));
			prodotto.setPrezzo(rs.getDouble("prezzo"));
			prodotto.setTitolo(rs.getString("nomeProdotto"));
			//AGGIUNGERE PATH FOTO
			
		
			prodotti.add(prodotto);
			
		}
		
	}finally {
		try {
			if(ps != null)
				ps.close();
		}finally {
			connection.close(); //Rilascio la connessione dal pool
		}
	}
	return prodotti;
}



@Override
public synchronized boolean doDelete(String id) throws SQLException{
	Connection connection = null;
	PreparedStatement ps = null;
	
	int result = 0; //Flag di controllo 
	int codice = Integer.parseInt(id);
	
	String deleteSQL = "DELETE FROM " + ProdottoModelDS.TABLE_NAME + " WHERE idProdotto = ?";
	
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
public synchronized void doUpdate(ProdottoBean prodotto) throws SQLException{
	Connection connection = null;
 	PreparedStatement ps = null;
	
	
	String updateSQL = "UPDATE "+ ProdottoModelDS.TABLE_NAME +"SET prezzo= ?, SET nomeProdotto= ?"; 
	System.out.println(updateSQL);
	try {
		
		connection = ds.getConnection(); 
		ps = connection.prepareStatement(updateSQL); //Passiamo la query SQL
		ps.setDouble(1, prodotto.getPrezzo());
		ps.setString(2, prodotto.getTitolo());
		//AGGIUNGERE PATH FOTO
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