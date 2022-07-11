package it.autoDAO;

public class Auto {

    /**ATTRIBUTI**/
    private String targa;
    private String marca;
    private String modello;

    /**COSTRUTTORE**/
    public Auto() {}

    public Auto(String targa, String marca, String modello) {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
    }

    /**GETTER & SETTER**/
    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

}
