package it.module.entities;

public class Coffee {
    protected long id;
    protected String name;
    protected double price;

    public Coffee() {
    }

    public Coffee( String name, double price) {

        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return "Caffè: "+ name  +" id["+ id +"] " +price + "eur/pz";
    }
}
