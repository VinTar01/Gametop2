package daomult;

import java.sql.SQLException;
import java.util.Collection;

import model.*;

public interface ProdottoModel {

	public void doSave(ProdottoBean prodotto) throws SQLException; // Salva un prodotto  nel DB
	
	public boolean doDelete(int id, String codProdotto) throws SQLException; //Elimina un prodotto dal DB passando id e codice
	
	public ProdottoBean doRetrieveByKey(int id,String codProdotto) throws SQLException; //Mi restituisce un determinato prodotto nel DB passando id e codice
	
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException; //Restituisce tutti i prodotti del DB
	
	public void updateGenere(GenereBean nuovoGenere); 
	
	public void updateAzienda(AziendaBean nuovaAzienda); 
	
	public void addPhoto(String codProdotto ,int idProdotto,String nomeFoto) throws SQLException; //aggiungere una foto del prodotto in base al codice e id passando il path
}

//AGGIUNGERE NEL DB PATH FOTO DEL PRODOTTO