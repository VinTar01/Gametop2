package daomult;

import java.sql.SQLException;
import model.GenereBean;


public interface GenereModel {
	public void doSave(GenereBean genere) throws SQLException; // Salva un genere  nel DB
	public boolean doDelete(int id, String genereNome) throws SQLException; //Elimina un genere dal DB passando id e nome

}
