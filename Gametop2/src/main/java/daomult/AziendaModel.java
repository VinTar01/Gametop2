package daomult;

import java.sql.SQLException;
import model.AziendaBean;


public interface AziendaModel {

	public void doSave(AziendaBean genere) throws SQLException; // Salva un'azienda  nel DB
	public boolean doDelete(int id, String aziendaNome) throws SQLException; //Elimina un'azienda dal DB passando id e nome
}
