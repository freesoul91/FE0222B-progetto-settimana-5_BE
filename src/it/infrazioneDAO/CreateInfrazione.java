package it.infrazioneDAO;

import poliziaCRUD.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateInfrazione {

    public void createTable(){
        Connection con = ConnectionFactory.getConnection();
        Statement statement;

        try {
            statement = con.createStatement();
            statement.executeUpdate("Create Table infrazione (id serial primary key, data varchar(20) not null, tipo varchar(70) not null, importo decimal(10,2) not null, targa_auto varchar(10), foreign key(targa_auto) references auto(targa))");
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
