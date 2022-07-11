package it.infrazioneDAO;

import it.autoDAO.Auto;
import it.autoDAO.AutoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poliziaCRUD.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class InfrazioneDAO {

    Logger logger = LoggerFactory.getLogger(AutoDAO.class);

    public boolean inserisciInfrazione(Infrazione infrazione){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        int i = 0;

        try {
            String insert = "INSERT INTO infrazione (id,data,tipo,importo,targa_auto) VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(insert);


            ps.setInt(1, infrazione.getId());
            ps.setString(2, infrazione.getData());
            ps.setString(3, infrazione.getTipo());
            ps.setDouble(4, infrazione.getImporto());
            ps.setString(5, infrazione.getTarga());

            i = ps.executeUpdate();
            logger.info("Inserimento avvenuto correttamente!");

        } catch (SQLException e) {
            logger.info("Errore Inserimento!");
            e.printStackTrace();
        }

        try {con.close();}
        catch (Exception e) {}


        if(i>0){
            return true;
        }
        else return false;
    }

    public void stampaDatiInfrazioniAuto(){
        Connection con = ConnectionFactory.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Infrazione> listaInfrazioni = null;
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM infrazione");

            listaInfrazioni = new ArrayList<Infrazione>();

            while (rs.next()){
                Infrazione infrazione = new Infrazione();
                infrazione.setId(rs.getInt("id"));
                infrazione.setData(rs.getString("data"));
                infrazione.setTipo(rs.getString("tipo"));
                infrazione.setImporto(rs.getDouble("importo"));
                infrazione.setTarga(rs.getString("targa_auto"));

                listaInfrazioni.add(infrazione);
            }
        }
        catch (SQLException e){
            logger.info("Errore getAll");
            e.printStackTrace();
        }

        try {rs.close();}
        catch (Exception e) {}

        try {con.close();}
        catch (Exception e) {}

        for (Infrazione infrazione : listaInfrazioni){
            Auto auto;
            AutoDAO autoDAO = new AutoDAO();
            auto = autoDAO.cercaAuto(infrazione.getTarga());
            System.out.println("----------------------------------------------------"+"\nID: "  + infrazione.getId() +  "\nINFRAZIONE: " + infrazione.getTipo() + "\nAUTO: "  + auto.getMarca() + " " + auto.getModello() + " " + infrazione.getTarga() + "\nDATA: " + infrazione.getData() + "\nIMPORTO: "  + infrazione.getImporto() + " euro" + "\n----------------------------------------------------");
        }
    }

    public boolean eliminaInfrazione(int id){
        Connection con = ConnectionFactory.getConnection();
        Statement st = null;
        int i=0;
        try{
            String delete = "DELETE FROM infrazione WHERE id = " + id;
            st = con.createStatement();
            i = st.executeUpdate(delete);
            logger.info("Delete avvenuto!");
        }
        catch (SQLException e){
            logger.info("Errore delete!");
            e.printStackTrace();
        }

        try {con.close();}
        catch (Exception e) {}

        if(i>0){
            return true;
        }
        else return false;
    }
}
