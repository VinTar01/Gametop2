package daomult;

import java.sql.SQLException;
import java.util.Collection;

import model.OrdineBean;
import model.UtenteBean;

public interface OrdineModel {

	public void doSave(OrdineBean ordine) throws SQLException; // Salva un ordine  nel DB
	public boolean doDelete(int id) throws SQLException; //Elimina un ordine dal DB passando id e nome
	public Collection<OrdineBean> doRetrieveByKey(UtenteBean utente) throws SQLException; //Restituisce tutti gli ordini di un utente
}
