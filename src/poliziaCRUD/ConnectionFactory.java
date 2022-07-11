package poliziaCRUD;

import it.autoDAO.AutoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:postgresql://localhost:5432/multedb";
    public static final String USER = "postgres";
    public static final String PASS = "epicode";



    public static Connection getConnection(){
        Connection con = null;

        Logger logger = LoggerFactory.getLogger(AutoDAO.class);

        try {
            con = DriverManager.getConnection(URL,USER,PASS);
            //logger.info("Connessione effettuata correttamente!");  //logger commentati per pulizia nella stampa della QUINTA funzionalità
        } catch (SQLException e) {
            //logger.info("Connessione non effettuata!");
            e.printStackTrace();
        }
        return con;
    }
}
