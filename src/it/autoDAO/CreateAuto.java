package it.autoDAO;

import poliziaCRUD.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAuto {

    public void createTable(){
        Connection con = ConnectionFactory.getConnection();
        Statement statement;

        try {
            statement = con.createStatement();
            statement.executeUpdate("Create Table auto (Targa varchar(10) " + "primary key, Marca varchar(20) not null, Modello varchar(30)) not null");
            System.out.println("Creazione tabella effettuata!");
        }
        catch (SQLException e){
            System.out.println("Errore creazione tabella!");
            e.printStackTrace();
        }

        try {con.close();}
        catch (Exception e) {}
    }
}
