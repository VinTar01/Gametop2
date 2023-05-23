package dao;

import java.sql.SQLException;
import java.util.Collection;


public interface GenericModel<T> {
	

	
	public void doSave(T param) throws SQLException; 
	//dato un T lo salvo nel db
	
	
	public T doRetrieveByKey(String param) throws SQLException; 
	//restituisce un T cercandolo per un parametro
	
	public Collection<T> doRetrieveAll() throws SQLException; 
	//restituisce una collezione di T 
	
	public boolean doDelete(String param) throws SQLException; //Elimina un T dal DB passando un parametro
	
	public void doUpdate(T param) throws SQLException; //Aggiorna un T dal DB passando un parametro
}
