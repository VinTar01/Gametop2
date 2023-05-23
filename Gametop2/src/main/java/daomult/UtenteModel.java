package daomult;
import model.UtenteBean;
import java.sql.SQLException;
import java.util.Collection;



public interface UtenteModel {

	public interface UserModel {

		
	
		public void doSave(UtenteBean utente) throws SQLException; 
		//dato un UserBean lo salvo nel db
		
		
		public UtenteBean doRetrieveByKey(String username,String password) throws SQLException; 
		//restituisce un utentebean cercandolo per username e password
		
		public UtenteBean doRetrieveByAdmin(String username) throws SQLException; 
		//restituisce un Userbean cercandolo per username
		
		public Collection<UtenteBean> RetrieveAll() throws SQLException;
		//restituisce tutti gli user


		public int checkEmail(String email) throws SQLException; 
		//seleziona un utente dall'email e controlla se esiste nel DB
	}
}
