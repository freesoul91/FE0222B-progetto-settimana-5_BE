package it.autoDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poliziaCRUD.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;


public class AutoDAO {

    Logger logger = LoggerFactory.getLogger(AutoDAO.class);

    public boolean inserisciAuto(Auto auto){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        int i = 0;

        try {
            String insert = "INSERT INTO auto (Targa,Marca,Modello) VALUES (?,?,?)";
            ps = con.prepareStatement(insert);


            ps.setString(1, auto.getTarga());
            ps.setString(2, auto.getMarca());
            ps.setString(3, auto.getModello());

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

    public ArrayList getAllAuto(){
        Connection con = ConnectionFactory.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Auto> listaStudenti = null;
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM auto");

            listaStudenti = new ArrayList<Auto>();

            while (rs.next()){
                Auto auto = new Auto();
                auto.setTarga(rs.getString("Targa"));
                auto.setMarca(rs.getString("Marca"));
                auto.setModello(rs.getString("Modello"));

                listaStudenti.add(auto);
            }

            logger.info("getAll Avvenuto!");
        }
        catch (SQLException e){
            logger.info("Errore getAll");
            e.printStackTrace();
        }

        try {rs.close();}
        catch (Exception e) {}

        try {con.close();}
        catch (Exception e) {}

        return listaStudenti;
    }

    public Auto cercaAuto(String targa){
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Auto auto = new Auto();
        try{
            PreparedStatement st = con.prepareStatement("SELECT * FROM auto WHERE targa like ?");

            st.setString(1,targa);

            rs = st.executeQuery();

            while (rs.next()) {
                auto.setTarga(rs.getString("Targa"));
                auto.setMarca(rs.getString("Marca"));
                auto.setModello(rs.getString("Modello"));
            }
        }
        catch (SQLException e){
            logger.info("Errore nella ricerca!");
            e.printStackTrace();
        }



        try {rs.close();}
        catch (Exception e) {}

        try {con.close();}
        catch (Exception e) {}

        return auto;
    }
}
