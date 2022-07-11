package poliziaCRUD;

import it.autoDAO.Auto;
import it.autoDAO.AutoDAO;
import it.autoDAO.CreateAuto;
import it.infrazioneDAO.CreateInfrazione;
import it.infrazioneDAO.Infrazione;
import it.infrazioneDAO.InfrazioneDAO;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /**CREAZIONE TABELLE**/
        //AUTO
        //CreateAuto createAuto = new CreateAuto();
        //createAuto.createTable();
        //INFRAZIONI
        //CreateInfrazione createInfrazione = new CreateInfrazione();
        //createInfrazione.createTable();


        PoliziaMunicipale();
    }

    public static void PoliziaMunicipale(){
        System.out.println("\nBenvenuto nell'applicazione della POLIZIA MUNICIPALE!\nCosa vuoi fare?\n--------------------------\n1) Inserisci dati Auto\n2) Inserisci dati Infrazione\n3) Visualizza tutte le Auto\n4) Cerca Auto da Targa\n5) Visualizza dati Infrazioni e Auto da Targa\n6) Elimina Infrazione\n--------------------------");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        switch (x){
            case 1:
                System.out.println("Inserisci Targa: ");
                scanner.nextLine();
                String targa = scanner.nextLine();
                System.out.println("Inserisci Marca: ");
                String marca = scanner.nextLine();
                System.out.println("Inserisci Modello: ");
                String modello = scanner.nextLine();
                Auto auto = new Auto(targa,marca,modello);
                AutoDAO autoDAO = new AutoDAO();
                autoDAO.inserisciAuto(auto);
                System.out.println("Auto aggiunta correttamente!");
                break;

            case 2:
                System.out.println("Inserisci ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Inserisci Data: ");
                String data = scanner.nextLine();
                System.out.println("Inserisci Tipo: ");
                String tipo = scanner.nextLine();
                System.out.println("Inserisci Importo: ");
                Double importo = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Inserisci Targa: ");
                String targa2 = scanner.nextLine();
                Infrazione infrazione = new Infrazione(id,data,tipo,importo,targa2);
                InfrazioneDAO infrazioneDAO = new InfrazioneDAO();
                infrazioneDAO.inserisciInfrazione(infrazione);
                System.out.println("Infrazione aggiunta correttamente!");
                break;

            case 3:
                AutoDAO autoDAO1 = new AutoDAO();
                autoDAO1.getAllAuto();
                break;

            case 4:
                System.out.println("Inserisci la targa dell'auto da cercare: ");
                scanner.nextLine();
                String targa3 = scanner.nextLine();
                AutoDAO autoDAO2 = new AutoDAO();
                Auto autoRicerca = autoDAO2.cercaAuto(targa3);
                System.out.println("Info auto cercata:\nMarca: " + autoRicerca.getMarca() + "\nModello: " + autoRicerca.getModello());
                break;

            case 5:
                InfrazioneDAO infrazioneDAO2 = new InfrazioneDAO();
                infrazioneDAO2.stampaDatiInfrazioniAuto();
                break;

            case 6:
                System.out.println("Inserire l'ID dell'infrazione da eliminare: ");
                int infrazioneDEL = scanner.nextInt();
                InfrazioneDAO infrazioneDAO1 = new InfrazioneDAO();
                infrazioneDAO1.eliminaInfrazione(infrazioneDEL);
                break;

            default:
                System.out.println("Operazione non valida!");
                break;
        }
    }
}
