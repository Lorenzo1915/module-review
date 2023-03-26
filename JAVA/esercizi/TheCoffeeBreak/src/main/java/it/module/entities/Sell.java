package it.module.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Sell {
    protected long id;
    protected String  dateSell;
    protected int quantity;
    protected long idCoffee;


    public Sell() {
    }

    public Sell( String dateSell, int quantity, long idCoffee) {
        this.dateSell = dateSell;
        this.quantity = quantity;
        this.idCoffee = idCoffee;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateSell() {
        return dateSell;
    }

    public void setDateSell(String dateSell) {
        this.dateSell = dateSell;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getIdCoffee() {
        return idCoffee;
    }

    public void setIdCoffee(long idCoffee) {
        this.idCoffee = idCoffee;
    }

    @Override
    public String toString(){
        return " id["+ id +"]  " +dateSell + "  Quantita:  " + quantity;
    }
}
